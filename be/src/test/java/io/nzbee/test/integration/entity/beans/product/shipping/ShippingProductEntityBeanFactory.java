package io.nzbee.test.integration.entity.beans.product.shipping;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.shipping.ShippingProductEntity;
import io.nzbee.entity.product.shipping.destination.IShippingDestinationService;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import io.nzbee.entity.product.shipping.type.IShippingTypeService;
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.IProductStatusRepository;

@Service
@Profile(value = "it")
public class ShippingProductEntityBeanFactory implements IShippingProductEntityBeanFactory {
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired 
	private IProductPriceTypeService productPriceTypeService; 
	
	@Autowired
	private IBrandService brandService;
	    
	@Autowired
	private IDepartmentService departmentService;
	    
	@Autowired
	private IProductStatusRepository productStatusRepository;
	
	@Autowired
	private IShippingDestinationService shippingDestinationService;
	
	@Autowired
	private IShippingTypeService shippingTypeService;
	
	@Autowired
	private ICategoryService categoryService;
	   
	@Override
	public ShippingProductEntity getBean() {
		ShippingProductEntity product = new ShippingProductEntity();
		product.setProductCreateDt(LocalDateTime.now());
		product.setUPC("123456789");
		
		ProductAttributeEntity paEng = new ProductAttributeEntity();
		paEng.setProductDesc( "Test shipping destination description");
		paEng.setProductImage("testpath/");
		paEng.setLclCd(Constants.localeENGB);
		paEng.setProduct(product);
		product.addProductAttribute(paEng);
		
		ProductAttributeEntity paCn = new ProductAttributeEntity();
		paCn.setProductDesc("測試產品");
		paEng.setProductImage("testpath/");
		paCn.setLclCd(Constants.localeZHHK);
		paCn.setProduct(product);
		product.addProductAttribute(paCn);

		ProductPriceType pptRetail = productPriceTypeService.findByCode(Constants.retailPriceCode).get();
		ProductPriceType pptMarkdown = productPriceTypeService.findByCode(Constants.markdownPriceCode).get();
		Currency currHKD = currencyService.findByCode(Constants.currencyHKD).get();
		Currency currUSD = currencyService.findByCode(Constants.currencyUSD).get();
		ProductPriceEntity retailPriceHKD = new ProductPriceEntity();
		ProductPriceEntity retailPriceUSD = new ProductPriceEntity();
		ProductPriceEntity markdownPriceHKD = new ProductPriceEntity();
		ProductPriceEntity markdownPriceUSD = new ProductPriceEntity();
		retailPriceHKD.setType(pptRetail);
		retailPriceUSD.setType(pptRetail);
		markdownPriceHKD.setType(pptMarkdown);
		markdownPriceUSD.setType(pptMarkdown);
		
		retailPriceHKD.setCurrency(currHKD);
		retailPriceHKD.setPriceValue(new Double(60.48));
		retailPriceUSD.setCurrency(currUSD);
		retailPriceUSD.setPriceValue(new Double(7.8));
		
		markdownPriceHKD.setCurrency(currHKD);
		markdownPriceHKD.setPriceValue(new Double(50.00));
		markdownPriceUSD.setCurrency(currUSD);
		markdownPriceUSD.setPriceValue(new Double(6.45));
		
		product.getPrices().add(retailPriceHKD);
		product.getPrices().add(retailPriceUSD);
		product.getPrices().add(markdownPriceHKD);
		product.getPrices().add(markdownPriceUSD);
		
		retailPriceHKD.setProduct(product);
		retailPriceUSD.setProduct(product);
		
		markdownPriceHKD.setProduct(product);    
		markdownPriceUSD.setProduct(product);
		
		//we need a category
		CategoryProductEntity cp = (CategoryProductEntity) categoryService.findByCode("SHP01").get();
				
		//add the category to the product
		product.addProductCategory(cp);
		
		//we need a brand
		product.setBrand(brandService.findByCode("HKP01").get());
				
		//we need a type
		product.setDepartment(departmentService.findByCode("SHP01").get());
				
		//we need a status
		product.setProductStatus(productStatusRepository.findByProductStatusCode("ACT01").get());
		
		product.setWeightLimit(new Double(5));
		product.setWeightFrom(new Double(2));
		product.setWeightTo(new Double(3));
		product.setTrackingLevel(1);
		
		Optional<ShippingDestinationEntity> osd = shippingDestinationService.findByCode("ADAD1");
		Optional<ShippingTypeEntity> ost = shippingTypeService.findByCode("AIR_REG_1");
		
		product.setShippingDestination(osd.get());
		product.setShippingType(ost.get());
		
		return product;
	}
	
	
}
