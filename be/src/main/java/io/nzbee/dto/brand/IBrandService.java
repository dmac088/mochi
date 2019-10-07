package io.nzbee.dto.brand;


import java.util.List;
import java.util.Optional;

import io.nzbee.dto.IService;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.tag.Tag;

public interface IBrandService extends IService<Brand> {

	List<Brand> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Tag> tags);

}
