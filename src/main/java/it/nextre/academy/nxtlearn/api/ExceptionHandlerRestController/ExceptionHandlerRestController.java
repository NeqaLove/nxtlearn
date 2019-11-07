package it.nextre.academy.nxtlearn.api.ExceptionHandlerRestController;

import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice("it.nextre.academy.nxtlearn.api") //non è in ascolto dall'esterno ma dall'interno di spring
public class ExceptionHandlerRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(NotFoundException ex) {
        logger.debug("RestControllerAdvice notFound");
        return new MyError(HttpStatus.NOT_FOUND, ex.getMessage())
                .addHeader("ciccio", "pippo")
                .addData("secret-code", 123456789)
                .getPage();
    }

    /*
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound2(NotFoundException ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/errors/404.html");
        mv.addObject("message", ex.getMessage());
        mv.setStatus(HttpStatus.NOT_FOUND);
        return mv;
    }   */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequest(BadRequestException ex) {
        logger.debug("reestcontrolleradvice BadRequestException");
        return new MyError(HttpStatus.BAD_REQUEST, ex.getMessage()).getPage();

    }

}//end class

class MyError {
    private MultiValueMap<String, String> customHeaders = new HttpHeaders();
    private HttpStatus statusCode;
    private Map<String, Object> data = new HashMap<>();

    public MyError(HttpStatus statusCode, Object body) {
        this.statusCode = statusCode;
        this.data.put("data", body);
        this.data.put("status", statusCode.value());
        this.data.put("error", statusCode.toString().substring(4).trim());
        this.data.put("timestamp", Instant.now().toEpochMilli());
    }

    public MyError addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public MyError addHeader(String key, String value) {
        this.customHeaders.put(key, Arrays.asList(value));
        return this;
    }

    public MyError addHeaders(String key, List<String> value) {
        this.customHeaders.put(key, value);
        return this;
    }

    public ResponseEntity getPage() {
        return new ResponseEntity(data, customHeaders, statusCode);
    }


}