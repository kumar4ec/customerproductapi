package com.example.customerproductapi.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String bookTitle;
	private double bookPrice;
	private int bookQuantity;

	private String category;
	private LocalDate publishDate;
	
	public Product()
	{
		
	}

	// Constructor with all fields
	public Product(String bookTitle, double bookPrice, int bookQuantity, String category, String publishDate)
	{
		this.bookTitle = bookTitle;
		this.bookPrice = bookPrice;
		this.bookQuantity = bookQuantity;
		this.category = category;
		this.publishDate = LocalDate.parse(publishDate); // Convert String to LocalDate
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getBookTitle()
	{
		return bookTitle;
	}

	public void setBookTitle(String bookTitle)
	{
		this.bookTitle = bookTitle;
	}

	public double getBookPrice()
	{
		return bookPrice;
	}

	public void setBookPrice(double bookPrice)
	{
		this.bookPrice = bookPrice;
	}

	public int getBookQuantity()
	{
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity)
	{
		this.bookQuantity = bookQuantity;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public LocalDate getPublishDate()
	{
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate)
	{
		this.publishDate = publishDate;
	}
}
