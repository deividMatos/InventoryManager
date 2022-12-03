package com.project.product.service;

import com.project.model.Person;
import com.project.model.Product;
import com.project.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.webjars.NotFoundException;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    ProductService service;
    @Mock
    ProductRepository repository;
   //----------------------TEST GET BY ID------------------------------
    @Test
    public void getById_success(){
        when (repository.findById(anyLong())).thenReturn(Optional.ofNullable(buildProduct()));
        Product response = service.getById(1L);
        assertNotEquals(null, response);
        assertEquals(1L, response.getId());
        assertEquals("description Test", response.getDescription());
    }
    @Test
    public void getById_notFound(){
        when(repository.findById(anyLong())).thenThrow(NoSuchElementException.class);
        Throwable response= assertThrows(
                NoSuchElementException.class,
                ()-> service.getById(1L)
        );
        assertEquals(NoSuchElementException.class,response.getClass());
    }
    //----------------------------TEST GET ALL------------------------------------
    @Test
    public void getAll_success() {
        ArrayList<Product> lista = new ArrayList<>();
        lista.add(buildProduct());
        when(repository.findAll()).thenReturn(lista);
        List<Product> response = service.getAllProduct();
        assertNotEquals(null, response);
        assertEquals (1L,response.get(0).getId());
    }
    //----------------------------TEST CREATE-------------------------------------
    @Test
    public void create_Success(){
        when(repository.save(any())).thenReturn(buildProduct());
        Product response = service.create(buildProduct());
        assertNotEquals(null, response);
        assertEquals( 1L, response.getId());
    }
    @Test
    public void create_allreadyExistsException() {
        when(repository.existsById(anyLong())).thenReturn(Boolean.TRUE);
        Throwable excessao = assertThrows(
                EntityExistsException.class,
                () -> service.create(buildProduct())
        );
        assertEquals(EntityExistsException.class, excessao.getClass());
    }
    //----------------------------TEST UPDATE-------------------------------------
    @Test
    public void update_success(){
        when(repository.existsById(any())).thenReturn(true);

        when(repository.save(any())).thenReturn((buildProduct()));
        Product response = service.update(buildProduct());
        assertNotEquals(null, response);
        assertEquals( 1L, response.getId());
    }
    @Test
    public void update_genericNotFound() {
        when(repository.existsById(any())).thenReturn(Boolean.FALSE);
        Throwable excessao = assertThrows(
                NotFoundException.class,
                () -> service.update((buildProduct()))
        );
        assertEquals(NotFoundException.class, excessao.getClass());
        assertEquals("Product not found", excessao.getMessage());
    }
    //----------------------------TEST DELETE-------------------------------------
    @Test
    public void delete_success(){
        doNothing().when(repository).deleteById(anyLong());
        service.delete(buildProduct().getId());
        verify(repository,times(1)).deleteById(anyLong());
    }
    @Test
    public void delete_notFound(){
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(anyLong());
        Throwable excessao = assertThrows(
                EmptyResultDataAccessException.class,
                ()->service.delete(buildProduct().getId())
        );
        assertEquals(EmptyResultDataAccessException.class, excessao.getClass());

    }
    //-----------------------------------------BUILD-----------------------------------------------
    public Product buildProduct() {
        return Product.builder()
                .id(1L)
                .creationPersonId(1L)
                .description("description Test")
                .build();
    }
}
