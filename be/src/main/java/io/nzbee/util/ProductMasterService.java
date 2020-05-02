package io.nzbee.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.core.io.ClassPathResource;
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
import io.nzbee.domain.product.Product;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ProductMasterService.class);

	public <ProductMasterSchema> List<ProductMasterSchema> loadObjectList(Class<ProductMasterSchema> type/*, String fileName*/) {
	    try {
	        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper mapper = new CsvMapper();
	        File file = new ClassPathResource("data/member_master_data.dat").getFile();
	        MappingIterator<ProductMasterSchema> readValues = 
	          mapper.reader(type).with(bootstrapSchema).readValues(file);
	        return readValues.readAll();
	    } catch (Exception e) {
	        //logger.error("Error occurred while loading object list from file " + fileName, e);
	        return Collections.emptyList();
	    }
	}
	
	public void writeProductMaster(String fileName) {
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
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
		
		Optional<Brand> bDo =
				brandDomainService.findByCode(globalVars.getLocaleENGB(), 
											  globalVars.getCurrencyHKD(), 
											  p.get_BRAND_CODE());

		
		Optional<Category> cDo = 
							categoryDomainService.findByCode(   globalVars.getLocaleENGB(), 
																				  globalVars.getCurrencyHKD(), 
																				  p.get_PRIMARY_CATEGORY_CODE());
		
		Optional<Department> dDo = 
				departmentDomainService.findByCode(globalVars.getLocaleENGB(), 
													 	  globalVars.getCurrencyHKD(), 
													 	  p.get_DEPARTMENT_CODE());
		
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(p.get_PRODUCT_CREATED_DATE());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		Product pDo = new Product(
								p.get_PRODUCT_UPC_CODE(),
								date,
							   	p.get_PRODUCT_DESCRIPTION_EN(),
							   	p.get_PRODUCT_RETAIL_PRICE_HKD(),
							   	p.get_PRODUCT_MARKDOWN_PRICE_HKD(),
							   	p.get_PRODUCT_IMAGE_EN(),
							   	"",
							   	globalVars.getLocaleENGB(),
							   	globalVars.getCurrencyHKD(),
							   	bDo.get(),
							   	dDo.get(),
							   	(ProductCategory) cDo.get());
		
		productDomainService.save(pDo);

	}
	
	public void extractProductMaster(Resource resource) {
		List<ProductMasterSchema> lpms = new ArrayList<ProductMasterSchema>();
	    try {
	    
	    	Set<Product> productsList = productDomainService.findAll(	globalVars.getLocaleENGB(),
	    														  		globalVars.getCurrencyHKD());
	    	//create a map of products (full list)
	    	Map<String, ProductMasterSchema> map = productsList.stream().collect(Collectors.toMap(p -> ((Product) p).getProductUPC(), p -> new ProductMasterSchema()));
	    	
	    	productsList.addAll(productDomainService.findAll(	globalVars.getLocaleZHHK(),
																globalVars.getCurrencyUSD()));
	    	
	    	lpms.addAll(productsList.stream().map(p -> {
		    	ProductMasterSchema pms = map.get(p.getProductUPC());
		    	
		    	pms.set_PRODUCT_UPC_CODE(p.getProductUPC());
		    	pms.set_PRODUCT_CREATED_DATE(format.format(p.getProductCreateDt()));
		    	
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
		    	
		    	Optional<Brand> brand = brandDomainService.findByProductCode( globalVars.getLocaleENGB(),
																	 globalVars.getCurrencyHKD(), 
																	 p.getProductUPC());
		    	
		    	pms.set_BRAND_CODE(brand.get().getBrandCode());
		    	
		    	if (p.getLclCd().equals(globalVars.getLocaleENGB())) 
        				{ pms.set_BRAND_DESCRIPTION_EN(brand.get().getBrandDesc()); }
		    	
		    	Optional<ProductCategory> c = categoryDomainService.findPrimaryByProductCode(globalVars.getLocaleENGB(),
																		 					 globalVars.getCurrencyHKD(), 
																		 					 p.getProductUPC()); 
		    	
		    	pms.set_PRIMARY_CATEGORY_CODE(c.get().getCategoryCode());
		    	
		    	if (p.getLclCd().equals(globalVars.getLocaleENGB())) 
						{ pms.setPRIMARY_CATEGORY_DESC_EN(c.get().getCategoryDesc()); 
						  pms.set_PRODUCT_IMAGE_EN(p.getProductImage());}
		    	
		    	
		    	
		    	
		    	
		    
	    	return pms;
	    	}).collect(Collectors.toList()));
	    	
	    	CsvMapper mapper = new CsvMapper(); 
	        CsvSchema schema = mapper.schemaFor(ProductMasterSchema.class);
	        schema = schema.withHeader();
	        schema = schema.withColumnSeparator(',');
	        
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
