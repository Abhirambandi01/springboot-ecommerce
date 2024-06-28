package com.ecom.ecommerce.repositories.projections;

public interface ProductProjection {
    // to get specific fields from product
    Long getId();
    String getTitle();
    String getDescription();
}
