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


import com.example.customerproductapi.entity.Product;
import com.example.customerproductapi.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		if (Objects.nonNull(createdProduct)) {

			return ResponseEntity.status(HttpStatus.CREATED).body("Product is created successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Product");
	}

	@GetMapping
	public ResponseEntity<Object> getAllProducts() {
		List<Product> ProductList = productService.getAllProducts();
		if (ProductList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Products found");
		}
		return ResponseEntity.ok(ProductList);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}

	@PatchMapping("/{id}")
	public Product patchProduct(@PathVariable Long id, @RequestBody Product product) {
		return productService.patchProduct(id, product);
	}
}
