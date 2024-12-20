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

import com.example.customerproductapi.entity.Customer;
import com.example.customerproductapi.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
		Customer createdCustomer = customerService.createCustomer(customer);
		if (Objects.nonNull(createdCustomer)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Customer is created successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create customer");
	}

	@GetMapping
	public ResponseEntity<Object> getAllCustomers() {
		List<Customer> customersList = customerService.getAllCustomers();
		if (customersList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customers found");
		}
		return ResponseEntity.ok(customersList);
	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		return customerService.updateCustomer(id, customer);
	}

	@PatchMapping("/{id}")
	public Customer patchCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		return customerService.patchCustomer(id, customer);
	}
}
