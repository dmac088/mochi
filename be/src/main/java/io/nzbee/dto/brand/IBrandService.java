package io.nzbee.dto.brand;


import java.util.List;
import io.nzbee.dto.ILocalizedService;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.tag.Tag;

public interface IBrandService extends ILocalizedService<Brand> {

	List<Brand> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Tag> tags);

	List<Brand> findByCategory(String locale, String currency, String code);

}
