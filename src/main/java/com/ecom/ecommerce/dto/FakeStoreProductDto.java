package com.ecom.ecommerce.dto;

import com.ecom.ecommerce.model.Category;
import com.ecom.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;

    // Converts FakeStoreProductDto to Product
    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.image);

        Category category = new Category();
        category.setTitle(this.category);
        product.setCategory(category);

        return product;
    }

    // Converts Product to FakeStoreProductDto
    public FakeStoreProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.image = product.getImageUrl();
        this.category = product.getCategory().getTitle();
    }
}
