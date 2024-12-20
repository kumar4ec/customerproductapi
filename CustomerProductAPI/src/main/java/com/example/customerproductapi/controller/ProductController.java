package com.example.customerproductapi.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import com.example.customerproductapi.entity.Product;
import com.example.customerproductapi.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/products")
public class ProductController
{

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Operation(summary = "Create a new product", description = "This API allows you to create a new product.")
	@ApiResponse(responseCode = "201", description = "Product created successfully", content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "500", description = "Failed to create product")
	@PostMapping
	public ResponseEntity<Object> createProduct(@RequestBody Product product)
	{
		logger.info("Received request to create product: {}", product);
		Product createdProduct = productService.createProduct(product);
		if (Objects.nonNull(createdProduct)) {

			logger.info("Product created successfully: {}", createdProduct);
			return ResponseEntity.status(HttpStatus.CREATED).body("Product is created successfully");
		}
		logger.error("Failed to create product: {}", product);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Product");
	}

	@Operation(summary = "Get all products", description = "This API retrieves all products from the system.")
	@ApiResponse(responseCode = "200", description = "List of products", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
	@ApiResponse(responseCode = "404", description = "No products found")
	@GetMapping
	public ResponseEntity<Object> getAllProducts()
	{
		logger.info("Received request to get all products");
		List<Product> productList = productService.getAllProducts();
		if (productList.isEmpty()) {
			logger.warn("No products found"); 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Products found");
		}
		logger.info("Successfully fetched {} products", productList.size()); // Log successful retrieval
		return ResponseEntity.ok(productList);
	}

	@Operation(summary = "Update product information", description = "This API allows you to update product details.")
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product)
	{
		logger.info("Received request to update product with ID {}: {}", id, product); 
        Product updatedProduct = productService.updateProduct(id, product);
        logger.info("Product updated successfully: {}", updatedProduct); // Log successful update
        return updatedProduct;
	}

	@Operation(summary = "Partial update for product", description = "This API allows you to update specific fields of a product.")
	@PatchMapping("/{id}")
	public Product patchProduct(@PathVariable Long id, @RequestBody Product product)
	{
		logger.info("Received request to partially update product with ID {}: {}", id, product); 
        Product patchedProduct = productService.patchProduct(id, product);
        logger.info("Product patched successfully: {}", patchedProduct); 
        return patchedProduct;
	}
}
