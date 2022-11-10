package com.project.movementType.service;


import com.project.movementType.Repository.MovementTypeRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovementTypeService {

    @InjectMocks
    private MovementTypeService service;

    @Mock
    private MovementTypeRepository repository;

}
