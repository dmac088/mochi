package io.nzbee.entity.product;

import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.entity.product.accessories.Accessories;

@Component(value="productMapper")
public class ProductMapper implements IProductMapper {

	@Override
	public io.nzbee.domain.product.Product entityToDo(
													Product e, 
													Brand brand, 
													Department department, 
													ProductCategory category) {
		
		
		
		
		if(e instanceof Accessories) {
			io.nzbee.domain.product.Product pO = new io.nzbee.domain.product.Accessories(
					e.getProductUPC(),
				   	e.getProductCreateDt(),
				   	e.getProductStatus().getCode(),
				   	e.getProductAttribute().getProductDesc(),
				   	e.getProductAttribute().getProductLongDesc(),
				   	e.getRetailPrice(),
				   	e.getMarkdownPrice(),
				   	e.getProductAttribute().getProductImage(),
				   	e.getLocale(),
				   	e.getCurrency(),
				   	brand,
				   	department,
				   	category
				   	);
			return pO;
		}
		return null;
	}

	@Override
	public io.nzbee.domain.product.Product entityToDo(Product e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product doToEntity(io.nzbee.domain.product.Product d) {
		// TODO Auto-generated method stub
		return null;
	}
}
