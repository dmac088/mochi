package io.nzbee.util.product.shipping;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Constants;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.attribute.IProductAttributeService;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.price.IProductPriceService;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.shipping.ShippingProductEntity;
import io.nzbee.entity.product.shipping.destination.IShippingDestinationService;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import io.nzbee.entity.product.shipping.type.IShippingTypeService;
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class ShippingProductMasterService {

	private static final Logger logger = LoggerFactory.getLogger(ShippingProductMasterService.class);
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
    
    @Autowired
    private IProductService productService;
    
	@Autowired
	private IProductAttributeService productAttributeService;

	@Autowired
	private ICategoryService categoryService; 
	
	@Autowired
	private IBrandService brandService; 
	
	@Autowired
	private IDepartmentService departmentService; 

	@Autowired
	private IProductStatusRepository productStatusService;
	
	@Autowired
	private IProductPriceService productPriceService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private IProductPriceTypeService productPriceTypeService;
	
	@Autowired 
	private IShippingTypeService shippingTypeService;
	
	@Autowired
	private IShippingDestinationService shippingDestinationService;
    
	@Transactional
	public void writeShippingProductMaster(String fileName) {
		logger.debug("called writeShippingProductMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<ShippingProductMasterSchema> readValues =
	        	mapper.readerFor(ShippingProductMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(sdms -> {
	        	this.persistShippingProductMaster(sdms);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistShippingProductMaster(ShippingProductMasterSchema sdms) {
		logger.debug("called persistShippingProductMaster");
		
		ShippingProductEntity spEn = this.mapToShippingProduct(
																  Constants.localeENGB,
																  Constants.currencyUSD,
																  sdms.get_PRODUCT_UPC_CODE(),
																  sdms.get_BRAND_CODE(),
																  sdms.get_PRODUCT_TEMPLATE_CODE(),
																  sdms.get_PRODUCT_CREATED_DATE(),
																  sdms.get_PRODUCT_DESCRIPTION_EN(),
																  sdms.get_PRODUCT_LONG_DESCRIPTION_EN(),
																  sdms.get_PRODUCT_RETAIL_PRICE_USD(),
																  sdms.get_PRODUCT_RETAIL_PRICE_USD(),
																  sdms.get_SERVICE_TYPE_CODE(), 
																  sdms.get_ZONE_CODE(),
																  sdms.get_DESTINATION_CODE(),
																  sdms.get_WEIGHT_LIMIT(),
																  sdms.get_WEIGHT_FROM(), 
																  sdms.get_WEIGHT_TO(), 
																  sdms.get_TRACKING_LEVEL());

		productService.save(Constants.localeENGB,
							Constants.currencyUSD,
							spEn);
		
		ShippingProductEntity spHk = this.mapToShippingProduct(
																  Constants.localeZHHK,
																  Constants.currencyHKD,
																  sdms.get_PRODUCT_UPC_CODE(),
																  sdms.get_BRAND_CODE(),
																  sdms.get_PRODUCT_TEMPLATE_CODE(),
																  sdms.get_PRODUCT_CREATED_DATE(),
																  sdms.get_PRODUCT_DESCRIPTION_HK(),
																  sdms.get_PRODUCT_LONG_DESCRIPTION_HK(),
																  sdms.get_PRODUCT_RETAIL_PRICE_HKD(),
																  sdms.get_PRODUCT_RETAIL_PRICE_HKD(),
																  sdms.get_SERVICE_TYPE_CODE(), 
																  sdms.get_ZONE_CODE(),
																  sdms.get_DESTINATION_CODE(),
																  sdms.get_WEIGHT_LIMIT(),
																  sdms.get_WEIGHT_FROM(), 
																  sdms.get_WEIGHT_TO(), 
																  sdms.get_TRACKING_LEVEL());

		productService.save(Constants.localeZHHK,
				  			Constants.currencyHKD,
				  			spHk);
		
		
	}
	
	
	private ShippingProductEntity mapToShippingProduct(
														String locale, 
														String currency,
														String upcCode,
														String brandCode,
														String templateCode,
														String productCreateDate,
														String productDesc,
														String productLongDesc,
														Double retailPrice,
														Double markdownPrice,
														String serviceCode,
														String zoneCode,
														String destinationCode,
														String weightLimit,
														String weightFrom,
														String weightTo,
														String trackingLevel) {
		
		
		Optional<ProductEntity> op = productService.findByCode(upcCode);
		
		Optional<BrandEntity> ob = brandService.findByCode(brandCode);
		
		Optional<DepartmentEntity> od = departmentService.findByCode(templateCode);
		
		Optional<ProductStatusEntity> ops = productStatusService.findByProductStatusCode(Constants.activeSKUCode);
		
		Optional<ProductAttributeEntity> opa = productAttributeService.findByCode(locale, upcCode);
		
		Optional<CategoryEntity> oca = categoryService.findByCode(Constants.shippingRootCategoryCode);
		
		CategoryProductEntity sc = (CategoryProductEntity) oca.get();
		
		ShippingProductEntity sp = 	op.isPresent() 
						  			? (ShippingProductEntity) op.get()
						  			: new ShippingProductEntity();
		
		ProductAttributeEntity pa = opa.isPresent()
									? opa.get()
									: (new ProductAttributeEntity());
									
		LocalDateTime createdDate = LocalDateTime.parse(productCreateDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));							
		
		sp.addCategory(sc);
		sp.setBrand(ob.get());
		sp.setDepartment(od.get());
		sp.setProductUPC(upcCode);
		sp.setProductCreateDt(createdDate);
		sp.setProductStatus(ops.get());
		
		pa.setProductDesc(productDesc);
		pa.setProductLongDesc(productLongDesc);
		pa.setLclCd(locale);
		
		sp.addProductAttribute(pa);
		pa.setProduct(sp);
		
		Currency curr = currencyService.findByCode(currency).get();
		ProductPriceType ptr = productPriceTypeService.findByCode(Constants.retailPriceCode).get();
		ProductPriceType ptm = productPriceTypeService.findByCode(Constants.markdownPriceCode).get();

		ProductStatusEntity ps = productStatusService.findByProductStatusCode(Constants.activeSKUCode).get();

		Optional<ProductPriceEntity> oprcr = 
				productPriceService.findByProductCode(upcCode, 
											Constants.retailPriceCode, 
											currency);

		//retail price
		ProductPriceEntity prcr = (	oprcr.isPresent()) 
									? oprcr.get()
									: new ProductPriceEntity();

		prcr.setType(ptr);
		prcr.setCurrency(curr);
		prcr.setPriceValue(retailPrice);

		Optional<ProductPriceEntity> oprcm = 
				productPriceService.findByProductCode(upcCode, 
											Constants.markdownPriceCode, 
											currency);

		//markdown price
		ProductPriceEntity prcm = (oprcm.isPresent()) 
							? oprcm.get()
							: new ProductPriceEntity();

		prcm.setType(ptm);
		prcm.setCurrency(curr);
		prcm.setPriceValue(markdownPrice);
		
		sp.setProductStatus(ps);
		sp.addProductPrice(prcr);
		sp.addProductPrice(prcm);
		
		
		sp.setInStock(true);
		
		Optional<ShippingTypeEntity> ost = shippingTypeService.findByCode(serviceCode);
		sp.setShippingType(ost.get());
		
		Optional<ShippingDestinationEntity> osd = shippingDestinationService.findByCode(destinationCode);
		sp.setShippingDestination(osd.get());
		
		sp.setWeightLimit(Double.parseDouble(weightLimit));
		sp.setWeightFrom(Double.parseDouble(weightFrom));
		sp.setWeightTo(Double.parseDouble(weightTo));
		sp.setTrackingLevel(Integer.parseInt(trackingLevel));
		
		return sp;
		
	}
}
