package com.project.movement.service;

import com.project.movement.controller.MovementController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovementServiceTest {
    @InjectMocks
    private MovementController controller;

    @Mock
    private MovementService service;
}
