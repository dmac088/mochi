package io.nzbee.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Globals;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.BrandServiceImpl;
import io.nzbee.domain.product.IProductService;

@Service
@Transactional
public class ProductMasterService {

	@Autowired
	private Globals globalVars;
	
	@Autowired
	@Qualifier("productDomainService")
	private IProductService productDomainService;

	
	@Autowired
	@Qualifier("brandDomainService")
	private BrandServiceImpl brandDomainService; 
	
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final Logger logger = LoggerFactory.getLogger(ProductMasterService.class);

//	public <ProductMasterSchema> List<ProductMasterSchema> loadObjectList(Class<ProductMasterSchema> type/*, String fileName*/) {
//	    try {
//	        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
//	        CsvMapper mapper = new CsvMapper();
//	        File file = new ClassPathResource("data/member_master_data.dat").getFile();
//	        MappingIterator<ProductMasterSchema> readValues = 
//	          mapper.reader(type).with(bootstrapSchema).readValues(file);
//	        return readValues.readAll();
//	    } catch (Exception e) {
//	        //logger.error("Error occurred while loading object list from file " + fileName, e);
//	        return Collections.emptyList();
//	    }
//	}
	
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
		
//		io.nzbee.dto.product.Product pDo = 
//				productDomainService.convertToProductDO(
//					p.get_PRODUCT_CREATED_DATE(), 
//					p.get_PRODUCT_UPC_CODE(), 
//					p.get_PRODUCT_DESCRIPTION_EN(), 
//					p.get_PRODUCT_RETAIL_PRICE_USD(), 
//					p.get_PRODUCT_MARKDOWN_PRICE_USD(), 
//					p.get_PRODUCT_IMAGE_EN(), 
//					GeneralVars.LANGUAGE_ENGLISH, 
//					GeneralVars.CURRENCY_USD, 
//					"/TBC");
//			
//		io.nzbee.dto.brand.Brand bDo =
//				brandDomainService.convertToBrandDO(
//					p.get_BRAND_CODE(), 
//					p.get_BRAND_DESCRIPTION_EN());
//				
//		pDo.setBrand(Optional.ofNullable(bDo));
//		
//		productDomainService.save(pDo);

	}
	
	public void extractProductMaster(Resource resource) {
		List<ProductMasterSchema> lpms = new ArrayList<ProductMasterSchema>();
	    try {
	    
	    	Set<Product> productsListA = productDomainService.findAll(	globalVars.getLocaleENGB(),
	    														  		globalVars.getCurrencyHKD());
	    	
	    	Set<Product> productsListB = productDomainService.findAll(	globalVars.getLocaleZHHK(),
					  													globalVars.getCurrencyUSD());
	    	
	    	Map<String, Product> map = productsListB.stream().collect(Collectors.toMap(p -> ((Product) p).getProductUPC(), p -> ((Product) p)));
	    	
	    	lpms.addAll(productsListA.stream().map(pA -> {
		    	ProductMasterSchema pms = new ProductMasterSchema();
		    	
		    	Product pB = map.get(pA.getProductUPC());
		    	
		    	pms.set_PRODUCT_UPC_CODE(pA.getProductUPC());
		    	pms.set_PRODUCT_CREATED_DATE(format.format(pA.getProductCreateDt()));
		    	
		    	pms.set_PRODUCT_DESCRIPTION_EN(pA.getProductDesc());
		    	pms.set_PRODUCT_DESCRIPTION_HK(pB.getProductDesc());
		    	
		    	pms.set_PRODUCT_RETAIL_PRICE_HKD(pA.getProductRetail());
		    	pms.set_PRODUCT_MARKDOWN_PRICE_HKD(pA.getProductMarkdown());
		    	
		    	pms.set_PRODUCT_RETAIL_PRICE_USD(pB.getProductRetail());
		    	pms.set_PRODUCT_MARKDOWN_PRICE_USD(pB.getProductMarkdown());
		    	
		    	
		    	Brand brandA = brandDomainService.findByProductCode( globalVars.getLocaleENGB(),
																	 globalVars.getCurrencyHKD(), 
																	 pA.getProductUPC()).get();
		    	
		    	pms.set_BRAND_CODE(brandA.getBrandCode());
		    	pms.set_BRAND_DESCRIPTION_EN(brandA.getBrandDesc());
		    	
		    	Brand brandB = brandDomainService.findByProductCode( globalVars.getLocaleENGB(),
																	 globalVars.getCurrencyHKD(), 
																	 pB.getProductUPC()).get();
		    	
		    	pms.set_BRAND_CODE(brandB.getBrandCode());
		    	pms.set_BRAND_DESCRIPTION_EN(brandB.getBrandDesc());
		    	
		    	
		    	pms.set_PRIMARY_CATEGORY_PATH("\\TBC");
		    	pms.set_PRODUCT_IMAGE_EN(pA.getProductImage());
	    		
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
