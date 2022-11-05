package com.project.person.Controller;

import com.project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.project.model.Person;
import com.project.person.service.PersonService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping ("/person/list")
    public List <Person> getAllPerson() {
        return personService.getAllPerson();
    }
    @GetMapping("/person/{idPerson}")
    public Person getById(@PathVariable("idPerson") long idPerson) {
        try {
            return personService.getById(idPerson);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao foi encontrado", exception);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }
    }

    @PostMapping("/person")
    public Person create(@RequestBody final Person person) {
        try {
            return personService.create(person);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }
    }
    @PutMapping("/person")
    public Person update(@RequestBody Person person) {
        try {
            return personService.update(person);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }
    }
    @DeleteMapping("/person/{idPerson}")
        public void delete(@PathVariable("idPerson") long idPerson){
        try {
            personService.delete(idPerson);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado", exception);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }
    }


}