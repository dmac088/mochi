package io.nzbee.util.product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import io.nzbee.Globals;
import io.nzbee.domain.product.Food;
import io.nzbee.domain.product.Product;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.domain.ports.IDepartmentPortService;
import io.nzbee.domain.ports.IProductPortService;

@Service
@Transactional
public class ProductMasterService {

	private static final Logger logger = LoggerFactory.getLogger(ProductMasterService.class);
	
	@Autowired
	private Globals globalVars;
	
	@Autowired
	private IProductPortService productDomainService;

	@Autowired
	private IBrandPortService brandDomainService; 
	
	@Autowired
	private ICategoryPortService categoryDomainService; 
	
	@Autowired
	private IDepartmentPortService departmentDomainService; 
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public void writeFoodMaster(String fileName) {
		logger.debug("called writeProductMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<FoodMasterSchema> readValues =
	        	mapper.readerFor(FoodMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(p -> {
	        	this.persistProductMaster(p);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistProductMaster(FoodMasterSchema p) {
		logger.debug("called persistProductMaster() ");
		Brand bDo =
				brandDomainService.findByCode(globalVars.getLocaleENGB(), 
											  globalVars.getCurrencyHKD(), 
											  p.get_BRAND_CODE());

		
		Category cDo = 
				categoryDomainService.findByCode(   globalVars.getLocaleENGB(), 
													globalVars.getCurrencyHKD(), 
													p.get_PRIMARY_CATEGORY_CODE());
		
		Department dDo = 
				departmentDomainService.findByCode(globalVars.getLocaleENGB(), 
												   globalVars.getCurrencyHKD(), 
												   p.get_DEPARTMENT_CODE());
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date createdDate = null;
		Date expiryDate = null;
		try {
		
			createdDate = 
				(p.get_PRODUCT_CREATED_DATE().length() == 0)
				? format.parse("1900-01-01")
				: format.parse(p.get_PRODUCT_CREATED_DATE());
				
			expiryDate = 
				(p.get_EXPIRY_DATE().length() == 0)
				? format.parse("9999-12-31")
				: format.parse(p.get_PRODUCT_CREATED_DATE());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//this is the upload template for food, another will be created for Jewellery and other product types
		Product pDo = new Food(
								p.get_PRODUCT_UPC_CODE(),
								createdDate,
								p.get_PRODUCT_STATUS_CODE(),
							   	p.get_PRODUCT_DESCRIPTION_EN(),
							   	p.get_PRODUCT_RETAIL_PRICE_HKD(),
							   	p.get_PRODUCT_MARKDOWN_PRICE_HKD(),
							   	p.get_PRODUCT_IMAGE_EN(),
							   	p.get_COUNTRY_OF_ORIGIN(),
							   	expiryDate,
							   	globalVars.getLocaleENGB(),
							   	globalVars.getCurrencyHKD(),
							   	bDo,
							   	dDo,
							   	(ProductCategory) cDo);
		
		productDomainService.save(pDo);

	}
	
	public void extractProductMaster(Resource resource) {
		logger.debug("called extractProductMaster() ");
		List<FoodMasterSchema> lpms = new ArrayList<FoodMasterSchema>();
	    try {
	    
	    	List<Food> productsList = productDomainService.findAllByType(globalVars.getLocaleENGB(),
	    														  		 globalVars.getCurrencyHKD(),
	    														  		 Food.class)
	    							  .stream()
	    							  .map(p -> (Food) p)
	    							  .collect(Collectors.toList());
	    	
	    	//create a map of products (full list)
	    	Map<String, FoodMasterSchema> map = productsList.stream().collect(Collectors.toMap(p -> ((Product) p).getProductUPC(), p -> new FoodMasterSchema()));
	 
	    	
	    	productsList.addAll(productDomainService.findAllByType(	globalVars.getLocaleZHHK(),
																globalVars.getCurrencyUSD(),
																Food.class)
	    											.stream()
									    			.map(p -> (Food) p)
													.collect(Collectors.toList()));
	    	
	    	lpms.addAll(productsList.stream().map(p -> {
	    		
		    	FoodMasterSchema pms = map.get(p.getProductUPC());
		    	
		    	pms.set_PRODUCT_UPC_CODE(p.getProductUPC());
		    	pms.set_PRODUCT_CREATED_DATE(format.format(p.getProductCreateDt()));
		    	
		    	pms.set_COUNTRY_OF_ORIGIN(p.getCountryOfOrigin());
		    	pms.set_PRODUCT_STATUS_CODE(p.getProductStatus());
		    	
		        if (p.getLclCd().equals(globalVars.getLocaleENGB())) 
		        		{ pms.set_PRODUCT_DESCRIPTION_EN(p.getProductDesc()); }
		        if (p.getLclCd().equals(globalVars.getLocaleZHHK())) 
        				{ pms.set_PRODUCT_DESCRIPTION_HK(p.getProductDesc()); }		
		        
		        if (p.getCurrency().equals(globalVars.getCurrencyHKD())) 
						{ pms.set_PRODUCT_RETAIL_PRICE_HKD(p.getProductRetail()); 
						  pms.set_PRODUCT_MARKDOWN_PRICE_HKD(p.getProductMarkdown());}	
		        		
		        if (p.getCurrency().equals(globalVars.getCurrencyUSD())) 
						{ pms.set_PRODUCT_RETAIL_PRICE_USD(p.getProductRetail()); 
						  pms.set_PRODUCT_MARKDOWN_PRICE_USD(p.getProductMarkdown());}
		    	
		    	Brand brand = brandDomainService.findByProductCode( p.getLclCd(),
																	p.getCurrency(), 
																	p.getProductUPC());
		    	
		    	pms.set_BRAND_CODE(brand.getBrandCode());
		    	
		    	if (p.getLclCd().equals(globalVars.getLocaleENGB())) 
        				{ pms.set_BRAND_DESCRIPTION_EN(brand.getBrandDesc()); }
		    	
		    	if (p.getLclCd().equals(globalVars.getLocaleZHHK())) 
						{ pms.set_BRAND_DESCRIPTION_HK(brand.getBrandDesc()); }
		    	
		    	ProductCategory c = categoryDomainService.findPrimaryByProductCode(p.getLclCd(),
																		 		   p.getCurrency(), 
																		 		   p.getProductUPC()); 
		    	
		    	pms.set_PRIMARY_CATEGORY_CODE(c.getCategoryCode());
		    	
		    	if (p.getLclCd().equals(globalVars.getLocaleENGB())) 
						{ pms.set_PRIMARY_CATEGORY_DESC_EN(c.getCategoryDesc()); 
						  pms.set_PRODUCT_IMAGE_EN(p.getProductImage());}
		    	
		    	if (p.getLclCd().equals(globalVars.getLocaleZHHK())) 
						{ pms.set_PRIMARY_CATEGORY_DESC_HK(c.getCategoryDesc()); 
						  pms.set_PRODUCT_IMAGE_HK(p.getProductImage());}
		    	
		    	Department d = departmentDomainService.findByProductCode(p.getLclCd(),
															 			 p.getCurrency(), 
															 			 p.getProductUPC()); 
		    	
		    	pms.set_DEPARTMENT_CODE(d.getDepartmentCode());
		    	
		    	if (p.getLclCd().equals(globalVars.getLocaleENGB())) 
		    			{ pms.set_DEPARTMENT_DESC_EN(d.getDepartmentDesc()); } 
		    	
		    	if (p.getLclCd().equals(globalVars.getLocaleZHHK())) 
						{ pms.set_DEPARTMENT_DESC_HK(d.getDepartmentDesc()); } 
		    	
		    	pms.set_PRODUCT_TYPE(p.getProductType());
		    	
	    	return pms;
	    	}).collect(Collectors.toSet()));
	    	
	    	CsvMapper mapper = new CsvMapper(); 
	        CsvSchema schema = mapper.schemaFor(FoodMasterSchema.class)
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