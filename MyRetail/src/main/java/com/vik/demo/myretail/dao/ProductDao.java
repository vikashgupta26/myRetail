package com.vik.demo.myretail.dao;

import com.vik.demo.myretail.exception.MyRetailProductException;
import com.vik.demo.myretail.model.ProductVO;

public interface ProductDao {

	public ProductVO getProduct(Long id) throws MyRetailProductException;
	
	public void addProduct(ProductVO productVO);
	
	public ProductVO updateProduct(ProductVO productVO) throws MyRetailProductException;
	
	public void deleteProduct(Long id) throws MyRetailProductException;
}
