package io.nzbee.external;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import io.nzbee.entity.brand.BrandAttributeService;
import io.nzbee.entity.category.CategoryAttributeService;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.ProductAttributeService;
import io.nzbee.entity.product.ProductPriceService;
import io.nzbee.entity.product.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
@Transactional
public class ProductMasterService {
	
	@Autowired
	@Qualifier("productEntityService")
	private ProductService productService;
	
	@Autowired
	private BrandAttributeService brandService; 
	
	@Autowired
	private ProductPriceService productPriceService;
	
	@Autowired 
	private ProductAttributeService productAttributeService;
	
	@Autowired 
	private CategoryAttributeService categoryAttributeService;
	
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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
	
	public void writeProductMaster() {
	    try {
	    
	    	List<Product> products = productService.getProducts();
	    	
	    	List<ProductMasterSchema> lpms = products.stream().map(p -> {
	    		ProductMasterSchema pms = new ProductMasterSchema();
	    		pms.set_PRODUCT_UPC_CODE(p.getProductUPC());
	    		pms.set_PRODUCT_CREATED_DATE(format.format(p.getProductCreateDt()));
	    		pms.set_PRODUCT_DESCRIPTION_EN(productAttributeService.getProductAttributeEN(p.getProductId()).getProductDesc());
	    		pms.set_PRODUCT_DESCRIPTION_HK(productAttributeService.getProductAttributeHK(p.getProductId()).getProductDesc());
	    		pms.set_PRODUCT_RETAIL_PRICE_USD(productPriceService.getCurrentRetailPriceUSD(p.getProductId()).getPriceValue());
	    		pms.set_PRODUCT_RETAIL_PRICE_HKD(productPriceService.getCurrentRetailPriceHKD(p.getProductId()).getPriceValue());
	    		pms.set_PRODUCT_MARKDOWN_PRICE_USD(productPriceService.getCurrentMarkdownPriceUSD(p.getProductId()).getPriceValue());
	    		pms.set_PRODUCT_MARKDOWN_PRICE_HKD(productPriceService.getCurrentMarkdownPriceHKD(p.getProductId()).getPriceValue());
	    		pms.set_BRAND_DESCRIPTION_EN(brandService.getBrandAttributesEN(p.getBrand().getBrandId()).getBrandDesc());
	    		pms.set_BRAND_DESCRIPTION_HK(brandService.getBrandAttributesHK(p.getBrand().getBrandId()).getBrandDesc());
	    		pms.set_PRIMARY_CATEGORY_PATH("\\TBC");
	    		pms.set_PRODUCT_IMAGE_EN(productAttributeService.getProductAttributeEN(p.getProductId()).getProductImage());
	    		pms.set_PRODUCT_IMAGE_HK(productAttributeService.getProductAttributeHK(p.getProductId()).getProductImage());
	    		//pms.set_BRAND_IMAGE_EN(productAttributeService.getProductAttributeEN(p.getProductId()).getProductImage());
	    		//pms.set_BRAND_IMAGE_HK(productAttributeService.getProductAttributeHK(p.getProductId()).getProductImage());
	    		
	    		return pms;
	    	}).collect(Collectors.toList());
	    	
	    	CsvMapper mapper = new CsvMapper(); 
	        CsvSchema schema = mapper.schemaFor(ProductMasterSchema.class);
	        schema = schema.withHeader();
	        schema = schema.withColumnSeparator(',');
	        
	        ObjectWriter myObjectWriter = mapper.writer(schema);
	        File tempFile = new ClassPathResource("data/product_master.dat").getFile();
	        FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
	        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
	        OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
	        myObjectWriter.writeValue(writerOutputStream, lpms);
	  
	    } catch (Exception e) {
	        //logger.error("Error occurred while loading object list from file " + fileName, e);
	        System.out.println(e);
	    }
	}
	
}
