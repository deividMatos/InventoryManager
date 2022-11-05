package com.project.movement.controller;

import com.project.model.Movement;
import com.project.movement.service.MovementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
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
        assertEquals("404 NOT_FOUND \"Movimento nao encontrado\"", response.getMessage());
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
        assertNotEquals(RuntimeException.class, throwable.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", throwable.getMessage());
    }

    @Test
    public void insertMovement_success() {
        when(service.insert(any(Movement.class))).thenReturn(buildMovement());
        Movement response = controller.insert(buildMovement());
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getCreationPersonId());
        assertEquals(1L, response.getProductId());
        assertEquals(2L, response.getQuantity());
    }
    
    @Test
    public void insertMovement_notAccepted() {
        when(service.insert(any(Movement.class))).thenThrow(new RuntimeException());
        Throwable throwable = assertThrows(
                ResponseStatusException.class,
                () -> controller.insert(buildMovement())
        );
        System.out.println(throwable);
        assertEquals(ResponseStatusException.class, throwable.getClass());
        assertNotEquals(RuntimeException.class, throwable.getClass());
        assertEquals("406 NOT_ACCEPTABLE \"Modelo nao aceitavel\"", throwable.getMessage());
    }

    @Test
    public void updateMovement_success() {
        when(service.update(any(Movement.class))).thenReturn(buildMovement());
        Movement response = controller.update(buildMovement());
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getCreationPersonId());
        assertEquals(1L, response.getProductId());
        assertEquals(2L, response.getQuantity());
    }

    @Test
    public void updateMovement_notAccepted() {
        when(service.update(any())).thenThrow(new RuntimeException());
        Throwable throwable = assertThrows(
                ResponseStatusException.class,
                () -> controller.update(buildMovement())
        );
        System.out.println(throwable);
        assertEquals(ResponseStatusException.class, throwable.getClass());
        assertNotEquals(RuntimeException.class, throwable.getClass());
        assertEquals("406 NOT_ACCEPTABLE \"Modelo nao aceitavel\"", throwable.getMessage());
    }

//    @Test
//    public void deleteByID_success() {
//        when(service.deleteById(anyLong())).thenThrow(HttpStatus.OK);
//        Movement response = controller.delete(1L);
//        assertNotEquals(null, response);
//        assertEquals(1L, response);
//    }


    public Movement buildMovement() {
        return Movement.builder()
                .id(1L)
                .creationPersonId(1L)
                .productId(1L)
                .quantity(2L)
                .build();
    }
}
