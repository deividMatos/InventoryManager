package com.project.movement.controller;

import com.project.movement.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.project.model.Movement;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
public class MovementController {
    @Autowired
    private MovementService movementService;

    @GetMapping("/movement/{idMovement}")
    public Movement getById(@PathVariable("idMovement") Long idMovement) {
        try {
            return movementService.getById(idMovement);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimento nao encontrado", exception);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }
    }

    @PostMapping("/movement")
    public Movement insert(@RequestBody Movement movement) {
        try {
            return movementService.insert(movement);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Modelo nao aceitavel", exception);
        }
    }

    @PutMapping("/movement")
    public Movement update(@RequestBody Movement movement) {
        try {
            return movementService.update(movement);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Modelo nao aceitavel", exception);
        }
    }

    @DeleteMapping("/movement/{idMovement}")
    public void delete(@PathVariable("idMovement") Long idMovement) {

        try {
            movementService.deleteById(idMovement);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id nao encontrado", exception);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }
    }
}
