package io.nzbee.dto.tag;

import java.util.List;
import io.nzbee.dto.ILocalizedService;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.category.Category;

public interface ITagService extends ILocalizedService<Tag> {
	
	List<Tag> findAll(String locale, String currency, String categoryDesc, List<Category> categories,
			List<Brand> brands);

	List<Tag> findAll(String locale, String currency, String categoryDesc, Double price, List<Category> categories,
			List<Brand> brands);


}
