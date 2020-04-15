package io.nzbee.entity.product;

import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.department.Department;
import io.nzbee.entity.product.food.Food;

@Component(value="productMapper")
public class ProductMapper implements IProductMapper {

	@Override
	public io.nzbee.domain.product.Product entityToDo(Product e, Brand brand, Department deprartment) {
		
		if(e instanceof Food) {
			return new io.nzbee.domain.product.Food(
					e.getProductUPC(),
				   	e.getProductCreateDt(),
				   	e.getProductAttribute().getProductDesc(),
				   	e.getRetailPrice(),
				   	e.getMarkdownPrice(),
				   	e.getImagePath(),
				   	e.getDisplayCategories(),
				   	((Food) e).getCountryOfOrigin(),
				   	((Food) e).getExpiryDate(),
				   	e.getLocale(),
				   	e.getCurrency(),
				   	brand,
				   	deprartment);
		}
		return null;
	}



}
