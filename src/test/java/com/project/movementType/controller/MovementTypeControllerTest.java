package com.project.movementType.controller;

import com.project.model.MovementType;
import com.project.movementType.Controller.MovementTypeController;
import com.project.movementType.Service.MovementTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
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
        when(service.insert(any(MovementType.class))).thenReturn(buildMovementType());
        MovementType response = controller.insert(buildMovementType());
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getCreationPersonId());
    }

    @Test
    public void insert_genericException() {
        //Arrange
        when(service.insert(any(MovementType.class))).thenThrow(new RuntimeException());
        // Act
        Throwable throwable = assertThrows(
                ResponseStatusException.class,
                () -> controller.insert(buildMovementType())
        );
        // Assert
        assertEquals(ResponseStatusException.class, throwable.getClass());
        assertNotEquals(RuntimeException.class, throwable.getClass());
    }

    @Test
    public void update_success() {
        when(service.update(any(MovementType.class))).thenReturn(buildMovementType());
        MovementType response = controller.update(buildMovementType());
        assertEquals(1L, response.getId());
        assertNotEquals(null, response.getId());
    }

    @Test
    public void update_genericException() {
        when(service.update(any(MovementType.class))).thenThrow(new RuntimeException());
        Throwable throwable = assertThrows(
                ResponseStatusException.class,
                () -> controller.update(buildMovementType())
        );
        assertEquals(ResponseStatusException.class, throwable.getClass());
        assertNotEquals(RuntimeException.class, throwable.getClass());
    }

    @Test
    public void delete_success() {
        doNothing().when(service).delete(anyLong());
        controller.delete(1L);
        verify(service, times(1)).delete(anyLong());
    }

    @Test
    public void delete_NotFound() {
        doThrow(EmptyResultDataAccessException.class).when(service).delete(anyLong());
        Throwable throwable = assertThrows(
                ResponseStatusException.class,
                () -> controller.delete(anyLong())
        );
        assertEquals(ResponseStatusException.class, throwable.getClass());
        assertNotEquals(NoSuchElementException.class, throwable.getClass());
        assertEquals("404 NOT_FOUND \"ID Movement Type nao encontrado\"", throwable.getMessage());
    }

    @Test
    public void delete_BadRequest() {
        doThrow(RuntimeException.class).when(service).delete(anyLong());
        Throwable throwable = assertThrows(
                ResponseStatusException.class,
                () -> controller.delete(anyLong())
        );
        assertEquals(ResponseStatusException.class, throwable.getClass());
        assertNotEquals(Exception.class, throwable.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", throwable.getMessage());
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
