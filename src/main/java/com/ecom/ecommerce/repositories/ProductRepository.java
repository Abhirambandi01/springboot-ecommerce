package com.ecom.ecommerce.repositories;

import com.ecom.ecommerce.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
    Product findByDescription(String description);

    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> getProductsByCategoryId(@Param("categoryId") Long categoryId);

    Page<Product> findAll(Pageable pageable);
    // List of products and other information related to pagination
    // that we will pass


}
