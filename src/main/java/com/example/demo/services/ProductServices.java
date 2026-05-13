package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

@Component
public class ProductServices
{
	@Autowired
	private ProductRepository productRepository;

	public void addProduct(Product p)
	{
		this.productRepository.save(p);
	}

	public List<Product> getAllProducts()
	{
		return (List<Product>) this.productRepository.findAll();
	}

	public Product getProduct(int id)
	{
		Optional<Product> optional = this.productRepository.findById(id);
		return optional.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
	}

	public void updateproduct(Product p, int id)
	{
		p.setPid(id);
		this.productRepository.save(p);
	}

	public void deleteProduct(int id)
	{
		this.productRepository.deleteById(id);
	}

	public Product getProductByName(String name)
	{
		if (name == null || name.trim().isEmpty()) return null;
		return this.productRepository.findByPnameIgnoreCase(name.trim());
	}
}
