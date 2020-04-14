package io.nzbee.entity.product;
import io.nzbee.domain.category.Category;

import org.springframework.stereotype.Component;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.entity.IMapper;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.food.Food;
import io.nzbee.entity.product.jewellery.Jewellery;

@Component(value="productMapper")
public class ProductMapper implements IMapper<io.nzbee.domain.product.Product, Product> {

	public io.nzbee.domain.product.Product entityToDo(Product e) {
		if(e instanceof Food) {
			return new io.nzbee.domain.product.Food(
					
			);
		}
		return new io.nzbee.domain.product.Jewellery(
			
		);
	}
	
	@Override
	public io.nzbee.domain.product.Product entityToDo(Product e, String locale, String currency) {
		if(e instanceof Food) {
			return new io.nzbee.domain.product.Food(
					
			);
		}
		return new io.nzbee.domain.product.Jewellery(

		);
	}
	
}
