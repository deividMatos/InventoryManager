package com.project.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Person;
import com.project.person.repository.PersonRepository;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person create(Person person){
        System.out.println(person.toString());
        return repository.save(person);
    }

    public Person getById(Long idPerson){
        return repository.findById(idPerson).get();
    }
    public Person update(Person person){
        if(repository.existsById(person.getId())){
            return repository.save(person);
        }else{
            throw new NotFoundException("Person not found");
        }
    }
    public void delete (Long idPerson){
        repository.deleteById(idPerson);
    }

    public List<Person> getAllPerson() {
        return repository.findAll();
    }
}