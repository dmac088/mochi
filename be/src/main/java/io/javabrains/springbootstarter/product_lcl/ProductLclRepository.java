package io.javabrains.springbootstarter.product_lcl;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface ProductLclRepository extends CrudRepository<ProductLcl, Long> {
	
	ArrayList<ProductLcl> findByProductProductId(Long productId);
	
	ArrayList<ProductLcl> findByProductProductIdAndLclCd(Long productId, String lng);
}
