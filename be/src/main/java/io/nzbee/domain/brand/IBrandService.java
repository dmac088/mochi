package io.nzbee.domain.brand;

import java.util.List;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.tag.Tag;
import io.nzbee.domain.IService;

public interface IBrandService extends IService<Brand> {

	List<Brand> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Tag> tags);
	
	List<Brand> findAll(String locale, String category);

	Brand converToBrandDO (io.nzbee.entity.brand.Brand brand, String locale);

	Brand convertToBrandDO(String brandCode, String brandDesc);

}
