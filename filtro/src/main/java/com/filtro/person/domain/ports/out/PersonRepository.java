package com.filtro.person.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.filtro.person.domain.models.Person;

public interface PersonRepository {
    Optional<Person> savePerson(Person person);
    List<String> findPersonBySkill(int idSkill);
    Optional<Person> updatePerson(Person person);
    boolean deletePerson(int id);
    List<String> getCities();
    List<String> getGender();
}
