package com.project.person.service;

import com.project.model.Person;
import com.project.person.repository.PersonRepository;
import org.springframework.stereotype.Service;
@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person getById(Long idPerson){
        return repository.findById(idPerson).get();
    }

    public Person insert(Person person){
        return repository.save (person);
    }

}