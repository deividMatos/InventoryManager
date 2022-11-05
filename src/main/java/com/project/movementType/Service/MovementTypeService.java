package com.project.movementType.Service;

import com.project.model.MovementType;
import com.project.movementType.Repository.MovementTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class MovementTypeService {
    @Autowired
    private final MovementTypeRepository repository;

    public MovementTypeService(MovementTypeRepository repository) {
        this.repository = repository;
    }

    public MovementType getById(Long id) {
        return repository.findById(id).get();
    }

    public MovementType insert(MovementType movementType) {
        return repository.save(movementType);
    }

    public MovementType update(MovementType movementType) {
        if(!repository.existsById(movementType.getId())){
            return repository.save(movementType);
        }else{
            throw new NotFoundException("MovementType not found");
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
