package com.project.movementType.Controller;

import com.project.model.MovementType;
import com.project.movementType.Service.MovementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.InvalidPropertiesFormatException;
import java.util.NoSuchElementException;

@RestController
public class MovementTypeController {
    @Autowired
    private MovementTypeService movementTypeService;

    @GetMapping("movement/type/{id}")
    public MovementType getById(@PathVariable("id") Long id) {

        try {
            return movementTypeService.getById(id);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MovementType nao encontrado", exception);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }
    }

    @PostMapping("movement/type")
    public MovementType insert(@RequestBody MovementType movementType) {

        try {
            return movementTypeService.insert(movementType);
        } catch (Exception exception) { // pensei em usar o (InvalidPropertiesFormatException exeption) pq na descrição do erro fala que as entradas são invalidas, estaria correto colocar? Daí eu teria que alterar no Movement e padronizar
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Entrada inválida", exception);
        }
    }

    @PutMapping("movement/type")
    public MovementType update(@RequestBody MovementType movementType) {

        try {
            return movementTypeService.update(movementType);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/movement/type/{id}")
    public void delete(@PathVariable("id") Long id) {
        try {
            movementTypeService.delete(id);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Movement Type nao encontrado", exception);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }
    }
}
