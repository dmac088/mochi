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
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.BrandAttribute;
import io.nzbee.entity.brand.BrandAttributeService;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.ProductAttribute;
import io.nzbee.entity.product.ProductAttributeService;
import io.nzbee.entity.product.ProductPrice;
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
		
		
		Optional<Product> oProduct = productService.getProduct(p.get_PRODUCT_UPC_CODE());
		Product product = oProduct.isPresent() ? oProduct.get() : new Product();
		product.setUPC(p.get_PRODUCT_UPC_CODE());
		
		//parsing dates is unsafe
		try {
			product.setProductCreateDt(new SimpleDateFormat(GeneralVars.DEFAULT_DATE_FORMAT).parse(p.get_PRODUCT_CREATED_DATE()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<ProductAttribute> lpa = new ArrayList<ProductAttribute>();
		//Product Attribute English
		Optional<ProductAttribute> oProductAttributeEN = productAttributeService.getProductAttribute(product.getProductId(), GeneralVars.LANGUAGE_ENGLISH);
		ProductAttribute productAttributeEN = oProductAttributeEN.isPresent() ? oProductAttributeEN.get() : new ProductAttribute();
		productAttributeEN.setProductDesc(p.get_PRODUCT_DESCRIPTION_EN());
		productAttributeEN.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		productAttributeEN.setProductImage(p.get_PRODUCT_IMAGE_EN());
		productAttributeEN.setProduct(product);
		lpa.add(productAttributeEN);
		
		//Product Attribute Hong Kong
		Optional<ProductAttribute> oProductAttributeHK = productAttributeService.getProductAttribute(product.getProductId(), GeneralVars.LANGUAGE_HK);
		ProductAttribute productAttributeHK = oProductAttributeHK.isPresent() ? oProductAttributeHK.get() : new ProductAttribute();
		productAttributeHK.setProductDesc(p.get_PRODUCT_DESCRIPTION_HK());
		productAttributeHK.setLclCd(GeneralVars.LANGUAGE_HK);
		productAttributeHK.setProductImage(p.get_PRODUCT_IMAGE_HK());
		productAttributeHK.setProduct(product);
		lpa.add(productAttributeHK);
		
		product.setAttributes(lpa);
		
		
		//Brand
		Optional<Brand> oBrand = brandService.getBrand(p.get_BRAND_CODE());
		Brand brand = oBrand.isPresent() ? oBrand.get() : new Brand();
		brand.setCode(p.get_BRAND_CODE());
		
		List<BrandAttribute> lba = new ArrayList<BrandAttribute>();
		BrandAttribute brandAttributeEN = new BrandAttribute();
		brandAttributeEN.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		brandAttributeEN.setBrandDesc(p.get_BRAND_DESCRIPTION_EN());
		lba.add(brandAttributeEN);
		
		BrandAttribute brandAttributeHK = new BrandAttribute();
		brandAttributeHK.setLclCd(GeneralVars.LANGUAGE_HK);
		brandAttributeHK.setBrandDesc(p.get_BRAND_DESCRIPTION_HK());
		lba.add(brandAttributeEN);
		
		brand.setAttributes(lba);
		product.setBrand(brand);
		
		//Price
		Optional<ProductPrice> oPrice = productPriceService.getCurrentRetailPriceUSD(product.getProductId());
		ProductPrice price = oPrice.isPresent() ? oPrice.get() : new ProductPrice();
		
		
		
		
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
	    		pms.set_PRODUCT_DESCRIPTION_EN(productAttributeService.getProductAttributeEN(p.getProductId()).get().getProductDesc());
	    		pms.set_PRODUCT_DESCRIPTION_HK(productAttributeService.getProductAttributeHK(p.getProductId()).get().getProductDesc());
	    		pms.set_PRODUCT_RETAIL_PRICE_USD(productPriceService.getCurrentRetailPriceUSD(p.getProductId()).get().getPriceValue());
	    		pms.set_PRODUCT_RETAIL_PRICE_HKD(productPriceService.getCurrentRetailPriceHKD(p.getProductId()).get().getPriceValue());
	    		pms.set_PRODUCT_MARKDOWN_PRICE_USD(productPriceService.getCurrentMarkdownPriceUSD(p.getProductId()).get().getPriceValue());
	    		pms.set_PRODUCT_MARKDOWN_PRICE_HKD(productPriceService.getCurrentMarkdownPriceHKD(p.getProductId()).get().getPriceValue());
	    		pms.set_BRAND_CODE(brandService.getBrand(p.getBrand().getId()).get().getCode());
	    		pms.set_BRAND_DESCRIPTION_EN(brandAttributeService.getBrandAttributesEN(p.getBrand().getId()).getBrandDesc());
	    		pms.set_BRAND_DESCRIPTION_HK(brandAttributeService.getBrandAttributesHK(p.getBrand().getId()).getBrandDesc());
	    		pms.set_PRIMARY_CATEGORY_PATH("\\TBC");
	    		pms.set_PRODUCT_IMAGE_EN(productAttributeService.getProductAttributeEN(p.getProductId()).get().getProductImage());
	    		pms.set_PRODUCT_IMAGE_HK(productAttributeService.getProductAttributeHK(p.getProductId()).get().getProductImage());
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
