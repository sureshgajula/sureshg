package com.att.clientassignment.productinventory.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.att.clientassignment.productinventory.model.Product;
import com.att.clientassignment.productinventory.model.Status;
import com.att.clientassignment.productinventory.service.ProductInventoryService;

@RestController
@RequestMapping("/productInventory")
public class ProductInventoryController {
	private final Logger LOG = LoggerFactory.getLogger(ProductInventoryController.class);

	@Autowired
	private ProductInventoryService productInventoryService;

	@PostMapping(path = "/addProduct", consumes =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> createProduct(@RequestBody Product product) {
		LOG.info("creating new Product: {}", product);
		Status status = new Status();
		if (productInventoryService.isProductExists(product)) {
			LOG.info("A Product with name " + product.getProductName() + " already exists");
			status.setStatusCode(HttpStatus.CONFLICT.value());
			status.setStatusMessage(HttpStatus.CONFLICT.getReasonPhrase());
			status.setStatusDesc("A Product with name " + product.getProductName() + " already exists");
			return new ResponseEntity<Status>(status, HttpStatus.CONFLICT);
		}
		productInventoryService.addProduct(product);
		status.setStatusCode(HttpStatus.CREATED.value());
		status.setStatusMessage(HttpStatus.CREATED.getReasonPhrase());
		status.setStatusDesc("Product Succesfully Added to Inventory..");
		return new ResponseEntity<Status>(status, HttpStatus.CREATED);
	}

	@PutMapping(path ="/updateProductById/{id}", consumes =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> updateProductById(@RequestBody Product product, @PathVariable("id") Long productId) {
		Product existingProduct = productInventoryService.getProductById(productId);
		Status status = new Status();
		if (existingProduct == null) {
			status.setStatusCode(HttpStatus.NOT_FOUND.value());
			status.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			status.setStatusDesc("Product with Id " + product.getProductId() + " not found");
			return new ResponseEntity<Status>(HttpStatus.NOT_FOUND);
		}
		existingProduct.setProductId(product.getProductId());
		existingProduct.setProductName(product.getProductName());
		existingProduct.setProductDescription(product.getProductDescription());
		existingProduct.setProductImage(product.getProductImage());

		productInventoryService.updateProduct(existingProduct);
		status.setStatusCode(HttpStatus.OK.value());
		status.setStatusMessage(HttpStatus.OK.getReasonPhrase());
		status.setStatusDesc("Product Succesfully Updated to Inventory..");
		return new ResponseEntity<Status>(status, HttpStatus.OK);

	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getAllProducts() {
		LOG.info("Getting all Products from Inventory");
		List<Product> allProducts = productInventoryService.getAllProducts();
		Status status = new Status();
		if (allProducts == null || allProducts.isEmpty()) {
			LOG.info("No Products found in Inventory");
			status.setStatusCode(HttpStatus.NO_CONTENT.value());
			status.setStatusMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
			status.setStatusDesc("No products available in Inventory");
			return new ResponseEntity<Status>(status, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);

	}

	@GetMapping("/getProductById/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable Long productId) {
		LOG.info("Getting Product with productId: {} ", productId);
		Product productById = productInventoryService.getProductById(productId);		
		if (productById == null) {
			LOG.info("Product with id " + productId + " not found");
			Status status = new Status();
			status.setStatusCode(HttpStatus.NOT_FOUND.value());
			status.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			status.setStatusDesc("Product with Id " + productId + " not found");
			return new ResponseEntity<Status>(status, HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<Product>(productById, HttpStatus.OK);
	}

	@DeleteMapping("/deleteProductById/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable Long productId) {
		LOG.info("Deleting Product with id: {} ", productId);
		Product deleteProductById = productInventoryService.getProductById(productId);		
		if (deleteProductById == null) {
			LOG.info("Unable to delete. Product with id " + productId + " not found");
			Status status = new Status();
			status.setStatusCode(HttpStatus.NOT_FOUND.value());
			status.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			status.setStatusDesc("Unable to delete. Product with id "+ productId + " not found");
			return new ResponseEntity<Status>(status, HttpStatus.NOT_FOUND);
		}
		productInventoryService.deleteByProductId(productId);
		return new ResponseEntity<Status>(HttpStatus.OK);
	}

}
