package io.nzbee.domain.product;

import java.util.List;
import org.springframework.data.domain.Page;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.IService;
import io.nzbee.domain.tag.Tag;

public interface IProductService extends IService<Product> {

	List<Product> findAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(String locale, String currency, int page, int size, String categoryDesc,
			List<Category> categories, List<Brand> brands, List<Tag> tags, String sortBy);

	Page<Product> findAll(String locale, String currency, Double price, int page, int size, String categoryDesc,
			List<Category> categories, List<Brand> brands, List<Tag> tags, String sortBy);



}
