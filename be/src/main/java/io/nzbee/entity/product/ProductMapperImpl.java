package io.nzbee.entity.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.entity.brand.IBrandMapper;
import io.nzbee.entity.category.ICategoryMapper;
import io.nzbee.entity.product.accessories.Accessories;
import io.nzbee.entity.product.department.IDepartmentMapper;

@Component(value="productMapper")
public class ProductMapperImpl implements IProductMapper {

	@Autowired
	private IBrandMapper brandMapper;
	
	@Autowired
	private ICategoryMapper categoryMapper;
	
	@Autowired
	private IDepartmentMapper departmentMapper;
	
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
		if(e instanceof Accessories) {
			io.nzbee.domain.product.Product pO = 
				new io.nzbee.domain.product.Accessories(
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
				   	brandMapper.entityToDo(e.getBrand()),
				   	departmentMapper.entityToDo(e.getDepartment()),
				   	(ProductCategory) categoryMapper.entityToDo(e.getPrimaryCategory()));
			return pO;
		}
		return null;
	}

	@Override
	public Product doToEntity(io.nzbee.domain.product.Product d) {
		// TODO Auto-generated method stub
		return null;
	}
}
