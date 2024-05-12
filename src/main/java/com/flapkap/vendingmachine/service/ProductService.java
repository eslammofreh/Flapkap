package com.flapkap.vendingmachine.service;

import com.flapkap.vendingmachine.model.Product;
import com.flapkap.vendingmachine.model.User;
import com.flapkap.vendingmachine.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public List<Product> getAllProducts() throws Exception {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error occurred while fetching products: " + e.getMessage());
        }
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setProductName(updatedProduct.getProductName());
            product.setAmountAvailable(updatedProduct.getAmountAvailable());
            product.setCost(updatedProduct.getCost());
            product.setSellerId(updatedProduct.getSellerId());
            return productRepository.save(product);
        } else {
            System.out.println("ProductNotFoundException");
            return null;
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public String buyProducts(Long userId, Long productId, int quantity) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            double totalCost = product.getCost() * quantity;
            Optional<User> userOptional = userService.getUserById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.getDeposit() >= totalCost) {
                    if (product.getAmountAvailable() >= quantity) {
                        product.setAmountAvailable(product.getAmountAvailable() - quantity);
                        productRepository.save(product);
                        user.setDeposit(user.getDeposit() - totalCost);
                        userService.updateUser(user.getId(), user);
                        return "Purchase successful! Total cost: " + totalCost;
                    } else {
                        return "Insufficient quantity available for purchase";
                    }
                } else {
                    return "Insufficient balance to make the purchase";
                }
            } else {
                return "User not found";
            }
        } else {
            return "Product not found";
        }
    }
}
