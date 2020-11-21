package io.nzbee.dto.product;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;

@Component
public class ProductDTOLightMapperImpl implements IProductDTOLightMapper {

	@Override
	public ProductDTOLight doToDto(Product d) {
		ProductDTOLight pdto = new ProductDTOLight();
		
		//brand
		pdto.setBrandDesc(d.getBrand().getBrandDesc());
		
		//product 
		pdto.setProductUPC(d.getProductUPC());
		pdto.setProductDesc(d.getProductDesc());
		pdto.setProductLongDesc(d.getProductLongDesc());
		pdto.setProductMarkdown(d.getProductMarkdown());
		pdto.setProductRetail(d.getProductRetail());
		pdto.setLocale(d.getLclCd());
		pdto.setCurrency(d.getCurrency());
		pdto.setProductType(d.getProductType());
		pdto.setInStock(d.isInStock());
		String categories = String.join(",", d.getCategories().stream().map(c -> c.getCategoryDesc()).collect(Collectors.toList()));
		pdto.setCategoriesList(categories);
		
		return pdto;
	}

	@Override
	public Product dtoToDo(ProductDTOLight dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
