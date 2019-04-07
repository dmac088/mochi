package io.javabrains.springbootstarter.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductAttributeRepository extends CrudRepository<ProductAttribute, Long> {

	List<ProductAttribute> findAll();

	List<ProductAttribute> findByLclCd(String lcl);
	
	List<ProductAttribute> findDistinctByLclCdAndProductCategoriesCategoryIdInAndProductPreviewFlag(String lcl, List<Long> categoryIds, Long previewFlag);
	
	ProductAttribute findByLclCdAndProductId(String lcl, Long id);

}
