package io.nzbee.domain.product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.services.IService;
import io.nzbee.domain.tag.Tag;

public interface IProductService extends IService<Product> {

	Optional<Product> findOne(String lcl, String currency, String code);

	Page<Product> findAll(String locale, String currency, String categoryDesc, Double price, int page, int size,
			String sortBy, List<Category> categories, List<Brand> brands, List<Tag> tags);

	List<Product> findAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(String locale, String currency, String categoryDesc, int page, int size, String sortBy,
			List<Category> categories, List<Brand> brands, List<Tag> tags);

	Product convertProductDtoToProductDO(io.nzbee.dto.product.Product productDto);

	io.nzbee.dto.product.Product convertProductDOToProductDto(Product productDO);


}
