package com.example.customerproductapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customerproductapi.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{

}
