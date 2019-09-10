package io.nzbee.domain.tag;

import java.util.List;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.services.IService;

public interface ITagService extends IService<Tag> {
	
	Tag findOneById(Long id, String lcl);

	Tag findOneByCode(String code, String lcl);

	Tag findOneByDesc(String desc, String lcl);

	List<Tag> findAll(String locale, String currency, String categoryDesc, List<Category> categories,
			List<Brand> brands);

	List<Tag> findAll(String locale, String currency, String categoryDesc, Double price, List<Category> categories,
			List<Brand> brands);


}
