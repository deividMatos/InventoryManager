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
        when(repository.existsById(1L)).thenReturn(true);
        Movement response = service.update(buildMovement());
        assertNotEquals(false, response);
    }

//    @Test
//    public void update_exception() {
//        when(repository.existsById(123L)).thenThrow(new NotFoundException("Movement not found"));
//    }

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
