package com.flapkap.vendingmachine.controller;

import com.flapkap.vendingmachine.model.Product;
import com.flapkap.vendingmachine.model.User;
import com.flapkap.vendingmachine.service.ProductService;
import com.flapkap.vendingmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VendingMachineController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/deposit")
    public User depositCoins(@RequestBody User user) {
        return userService.depositCoins(user.getId(), user.getDeposit());
    }

    @PostMapping("/buy")
    public String buyProducts(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return productService.buyProducts(userId, productId, quantity);
    }

    @PostMapping("/reset")
    public User resetDeposit(@RequestParam Long userId) {
        return userService.resetDeposit(userId);
    }
}
