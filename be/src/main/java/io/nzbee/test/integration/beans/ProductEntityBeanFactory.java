package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.type.ProductType;
import io.nzbee.variables.GeneralVars;

@Service(value = "productEntityBeanFactory")
@Profile(value = "dev")
public class ProductEntityBeanFactory {

	@Bean
	public final Product getProductEntityBean() {
		
		Product product = new Product();
		product.setProductCreateDt(new Date());
		product.setUPC("123456789");
		
		ProductType productType = new ProductType();
		productType.setCode("TST01");
		productType.setDesc("test product type");
		product.setProductType(productType);
		
		
//		ProductPrice retailPrice = new ProductPrice();
//		ProductPrice markdownPrice = new ProductPrice();
		
//		Currency currency = new Currency();
//		currency.setCode("HKD");
//		
		Brand brand = new Brand();
		brand.setCode("TST01");
		product.setBrand(brand);
		
//		BrandAttribute brandAttribute = new BrandAttribute();
//		brandAttribute.setBrandDesc("test brand description");
//		brandAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
//		brand.getAttributes().add(brandAttribute);
		
//		brand.getProducts().add(product);
//		
//		CategoryAttribute categoryAttribute = new CategoryAttribute();
//		categoryAttribute.setCategoryDesc("test category attribute");
//		categoryAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
//		
//		CategoryProduct categoryProduct = new CategoryProduct();
//		categoryProduct.setCategoryCode("TST01");
//		categoryProduct.setCategoryLevel(new Long(1));
//		categoryProduct.getAttributes().add(categoryAttribute);
//		product.addProductCategory(categoryProduct);
//		
//		ProductAttribute productAttribute = new ProductAttribute();
//		productAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
//		productAttribute.setProductDesc("test product description");
//		product.getAttributes().add(productAttribute);
//		product.addProductCategory(categoryProduct);
//		
//		retailPrice.setPriceValue(new Double(10));
//		retailPrice.setCurrency(currency);
//		
//		markdownPrice.setPriceValue(new Double(7));
		
		return product;
	}
	
}
