package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.dto.ErrorDto;
import com.ecom.ecommerce.exceptions.ProductNotFoundException;
import com.ecom.ecommerce.model.Product;
import com.ecom.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ProductNotFoundException {
        Product postProductResponse = productService.createProduct(product);
        return new ResponseEntity<>(postProductResponse, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotFoundException {
        product.setId(id);
        return productService.updateProduct(product);
    }

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products")
    public Page<Product> getAllProducts(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber, @RequestParam("sortBy") String fieldName) throws ProductNotFoundException {
        return productService.getAllProducts(pageSize, pageNumber, fieldName);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e) {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage(e.getMessage());
//        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//    }

    @GetMapping("/health")
    public  ResponseEntity<String> getProductHealth() {
        return new ResponseEntity<>("Backend application is running ", HttpStatus.OK);
    }

}
