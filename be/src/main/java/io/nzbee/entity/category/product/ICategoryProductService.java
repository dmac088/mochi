package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.category.Category;

public interface ICategoryProductService {

	Optional<CategoryProduct> findById(long id);
	
	List<CategoryProduct> findAll();
	
	List<CategoryProduct> getAll();
	
	List<CategoryProduct> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale);
	
	Optional<CategoryProduct> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale);
	
	Optional<CategoryProduct> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale);
	
	List<CategoryProduct> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale);

	List<CategoryProduct> find(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc,
			List<String> brandCodes, List<String> tagCodes, String locale);

	Set<Category> recurseCategories(Set<Category> arrayList, Category pc);
	
}
