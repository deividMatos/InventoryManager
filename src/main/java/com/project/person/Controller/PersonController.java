package com.project.person.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.model.Person;
import com.project.person.service.PersonService;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/person/{idPerson}")
    public Person getById(@PathVariable("idPerson") long idPerson) {
        Person response = personService.getById(idPerson);
        return response;
    }

    @PostMapping("/person")
    public Person create(@RequestBody final Person person) {
        return personService.create(person);
    }
    @PutMapping("/person")
    public Person update(@RequestBody Person person) {
        Person response = personService.update(person);
        return response;
    }
    @DeleteMapping("/person/{idPerson}")
        public void delete(@PathVariable("idPerson") long idPerson){
       personService.delete(idPerson);
    }

// TODO: adicionar get list
}