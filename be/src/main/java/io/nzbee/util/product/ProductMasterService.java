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
import io.nzbee.Constants;
import io.nzbee.domain.product.Accessories;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.tag.Tag;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.domain.ports.IDepartmentPortService;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.ports.ITagPortService;

@Service
@Transactional
public class ProductMasterService {

	private static final Logger logger = LoggerFactory.getLogger(ProductMasterService.class);
	
	@Autowired
	private IProductPortService productDomainService;

	@Autowired
	private IBrandPortService brandDomainService; 
	
	@Autowired
	private ICategoryPortService categoryDomainService; 
	
	@Autowired
	private ITagPortService tagDomainService;
	
	@Autowired
	private IDepartmentPortService departmentDomainService; 
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;

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
	
	public void persistProductMaster(AccessoriesMasterSchema p) {
		logger.debug("called persistProductMaster() ");
		Brand bDo =
				brandDomainService.findByCode(Constants.localeENGB, 
											  p.get_BRAND_CODE());
		
		Category cDo = 
				categoryDomainService.findByCode(   Constants.localeENGB,  
													p.get_PRIMARY_CATEGORY_CODE());
		
		Department dDo = 
				departmentDomainService.findByCode(Constants.localeENGB, 
												   p.get_PRODUCT_TEMPLATE_CODE());
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date createdDate = null;
		try {
		
			createdDate = 
				(p.get_PRODUCT_CREATED_DATE().length() == 0)
				? new Date()
				: format.parse(p.get_PRODUCT_CREATED_DATE());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//this is the upload template for food, another will be created for Jewellery and other product types
		Product pDo = new Accessories(
								p.get_PRODUCT_UPC_CODE(),
								createdDate,
								p.get_PRODUCT_STATUS_CODE(),
							   	p.get_PRODUCT_DESCRIPTION_EN(),
							   	p.get_PRODUCT_RETAIL_PRICE_HKD(),
							   	p.get_PRODUCT_MARKDOWN_PRICE_HKD(),
							   	p.get_PRODUCT_IMAGE_EN(),
							   	Constants.localeENGB,
							   	Constants.currencyHKD,
							   	bDo,
							   	dDo,
							   	(ProductCategory) cDo);

		//add the tags to the domain object
		addTagToProduct(Constants.localeENGB, p.get_TAG_CODE_A(), pDo);
		addTagToProduct(Constants.localeENGB, p.get_TAG_CODE_B(), pDo);
		addTagToProduct(Constants.localeENGB, p.get_TAG_CODE_C(), pDo);
		addTagToProduct(Constants.localeENGB, p.get_TAG_CODE_D(), pDo);
		addTagToProduct(Constants.localeENGB, p.get_TAG_CODE_E(), pDo);
		addTagToProduct(Constants.localeZHHK, p.get_TAG_CODE_A(), pDo);
		addTagToProduct(Constants.localeZHHK, p.get_TAG_CODE_B(), pDo);
		addTagToProduct(Constants.localeZHHK, p.get_TAG_CODE_C(), pDo);
		addTagToProduct(Constants.localeZHHK, p.get_TAG_CODE_D(), pDo);
		addTagToProduct(Constants.localeZHHK, p.get_TAG_CODE_E(), pDo);
		
		productDomainService.save(pDo);

	}
	
	private void addTagToProduct(String locale, String tagCode, Product p) {
		if(tagCode.length() == 5) {
			Tag t = tagDomainService.findByCode(locale, tagCode);
			p.addTag(t);
		}
	}
	
	public void extractProductMaster(Resource resource) {
		logger.debug("called extractProductMaster() ");
		List<AccessoriesMasterSchema> lpms = new ArrayList<AccessoriesMasterSchema>();
	    try {
	    
	    	List<Accessories> productsList = productDomainService.findAllByType(Constants.localeENGB,
	    														  		 Constants.currencyHKD,
	    														  		Accessories.class)
	    							  .stream()
	    							  .map(p -> (Accessories) p)
	    							  .collect(Collectors.toList());
	    	
	    	//create a map of products (full list)
	    	Map<String, AccessoriesMasterSchema> map = productsList.stream().collect(Collectors.toMap(p -> ((Product) p).getProductUPC(), p -> new AccessoriesMasterSchema()));
	 
	    	
	    	productsList.addAll(productDomainService.findAllByType(	Constants.localeZHHK,
																Constants.currencyUSD,
																Accessories.class)
	    											.stream()
									    			.map(p -> (Accessories) p)
													.collect(Collectors.toList()));
	    	
	    	lpms.addAll(productsList.stream().map(p -> {
	    		
		    	AccessoriesMasterSchema pms = map.get(p.getProductUPC());
		    	
		    	pms.set_PRODUCT_UPC_CODE(p.getProductUPC());
		    	pms.set_PRODUCT_CREATED_DATE(format.format(p.getProductCreateDt()));
		    	
		    	pms.set_PRODUCT_STATUS_CODE(p.getProductStatus());
		    	
		        if (p.getLclCd().equals(Constants.localeENGB)) 
		        		{ pms.set_PRODUCT_DESCRIPTION_EN(p.getProductDesc()); }
		        if (p.getLclCd().equals(Constants.localeZHHK)) 
        				{ pms.set_PRODUCT_DESCRIPTION_HK(p.getProductDesc()); }		
		        
		        if (p.getCurrency().equals(Constants.currencyHKD)) 
						{ pms.set_PRODUCT_RETAIL_PRICE_HKD(p.getProductRetail()); 
						  pms.set_PRODUCT_MARKDOWN_PRICE_HKD(p.getProductMarkdown());}	
		        		
		        if (p.getCurrency().equals(Constants.currencyUSD)) 
						{ pms.set_PRODUCT_RETAIL_PRICE_USD(p.getProductRetail()); 
						  pms.set_PRODUCT_MARKDOWN_PRICE_USD(p.getProductMarkdown());}
		    	
		    	Brand brand = brandDomainService.findByProductCode( p.getLclCd(), 
																	p.getProductUPC());
		    	
		    	pms.set_BRAND_CODE(brand.getBrandCode());
		    	
		    	if (p.getLclCd().equals(Constants.localeENGB)) 
        				{ pms.set_BRAND_DESCRIPTION_EN(brand.getBrandDesc()); }
		    	
		    	if (p.getLclCd().equals(Constants.localeZHHK)) 
						{ pms.set_BRAND_DESCRIPTION_HK(brand.getBrandDesc()); }
		    	
		    	ProductCategory c = categoryDomainService.findPrimaryByProductCode(p.getLclCd(),
																		 		   p.getProductUPC()); 
		    	
		    	pms.set_PRIMARY_CATEGORY_CODE(c.getCategoryCode());
		    	
		    	if (p.getLclCd().equals(Constants.localeENGB)) 
						{ pms.set_PRIMARY_CATEGORY_DESC_EN(c.getCategoryDesc()); 
						  pms.set_PRODUCT_IMAGE_EN(p.getProductImage());}
		    	
		    	if (p.getLclCd().equals(Constants.localeZHHK)) 
						{ pms.set_PRIMARY_CATEGORY_DESC_HK(c.getCategoryDesc()); 
						  pms.set_PRODUCT_IMAGE_HK(p.getProductImage());}
		    	
		    	Department d = departmentDomainService.findByProductCode(p.getLclCd(),
															 			 p.getCurrency(), 
															 			 p.getProductUPC()); 
		    	
		    	pms.set_PRODUCT_TEMPLATE_CODE(d.getDepartmentCode());
		    	
		    	if (p.getLclCd().equals(Constants.localeENGB)) 
		    			{ pms.set_PRODUCT_TEMPLATE_DESC_EN(d.getDepartmentDesc()); } 
		    	
		    	if (p.getLclCd().equals(Constants.localeZHHK)) 
						{ pms.set_PRODUCT_TEMPLATE_DESC_HK(d.getDepartmentDesc()); } 
		    	
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
