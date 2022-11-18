package com.project.movementType.controller;

import com.project.model.MovementType;
import com.project.movementType.Controller.MovementTypeController;
import com.project.movementType.Service.MovementTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovementTypeControllerTest {

    @InjectMocks
    private MovementTypeController controller;

    @Mock
    private MovementTypeService service;

    @Test
    public void getByID_success() {
        when(service.getById(anyLong())).thenReturn(buildMovementType());
        MovementType response = controller.getById(1L);
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals("string test description", response.getDescription());
        assertEquals(1L, response.getCreationPersonId());
    }

    @Test
    public void getByID_notFound() {
        when(service.getById(anyLong())).thenThrow(NoSuchElementException.class);
        Throwable response = assertThrows(
                ResponseStatusException.class,
                () -> controller.getById(1L),
                "Tipo de Movimento nao Encontrado"
        );
        assertEquals(ResponseStatusException.class, response.getClass());
        assertNotEquals(NoSuchElementException.class, response.getClass());
        assertEquals("404 NOT_FOUND \"MovementType nao encontrado\"", response.getMessage());
    }

    @Test
    public void getByID_genericException() {
        when(service.getById(anyLong())).thenThrow(new RuntimeException("Cannot process"));
        Throwable thrown = assertThrows(
                ResponseStatusException.class,
                () -> controller.getById(1L)
        );
        System.out.println(thrown);
        assertEquals(ResponseStatusException.class, thrown.getClass());
        assertNotEquals(NoSuchElementException.class, thrown.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", thrown.getMessage());
    }

    @Test
    public void insert_success() {

    }

    @Test
    public void insert_genericException() {

    }

    @Test
    public void update_success() {

    }

    @Test
    public void update_genericException() {

    }

    @Test
    public void delete_success() {

    }

    @Test
    public void delete_NotFound() {

    }

    @Test
    public void delete_BadRequest() {

    }

    public MovementType buildMovementType() {
        return MovementType.builder()
                .id(1L)
                .description("string test description")
                .dateUpdated(new Date())
                .creationPersonId(1L)
                .build();
    }
}
