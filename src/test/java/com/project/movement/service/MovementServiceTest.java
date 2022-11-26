package com.project.movement.service;

import com.project.model.Movement;
import com.project.movement.controller.MovementController;
import com.project.movement.repository.MovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MovementServiceTest {
    @InjectMocks
    private MovementService service;

    @Mock
    private MovementRepository repository;

    @Test
    public void getById() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(buildMovement()));
        Movement response = service.getById(anyLong());
        assertNotEquals(null, response);
        assertEquals( 1L, response.getId());
        assertEquals(1L, response.getCreationPersonId());
        assertEquals(1L, response.getProductId());
        assertEquals(2L, response.getQuantity());
    }

    @Test
    public void insert() {
        when(repository.save(buildMovement())).thenReturn(buildMovement());
        Movement response = service.insert(buildMovement());
        assertNotEquals(null, response);
        assertEquals( 1L, response.getId());
        assertEquals(1L, response.getCreationPersonId());
        assertEquals(1L, response.getProductId());
        assertEquals(2L, response.getQuantity());
    }

    @Test
    public void update_if_id_exists() {
        Movement build = buildMovement();
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(build)).thenReturn(build);
        Movement response = service.update(build);
        assertNotEquals(null, response);
        assertEquals(build, response);
    }

    @Test
    public void update_exception() {
        Movement build = buildMovement();
        when(repository.existsById(anyLong())).thenReturn(false);
        Throwable throwable = assertThrows(
                NotFoundException.class,
                () -> service.update(build)
        );
        assertEquals(NotFoundException.class, throwable.getClass());
        assertNotEquals(RuntimeException.class, throwable.getClass());
        assertEquals("Movement not found", throwable.getMessage());
    }

    @Test
    public void delete() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(anyLong());
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