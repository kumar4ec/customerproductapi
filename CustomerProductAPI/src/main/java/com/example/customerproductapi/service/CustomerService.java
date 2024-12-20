package com.example.customerproductapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customerproductapi.entity.Customer;
import com.example.customerproductapi.exception.ResourceNotFoundException;
import com.example.customerproductapi.repository.CustomerRepository;

@Service
public class CustomerService
{

	@Autowired
	private CustomerRepository customerRepository;

	public Customer createCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers()
	{
		return customerRepository.findAll();
	}

	/**
	 * 
	 * @param id
	 * @param customer
	 * @return
	 */
	public Customer updateCustomer(Long id, Customer customer)
	{

		customer.setId(id);
		return customerRepository.findById(id).map(existingCustomer -> {
			existingCustomer.setFirstName(customer.getFirstName());
			existingCustomer.setLastName(customer.getLastName());
			existingCustomer.setOfficeEmail(customer.getOfficeEmail());
			existingCustomer.setPersonalEmail(customer.getPersonalEmail());
			existingCustomer.setFamilyMembers(customer.getFamilyMembers());
			existingCustomer.setPhoneNumber(customer.getPhoneNumber());
			existingCustomer.setAddress(customer.getAddress());

			return customerRepository.save(existingCustomer);
		}).orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + id + " not found"));
	}

	/**
	 * 
	 * @param id
	 * @param customer
	 * @return
	 */
	public Customer patchCustomer(Long id, Customer customer)
	{

		customer.setId(id);
		return customerRepository.findById(id).map(existingCustomer -> {
			// Update only the non-null fields in the customer object
			if (customer.getFirstName() != null) {
				existingCustomer.setFirstName(customer.getFirstName());
			}
			if (customer.getLastName() != null) {
				existingCustomer.setLastName(customer.getLastName());
			}
			if (customer.getOfficeEmail() != null) {
				existingCustomer.setOfficeEmail(customer.getOfficeEmail());
			}
			if (customer.getPersonalEmail() != null) {
				existingCustomer.setPersonalEmail(customer.getPersonalEmail());
			}
			if (customer.getFamilyMembers() != null) {
				existingCustomer.setFamilyMembers(customer.getFamilyMembers());
			}
			if (customer.getPhoneNumber() != null) {
				existingCustomer.setPhoneNumber(customer.getPhoneNumber());
			}
			if (customer.getAddress() != null) {
				existingCustomer.setAddress(customer.getAddress());
			}

			return customerRepository.save(existingCustomer);
		}).orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + id + " not found"));
	}
}
