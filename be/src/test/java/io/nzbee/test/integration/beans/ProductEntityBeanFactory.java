package io.nzbee.test.integration.beans;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.basic.ProductBasic;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.Tag;

@Service(value = "productEntityBeanFactory")
@Profile(value = "tst")
public class ProductEntityBeanFactory {
	
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
	private ICategoryService categoryService;
	
	@Autowired
	private ITagService tagService;
	
	public final ProductEntity getProductEntityBean() {
	
		ProductBasic product = new ProductBasic();
		product.setProductCreateDt(LocalDateTime.now());
		product.setUPC("123456789");
		
		ProductAttributeEntity paEng = new ProductAttributeEntity();
		paEng.setProductDesc("test product");
		paEng.setProductImage("testpath/");
		paEng.setLclCd("en-GB");
		paEng.setProduct(product);
		product.addProductAttribute(paEng);
		
		ProductAttributeEntity paCn = new ProductAttributeEntity();
		paCn.setProductDesc("測試產品");
		paEng.setProductImage("testpath/");
		paCn.setLclCd("zh-HK");
		paCn.setProduct(product);
		product.addProductAttribute(paCn);

		ProductPriceType ppt = productPriceTypeService.findByCode("RET01").get();
		Currency currHKD = currencyService.findByCode("HKD").get();
		Currency currUSD = currencyService.findByCode("USD").get();
		ProductPrice priceHKD = new ProductPrice();
		ProductPrice priceUSD = new ProductPrice();
		priceHKD.setType(ppt);
		priceUSD.setType(ppt);
		priceHKD.setCurrency(currHKD);
		priceHKD.setPriceValue(new Double(78));
		priceUSD.setCurrency(currUSD);
		priceUSD.setPriceValue(new Double(7.8));
		product.getPrices().add(priceHKD);
		product.getPrices().add(priceUSD);
		priceHKD.setProduct(product);
		priceUSD.setProduct(product);
		
		//we need a brand
		product.setBrand(brandService.findByCode(Constants.localeENGB, 
												 "PLA01").get());
				
				
		//we need a type
		product.setDepartment(departmentService.findByCode(	Constants.localeENGB, 
				 											"ACC01").get());
				
		//we need a status
		product.setProductStatus(productStatusRepository.findByProductStatusCode("ACT01").get());
				
		//we need a category
		CategoryProduct cpf = (CategoryProduct) categoryService.findByCode(Constants.localeENGB, 
																		  "POM01").get();
				
		CategoryProduct cpv = (CategoryProduct) categoryService.findByCode(Constants.localeENGB, 
				  														  "CIT01").get();
		
		//add the category to the product
		product.addProductCategory(cpf);
		product.addProductCategory(cpv);
		product.setPrimaryCategory(cpf);
		
		//we should add a tag
		Tag t = tagService.findByCode(	Constants.localeENGB, 
										"ORG01").get();
		
		product.addTag(t);
		
		return product;
	}
	
	
}
