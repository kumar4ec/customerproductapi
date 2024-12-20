package com.example.customerproductapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customerproductapi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{

}
