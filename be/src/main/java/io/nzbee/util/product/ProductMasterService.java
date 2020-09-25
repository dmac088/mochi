package io.nzbee.util.product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Constants;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.accessories.Accessories;
import io.nzbee.entity.product.attribute.IProductAttributeService;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.Department;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.price.IProductPriceService;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.Tag;
import io.nzbee.util.FileStorageServiceUpload;

//import io.nzbee.domain.product.Accessories;
//import io.nzbee.domain.product.Product;
//import io.nzbee.domain.tag.Tag;
//import io.nzbee.domain.brand.Brand;
//import io.nzbee.domain.category.Category;
//import io.nzbee.domain.category.ProductCategory;
//import io.nzbee.domain.department.Department;
//
//import io.nzbee.domain.ports.IBrandPortService;
//import io.nzbee.domain.ports.ICategoryPortService;
//import io.nzbee.domain.ports.IDepartmentPortService;
//import io.nzbee.domain.ports.IProductPortService;
//import io.nzbee.domain.ports.ITagPortService;

@Service
@Transactional
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

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public void writeAccessoriesMaster(String fileName) {
		logger.debug("called writeProductMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<AccessoriesMasterSchema> readValues =
	        	mapper.readerFor(AccessoriesMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(p -> {
	        	this.persistProductMaster(p);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	@Transactional
	public void persistProductMaster(AccessoriesMasterSchema p) {
		
		//english with USD
		Accessories pe = 
		mapToDB(
				 Constants.localeENGB, 
				 Constants.currencyUSD,
				 p.get_PRODUCT_UPC_CODE(),
				 p.get_BRAND_CODE(),
				 p.get_PRIMARY_CATEGORY_CODE(),
				 p.get_PRODUCT_TEMPLATE_CODE(),
				 p.get_PRODUCT_CREATED_DATE(),
				 p.get_BRAND_DESCRIPTION_EN(),
				 p.get_PRODUCT_LONG_DESCRIPTION_EN(),
				 p.get_PRODUCT_RETAIL_PRICE_USD(),
				 p.get_PRODUCT_MARKDOWN_PRICE_USD(),
				 p.get_TAG_CODE_A(),
				 p.get_TAG_CODE_B(),
				 p.get_TAG_CODE_C(),
				 p.get_TAG_CODE_D(),
				 p.get_TAG_CODE_E()	
		);
		productService.save(pe);
		
		pe = mapToDB(
				 Constants.localeZHHK, 
				 Constants.currencyHKD,
				 p.get_PRODUCT_UPC_CODE(),
				 p.get_BRAND_CODE(),
				 p.get_PRIMARY_CATEGORY_CODE(),
				 p.get_PRODUCT_TEMPLATE_CODE(),
				 p.get_PRODUCT_CREATED_DATE(),
				 p.get_BRAND_DESCRIPTION_HK(),
				 p.get_PRODUCT_LONG_DESCRIPTION_HK(),
				 p.get_PRODUCT_RETAIL_PRICE_HKD(),
				 p.get_PRODUCT_MARKDOWN_PRICE_HKD(),
				 p.get_TAG_CODE_A(),
				 p.get_TAG_CODE_B(),
				 p.get_TAG_CODE_C(),
				 p.get_TAG_CODE_D(),
				 p.get_TAG_CODE_E()	
		);
		productService.save(pe);
		
	}
	
	
	private Accessories mapToDB(String locale, 
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
		logger.debug("called persistProductMaster() ");
		
		Optional<Product> op = productService.findByCode(upcCode);
		
		Optional<Brand> ob = brandService.findByCode(locale, brandCode);
		
		Optional<Category> oc = categoryService.findByCode(locale, categoryCode);
		
		Optional<Department> od = departmentService.findByCode(locale, templateCode);
		
		Optional<ProductStatus> ops = productStatusService.findByProductStatusCode(Constants.activeSKUCode);
		
		Optional<ProductAttribute> opa = productAttributeService.findByCode(locale, upcCode);
		
		ProductAttribute pa = (opa.isPresent()) 
		? opa.get()
		: (new io.nzbee.entity.product.attribute.ProductAttribute());
		
		
		LocalDateTime createdDate = null;
		
		createdDate = LocalDateTime.parse(productCreateDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			
		//this is the upload template for food, another will be created for Jewellery and other product types
		Accessories pe = (op.isPresent()) 
						 ? (Accessories) op.get()
						 : new Accessories();			  
					  
		pe.setPrimaryCategory((CategoryProduct) oc.get());
		pe.setBrand(ob.get());
		pe.setDepartment(od.get());
		pe.setProductUPC(upcCode);
		pe.setProductCreateDt(createdDate);
		pe.setProductStatus(ops.get());
		
		pe.setLocale(locale);
		pe.setCurrency(currency);
		
		pa.setProductDesc(productDesc);
		pa.setProductLongDesc(productLongDesc);
		pa.setLclCd(locale);
		
		pa.setProduct(pe);
		pe.setProductAttribute(pa);
		
		Currency curr = currencyService.findByCode(currency).get();
		ProductPriceType ptr = productPriceTypeService.findByCode(Constants.retailPriceCode).get();
		ProductPriceType ptm = productPriceTypeService.findByCode(Constants.markdownPriceCode).get();

		ProductStatus ps = productStatusService.findByProductStatusCode(Constants.activeSKUCode).get();

		Optional<ProductPrice> oprcr = 
				productPriceService.findOne(upcCode, 
											Constants.retailPriceCode, 
											Constants.currencyUSD);

		//retail price
		ProductPrice prcr = (oprcr.isPresent()) 
							? oprcr.get()
							: new ProductPrice();

		prcr.setType(ptr);
		prcr.setCurrency(curr);
		prcr.setPriceValue(retailPrice);

		Optional<ProductPrice> oprcm = 
				productPriceService.findOne(upcCode, 
											Constants.markdownPriceCode, 
											Constants.currencyUSD);

		//markdown price
		ProductPrice prcm = (oprcm.isPresent()) 
							? oprcm.get()
							: new ProductPrice();

		prcm.setType(ptm);
		prcm.setCurrency(curr);
		prcm.setPriceValue(markdownPrice);
		
		pe.setProductStatus(ps);
		pe.addProductPrice(prcr);
		pe.addProductPrice(prcm);
		
		//add the tags to the domain object
//		addTagToProduct(locale, tagCodeA.toUpperCase(), pe);
//		addTagToProduct(locale, tagCodeB.toUpperCase(), pe);
//		addTagToProduct(locale, tagCodeC.toUpperCase(), pe);
//		addTagToProduct(locale, tagCodeD.toUpperCase(), pe);
//		addTagToProduct(locale, tagCodeE.toUpperCase(), pe);
		
		return pe;
	}
	
	private void addTagToProduct(String locale, String tagCode, Product p) {
		if(tagCode.length() == 5) {
			Tag t = tagService.findByCode(locale, tagCode).get();
			p.addTag(t);
		}
	}
	
	public void extractProductMaster(Resource resource) {
		logger.debug("called extractProductMaster() ");
		List<AccessoriesMasterSchema> lpms = new ArrayList<AccessoriesMasterSchema>();
	    try {
	    
	    	List<Accessories> productsList = productService.findAllByType(Constants.localeENGB,
	    														  		 Constants.currencyHKD,
	    														  		 Accessories.class)
	    							  .stream()
	    							  .map(p -> (Accessories) p)
	    							  .collect(Collectors.toList());
	    	
	    	//create a map of products (full list)
	    	Map<String, AccessoriesMasterSchema> map = productsList.stream().collect(Collectors.toMap(p -> ((Product) p).getProductUPC(), p -> new AccessoriesMasterSchema()));
	 
	    	
	    	productsList.addAll(productService.findAllByType(	Constants.localeZHHK,
																Constants.currencyUSD,
																Accessories.class)
	    											.stream()
									    			.map(p -> (Accessories) p)
													.collect(Collectors.toList()));
	    	
	    	lpms.addAll(productsList.stream().map(p -> {
	    		
		    	AccessoriesMasterSchema pms = map.get(p.getProductUPC());
		    	
		    	pms.set_PRODUCT_UPC_CODE(p.getProductUPC());
		    	pms.set_PRODUCT_CREATED_DATE(format.format(p.getProductCreateDt()));
		    	pms.set_PRODUCT_STATUS_CODE(p.getProductStatus().getCode());
		    	
		        if (p.getLocale().equals(Constants.localeENGB)) 
		        		{ pms.set_PRODUCT_DESCRIPTION_EN(p.getProductAttribute().getProductDesc()); }
		        if (p.getLocale().equals(Constants.localeZHHK)) 
        				{ pms.set_PRODUCT_DESCRIPTION_HK(p.getProductAttribute().getProductDesc()); }		
		        
		        if (p.getCurrency().equals(Constants.currencyHKD)) 
						{ pms.set_PRODUCT_RETAIL_PRICE_HKD(p.getCurrentRetailPriceHKD()); 
						  pms.set_PRODUCT_MARKDOWN_PRICE_HKD(p.getCurrentMarkdownPriceHKD());}	
		        		
		        if (p.getCurrency().equals(Constants.currencyUSD)) 
						{ pms.set_PRODUCT_RETAIL_PRICE_USD(p.getCurrentRetailPriceUSD()); 
						  pms.set_PRODUCT_MARKDOWN_PRICE_USD(p.getCurrentMarkdownPriceUSD());}
		    	
		    	Optional<Brand> brand = brandService.findByProductCode( p.getLocale(), 
																	p.getProductUPC());
		    	
		    	pms.set_BRAND_CODE(brand.get().getBrandCode());
		    	
		    	if (p.getLocale().equals(Constants.localeENGB)) 
        				{ pms.set_BRAND_DESCRIPTION_EN(brand.get().getBrandDescENGB()); }
		    	
		    	if (p.getLocale().equals(Constants.localeZHHK)) 
						{ pms.set_BRAND_DESCRIPTION_HK(brand.get().getBrandDescZHHK()); }
		    	
		    	Optional<Category> c = categoryService.findByCode(p.getLocale(), p.getPrimaryCategory().getCategoryCode());
		    	
		    	pms.set_PRIMARY_CATEGORY_CODE(c.get().getCategoryCode());
		    	
		    	if (p.getLocale().equals(Constants.localeENGB)) 
						{ pms.set_PRIMARY_CATEGORY_DESC_EN(c.get().getCategoryDescENGB()); 
						  pms.set_PRODUCT_IMAGE_EN(p.getProductImageENGB());}
		    	
		    	if (p.getLocale().equals(Constants.localeZHHK)) 
						{ pms.set_PRIMARY_CATEGORY_DESC_HK(c.get().getCategoryDescZHHK()); 
						  pms.set_PRODUCT_IMAGE_HK(p.getProductImageZHHK());}
		    	
		    	Optional<Department> d = departmentService.findByProductCode(p.getLocale(),
															 	   p.getProductUPC()); 
		    	
		    	pms.set_PRODUCT_TEMPLATE_CODE(d.get().getDepartmentCode());
		    	
		    	if (p.getLocale().equals(Constants.localeENGB)) 
		    			{ pms.set_PRODUCT_TEMPLATE_DESC_EN(d.get().getDepartmentDescENGB()); } 
		    	
		    	if (p.getLocale().equals(Constants.localeZHHK)) 
						{ pms.set_PRODUCT_TEMPLATE_DESC_HK(d.get().getDepartmentDescZHHK()); } 
		    	
	    	return pms;
	    	}).collect(Collectors.toSet()));
	    	
	    	CsvMapper mapper = new CsvMapper(); 
	        CsvSchema schema = mapper.schemaFor(AccessoriesMasterSchema.class)
	        		.withHeader()
	        		.withColumnSeparator('\t')
	        		.withQuoteChar('"');
	        
	        ObjectWriter myObjectWriter = mapper.writer(schema);
	        String ow = myObjectWriter.writeValueAsString(lpms);
	        PrintWriter out = new PrintWriter(resource.getFile().getAbsolutePath());
	        out.write(ow);
	        out.flush();
	        out.close();
	        
	    } catch (Exception e) {
	        logger.error("Error occurred while loading object list from file " + resource.getFilename(), e);
	    }
	}
	
}
