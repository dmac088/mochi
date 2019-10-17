package io.nzbee.domain.category;


import java.util.List;
import io.nzbee.domain.IService;


public interface ICategoryService extends IService<Category> {

	List<Category> findAll(String locale, String currency);

	List<Category> findByParent(String locale, String currency, String parentCategoryCode);

	List<Category> findAllForLevel(String locale, String currency, Long level);

	
}
