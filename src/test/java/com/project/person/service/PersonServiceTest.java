package com.project.person.service;

import com.project.model.Person;
import com.project.person.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

   @InjectMocks PersonService service;
   @Mock PersonRepository repository;

   //----------------------TEST GET BY ID------------------------------
    @Test
    public void getById_success(){
        when (repository.findById(anyLong())).thenReturn(Optional.ofNullable(buildPerson()));
        Person response = service.getById(1L);
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals("person", response.getFirstName());
    }
    @Test
    public void getById_notFound(){
        when(repository.findById(anyLong())).thenThrow(NoSuchElementException.class);
        Throwable response= assertThrows(
                NoSuchElementException.class,
                ()-> service.getById(1L),
                "Usuario nao foi encontrado"
        );
        assertEquals(NoSuchElementException.class,response.getClass());
        assertNotEquals(ResponseStatusException.class, response.getClass());
    }
    @Test
    public void getByID_genericException() {
        when(repository.existsById(anyLong())).thenThrow(new RuntimeException("Cannot process"));
        Throwable thrown = assertThrows(
                ResponseStatusException.class,
                () -> service.getById(1L)
        );
        assertEquals(ResponseStatusException.class, thrown.getClass());
        assertNotEquals(NoSuchElementException.class, thrown.getClass());
    }
    //----------------------------TEST GET ALL------------------------------------
    @Test
    public void getAll_success() {
        ArrayList<Person> lista = new ArrayList<>();
        lista.add(buildPerson());
        when(repository.findAll()).thenReturn(lista);
        List<Person> response = service.getAllPerson();
        assertNotEquals(null, response);
        assertEquals (1L,response.get(0).getId());
    }
    //----------------------------TEST CREATE-------------------------------------
    @Test
    public void create_Success(){
        when(repository.save(any())).thenReturn(buildPerson());
        Person response = service.create(buildPerson());
        assertNotEquals(null, response);
        assertEquals( 1L, response.getId());
        assertEquals("person", response.getFirstName());
    }
    @Test
    public void create_genericException() {
        when(repository.save(any(Person.class))).thenThrow(RuntimeException.class);
        Throwable thrown = assertThrows(
                ResponseStatusException.class,
                () -> service.create((buildPerson()))
        );
    }
    //----------------------------TEST UPDATE-------------------------------------
    @Test
    public void update_success(){
        when(repository.existsById(any())).thenReturn(true);

        when(repository.save(any())).thenReturn((buildPerson()));
        Person response = service.update(buildPerson());
        assertNotEquals(null, response);
        assertEquals( 1L, response.getId());
        assertEquals("person", response.getFirstName());
    }
    @Test
    public void update_genericException() {
        when(repository.existsById(any())).thenThrow(RuntimeException.class);
        Throwable excessao = assertThrows(
                ResponseStatusException.class,
                () -> service.update((buildPerson()))
        );
        assertEquals(ResponseStatusException.class, excessao.getClass());
        assertNotEquals(NoSuchElementException.class, excessao.getClass());
    }
    //----------------------------TEST DELETE-------------------------------------
    @Test
    public void delete_success(){
        doNothing().when(repository).deleteById(anyLong());
        service.delete(buildPerson().getId());
        verify(repository,times(1)).deleteById(anyLong());
    }
    @Test
    public void delete_genericException(){
        doThrow(RuntimeException.class).when(repository).deleteById(anyLong());
        Throwable excessao = assertThrows(
                RuntimeException.class,
                ()->service.delete(buildPerson().getId())
        );
        assertEquals(ResponseStatusException.class, excessao.getClass());
        assertNotEquals(NoSuchElementException.class, excessao.getClass());
    }
    @Test
    public void delete_notFound(){
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(anyLong());
        Throwable excessao = assertThrows(
                EmptyResultDataAccessException.class,
                ()->service.delete(buildPerson().getId())
        );
        assertEquals(EmptyResultDataAccessException.class, excessao.getClass());
        //assertNotEquals(ResponseStatusException.class, excessao.getClass());
    }
    //-----------------------------------------BUILD-----------------------------------------------
  public Person buildPerson() {
      return Person.builder()
              .id(1L)
              .firstName("person")
              .build();
  }
}
