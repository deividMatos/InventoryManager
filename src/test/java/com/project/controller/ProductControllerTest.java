package com.project.controller;


import com.project.model.Product;
import com.project.product.controller.ProductController;
import com.project.product.service.ProductService;

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
