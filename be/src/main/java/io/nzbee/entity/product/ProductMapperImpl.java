package io.nzbee.entity.product;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.entity.brand.IBrandMapper;
import io.nzbee.entity.category.ICategoryMapper;
import io.nzbee.entity.product.basic.ProductBasicEntity;
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
													ProductEntity e, 
													Brand brand, 
													Department department, 
													ProductCategory category) {
		
		if(e instanceof ProductBasicEntity) {
			io.nzbee.domain.product.Product pO = new io.nzbee.domain.product.BasicProduct(
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
				   	e.isInStock(),
				   	brand,
				   	department,
				   	category,
				   	e.getCategories().stream().map(c -> (ProductCategory) categoryMapper.entityToDo(c)).collect(Collectors.toSet()));
				   
			return pO;
		}
		return null;
	}

	@Override
	public io.nzbee.domain.product.Product entityToDo(ProductEntity e) {
		// TODO Auto-generated method stub
		if(e instanceof ProductBasicEntity) {
			io.nzbee.domain.product.Product pO = 
				new io.nzbee.domain.product.BasicProduct(
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
				   	e.isInStock(),
				   	brandMapper.entityToDo(e.getBrand()),
				   	departmentMapper.entityToDo(e.getDepartment()),
				   	(ProductCategory) categoryMapper.entityToDo(e.getPrimaryCategory()),
				    e.getCategories().stream().map(c -> (ProductCategory) categoryMapper.entityToDo(c)).collect(Collectors.toSet()));
			return pO;
		}
		return null;
	}

	@Override
	public ProductEntity doToEntity(io.nzbee.domain.product.Product d) {
		// TODO Auto-generated method stub
		return null;
	}
}
