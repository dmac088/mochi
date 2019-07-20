package io.nzbee.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import io.nzbee.entity.brand.BrandService;
import io.nzbee.entity.brand.BrandAttributeService;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.ProductAttribute;
import io.nzbee.entity.product.ProductAttributeService;
import io.nzbee.entity.product.ProductPriceService;
import io.nzbee.entity.product.ProductService;
import io.nzbee.variables.GeneralVars;


@Service
@Transactional
public class ProductMasterService {
	
	@Autowired
	@Qualifier("productEntityService")
	private ProductService productService;
	
	@Autowired
	@Qualifier("brandEntityService")
	private BrandService brandService; 
	
	@Autowired
	private BrandAttributeService brandAttributeService; 
	
	@Autowired
	private ProductPriceService productPriceService;
	
	@Autowired 
	private ProductAttributeService productAttributeService;
	
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
	
	public io.nzbee.domain.Product persistProductMaster(ProductMasterSchema p) {
		
		Product product = null;
		Optional<Product> oProduct = productService.getProduct(p.get_PRODUCT_UPC_CODE());
		if (!oProduct.isPresent()) {
			product = oProduct.get();
		} else {
			product = new Product();
		}
		
		product.setUPC(p.get_PRODUCT_UPC_CODE());
		try {
			product.setProductCreateDt(new SimpleDateFormat(GeneralVars.DEFAULT_DATE_FORMAT).parse(p.get_PRODUCT_CREATED_DATE()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<ProductAttribute> lpa = new ArrayList<ProductAttribute>();
		//product attribute english
		ProductAttribute productAttributeEN = new ProductAttribute(); 
		productAttributeEN.setProductDesc(p.get_PRODUCT_DESCRIPTION_EN());
		productAttributeEN.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		productAttributeEN.setProductImage(p.get_PRODUCT_IMAGE_EN());
		productAttributeEN.setProduct(product);
		lpa.add(productAttributeEN);
		
		ProductAttribute productAttributeHK = new ProductAttribute(); 
		productAttributeHK.setProductDesc(p.get_PRODUCT_DESCRIPTION_HK());
		productAttributeHK.setLclCd(GeneralVars.LANGUAGE_HK);
		productAttributeHK.setProductImage(p.get_PRODUCT_IMAGE_HK());
		productAttributeHK.setProduct(product);
		lpa.add(productAttributeHK);
		
		product.setAttributes(lpa);
		
		//Brand brand = brandService.getBrand(p.get_brand)
		
		return null;
	}
	
	public void extractProductMaster(Resource resource) {
		List<ProductMasterSchema> lpms = new ArrayList<ProductMasterSchema>();
	    try {
	    
	    	List<Product> products = productService.getProducts();
	    	
	    	 	lpms.addAll(products.stream().map(p -> {
	    		ProductMasterSchema pms = new ProductMasterSchema();
	    		pms.set_PRODUCT_UPC_CODE(p.getUPC());
	    		pms.set_PRODUCT_CREATED_DATE(format.format(p.getProductCreateDt()));
	    		pms.set_PRODUCT_DESCRIPTION_EN(productAttributeService.getProductAttributeEN(p.getProductId()).getProductDesc());
	    		pms.set_PRODUCT_DESCRIPTION_HK(productAttributeService.getProductAttributeHK(p.getProductId()).getProductDesc());
	    		pms.set_PRODUCT_RETAIL_PRICE_USD(productPriceService.getCurrentRetailPriceUSD(p.getProductId()).getPriceValue());
	    		pms.set_PRODUCT_RETAIL_PRICE_HKD(productPriceService.getCurrentRetailPriceHKD(p.getProductId()).getPriceValue());
	    		pms.set_PRODUCT_MARKDOWN_PRICE_USD(productPriceService.getCurrentMarkdownPriceUSD(p.getProductId()).getPriceValue());
	    		pms.set_PRODUCT_MARKDOWN_PRICE_HKD(productPriceService.getCurrentMarkdownPriceHKD(p.getProductId()).getPriceValue());
	    		pms.set_BRAND_CODE(brandService.getBrand(p.getBrand().getId()).get().getCode());
	    		pms.set_BRAND_DESCRIPTION_EN(brandAttributeService.getBrandAttributesEN(p.getBrand().getId()).getBrandDesc());
	    		pms.set_BRAND_DESCRIPTION_HK(brandAttributeService.getBrandAttributesHK(p.getBrand().getId()).getBrandDesc());
	    		pms.set_PRIMARY_CATEGORY_PATH("\\TBC");
	    		pms.set_PRODUCT_IMAGE_EN(productAttributeService.getProductAttributeEN(p.getProductId()).getProductImage());
	    		pms.set_PRODUCT_IMAGE_HK(productAttributeService.getProductAttributeHK(p.getProductId()).getProductImage());
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
