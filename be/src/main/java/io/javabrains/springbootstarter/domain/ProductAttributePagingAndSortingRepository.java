package io.javabrains.springbootstarter.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductAttributePagingAndSortingRepository extends PagingAndSortingRepository<ProductAttribute, Long> {

	Page<ProductAttribute> findByLclCd(String lcl, Pageable pageable);

}
