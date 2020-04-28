package io.nzbee.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import io.nzbee.entity.brand.BrandServiceImpl;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;

@Service
@Transactional
public class ProductMasterService {

	@Autowired
	private Globals globalVars;
	
	@Autowired
	@Qualifier("productEntityService")
	private IProductService productEntityService;
	
	@Autowired
	@Qualifier("productDomainService")
	private io.nzbee.domain.product.IProductService productDomainService;
	
	@Autowired
	@Qualifier("brandEntityService")
	private BrandServiceImpl brandService; 
	
	
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
	    
	    	List<Product> products = productEntityService.findAll(globalVars.getLocaleENGB(),
	    														  globalVars.getCurrencyHKD());
	    	
	    	lpms.addAll(products.stream().map(p -> {
		    	ProductMasterSchema pms = new ProductMasterSchema();
		    	pms.set_PRODUCT_UPC_CODE(p.getUPC());
		    	pms.set_PRODUCT_CREATED_DATE(format.format(p.getProductCreateDt()));
		    	pms.set_PRODUCT_DESCRIPTION_EN(p.getProductDescENGB());
		    	pms.set_PRODUCT_DESCRIPTION_HK(p.getProductDescZHHK());
		    	
		    	pms.set_PRODUCT_RETAIL_PRICE_USD(p.getPrices().stream().filter(prc -> prc.getCurrency().getCode()
																						   .equals(globalVars.getCurrencyUSD())
																						&& 
																						prc.getType().getCode()
																						   .equals(globalVars.getRetailPriceCode())
																						).findFirst().get().getPriceValue());
		    	
		    	pms.set_PRODUCT_RETAIL_PRICE_HKD(p.getPrices().stream().filter(prc -> prc.getCurrency().getCode()
																						   .equals(globalVars.getCurrencyHKD())
																						&& 
																						prc.getType().getCode()
																						   .equals(globalVars.getRetailPriceCode())
																						).findFirst().get().getPriceValue());
		    	
		    	pms.set_PRODUCT_MARKDOWN_PRICE_USD(p.getPrices().stream().filter(prc -> prc.getCurrency().getCode()
																						   .equals(globalVars.getCurrencyUSD())
																						&& 
																						prc.getType().getCode()
																						   .equals(globalVars.getMarkdownPriceCode())
																						).findFirst().get().getPriceValue());
		    	
		    	pms.set_PRODUCT_MARKDOWN_PRICE_HKD(p.getPrices().stream().filter(prc -> prc.getCurrency().getCode()
		    																			   .equals(globalVars.getCurrencyHKD())
		    																			&& 
		    																			prc.getType().getCode()
		    																			   .equals(globalVars.getMarkdownPriceCode())
		    																			).findFirst().get().getPriceValue());
		    	pms.set_BRAND_CODE(p.getBrand().getBrandCode());
		    	pms.set_BRAND_DESCRIPTION_EN(p.getBrand().getAttributes().stream().filter(b -> b.getLclCd().equals(globalVars.getLocaleENGB())).findFirst().get().getBrandDesc());
		    	pms.set_BRAND_DESCRIPTION_HK(p.getBrand().getAttributes().stream().filter(b -> b.getLclCd().equals(globalVars.getLocaleZHHK())).findFirst().get().getBrandDesc());
		    	pms.set_PRIMARY_CATEGORY_PATH("\\TBC");
		    	pms.set_PRODUCT_IMAGE_EN(p.getProductImageENGB());
		    	pms.set_PRODUCT_IMAGE_HK(p.getProductImageZHHK());
		    	//pms.set_BRAND_IMAGE_EN(productAttributeService.getProductAttributeEN(p.getProductId()).getProductImage());
		    	//pms.set_BRAND_IMAGE_HK(productAttributeService.getProductAttributeHK(p.getProductId()).getProductImage());
	    		
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
