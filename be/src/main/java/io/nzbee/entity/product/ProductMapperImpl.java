package io.nzbee.entity.product;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.product.BasicProduct;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.brand.IBrandMapper;
import io.nzbee.entity.category.product.ICategoryProductMapper;
import io.nzbee.entity.product.department.IDepartmentMapper;

@Component(value="productMapper")
public class ProductMapperImpl implements IProductMapper {

	@Autowired
	private IBrandMapper brandMapper;
	
	@Autowired
	private IDepartmentMapper departmentMapper;
	
	@Autowired
	private ICategoryProductMapper categoryProductMapper;
	
	@Override
	public Product DTOToDo(ProductDTO dto) {
		
		if(dto instanceof ProductDTO) {
			Product pO = new BasicProduct(
					dto.getProductUPC(),
				   	dto.getProductCreateDt(),
				   	dto.getProductStatusCode(),
				   	dto.getProductDesc(),
				   	dto.getProductLongDesc(),
				   	dto.getRetailPrice(),
				   	dto.getMarkdownPrice(),
				   	dto.getProductImage(),
				   	dto.getLocale(),
				   	dto.getCurrency(),
				   	dto.isInStock(),
				   	brandMapper.DTOToDo(dto.getBrand()),
				   	departmentMapper.DTOToDo(dto.getDepartment()),
				   	categoryProductMapper.DTOToDo(dto.getPrimaryCategory()),
				   	dto.getCategories().stream().map(c -> categoryProductMapper.DTOToDo(c)).collect(Collectors.toSet()));
				   
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
