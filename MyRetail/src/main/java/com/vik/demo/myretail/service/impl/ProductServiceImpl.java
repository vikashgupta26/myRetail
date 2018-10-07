package com.vik.demo.myretail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vik.demo.myretail.dao.ProductDao;
import com.vik.demo.myretail.exception.MyRetailProductException;
import com.vik.demo.myretail.model.ProductVO;
import com.vik.demo.myretail.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Override
	public ProductVO getProduct(Long id)  throws MyRetailProductException {
		return productDao.getProduct(id);
	}
	
	public void addProduct(ProductVO productVO) {
		productDao.addProduct(productVO);
	}

	@Override
	public ProductVO updateProduct(ProductVO productVO) throws MyRetailProductException {
		return productDao.updateProduct(productVO);
	}

	@Override
	public void deleteProduct(Long id) throws MyRetailProductException {
		productDao.deleteProduct(id);
	}

}
