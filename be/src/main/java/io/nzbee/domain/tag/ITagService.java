package io.nzbee.domain.tag;

import java.util.List;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.IService;

public interface ITagService extends IService<Tag> {

	List<Tag> findAll(String locale, String currency, String categoryDesc, List<Category> categories,
			List<Brand> brands);

	List<Tag> findAll(String locale, String currency, String categoryDesc, Double price, List<Category> categories,
			List<Brand> brands);


}
