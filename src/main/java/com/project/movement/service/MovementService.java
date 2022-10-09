package com.project.movement.service;

import com.project.model.Movement;
import com.project.movement.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovementService {
    @Autowired
    private final MovementRepository repository;

    public MovementService(MovementRepository repository) { this.repository = repository; }

    public Movement getById(Long id){
        return repository.findById(id).get();
    }

    public Movement insert(Movement movement) {
        return repository.save(movement);
    }

    public Movement update(Movement movement) {
        return repository.save(movement);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
