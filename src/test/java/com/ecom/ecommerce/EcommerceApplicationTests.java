//package com.ecom.ecommerce;
//
//import com.ecom.ecommerce.dto.FakeStoreProductDto;
//import com.ecom.ecommerce.exceptions.ProductNotFoundException;
//import com.ecom.ecommerce.model.Category;
//import com.ecom.ecommerce.model.Product;
//import com.ecom.ecommerce.repositories.CategoryRepository;
//import com.ecom.ecommerce.repositories.ProductRepository;
//import com.ecom.ecommerce.service.FakeStoreProductService;
//import com.ecom.ecommerce.service.ProductService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class EcommerceApplicationTests {
//
//	@Autowired
//	private ProductRepository productRepository;
//
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//    @Qualifier("fakeStoreProductService")
//    @Autowired
//	private ProductService productService;
//
//	@MockBean
//	private RestTemplate restTemplate;
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	void testingQueries(){
//		List<Product> products = productRepository.getProductsByCategoryId(1L);
//		assertFalse(products.isEmpty());
//		System.out.println(products.get(0));
//	}
//
//	@Test
//	void fetchCategoryLazy(){
//		Optional<Category> optionalCategory = categoryRepository.findById(1L);
//		assertTrue(optionalCategory.isPresent());
//		Category category = optionalCategory.get();
//		assertNotNull(category.getId());
//		System.out.println(category.getId());
//		System.out.println("We are done here");
//
//		List<Product> products = category.getProducts();
//		assertNotNull(products);
//		System.out.println(products.size());
//		System.out.println("Products fetched");
//	}
//
//	@Test
//	void testCreateProduct() throws ProductNotFoundException {
//		Product product = new Product();
//		product.setTitle("Test Product");
//		product.setDescription("Test Description");
//		product.setPrice(100.0);
//		product.setImageUrl("http://amazon.com/image.jpg");
//
//		Category category = new Category();
//		category.setTitle("Test Category");
//		product.setCategory(category);
//
//		FakeStoreProductDto productDto = new FakeStoreProductDto(product);
//		when(restTemplate.postForEntity(anyString(), any(), eq(FakeStoreProductDto.class)))
//				.thenReturn(new ResponseEntity<>(productDto, HttpStatus.OK));
//
//		Product createdProduct = productService.createProduct(product);
//		assertNotNull(createdProduct);
//		assertEquals("Test Product", createdProduct.getTitle());
//	}
//
//	@Test
//	void testUpdateProduct() throws Exception {
//		Product product = new Product();
//		product.setId(1L);
//		product.setTitle("Updated Product");
//		product.setDescription("Updated Description");
//		product.setPrice(150.0);
//		product.setImageUrl("http://flipkart.com/updated_image.jpg");
//
//		Category category = new Category();
//		category.setTitle("Updated Category");
//		product.setCategory(category);
//
//		when(restTemplate.getForEntity(anyString(), eq(FakeStoreProductDto.class)))
//				.thenReturn(new ResponseEntity<>(new FakeStoreProductDto(product), HttpStatus.OK));
//		doNothing().when(restTemplate).put(anyString(), any(FakeStoreProductDto.class));
//
//		Product updatedProduct = productService.updateProduct(product);
//		assertNotNull(updatedProduct);
//		assertEquals("Updated Product", updatedProduct.getTitle());
//	}
//
//	@Test
//	void testDeleteProduct() {
//		doNothing().when(restTemplate).delete(anyString());
//		assertDoesNotThrow(() -> productService.deleteProduct(1L));
//	}
//
//	@Test
//	void testGetProduct() throws Exception {
//		Product product = new Product();
//		product.setId(1L);
//		product.setTitle("Test Product");
//		product.setDescription("Test Description");
//		product.setPrice(100.0);
//		product.setImageUrl("http://flipkart.com/image.jpg");
//
//		Category category = new Category();
//		category.setTitle("Test Category");
//		product.setCategory(category);
//
//		when(restTemplate.getForEntity(anyString(), eq(FakeStoreProductDto.class)))
//				.thenReturn(new ResponseEntity<>(new FakeStoreProductDto(product), HttpStatus.OK));
//
//		Product fetchedProduct = productService.getProduct(1L);
//		assertNotNull(fetchedProduct);
//		assertEquals("Test Product", fetchedProduct.getTitle());
//	}
//
//	@Test
//	void testGetAllProducts() {
//		Product product1 = new Product();
//		product1.setId(1L);
//		product1.setTitle("Test Product 1");
//		product1.setDescription("Test Description 1");
//		product1.setPrice(100.0);
//		product1.setImageUrl("http://amazon.com/image1.jpg");
//
//		Product product2 = new Product();
//		product2.setId(2L);
//		product2.setTitle("Test Product 2");
//		product2.setDescription("Test Description 2");
//		product2.setPrice(200.0);
//		product2.setImageUrl("http://flipkart.com/image2.jpg");
//
//		FakeStoreProductDto[] products = { new FakeStoreProductDto(product1), new FakeStoreProductDto(product2) };
//
//		when(restTemplate.getForObject(anyString(), eq(FakeStoreProductDto[].class)))
//				.thenReturn(products);
//
//		List<Product> fetchedProducts = productService.getAllProducts();
//		assertNotNull(fetchedProducts);
//		assertEquals(2, fetchedProducts.size());
//	}
//}
