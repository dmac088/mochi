package io.nzbee.dto.brand;


import java.util.List;
import io.nzbee.dto.ILocalizedService;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.tag.Tag;

public interface IBrandService extends ILocalizedService<io.nzbee.dto.brand.Brand,
														 io.nzbee.entity.brand.Brand,
														 io.nzbee.domain.brand.Brand> {

	List<Brand> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Tag> tags);

	List<Brand> findAll(String locale, String currency, String code);

}
