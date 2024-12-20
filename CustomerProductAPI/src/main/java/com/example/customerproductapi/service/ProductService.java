package com.example.customerproductapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customerproductapi.entity.Product;
import com.example.customerproductapi.exception.ResourceNotFoundException;
import com.example.customerproductapi.repository.ProductRepository;

@Service
public class ProductService
{

	@Autowired
	private ProductRepository productRepository;

	/**
	 * 
	 * @param product
	 * @return
	 */
	public Product createProduct(Product product)
	{
		return productRepository.save(product);
	}

	/**
	 * 
	 * @return
	 */
	public List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}

	/**
	 * 
	 * @param id
	 * @param product
	 * @return
	 */
	public Product updateProduct(Long id, Product product)
	{
		product.setId(id);
		// Check if the product exists in the database
		return productRepository.findById(id).map(existingProduct -> {
			// Update all fields with the values from the product request
			existingProduct.setBookTitle(product.getBookTitle());
			existingProduct.setBookPrice(product.getBookPrice());
			existingProduct.setBookQuantity(product.getBookQuantity());
			existingProduct.setCategory(product.getCategory());
			existingProduct.setPublishDate(product.getPublishDate());

			return productRepository.save(existingProduct);
		}).orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
	}

	/**
	 * 
	 * @param id
	 * @param product
	 * @return
	 */
	public Product patchProduct(Long id, Product product)
	{
		product.setId(id);
		return productRepository.findById(id).map(existingProduct -> {
			// Update the product with the new values
			if (product.getBookTitle() != null) {
				existingProduct.setBookTitle(product.getBookTitle());
			}
			if (product.getBookPrice() != 0) {
				existingProduct.setBookPrice(product.getBookPrice());
			}
			if (product.getBookQuantity() != 0) {
				existingProduct.setBookQuantity(product.getBookQuantity());
			}
			if (product.getCategory() != null) {
				existingProduct.setCategory(product.getCategory());
			}
			if (product.getPublishDate() != null) {
				existingProduct.setPublishDate(product.getPublishDate());
			}
			// Save and return updated product
			return productRepository.save(existingProduct);
		}).orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
	}
}
