package com.project.movement.controller;

import com.project.movement.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.model.Movement;

@RestController
public class MovementController {
    @Autowired
    private MovementService movementService;

    @GetMapping("/movement/{idMovement}")
    public Movement getById(@PathVariable("idMovement") Long idMovement) {
        Movement response = movementService.getById(idMovement);
        return response;
    }

    @PostMapping("/movement")
    public Movement insert(@RequestBody Movement movement) {
        return movementService.insert(movement);
    }

    @PutMapping("/movement")
    public Movement update(@RequestBody Movement movement) {
        return movementService.update(movement);
    }

    @DeleteMapping("/movement/{idMovement}")
    public void delete(@PathVariable("idMovement") Long idMovement) {
        movementService.deleteById(idMovement);
    }
}
