package it.nextre.academy.nxtlearn.controller;

import it.nextre.academy.nxtlearn.exception.PersonaNotFoundException;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.FileHandler;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    FileHandler fh;

    @Autowired
    PersonaService personaService;

    @GetMapping("/new")
    public String getNewPersona(Model model) {
        logger.info("LOG: getNewPersona()");
        model.addAttribute("persona", personaService.getRandom());
        return "persona.html";
    }

    @GetMapping("/new/{qta}")
    public String getNewPersone(Model model, @PathVariable("qta") Integer qta) {
        logger.info("LOG: getNewPersona()");
        model.addAttribute("persone", personaService.getRandom(qta));
        return "persone.html";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable("id") Integer id) {
        logger.info("Log: getId()" + id);
        Persona tmp = personaService.findPersona(id);
        if (tmp != null) {
            model.addAttribute("persona", tmp);
        } else
            //model.addAttribute("persona", new Persona());
            throw new PersonaNotFoundException();
        return "persona.html";
    }

    @GetMapping
    public String getPersone(Model model) {
        logger.info("LOG getPersone()");
        model.addAttribute("persone", personaService.getPersone());
        return "persone.html";
    }

}//end class
