package com.ecom.ecommerce.service;

import com.ecom.ecommerce.dto.FakeStoreProductDto;
import com.ecom.ecommerce.exceptions.ProductNotFoundException;
import com.ecom.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//import static com.sun.org.apache.xml.internal.serializer.Version.getProduct;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    private final RedisTemplate redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto productDto = new FakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "http://ecommerce/products", productDto, FakeStoreProductDto.class);
        if (response.getBody() != null) {
            return response.getBody().toProduct();
        }
        return null;
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        FakeStoreProductDto productDto = new FakeStoreProductDto(product);
        restTemplate.put("http://ecommerce/products/" + product.getId(), productDto);
        return getSingleProduct(product.getId());
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("http://ecommerce/products/" + id);
    }

//    @Override
//    public Product getSingleProduct(Long id) throws ProductNotFoundException {
//        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
//                "http://ecommerce/products/" + id, FakeStoreProductDto.class);
//        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
//            throw new ProductNotFoundException("Product not found with id " + id);
//        }
//        return response.getBody().toProduct();
//    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException{
        Product productFromRedis = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_"+productId);
        if (productFromRedis != null) {
            return productFromRedis;
        }
        FakeStoreProductDto productDto = restTemplate.getForObject(
                "http://ecommerce/products/" + productId, FakeStoreProductDto.class
        );

        if(productDto == null) {
            throw new ProductNotFoundException("Product not found with id " + productId);
        }
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_"+productId, productDto.toProduct());
        return productDto.toProduct();
    }

    //    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNumber, String fieldName) {
//        FakeStoreProductDto[] response = restTemplate.getForObject(
//                "http://ecommerce/products", FakeStoreProductDto[].class);
//        List<Product> products = new ArrayList<>();
//        if (response != null) {
//            for (FakeStoreProductDto dto : response) {
//                products.add(dto.toProduct());
//            }
//        }
//        return products;
//    }
        return null;
    }
}
