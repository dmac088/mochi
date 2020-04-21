package io.nzbee.entity.product;

import java.util.Optional;

import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.entity.product.food.Food;

@Component(value="productMapper")
public class ProductMapper implements IProductMapper {

	@Override
	public Optional<io.nzbee.domain.product.Product> entityToDo(
													Optional<Product> e, 
													Optional<Brand> brand, 
													Optional<Department> department, 
													Optional<ProductCategory> category) {
		
		if(!e.isPresent() ||
		   !brand.isPresent() ||
		   !department.isPresent() ||
		   !category.isPresent()) {	
			return Optional.ofNullable(null);
		}
		
		Product pe = e.get();
		io.nzbee.domain.product.Product pO = null;
		
		if(pe instanceof Food) {
			pO = new io.nzbee.domain.product.Food(
					pe.getProductUPC(),
				   	pe.getProductCreateDt(),
				   	pe.getProductAttribute().getProductDesc(),
				   	pe.getRetailPrice(),
				   	pe.getMarkdownPrice(),
				   	pe.getImagePath(),
				   	pe.getDisplayCategories(),
				   	((Food) pe).getCountryOfOrigin(),
				   	((Food) pe).getExpiryDate(),
				   	pe.getLocale(),
				   	pe.getCurrency(),
				   	brand.get(),
				   	department.get(),
				   	category.get()
				   	);
		}
		return Optional.ofNullable(pO);
	}
}
