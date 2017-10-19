package com.Dao;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.entity.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product>{

	Product findOneByProductId(Long productId);

}
