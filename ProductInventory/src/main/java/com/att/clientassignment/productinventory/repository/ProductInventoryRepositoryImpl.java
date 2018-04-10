package com.att.clientassignment.productinventory.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.att.clientassignment.productinventory.model.Product;

@Repository
public class ProductInventoryRepositoryImpl implements ProductInventoryRepository {

	private static final AtomicLong generatedProductId = new AtomicLong();
	static List<Product> products = new ArrayList<Product>(Arrays.asList());
	
	/*[
	    {
	        "productId": 1,
	        "productName": "Redtape",
	        "productDescription": "Redtape is a shoe company",
	        "displayItems": {
	            "propertyNames": [
	                "Red Color",
	                "Casual Shoe"
	            ]
	        },
	        "productImage": "pic1.png"
	    },
	    {
	        "productId": 2,
	        "productName": "Nike",
	        "productDescription": "Nike is a best brand",
	        "displayItems": {
	            "propertyNames": [
	                "Blue Color",
	                "Sports Shoe"
	            ]
	        },
	        "productImage": "pic2.png"
	    }
	]*/

	@Override
	public void addProduct(Product product) {
		if (product.getProductId() == 0) {
			product.setProductId(generatedProductId.incrementAndGet());
		} else {
			product.setProductId(product.getProductId());
		}
		products.add(product);

	}

	@Override
	public void updateProduct(Product product) {
		int index = products.indexOf(product);
		products.set(index, product);

	}

	@Override
	public Product getProductById(Long productId) {
		for (Product product : products) {
			if (product.getProductId() == productId) {
				return product;
			}
		}
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		return products;
	}

	@Override
	public void deleteByProductId(Long productId) {
		Product product =  getProductById(productId);
		products.remove(product);
	}

	@Override
	public boolean isProductExists(Product existingProduct) {
		return findByProductName(existingProduct.getProductName()) != null;
	}

	public Product findByProductName(String productName) {
		for (Product product : products) {
			if (product.getProductName() == productName) {
				return product;
			}
		}
		return null;
	}

}
