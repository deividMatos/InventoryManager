package com.project.product.controller;


import com.project.model.Product;
import com.project.product.controller.ProductController;
import com.project.product.service.ProductService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService service;


    @Test
    public void hello_success() {
        String response = controller.hello();
        assertEquals("Hello Controller", response);
        assertNotEquals("Deivid", response);
    }

//----------------------TEST GET BY ID------------------------------
    @Test
    public void getByID_success() {
        when(service.getById(anyLong())).thenReturn(buildProduct());
        Product response = controller.getByID(1L);
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals("description Test", response.getDescription());
    }
    @Test
    public void getByID_notFound() {
        when(service.getById(anyLong())).thenThrow(NoSuchElementException.class);
        Throwable response = assertThrows(
                ResponseStatusException.class,
                () -> controller.getByID(1L),
                "Produto nao encontrado2"
        );
        assertEquals(ResponseStatusException.class, response.getClass());
        assertNotEquals(NoSuchElementException.class, response.getClass());
        assertEquals("404 NOT_FOUND \"Produto nao encontrado\"", response.getMessage());

    }
    @Test
    public void getByID_genericException() {
        when(service.getById(anyLong())).thenThrow(new RuntimeException("Cannot process"));
        Throwable thrown = assertThrows(
                ResponseStatusException.class,
                () -> controller.getByID(1L)
        );
        System.out.println(thrown);
        assertEquals(ResponseStatusException.class, thrown.getClass());
        assertNotEquals(NoSuchElementException.class, thrown.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", thrown.getMessage());

    }
    //----------------------------TEST GET ALL------------------------------------
    @Test
    public void getAll_success() {
        //criar o array que recece o build
        ArrayList<Product> lista = new ArrayList<>();
        //adicionar o buid na lista
        lista.add(buildProduct());
        when(service.getAllProduct()).thenReturn(lista);
        List<Product> response= controller.getAllProduct();
        assertNotEquals(null, response);
        assertEquals (1L,response.get(0).getId());
    }
    //----------------------------TEST CREATE-------------------------------------
    @Test
    public void create_success() {
        when(service.create(any())).thenReturn(buildProduct());
        Product response =controller.create(buildProduct());
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals("description Test", response.getDescription());
    }
    @Test
    public void create_genericException() {
        when(service.create(any(Product.class))).thenThrow(RuntimeException.class);
        Throwable thrown = assertThrows(
                ResponseStatusException.class,
                () -> controller.create((buildProduct()))
        );
        assertEquals(ResponseStatusException.class, thrown.getClass());
        assertNotEquals(NoSuchElementException.class, thrown.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", thrown.getMessage());
    }
    //----------------------------TEST UPDATE-------------------------------------
    @Test
    public void update_success(){
        when(service.update(any())).thenReturn(buildProduct());
        Product response =controller.update(buildProduct());
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals("description Test", response.getDescription());
    }
    @Test
    public void update_genericException(){
        when(service.update(any(Product.class))).thenThrow(RuntimeException.class);
        Throwable excessao = assertThrows(
                ResponseStatusException.class,
                () -> controller.update((buildProduct()))
        );
        assertEquals(ResponseStatusException.class, excessao.getClass());
        assertNotEquals(NoSuchElementException.class, excessao.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", excessao.getMessage());
    }
    //----------------------------------TEST DELETE-------------------------------------------
    @Test
    public void delete_success(){
        doNothing().when(service).delete(anyLong());
        controller.delete(buildProduct().getId());
        verify(service,times(1)).delete(anyLong());
    }
    @Test
    public void delete_genericException(){
        doThrow(RuntimeException.class).when(service).delete(anyLong());
        Throwable excessao = assertThrows(
                ResponseStatusException.class,
                ()->controller.delete(buildProduct().getId()));
        assertEquals(ResponseStatusException.class, excessao.getClass());
        assertNotEquals(NoSuchElementException.class, excessao.getClass());
        assertEquals("400 BAD_REQUEST \"Problema na requisicao\"", excessao.getMessage());
    }
    @Test
    public void delete_notFound(){
        doThrow(EmptyResultDataAccessException.class).when(service).delete(anyLong());
        Throwable excessao = assertThrows(
                ResponseStatusException.class,
                ()->controller.delete(buildProduct().getId())
        );
        assertEquals(ResponseStatusException.class, excessao.getClass());
        assertNotEquals(Exception.class, excessao.getClass());
        assertEquals("404 NOT_FOUND \"Produto nao encontrado\"", excessao.getMessage());
    }
    //-----------------------------------------BUILD-----------------------------------------------
    public Product buildProduct() {
        return Product.builder()
                .id(1L)
                .creationPersonId(1L)
                .dateCreation(new Date())
                .dateUpdated(new Date())
                .description("description Test")
                .build();
    }
}
