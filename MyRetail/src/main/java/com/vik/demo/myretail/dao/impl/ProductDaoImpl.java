package com.vik.demo.myretail.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.vik.demo.myretail.dao.ProductDao;
import com.vik.demo.myretail.exception.MyRetailProductException;
import com.vik.demo.myretail.model.Product;
import com.vik.demo.myretail.model.ProductDetails;
import com.vik.demo.myretail.model.ProductVO;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	private static final String PRODUCT_NOT_FOUND = "Product Not Found";
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public ProductVO getProduct(Long id) throws MyRetailProductException {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		if (product == null) {
			throw new MyRetailProductException(PRODUCT_NOT_FOUND, new Exception(), HttpStatus.NOT_FOUND);
		}
		ProductVO productVO = new ProductVO();
		BeanUtils.copyProperties(product, productVO);

		Query query = new Query();
		query.addCriteria(Criteria.where("prodId").in(productVO.getId()+""));
		ProductDetails productDetails = mongoTemplate.findOne(query, ProductDetails.class);
		productDetails.setProdId(null);
		productVO.setProductDetails(productDetails);
		return productVO;
	}

	public void addProduct(ProductVO productVO) {
		Product product = new Product();
		BeanUtils.copyProperties(productVO, product);
		Session session = sessionFactory.getCurrentSession();
		session.save(product);
		
		ProductDetails productDetails = new ProductDetails();
		BeanUtils.copyProperties(productVO.getProductDetails(), productDetails);
		productDetails.setProdId(productVO.getId()+"");
		mongoTemplate.save(productDetails);
	}

	public ProductVO updateProduct(ProductVO productVO) throws MyRetailProductException {
		Product productUI = new Product();
		BeanUtils.copyProperties(productVO, productUI);
		Session session = sessionFactory.getCurrentSession();
		Product productDB = (Product) session.get(Product.class, productUI.getId());
		if (productDB == null) {
			throw new MyRetailProductException(PRODUCT_NOT_FOUND, new Exception(), HttpStatus.NOT_FOUND);
		}
		BeanUtils.copyProperties(productUI, productDB);
		session.update(productDB);
		return productVO;
	}

	public void deleteProduct(Long id) throws MyRetailProductException {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		if (product == null) {
			throw new MyRetailProductException(PRODUCT_NOT_FOUND, new Exception(), HttpStatus.NOT_FOUND);
		}
		session.delete(product);
		Query query = new Query();
		query.addCriteria(Criteria.where("prodId").in(id+""));
		mongoTemplate.findAndRemove(query, ProductDetails.class);
	}
}
