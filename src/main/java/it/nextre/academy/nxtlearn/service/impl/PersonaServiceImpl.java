package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.myutils.DummyData;
import it.nextre.academy.nxtlearn.repository.PersonaRepository;
import it.nextre.academy.nxtlearn.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public Persona getRandom() {
        Persona p = new Persona();
        p.setNome(DummyData.getName());
        p.setCognome(DummyData.getSurname());
        p = personaRepository.save(p);
        return p;
    }

    @Override
    public List<Persona> getRandom(Integer numero) {
        List<Persona> tmp = new ArrayList<>();
        for (int i = 0; i < numero; i++) {
            tmp.add(getRandom());
        }
        return tmp;
    }

    @Override
    public List<Persona> getPersone() {
        return personaRepository.getAll();
    }

    @Override
    public Persona findPersona(Integer id) {
        if (id != null && id > 0) {
            return personaRepository.findId(id);
        }
        return null;
    }

    public boolean deletePersonaById(Integer id) {
        if (id != null && id > 0) {
            return personaRepository.removeId(id);
        }
        return false;
    }

    @Override
    public Persona addPersona(Persona persona) {
        if (persona != null && persona.getId() == null) {
            persona = personaRepository.save(persona);
            return persona;
        }
        return null;
    }

    @Override
    public Persona update(Persona persona) {
        if (persona != null && findPersona(persona.getId()) != null) {
            return personaRepository.save(persona);
        }
        return null;
    }

}//end class
