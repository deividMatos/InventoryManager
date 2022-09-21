package com.project.person.Controller;

import com.project.model.Person;
import com.project.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/person/{idPerson}")
    public Person getById(@PathVariable("idPerson") Long idPerson) {
        return personService.getById(idPerson);
    }

    @PostMapping("/person/insert")
    @ResponseBody
    public Person getPerson(@RequestBody final Person person) {
        return personService.insert(person);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Person> updateCustomer(@PathVariable String id, @RequestBody Person patch) {
        return null;
    }
}