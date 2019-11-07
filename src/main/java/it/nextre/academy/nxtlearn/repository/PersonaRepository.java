package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Persona;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonaRepository {

    private List<Persona> db = new ArrayList<>();

    {
        db.add(new Persona(1, "Mario", "Red"));
        db.add(new Persona(2, "Michele", "Bossi"));
        db.add(new Persona(3, "Michela", "Manelli"));
        db.add(new Persona(4, "Gino", "Brunetta"));
        db.add(new Persona(5, "Maria", "Vongola"));
        db.add(new Persona(6, "Gina", "Branzino"));
    }

    public Persona save(Persona persona) {
        if (persona.getId() != null && persona.getId() > 0) {
            if (db.stream()
                    .map(per -> per.getId())
                    .collect(Collectors.toList())
                    .contains(persona.getId())) {
                //posso aggiornare
                Persona tmp = db.stream()
                        .filter(persona1 -> persona1.getId().equals(persona.getId()))
                        .findFirst()
                        .get();
                tmp.setNome(persona.getNome());
                tmp.setCognome(persona.getCognome());
                return tmp.clone();

            } else {
                return null;
            }

        } else {
            //devo inserire
            //calcolare il nuovo id
            persona.setId(1 + db.stream()
                    .map(persona1 -> persona1.getId())
                    .max(Integer::compareTo)
                    .orElse(0));
            db.add(persona);
            return persona.clone();
        }
    }

    public List<Persona> getAll() {
        List<Persona> tmp = new ArrayList<>();
        for (Persona p : db) {
            tmp.add(p.clone());
        }
        return tmp;
    }

    public Persona findId(Integer id) {
        Persona tmp = db.stream().filter(persona1 -> persona1.getId().equals(id)).findFirst().orElse(null);
        System.out.println("Ho trovato una persona");
        return (tmp != null) ? tmp.clone() : null;
    }

    public boolean removeId(Integer id) {

        Persona tmp = findId(id);
        if (tmp != null) {
            return db.remove(tmp);
        }
        return false;
    }

    public Persona addPersona(Persona p) {
        p.setId(1 + db.stream()
                .map(per -> per.getId())
                .max(Integer::compareTo)
                .orElse(0));
        db.add(p);
        return p.clone();
    }

}//end class
