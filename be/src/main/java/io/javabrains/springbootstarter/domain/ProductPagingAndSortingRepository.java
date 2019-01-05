package io.javabrains.springbootstarter.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPagingAndSortingRepository extends PagingAndSortingRepository<Product, Long> {

	Page<Product> findAll(Pageable pageable);
	
	Page<Product> findByLclCd(String lcl, Pageable pageable);
	
	Page<Product> findByLclCdAndCategoriesCategoryId(String lcl, Long productCategoryId, Pageable pageable);
}
