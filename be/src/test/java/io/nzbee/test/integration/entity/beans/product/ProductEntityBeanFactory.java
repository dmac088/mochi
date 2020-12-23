package io.nzbee.test.integration.entity.beans.product;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.Constants;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.physical.PhysicalProductEntity;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagEntity;

@Service(value = "productEntityBeanFactory")
@Profile(value = "tst")
public class ProductEntityBeanFactory implements IProductEntityBeanFactory {
	
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
	private IPromotionService promotionService;
	
	@Autowired
	private ITagService tagService;

	@Override
	public ProductEntity getBean() {
		PhysicalProductEntity product = new PhysicalProductEntity();
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
		
		//we need a brand
		product.setBrand(brandService.findByCode("PLA01").get());
				
		//we need a type
		product.setDepartment(departmentService.findByCode("ACC01").get());
				
		//we need a status
		product.setProductStatus(productStatusRepository.findByProductStatusCode("ACT01").get());
				
		//we need a category
		CategoryProductEntity cpf = (CategoryProductEntity) categoryService.findByCode("POM01").get();
				
		CategoryProductEntity cpv = (CategoryProductEntity) categoryService.findByCode("CIT01").get();
		
		//add the category to the product
		product.addProductCategory(cpf);
		product.addProductCategory(cpv);
		
		//we should add a tag
		TagEntity t = tagService.findByCode("ORG01").get();
		
		product.addTag(t);
		
		//we should add a promotion 
		product.addPromotion(promotionService.findByCode("B2G50").get());
		
		return product;
	}
	
	
}
