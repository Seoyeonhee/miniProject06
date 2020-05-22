package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	/// Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/// Constructor
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public void insertProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct", product);
	}
	
	public Product getProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}
	
	public List<Product> getProductList(Search search, String role) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("role", role);
		
		System.out.println("map : " + map);
		List<Product> list = sqlSession.selectList("ProductMapper.getProductList", map);
		System.out.println(list);
		//return sqlSession.selectList("ProductMapper.getProductList", search);
		return list;
	}
	
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
}
