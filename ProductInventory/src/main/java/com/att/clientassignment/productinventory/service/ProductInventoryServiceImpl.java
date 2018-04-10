package com.att.clientassignment.productinventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.clientassignment.productinventory.model.Product;
import com.att.clientassignment.productinventory.repository.ProductInventoryRepository;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {
	
	@Autowired
	private ProductInventoryRepository productInventoryRepository;

	@Override
	public void addProduct(Product addProduct) {
		productInventoryRepository.addProduct(addProduct);
	}

	@Override
	public void updateProduct(Product updateProduct) {
		productInventoryRepository.updateProduct(updateProduct);

	}

	@Override
	public Product getProductById(Long productId) {
		return productInventoryRepository.getProductById(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		return productInventoryRepository.getAllProducts();
	}

	@Override
	public void deleteByProductId(Long productId) {
		productInventoryRepository.deleteByProductId(productId);


	}

	@Override
	public boolean isProductExists(Product existingProduct) {
		return productInventoryRepository.isProductExists(existingProduct);
	}

}
