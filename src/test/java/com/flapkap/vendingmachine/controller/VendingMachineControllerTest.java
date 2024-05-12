package com.flapkap.vendingmachine.controller;

import com.flapkap.vendingmachine.model.Product;
import com.flapkap.vendingmachine.service.ProductService;
import com.flapkap.vendingmachine.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    @InjectMocks
    private VendingMachineController controller;

    @Test
    public void testGetAllProducts() throws Exception {
        // Mock data
        List<Product> mockProducts = Arrays.asList(new Product(), new Product());
        when(productService.getAllProducts()).thenReturn(mockProducts);

        // Call controller method
        ResponseEntity<List<Product>> response = controller.getAllProducts();

        // Verify
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(mockProducts);
    }

}
