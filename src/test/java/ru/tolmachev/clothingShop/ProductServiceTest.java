package ru.tolmachev.clothingShop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tolmachev.clothingShop.Models.Product;
import ru.tolmachev.clothingShop.Repository.ProductRepository;
import ru.tolmachev.clothingShop.Service.ProductService;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    void getAllProducts(){
        Product product = new Product();
        product.setId(1L);
        product.setName("testProduct");

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(product);

        Mockito.when(productRepository.findAll()).thenReturn(mockProductList);

        List<Product> allProducts = productService.getAllProducts();

        Mockito.verify(productRepository, Mockito.times(1)).findAll();

        assertNotNull(allProducts);
        assertEquals(1, allProducts.size());
        assertEquals("testProduct", allProducts.get(0).getName());
    }
    @Test
    void getAllProducts_EmptyList() {
        Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<Product> allProducts = productService.getAllProducts();

        Mockito.verify(productRepository, Mockito.times(1)).findAll();

        assertTrue(allProducts.isEmpty());
    }

}
