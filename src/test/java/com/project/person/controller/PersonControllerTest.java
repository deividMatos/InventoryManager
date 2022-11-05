package com.project.person.controller;

import com.project.model.Person;
import com.project.person.Controller.PersonController;
import com.project.person.service.PersonService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
    @InjectMocks PersonController controller;
    @Mock PersonService service;
//----------------------TEST GET BY ID------------------------------
    @Test
    public void getByID_success() {
        when(service.getById(anyLong())).thenReturn(buildPerson());
        Person response= controller.getById(1L);
        assertNotEquals(null, response);
        assertEquals( 1L, response.getId());
        assertEquals("person", response.getFirstName());
    }
    @Test
    public void getById_notFound(){
        when(service.getById(anyLong())).thenThrow(NoSuchElementException.class);
        Throwable response= assertThrows(
                ResponseStatusException.class,
                () -> controller.getById(1L),
                "Usuario nao foi encontrado"
        );
        assertEquals(ResponseStatusException.class, response.getClass());
        assertNotEquals(NoSuchElementException.class, response.getClass());
        assertEquals("404 NOT_FOUND \"Usuario nao foi encontrado\"", response.getMessage());
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
//----------------------------TEST CREATE-------------------------------------
    @Test
    public void create_Success(){
        when(service.create(any())).thenReturn(buildPerson());
        Person response = controller.create(buildPerson());
        assertNotEquals(null, response);
        assertEquals( 1L, response.getId());
        assertEquals("person", response.getFirstName());
    }
    @Test
    public void create_genericException() {
        when(service.create(any(Person.class))).thenThrow(RuntimeException.class);
        Throwable thrown = assertThrows(
                ResponseStatusException.class,
                () -> controller.create((buildPerson()))
        );
        assertEquals(ResponseStatusException.class, thrown.getClass());
        assertNotEquals(NoSuchElementException.class, thrown.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", thrown.getMessage());
    }
//----------------------------TEST UPDATE-------------------------------------
    @Test
    public void update_success(){
        when(service.update(any())).thenReturn(buildPerson());
        Person response = controller.update(buildPerson());
        assertNotEquals(null, response);
        assertEquals( 1L, response.getId());
        assertEquals("person", response.getFirstName());
    }
    @Test
    public void update_genericException() {
        when(service.update(any(Person.class))).thenThrow(RuntimeException.class);
        Throwable excessao = assertThrows(
                ResponseStatusException.class,
                () -> controller.update((buildPerson()))
        );
        assertEquals(ResponseStatusException.class, excessao.getClass());
        assertNotEquals(NoSuchElementException.class, excessao.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", excessao.getMessage());
    }
//----------------------------TEST DELETE-------------------------------------
   @Test
   public void delete_success(){
        doNothing().when(service).delete(anyLong());
        controller.delete(buildPerson().getId());
        verify(service,times(1)).delete(anyLong());
   }
   @Test
   public void delete_genericException(){
        doThrow(RuntimeException.class).when(service).delete(anyLong());
        Throwable excessao = assertThrows(
               ResponseStatusException.class,
               ()->controller.delete(buildPerson().getId())
       );
        assertEquals(ResponseStatusException.class, excessao.getClass());
       assertNotEquals(NoSuchElementException.class, excessao.getClass());
       assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", excessao.getMessage());
    }
    @Test
    public void delete_notFound(){
        doThrow(EmptyResultDataAccessException.class).when(service).delete(anyLong());
        Throwable excessao = assertThrows(
                ResponseStatusException.class,
                ()->controller.delete(buildPerson().getId())
        );
        assertEquals(ResponseStatusException.class, excessao.getClass());
        assertNotEquals(Exception.class, excessao.getClass());
        assertEquals("404 NOT_FOUND \"Produto nao encontrado\"", excessao.getMessage());
    }
//-----------------------------------------BUILD-----------------------------------------------
    public Person buildPerson() {
        return Person.builder()
                .id(1L)
                .firstName("person")
                .build();
    }
}
