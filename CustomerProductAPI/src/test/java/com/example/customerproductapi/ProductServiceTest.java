package com.example.customerproductapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.customerproductapi.entity.Product;
import com.example.customerproductapi.exception.ResourceNotFoundException;
import com.example.customerproductapi.repository.ProductRepository;
import com.example.customerproductapi.service.ProductService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest
{

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	private Product product;

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this); // Initializes the mocks
		product = new Product("Test Book", 100.0, 10, "Fiction", "2022-01-01");
	}

	@Test
	void testCreateProduct()
	{
		// Given
		when(productRepository.save(any(Product.class))).thenReturn(product);

		// When
		Product createdProduct = productService.createProduct(product);

		// Then
		assertNotNull(createdProduct);
		assertEquals("Test Book", createdProduct.getBookTitle());
		verify(productRepository, times(1)).save(any(Product.class)); // Argument matcher to allow any product
	}

	@Test
	void testGetAllProducts()
	{
		// Given
		when(productRepository.findAll()).thenReturn(Arrays.asList(product));

		// When
		var products = productService.getAllProducts();

		// Then
		assertNotNull(products);
		assertEquals(1, products.size());
		assertEquals("Test Book", products.get(0).getBookTitle());
		verify(productRepository, times(1)).findAll();
	}

	@Test
	void testUpdateProduct_Success()
	{
		// Given
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
		Product updatedProduct = new Product("Updated Book", 120.0, 15, "Non-Fiction", "2023-01-01");
		when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

		// When
		Product result = productService.updateProduct(1L, updatedProduct);

		// Then
		assertNotNull(result);
		assertEquals("Updated Book", result.getBookTitle());
		assertEquals(120.0, result.getBookPrice());
		verify(productRepository, times(1)).findById(1L);
		verify(productRepository, times(1)).save(any(Product.class)); // Argument matcher to allow any product
	}

	@Test
	void testUpdateProduct_Failure_ProductNotFound()
	{
		// Given
		when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

		// When & Then
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			productService.updateProduct(1L, product);
		});

		assertEquals("Product with ID 1 not found", exception.getMessage());
		verify(productRepository, times(1)).findById(1L);
	}

	@Test
	void testPatchProduct_Success()
	{
		// Given
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
		Product patchedProduct = new Product("Patched Book", 110.0, 12, "Fantasy", "2023-02-01");
		when(productRepository.save(any(Product.class))).thenReturn(patchedProduct);

		// When
		Product result = productService.patchProduct(1L, patchedProduct);

		// Then
		assertNotNull(result);
		assertEquals("Patched Book", result.getBookTitle());
		assertEquals(110.0, result.getBookPrice());
		verify(productRepository, times(1)).findById(1L);
		verify(productRepository, times(1)).save(any(Product.class)); // Argument matcher to allow any product
	}

	@Test
	void testPatchProduct_Failure_ProductNotFound()
	{
		// Given
		when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

		// When & Then
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			productService.patchProduct(1L, product);
		});

		assertEquals("Product with ID 1 not found", exception.getMessage());
		verify(productRepository, times(1)).findById(1L);
	}
}
