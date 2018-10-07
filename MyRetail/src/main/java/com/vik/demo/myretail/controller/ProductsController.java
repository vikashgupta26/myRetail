package com.vik.demo.myretail.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vik.demo.myretail.exception.MyRetailProductException;
import com.vik.demo.myretail.model.ProductVO;
import com.vik.demo.myretail.service.ProductService;
import com.vik.demo.myretail.util.CustomError;

@RestController
@RequestMapping("/product")
public class ProductsController {

	@Autowired
	private ProductService productService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);


	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id) {
		LOGGER.info("getProduct start");
		ProductVO product;
		try {
			product = productService.getProduct(id);
		} catch (MyRetailProductException e) {
			CustomError customError = new CustomError(e.getMessage(), e.getCause().getMessage());
			LOGGER.error(e.getMessage()+" ", e);
			return new ResponseEntity<CustomError>(customError, e.getCode());
		}
		LOGGER.info("getProduct end");
		return new ResponseEntity<ProductVO>(product, HttpStatus.OK);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<?> addProduct(@RequestBody ProductVO productVO) {
		productService.addProduct(productVO);
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.setLocation(new URI("product/" + productVO.getId()));
		} catch (URISyntaxException e) {
			LOGGER.error(e.getMessage()+" ", e);
		}
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateUser(@RequestBody ProductVO productVO) {
		try {
			productService.updateProduct(productVO);
		} catch (MyRetailProductException e) {
			CustomError customError = new CustomError(e.getMessage(), e.getCause().getMessage());
			LOGGER.error(e.getMessage()+" ", e);
			return new ResponseEntity<CustomError>(customError, e.getCode());
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		try {
			productService.deleteProduct(id);
		} catch (MyRetailProductException e) {
			CustomError customError = new CustomError(e.getMessage(), e.getCause().getMessage());
			LOGGER.error(e.getMessage()+" ", e);
			return new ResponseEntity<CustomError>(customError, e.getCode());
		}
		 return new ResponseEntity<ProductVO>(HttpStatus.NO_CONTENT);
	}

}
