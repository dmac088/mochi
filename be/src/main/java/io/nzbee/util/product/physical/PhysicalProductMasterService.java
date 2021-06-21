package io.nzbee.util.product.physical;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.physical.PhysicalProductEntity;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PhysicalProductMasterService {

	private static final Logger logger = LoggerFactory.getLogger(PhysicalProductMasterService.class);
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryProductService categoryService;

	@Autowired
	private ITagService tagService;
	
	@Autowired
	private IProductStatusRepository productStatusService;
	
	@Autowired
	private IBrandService brandService;
	
	@Autowired 
	private IDepartmentService departmentService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private IProductPriceTypeService productPriceTypeService;
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    //cached lists for performance improvement
    private List<BrandEntity> cachedBrandList = new ArrayList<BrandEntity>();
    
    private List<DepartmentEntity> cachedDepartmentList = new ArrayList<DepartmentEntity>();
    
    private List<ProductStatusEntity> cachedProductStatusList = new ArrayList<ProductStatusEntity>();
    
    private List<CategoryProductEntity> cachedProductCategoryList = new ArrayList<CategoryProductEntity>();
    
    private List<ProductPriceType> cachedPriceTypes = new ArrayList<ProductPriceType>();
    
    private List<Currency> cachedCurrencies  = new ArrayList<Currency>();
    
    private List<TagEntity> cachedTags = new ArrayList<TagEntity>();
    
	//@Transactional
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
	        
	        MappingIterator<PhysicalProductMasterSchema> readValues =
	        	mapper.readerFor(PhysicalProductMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        cachedBrandList.addAll(brandService.findAll());
	        cachedDepartmentList.addAll(departmentService.findAll());
	        cachedProductStatusList.addAll(productStatusService.findAll());
	        cachedProductCategoryList.addAll(categoryService.findAll());
	        cachedCurrencies.addAll(currencyService.findAll());
	        cachedPriceTypes.addAll(productPriceTypeService.findAll());
	        cachedTags.addAll(tagService.findAll());
	        
	        readValues.readAll().stream().forEach(p -> {
	        	this.persistProductMaster(p);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistProductMaster(PhysicalProductMasterSchema p) {
		
		//english with USD
		PhysicalProductEntity pe = map(
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
				 p.get_TAG_CODE_E(),
				 p.get_WIDTH(),
				 p.get_HEIGHT(),
				 p.get_LENGTH(),
				 p.get_WEIGHT()
		);
		productService.save(Constants.localeENGB, Constants.currencyUSD, pe);
		
		pe = map(
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
				 p.get_TAG_CODE_E(),
				 p.get_WIDTH(),
				 p.get_HEIGHT(),
				 p.get_LENGTH(),
				 p.get_WEIGHT()
		);
		productService.save(Constants.localeZHHK, Constants.currencyHKD, pe);
		
	}
	
	
	private PhysicalProductEntity map(	 String locale, 
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
										 String tagCodeE,
										 String width,
										 String height,
										 String length,
										 String weight
						 ) {
		logger.debug("called map() ");
		
		Optional<ProductEntity> op 				= productService.findEntityByCode(upcCode);
		
		PhysicalProductEntity pe 				= (op.isPresent()) 
									 			? (PhysicalProductEntity) op.get()
									 			: new PhysicalProductEntity();
		
		Optional<BrandEntity> ob 				= cachedBrandList.stream().filter(b -> b.getBrandCode().equals(brandCode)).findAny();
		
		Optional<DepartmentEntity> od 			= cachedDepartmentList.stream().filter(d -> d.getDepartmentCode().equals(templateCode)).findAny();
		
		Optional<ProductStatusEntity> ops 		= cachedProductStatusList.stream().filter(ps -> ps.getCode().equals(Constants.activeSKUCode)).findAny();
		
		Optional<CategoryProductEntity> opc 	= cachedProductCategoryList.stream().filter(pc -> pc.getCategoryCode().equals(categoryCode)).findAny();
				
		Optional<ProductAttributeEntity> opa 	= 	(op.isPresent()) 
				 									? op.get().getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findAny().isPresent()
				 									  ? op.get().getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findAny()
				 									  : Optional.ofNullable(new ProductAttributeEntity())
				 									: Optional.ofNullable(new ProductAttributeEntity());
		
		LocalDateTime createdDate = LocalDateTime.parse(productCreateDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					  
		pe.setBrand(ob.get());
		pe.setDepartment(od.get());
		pe.setProductUPC(upcCode);
		pe.setProductCreateDt(createdDate);
		pe.setProductStatus(ops.get());
		pe.getCategories();
		pe.addCategory(opc.get());
		
		pe.setWidthDimension(Integer.parseInt(width));
		pe.setHeightDimension(Integer.parseInt(height));
		pe.setLengthDimension(Integer.parseInt(length));
		pe.setWeightDimension(Double.parseDouble(weight));

		opa.get().setProductDesc(productDesc);
		opa.get().setProductLongDesc(productLongDesc);
		opa.get().setLclCd(locale);
		
		opa.get().setProduct(pe);
		pe.addProductAttribute(opa.get());
		
		Optional<Currency> ocurr 		= cachedCurrencies.stream().filter(c -> c.getCode().equals(currency)).findAny();
		
		Optional<ProductPriceType> ptr 	= cachedPriceTypes.stream().filter(pt -> pt.getCode().equals(Constants.retailPriceCode)).findAny();
		
		Optional<ProductPriceType> ptm 	= cachedPriceTypes.stream().filter(pt -> pt.getCode().equals(Constants.markdownPriceCode)).findAny();

		Optional<ProductPriceEntity> oprcr = pe.getPrices().stream()
												.filter(p -> p.getType().getCode().equals(Constants.retailPriceCode) && p.getCurrency().getCode().equals(currency)).findAny();

		//retail price
		ProductPriceEntity prcr = (	oprcr.isPresent()) 
									? oprcr.get()
									: new ProductPriceEntity();

		prcr.setType(ptr.get());
		prcr.setCurrency(ocurr.get());
		prcr.setPriceValue(retailPrice);

		Optional<ProductPriceEntity> oprcm = pe.getPrices().stream()
												.filter(p -> p.getType().getCode().equals(Constants.markdownPriceCode) && p.getCurrency().getCode().equals(currency)).findAny();

		//markdown price
		ProductPriceEntity prcm = (oprcm.isPresent()) 
							? oprcm.get()
							: new ProductPriceEntity();

		prcm.setType(ptm.get());
		prcm.setCurrency(ocurr.get());
		prcm.setPriceValue(markdownPrice);
		
		pe.setProductStatus(ops.get());
		pe.addProductPrice(prcr);
		pe.addProductPrice(prcm);
		
		//add the tags to the domain object
		addTagToProduct(tagCodeA, pe);
		addTagToProduct(tagCodeB, pe);
		addTagToProduct(tagCodeC, pe);
		addTagToProduct(tagCodeD, pe);
		addTagToProduct(tagCodeE, pe);
		
		return pe;
	}
	
	private void addTagToProduct(String tagCode, ProductEntity p) {
		if (tagCode == null) return;
		if(tagCode.length() == 5) {
			Optional<TagEntity> ot = cachedTags.stream().filter(t -> t.getTagCode().equals(tagCode)).findAny();
			if(ot.isPresent()) {
				p.addTag(ot.get());
			}
		}
	}
	
}
