package com.ecom.ecommerce.service;

import com.ecom.ecommerce.exceptions.ProductNotFoundException;
import com.ecom.ecommerce.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product) throws ProductNotFoundException;
    Product updateProduct(Product product) throws ProductNotFoundException;
    void deleteProduct(Long id);
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    Page<Product> getAllProducts(int pageSize, int pageNumber, String fieldName) throws ProductNotFoundException;
}
