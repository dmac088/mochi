package io.nzbee.dto.brand;


import java.util.List;
import java.util.Optional;

import io.nzbee.dto.category.Category;
import io.nzbee.dto.product.Product;
import io.nzbee.dto.tag.Tag;

public interface IBrandService {
	
	Optional<Brand> findOne(String lcl, Long brandId);
	
	Optional<Brand> findOneByCode(String lcl, String brandCode);
	
	Optional<Brand> findOneByDesc(String lcl, String brandDesc);
	
	List<Brand> findAll(String lcl);

	List<Brand> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Tag> tags);

	Brand converToBrandDO (io.nzbee.entity.brand.Brand brand, String locale);

	Optional<Brand> findOne(Product product);

	Brand convertToBrandDO(String brandCode, String brandDesc);

	List<Brand> findAll(String category, String lcl);

}
