package com.project.movementType.Controller;

import com.project.model.MovementType;
import com.project.movementType.Service.MovementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovementTypeController {
    @Autowired
    private MovementTypeService movementTypeService;

    @GetMapping("movement/type/{id}")
    public MovementType getById(@PathVariable("id") Long id) {
        return movementTypeService.getById(id);
    }

    @PostMapping("movement/type")
    public MovementType insert(@RequestBody MovementType movementType) {
        return movementTypeService.insert(movementType);
    }

    @PutMapping("movement/type")
    public MovementType update(@RequestBody MovementType movementType) {
        return movementTypeService.update(movementType);
    }

    @DeleteMapping("/movement/type/{id}")
    public void delete(@PathVariable("id") Long id) {
        movementTypeService.delete(id);
    }
}
