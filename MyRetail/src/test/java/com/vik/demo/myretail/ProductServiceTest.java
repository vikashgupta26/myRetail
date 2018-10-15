package com.vik.demo.myretail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.stub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vik.demo.myretail.dao.ProductDao;
import com.vik.demo.myretail.exception.MyRetailProductException;
import com.vik.demo.myretail.model.ProductDetails;
import com.vik.demo.myretail.model.ProductVO;
import com.vik.demo.myretail.service.ProductService;
import com.vik.demo.myretail.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	private ProductDao productDao;

	@InjectMocks
	private ProductService productService = new ProductServiceImpl();

	private ProductVO returnOneProduct() {
		ProductVO product = new ProductVO();
		product.setPrice(43.20f);
		product.setCurrCode("USD");
		product.setId(23l);
		ProductDetails productDetails = new ProductDetails();
		productDetails.setProductDescription("This is a new test product description ");
		productDetails.setProductName("This is a new test product");
		product.setProductDetails(productDetails);
		return product;
	}

	@Test
	public void testGetProduct() {
		try {
			stub(productDao.getProduct(anyLong())).toReturn(returnOneProduct());
			assertEquals(returnOneProduct().getId(), productService.getProduct(45l).getId());
		} catch (MyRetailProductException e) {
			assertFalse(true);
		}
	}

	@Test
	public void testAddProduct() {
		productService.addProduct(returnOneProduct());
		assertTrue(true);
	}

	@Test
	public void testUpdateProduct() {
		try {
			ProductVO product = returnOneProduct();
			stub(productDao.updateProduct(product)).toReturn(product);
			assertEquals(product.getId(), productService.updateProduct(product).getId());
		} catch (MyRetailProductException e) {
			assertFalse(true);
		}
	}

	@Test
	public void testDeleteProduct() {
		try {
			productService.deleteProduct(23l);
			assertTrue(true);
		} catch (MyRetailProductException e) {
			assertTrue(false);
		}
	}

}
