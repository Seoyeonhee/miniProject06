package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.domain.Product;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public ProductServiceImpl() {
		productDao = new ProductDaoImpl();
	}

	public void addProduct(Product product) throws Exception {
		productDao.insertProduct(product);
	}

	public Product getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}


	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}

	public Map<String, Object> getProductList(Search search, String role) throws Exception {
		System.out.println("ProductServiceImpl�� getProductList ����");
		List<Product> list= productDao.getProductList(search, role);
		System.out.println("ProductServiceImpl : list => " + list);
		int totalCount = productDao.getTotalCount(search);
		System.out.println("ProductServiceImpl : totalCount => " + totalCount);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
}
