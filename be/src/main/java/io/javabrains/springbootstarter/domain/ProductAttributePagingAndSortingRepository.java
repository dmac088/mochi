package io.javabrains.springbootstarter.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductAttributePagingAndSortingRepository extends PagingAndSortingRepository<ProductAttribute, Long> {

	Page<ProductAttribute> findByLclCd(String lcl, Pageable pageable);

	Page<ProductAttribute> findByLclCdAndProductCategoriesCategoryId(String lcl, Long categoryId, Pageable pageable);
	
	Page<ProductAttribute> findDistinctByLclCdAndProductCategoriesCategoryIdIn(String lcl, List<Long> categoryIds, Pageable pageable);
	
	Page<ProductAttribute> findDistinctByLclCdAndProductCategoriesCategoryIdInAndProductBrandBrandAttributesBrandDesc(String lcl, List<Long> categoryIds, String brandDesc, Pageable pageable);
	
}
