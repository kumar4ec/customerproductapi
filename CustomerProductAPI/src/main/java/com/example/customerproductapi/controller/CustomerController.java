package com.example.customerproductapi.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.LoggerFactory;
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

import com.example.customerproductapi.entity.Customer;
import com.example.customerproductapi.service.CustomerService;
import org.slf4j.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/customers")
public class CustomerController
{

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;

	@Operation(summary = "Create a new customer", description = "This API allows you to create a new customer in the system.")
	@ApiResponse(responseCode = "201", description = "Customer created successfully", content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "500", description = "Failed to create customer")
	@PostMapping
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer)
	{
		logger.info("Received request to create customer: {}", customer.getFirstName());

		Customer createdCustomer = customerService.createCustomer(customer);

		if (Objects.nonNull(createdCustomer)) {
			logger.info("Customer created successfully: {}", createdCustomer.getFirstName());
			return ResponseEntity.status(HttpStatus.CREATED).body("Customer is created successfully");
		}

		logger.error("Failed to create customer: {}", customer.getFirstName());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create customer");
	}

	@Operation(summary = "Get all customers", description = "This API retrieves all customers from the system.")
	@ApiResponse(responseCode = "200", description = "List of customers", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)))
	@ApiResponse(responseCode = "404", description = "No customers found")
	@GetMapping
	public ResponseEntity<Object> getAllCustomers()
	{
		logger.info("Received request to fetch all customers.");

		List<Customer> customersList = customerService.getAllCustomers();

		if (customersList.isEmpty()) {
			logger.warn("No customers found in the system."); // Log if no customers found
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customers found");
		}

		logger.info("Returning list of {} customers.", customersList.size()); // Log number of customers returned
		return ResponseEntity.ok(customersList);
	}

	@Operation(summary = "Update customer information", description = "This API allows you to update customer details.")
	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer)
	{
		logger.info("Received request to update customer with ID: {}", id);
		Customer updatedCustomer = customerService.updateCustomer(id, customer);
		logger.info("Customer with ID: {} updated successfully.", id); // Log success
		return updatedCustomer;
	}

	@Operation(summary = "Partial update for customer", description = "This API allows you to update specific fields of a customer.")
	@PatchMapping("/{id}")
	public Customer patchCustomer(@PathVariable Long id, @RequestBody Customer customer)
	{
		logger.info("Received request for partial update of customer with ID: {}", id); 
		Customer patchedCustomer = customerService.patchCustomer(id, customer);
		logger.info("Customer with ID: {} patched successfully.", id); 
		return patchedCustomer;
	}
}
