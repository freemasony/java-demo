package com.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Dao.ProductDao;
import com.entity.Product;

@Component("productService")
public class ProductService {

	@Autowired(required = true)
	private ProductDao productdao;

	public Product findOneByProductId(Long productId) {
		return productdao.findOneByProductId(productId);
	}
	
	public Product findOne(Long id){
		return productdao.findOne(id);
	}
 

	public void update(Product product) {
		// TODO Auto-generated method stub
		productdao.save(product);
	}

}
