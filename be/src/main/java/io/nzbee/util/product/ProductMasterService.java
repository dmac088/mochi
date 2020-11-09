package io.nzbee.util.product;

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
import io.nzbee.entity.product.basic.ProductBasicEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.price.IProductPriceService;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class ProductMasterService {

	private static final Logger logger = LoggerFactory.getLogger(ProductMasterService.class);
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IProductAttributeService productAttributeService;

	@Autowired
	private IBrandService brandService; 
	
	@Autowired
	private ITagService tagService;
	
	@Autowired
	private ICategoryService categoryService; 
	
	@Autowired
	private IDepartmentService departmentService; 

	@Autowired
	private IProductStatusRepository productStatusService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private IProductPriceTypeService productPriceTypeService;
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
    
	@Autowired
	private IProductPriceService productPriceService;

	@Transactional
	public void writeProductMaster(String fileName) {
		logger.debug("called writeProductMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<ProductMasterSchema> readValues =
	        	mapper.readerFor(ProductMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(p -> {
	        	this.persistProductMaster(p);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistProductMaster(ProductMasterSchema p) {
		
		//english with USD
		ProductBasicEntity pe = mapToAccessory(
				 Constants.localeENGB, 
				 Constants.currencyUSD,
				 p.get_PRODUCT_UPC_CODE(),
				 p.get_BRAND_CODE(),
				 p.get_PRIMARY_CATEGORY_CODE(),
				 p.get_PRODUCT_TEMPLATE_CODE(),
				 p.get_PRODUCT_CREATED_DATE(),
				 p.get_PRODUCT_DESCRIPTION_EN(),
				 p.get_PRODUCT_LONG_DESCRIPTION_EN(),
				 p.get_PRODUCT_RETAIL_PRICE_USD(),
				 p.get_PRODUCT_MARKDOWN_PRICE_USD(),
				 p.get_TAG_CODE_A(),
				 p.get_TAG_CODE_B(),
				 p.get_TAG_CODE_C(),
				 p.get_TAG_CODE_D(),
				 p.get_TAG_CODE_E()	
		);
		productService.save(Constants.localeENGB, Constants.currencyUSD, pe);
		
		pe = mapToAccessory(
				 Constants.localeZHHK, 
				 Constants.currencyHKD,
				 p.get_PRODUCT_UPC_CODE(),
				 p.get_BRAND_CODE(),
				 p.get_PRIMARY_CATEGORY_CODE(),
				 p.get_PRODUCT_TEMPLATE_CODE(),
				 p.get_PRODUCT_CREATED_DATE(),
				 p.get_PRODUCT_DESCRIPTION_HK(),
				 p.get_PRODUCT_LONG_DESCRIPTION_HK(),
				 p.get_PRODUCT_RETAIL_PRICE_HKD(),
				 p.get_PRODUCT_MARKDOWN_PRICE_HKD(),
				 p.get_TAG_CODE_A(),
				 p.get_TAG_CODE_B(),
				 p.get_TAG_CODE_C(),
				 p.get_TAG_CODE_D(),
				 p.get_TAG_CODE_E()	
		);
		productService.save(Constants.localeZHHK, Constants.currencyHKD, pe);
		
	}
	
	
	private ProductBasicEntity mapToAccessory(String locale, 
						 String currency,
						 String upcCode,
						 String brandCode,
						 String categoryCode,
						 String templateCode,
						 String productCreateDate,
						 String productDesc,
						 String productLongDesc,
						 Double retailPrice,
						 Double markdownPrice,
						 String tagCodeA,
						 String tagCodeB,
						 String tagCodeC,
						 String tagCodeD,
						 String tagCodeE
						 ) {
		logger.debug("called mapToAccessory() ");
		
		Optional<ProductEntity> op = productService.findByCode(upcCode);
		
		Optional<BrandEntity> ob = brandService.findByCode(brandCode);
		
		Optional<CategoryEntity> oc = categoryService.findByCode(categoryCode);
		
		Optional<DepartmentEntity> od = departmentService.findByCode(templateCode);
		
		Optional<ProductStatusEntity> ops = productStatusService.findByProductStatusCode(Constants.activeSKUCode);
		
		Optional<ProductAttributeEntity> opa = productAttributeService.findByCode(locale, upcCode);
		
		ProductAttributeEntity pa = (opa.isPresent()) 
		? opa.get()
		: (new ProductAttributeEntity());
		
		
		LocalDateTime createdDate = LocalDateTime.parse(productCreateDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			
		//this is the upload template for food, another will be created for Jewellery and other product types
		ProductBasicEntity pe = (op.isPresent()) 
						 ? (ProductBasicEntity) op.get()
						 : new ProductBasicEntity();			  
					  
		pe.setPrimaryCategory((CategoryProductEntity) oc.get());
		pe.setBrand(ob.get());
		pe.setDepartment(od.get());
		pe.setProductUPC(upcCode);
		pe.setProductCreateDt(createdDate);
		pe.setProductStatus(ops.get());

		pa.setProductDesc(productDesc);
		pa.setProductLongDesc(productLongDesc);
		pa.setLclCd(locale);
		
		pa.setProduct(pe);
		pe.addProductAttribute(pa);
		
		Currency curr = currencyService.findByCode(currency).get();
		ProductPriceType ptr = productPriceTypeService.findByCode(Constants.retailPriceCode).get();
		ProductPriceType ptm = productPriceTypeService.findByCode(Constants.markdownPriceCode).get();

		ProductStatusEntity ps = productStatusService.findByProductStatusCode(Constants.activeSKUCode).get();

		Optional<ProductPriceEntity> oprcr = 
				productPriceService.findOne(upcCode, 
											Constants.retailPriceCode, 
											currency);

		//retail price
		ProductPriceEntity prcr = (oprcr.isPresent()) 
							? oprcr.get()
							: new ProductPriceEntity();

		prcr.setType(ptr);
		prcr.setCurrency(curr);
		prcr.setPriceValue(retailPrice);

		Optional<ProductPriceEntity> oprcm = 
				productPriceService.findOne(upcCode, 
											Constants.markdownPriceCode, 
											currency);

		//markdown price
		ProductPriceEntity prcm = (oprcm.isPresent()) 
							? oprcm.get()
							: new ProductPriceEntity();

		prcm.setType(ptm);
		prcm.setCurrency(curr);
		prcm.setPriceValue(markdownPrice);
		
		pe.setProductStatus(ps);
		pe.addProductPrice(prcr);
		pe.addProductPrice(prcm);
		
		//add the tags to the domain object
		addTagToProduct(locale, tagCodeA, pe);
		addTagToProduct(locale, tagCodeB, pe);
		addTagToProduct(locale, tagCodeC, pe);
		addTagToProduct(locale, tagCodeD, pe);
		addTagToProduct(locale, tagCodeE, pe);
		
		return pe;
	}
	
	private void addTagToProduct(String locale, String tagCode, ProductEntity p) {
		if (tagCode == null) return;
		if(tagCode.length() == 5) {
			TagEntity t = tagService.findByCode(tagCode.toUpperCase()).get();
			p.addTag(t);
		}
	}
	
//	public void extractProductMaster(Resource resource) {
//		logger.debug("called extractProductMaster() ");
//		List<ProductMasterSchema> lpms = new ArrayList<ProductMasterSchema>();
//	    try {
//	    
//	    	List<ProductBasicEntity> productsList = productService.findAllByType(Constants.localeENGB,
//	    														  		  Constants.currencyHKD,
//	    														  		  ProductBasicEntity.class)
//	    							  .stream()
//	    							  .map(p -> (ProductBasicEntity) p)
//	    							  .collect(Collectors.toList());
//	    	
//	    	//create a map of products (full list)
//	    	Map<String, ProductMasterSchema> map = productsList.stream().collect(Collectors.toMap(p -> ((ProductEntity) p).getProductUPC(), p -> new ProductMasterSchema()));
//	    	
//	    	lpms.addAll(productsList.stream().map(p -> {
//	    		
//		    	ProductMasterSchema pms = map.get(p.getProductUPC());
//		    	
//		    	pms.set_PRODUCT_UPC_CODE(p.getProductUPC());
//		    	pms.set_PRODUCT_CREATED_DATE(format.format(p.getProductCreateDt()));
//		    	pms.set_PRODUCT_STATUS_CODE(p.getProductStatus().getCode());
//		    	pms.set_PRODUCT_DESCRIPTION_EN(p.getProductDescENGB());
//        		pms.set_PRODUCT_DESCRIPTION_HK(p.getProductDescZHHK()); 		
//		        pms.set_PRODUCT_RETAIL_PRICE_HKD(p.getCurrentRetailPriceHKD());
//		        pms.set_PRODUCT_MARKDOWN_PRICE_HKD(p.getCurrentMarkdownPriceHKD());	
//		        pms.set_PRODUCT_RETAIL_PRICE_USD(p.getCurrentRetailPriceUSD()); 
//				pms.set_PRODUCT_MARKDOWN_PRICE_USD(p.getCurrentMarkdownPriceUSD());
//		    	pms.set_BRAND_CODE(p.getBrand().getBrandCode());
//		    	pms.set_BRAND_DESCRIPTION_EN(p.getBrand().getBrandDescENGB());
//		    	pms.set_BRAND_DESCRIPTION_HK(p.getBrand().getBrandDescZHHK());
//		    	pms.set_PRIMARY_CATEGORY_CODE(p.getPrimaryCategory().getCategoryCode());
//		    	pms.set_PRIMARY_CATEGORY_DESC_EN(p.getPrimaryCategory().getCategoryDescENGB());
//		    	pms.set_PRODUCT_IMAGE_EN(p.getProductImageENGB());
//		    	pms.set_PRIMARY_CATEGORY_DESC_HK(p.getPrimaryCategory().getCategoryDescZHHK());
//		    	pms.set_PRODUCT_IMAGE_HK(p.getProductImageZHHK());
//		    	pms.set_PRODUCT_TEMPLATE_CODE(p.getDepartment().getDepartmentCode());
//		    	pms.set_PRODUCT_TEMPLATE_DESC_EN(p.getDepartment().getDepartmentDescENGB());
//		    	pms.set_PRODUCT_TEMPLATE_DESC_HK(p.getDepartment().getDepartmentDescZHHK());
//		    	
//		    	return pms;
//	    	}).collect(Collectors.toSet()));
//	    	
//	    	CsvMapper mapper = new CsvMapper(); 
//	        CsvSchema schema = mapper.schemaFor(ProductMasterSchema.class)
//	        		.withHeader()
//	        		.withColumnSeparator('\t')
//	        		.withQuoteChar('"');
//	        
//	        ObjectWriter myObjectWriter = mapper.writer(schema);
//	        String ow = myObjectWriter.writeValueAsString(lpms);
//	        PrintWriter out = new PrintWriter(resource.getFile().getAbsolutePath());
//	        out.write(ow);
//	        out.flush();
//	        out.close();
//	        
//	    } catch (Exception e) {
//	        logger.error("Error occurred while loading object list from file " + resource.getFilename(), e);
//	    }
//	}
	
}
