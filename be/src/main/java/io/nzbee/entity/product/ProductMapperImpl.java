package io.nzbee.entity.product;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.product.PhysicalProduct;
import io.nzbee.domain.product.ShippingProduct;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.brand.IBrandMapper;
import io.nzbee.entity.category.product.ICategoryProductMapper;
import io.nzbee.entity.product.department.IDepartmentMapper;
import io.nzbee.entity.product.physical.PhysicalProductDTO;
import io.nzbee.entity.product.shipping.ShippingProductDTO;
import io.nzbee.entity.promotion.IPromotionMapper;

@Component(value="productMapper")
public class ProductMapperImpl implements IProductMapper {

	@Autowired
	private IBrandMapper brandMapper;
	
	@Autowired
	private IDepartmentMapper departmentMapper;
	
	@Autowired
	private ICategoryProductMapper categoryProductMapper;
	
	@Autowired
	private IPromotionMapper promotionMapper;
	
	@Override
	public Product DTOToDo(ProductDTO dto) {
		
		if(dto instanceof PhysicalProductDTO) {
			Product pO = new PhysicalProduct(
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
				   	dto.getCategories().stream().map(c -> categoryProductMapper.DTOToDo(c)).collect(Collectors.toList()),
				   	dto.getPromotions().stream().map(promo -> promotionMapper.DTOToDo(promo)).collect(Collectors.toList()));
				   
			return pO;
		}
		if(dto instanceof ShippingProductDTO) {
			Product pO = new ShippingProduct(
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
				   	dto.getCategories().stream().map(c -> categoryProductMapper.DTOToDo(c)).collect(Collectors.toList()),
				   	dto.getPromotions().stream().map(promo -> promotionMapper.DTOToDo(promo)).collect(Collectors.toList()),
				   	new Double(((ShippingProductDTO) dto).getWeightLimit()),
				   	new Double(((ShippingProductDTO) dto).getWeightFrom()),
				   	new Double(((ShippingProductDTO) dto).getWeightTo()),
				   	((ShippingProductDTO) dto).getShippingDestination().getShippingDestinationCode(),
				   	((ShippingProductDTO) dto).getShippingDestination().getShippingDestinationDesc(),
				   	((ShippingProductDTO) dto).getShippingtype().getShippingTypeCode(),
				   	((ShippingProductDTO) dto).getShippingtype().getShippingTypeDesc()
			);
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
