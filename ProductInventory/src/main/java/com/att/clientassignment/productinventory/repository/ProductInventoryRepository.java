package com.att.clientassignment.productinventory.repository;

import java.util.List;

import com.att.clientassignment.productinventory.model.Product;

public interface ProductInventoryRepository {
	
	public void addProduct(Product product);
	public void updateProduct(Product product);
	public Product getProductById(Long productId);
	public List<Product> getAllProducts();
	public void deleteByProductId(Long productId);
	boolean isProductExists(Product existingProduct);

}
