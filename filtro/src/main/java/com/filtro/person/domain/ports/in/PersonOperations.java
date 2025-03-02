package com.filtro.person.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.filtro.person.domain.models.Person;

public interface PersonOperations {
    Optional<Person> addPerson(Person person);
    List<String> checkPersonBySkill(int idSkill);
    Optional<Person> updatePerson(Person person);
    Optional<Person> checkPersonById(int id);
    boolean deletePerson(int id);
    List<String> showCities();
    List<String> showGenders();
    Optional<Person> updatePersonSkill(Person person, int idskill);
}
