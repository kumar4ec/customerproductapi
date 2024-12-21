package com.example.customerproductapi;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.customerproductapi.entity.Customer;
import com.example.customerproductapi.repository.CustomerRepository;
import com.example.customerproductapi.service.CustomerService;
import com.example.customerproductapi.exception.ResourceNotFoundException;

@SpringBootTest
public class CustomerServiceTest
{

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerService customerService;

	private Customer customer;

	@BeforeEach
	public void setUp()
	{
		// Initialize mocks
		MockitoAnnotations.openMocks(this);

		
		customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setOfficeEmail("john.doe@example.com");
		customer.setPersonalEmail("john.doe@gmail.com");
		customer.setPhoneNumber("1234567890");
		customer.setAddress("123 Main St");
	}

	@Test
	public void testCreateCustomer()
	{

		when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		Customer createdCustomer = customerService.createCustomer(customer);

		// Assert
		assertNotNull(createdCustomer);
		assertEquals("John", createdCustomer.getFirstName());
		assertEquals("Doe", createdCustomer.getLastName());

		verify(customerRepository, times(1)).save(any(Customer.class));
	}

	@Test
	public void testGetAllCustomers()
	{

		when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

		List<Customer> customers = customerService.getAllCustomers();

		// Assert
		assertNotNull(customers);
		assertFalse(customers.isEmpty());
		assertEquals(1, customers.size());

		verify(customerRepository, times(1)).findAll(); // Verify the findAll method was called
	}

	@Test
	public void testUpdateCustomer()
	{

		Customer updatedCustomer = new Customer();
		updatedCustomer.setFirstName("Updated");
		updatedCustomer.setLastName("Name");

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

		Customer result = customerService.updateCustomer(1L, updatedCustomer);

		// Assert
		assertNotNull(result);
		assertEquals("Updated", result.getFirstName());
		assertEquals("Name", result.getLastName());

		verify(customerRepository, times(1)).findById(1L); // Verify the findById method was called
		verify(customerRepository, times(1)).save(any(Customer.class)); // Verify the save method was called
	}

	@Test
	public void testUpdateCustomerThrowsException()
	{

		when(customerRepository.findById(1L)).thenReturn(Optional.empty()); // Customer not found

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			customerService.updateCustomer(1L, customer);
		});

		assertEquals("Customer with ID 1 not found", exception.getMessage());

		verify(customerRepository, times(1)).findById(1L);
	}

	@Test
	public void testPatchCustomer()
	{
		// Arrange
		Customer patchCustomer = new Customer();
		patchCustomer.setFirstName("Patched");

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		when(customerRepository.save(any(Customer.class))).thenReturn(patchCustomer);

		// Act
		Customer result = customerService.patchCustomer(1L, patchCustomer);

		// Assert
		assertNotNull(result);
		assertEquals("Patched", result.getFirstName());

		verify(customerRepository, times(1)).findById(1L); // Verify the findById method was called
		verify(customerRepository, times(1)).save(any(Customer.class)); // Verify the save method was called
	}

	@Test
	public void testPatchCustomerThrowsException()
	{

		when(customerRepository.findById(1L)).thenReturn(Optional.empty()); // Customer not found

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			customerService.patchCustomer(1L, customer);
		});

		assertEquals("Customer with ID 1 not found", exception.getMessage());

		verify(customerRepository, times(1)).findById(1L); // Verify the findById method was called
	}
}
