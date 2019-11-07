package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.Persona;

import java.util.List;


public interface PersonaService {

    Persona getRandom();

    List<Persona> getRandom(Integer numero);

    List<Persona> getPersone();

    Persona findPersona(Integer id);

    boolean deletePersonaById(Integer id);

    Persona addPersona(Persona persona);

    Persona update(Persona persona);
}
