package it.nextre.academy.nxtlearn.api.ExceptionHandlerRestController;

import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.exception.PersonaNotFoundException;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personarest")
public class PersonaRestController {


    @Autowired
    PersonaService personaService;
    //todo..
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public List<Persona> getAll() {
        logger.debug("Log: getAll()");
        return personaService.getPersone();
    }

    @GetMapping("/{id}")
    public Persona getOne(@PathVariable("id") Integer id) {
        logger.debug("Log: getOne() with id: " + id);
        Persona tmp = personaService.findPersona(id);
        if (tmp != null) {
            return tmp;
        } else
            throw new PersonaNotFoundException();
    }

    @DeleteMapping("/deletebyid/{id}")
    public Persona deletePersona(Model model, @PathVariable("id") Integer id) {
        logger.info("Log: deleteById()" + id);
        Persona tmp = personaService.findPersona(id);
        if (tmp != null) {
            model.addAttribute("persona", tmp);
            personaService.deletePersonaById(id);
        } else
            //model.addAttribute("persona", new Persona());
            throw new PersonaNotFoundException();
        return tmp;
    }

    @PostMapping("/create")
    public Persona addPersona(@RequestBody Persona persona) {
        logger.info("Log: POST addPersona()" + persona);
        if (persona != null) {
            persona = personaService.addPersona(persona);
            if (persona == null) {
                //todo..
                throw new BadRequestException();
            }
        }
        return persona;
    }

    @GetMapping("/update/{/id}")
    public Persona editPersona(@RequestBody Persona persona, @PathVariable("id") Integer id) {
        if (persona != null && persona.getId().equals(id)) {
            Persona tmp = personaService.update(persona);
            if (tmp != null) {
                return tmp;
            } else {
                throw new BadRequestException();
            }

        }
        logger.warn("INCOMPATIBILE");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    }


    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") Integer id) {
        logger.debug("DELETE persona.deleteById() with id: " + id);
        Map<String, Object> risp = new HashMap<>();
        risp.put("result", personaService.deletePersonaById(id));
        return personaService.deletePersonaById(id);
    }

}//end class
