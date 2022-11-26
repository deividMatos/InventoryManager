package com.project.movementType.service;


import com.project.model.MovementType;
import com.project.movementType.Repository.MovementTypeRepository;
import com.project.movementType.Service.MovementTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovementTypeServiceTest {

    @InjectMocks
    private MovementTypeService service;

    @Mock
    private MovementTypeRepository repository;

    @Test
    public void getById() {
        MovementType build = buildMovementType();
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(build));
        MovementType response = service.getById(anyLong());
        assertNotEquals(null, response);
        assertEquals(build, response);
    }

    @Test
    public void insert() {
        MovementType build = buildMovementType();
        when(repository.save(build)).thenReturn(build);
        MovementType response = service.insert(build);
        assertNotEquals(null, response);
    }

    @Test
    public void update_if_id_exists() {
        MovementType build = buildMovementType();
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(build)).thenReturn(build);
        MovementType response = service.update(build);
        System.out.println(response);
        assertNotEquals(null, response);
        assertEquals(build, response);
    }

    @Test
    public void update_notFoundException() {
        MovementType build = buildMovementType();
        when(repository.existsById(anyLong())).thenReturn(false);
        Throwable throwable = assertThrows(
                NotFoundException.class,
                () -> service.update(build)
        );
        assertEquals(NotFoundException.class, throwable.getClass());
        assertNotEquals(RuntimeException.class, throwable.getClass());
        assertEquals("MovementType not found", throwable.getMessage());
    }

    @Test
    public void delete_success() {
        doNothing().when(repository).deleteById(anyLong());
        service.delete(1L);
        verify(repository, times(1)).deleteById(anyLong());
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
