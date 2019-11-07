package it.nextre.academy.nxtlearn.controller;


import it.nextre.academy.nxtlearn.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(("it.nextre.academy.nxtlearn.controller")) //non è in ascolto dall'esterno ma dall'interno di spring
public class ExceptionHandlerController {

    //@ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(NotFoundException ex) {
        return new MyErrorPage(HttpStatus.NOT_FOUND, ex.getMessage()).getPage();
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound2(NotFoundException ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/errors/404.html");
        mv.addObject("message", ex.getMessage());
        mv.setStatus(HttpStatus.NOT_FOUND);
        return mv;
    }


}//end class

class MyErrorPage {
    private HttpStatus statusCode;
    private Object body;

    MyErrorPage(HttpStatus statusCode, Object body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public ResponseEntity getPage() {
        return new ResponseEntity(body, statusCode);
    }
}