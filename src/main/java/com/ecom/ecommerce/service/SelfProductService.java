package com.ecom.ecommerce.service;

import com.ecom.ecommerce.exceptions.ProductNotFoundException;
import com.ecom.ecommerce.model.Category;
import com.ecom.ecommerce.model.Product;
import com.ecom.ecommerce.repositories.CategoryRepository;
import com.ecom.ecommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final RedisTemplate redisTemplate;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, RedisTemplate redisTemplate) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product createProduct(Product product) throws ProductNotFoundException {
        Product newProduct = new Product();
        try{
            Category category = categoryRepository.findByTitle(product.getCategory().getTitle());
            if (category == null) {
                category = new Category();
                category.setTitle(product.getCategory().getTitle());
                category = categoryRepository.save(category);
            }else {
                product.setCategory(category);
            }
            newProduct =  productRepository.save(product);
        }catch (Exception e){
            e.printStackTrace();
            throw new ProductNotFoundException("Product Not Found..!");
        }
        // Expected exceptions
        // General catch
        return newProduct;
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        if (!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException("Product not found with id " + product.getId());
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException("Product not found with id " + id);
    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNumber, String fieldName) {
        Page<Product> allProducts = productRepository.findAll(PageRequest.of(pageNumber,pageSize, Sort.by(fieldName).ascending()));
        return allProducts;
    }

}
