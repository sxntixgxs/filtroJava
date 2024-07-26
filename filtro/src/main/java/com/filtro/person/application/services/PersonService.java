package com.filtro.person.application.services;

import java.util.List;
import java.util.Optional;

import com.filtro.person.domain.models.Person;
import com.filtro.person.domain.ports.in.PersonOperations;
import com.filtro.person.domain.ports.out.PersonRepository;

public class PersonService implements PersonOperations {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Optional<Person> addPerson(Person person) {
        return personRepository.savePerson(person);
    }

    @Override
    public List<String> checkPersonBySkill(int idSkill) {
        return personRepository.findPersonBySkill(idSkill);
    }

    @Override
    public Optional<Person> updatePerson(Person person) {
        return personRepository.updatePerson(person);
    }

    @Override
    public boolean deletePerson(int id) {
        return personRepository.deletePerson(id);
    }

    @Override
    public List<String> showCities() {
        return personRepository.getCities();
    }

    @Override
    public List<String> showGenders() {
        return personRepository.getGender();
    }

    @Override
    public Optional<Person> checkPersonById(int id) {
        return personRepository.getPersonById(id);
    }

    @Override
    public Optional<Person> updatePersonSkill(Person person, int idskill) {
        return personRepository.updatePersonSkill(person, idskill);
    }
}
