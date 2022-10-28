package com.project.movement.controller;

import com.project.model.Movement;
import com.project.movement.service.MovementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovementControllerTest {
    @InjectMocks
    private MovementController controller;

    @Mock
    private MovementService service;

    @Test
    public void getByID_success() {
        when(service.getById(anyLong())).thenReturn(buildMovement());
        Movement response = controller.getById(1L);
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getCreationPersonId());
        assertEquals(1L, response.getProductId());
        assertEquals(2L, response.getQuantity());
    }

    @Test
    public void getByID_notFound() {
        when(service.getById(anyLong())).thenThrow(NoSuchElementException.class);
        Throwable response = assertThrows(
                ResponseStatusException.class,
                () -> controller.getById(1L),
                "Movimento nao encontrado"
        );
        System.out.println(response);
        assertEquals(ResponseStatusException.class, response.getClass());
        assertNotEquals(NoSuchElementException.class, response.getClass());
        assertEquals("404 NOT_FOUND \"Produto nao encontrado\"", response.getMessage());
    }

    @Test
    public void getByID_genericException() {
        when(service.getById(anyLong())).thenThrow(new RuntimeException());
        Throwable throwable = assertThrows(
                ResponseStatusException.class,
                () -> controller.getById(1L)
        );
        System.out.println(throwable);
        assertEquals(ResponseStatusException.class, throwable.getClass());
        assertNotEquals(ResponseStatusException.class, throwable.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", throwable.getMessage());
    }

    public Movement buildMovement() {
        return Movement.builder()
                .id(1L)
                .creationPersonId(1L)
                .productId(1L)
                .quantity(2L)
                .build();
    }
}
