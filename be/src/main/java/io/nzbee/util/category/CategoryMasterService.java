package io.nzbee.util.category;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Constants;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.accessories.Accessories;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.department.Department;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.util.FileStorageServiceUpload;

@Service
@Transactional
public class CategoryMasterService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryMasterService.class);
	
	@Autowired
	private ICategoryService categoryService;
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
	public void writeCategoryMaster(String fileName) {
		logger.debug("called writeCategoryMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<CategoryMasterSchema> readValues =
	        	mapper.readerFor(CategoryMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(c -> {
	        	this.persistCategoryMaster(c);
	        });
	        
	        
	        Set<Category> lc = categoryService.findAll();
	        lc.stream().forEach(c -> {
	        	if(!(c.getCategoryParentCode() == null)) {
	        		Optional<io.nzbee.entity.category.Category> opc = categoryService.findByCode(c.getCategoryParentCode());
	        		if(opc.isPresent()) {
	        			c.setCategoryParentId(opc.get().getCategoryId());
	        			categoryService.save(c);
	        		}
	        	}
	        });
	        
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	@Transactional
	public void persistCategoryMaster(CategoryMasterSchema c) {
		logger.debug("called persistCategoryMaster() ");
		
		Category cEN = c.get_CATEGORY_TYPE().equals("productcategory")
						? mapToProductCategory(c.get_CATEGORY_CODE(),
											   new Long(c.get_CATEGORY_LEVEL()),
											   c.get_PARENT_CATEGORY_CODE(),
											   c.get_CATEGORY_DESC_EN(),
											   Constants.localeENGB)
						: mapToBrandCategory(c.get_CATEGORY_CODE(),
											 c.get_CATEGORY_DESC_EN(),
											 Constants.localeENGB);

		categoryService.save(cEN);
		
		Category cCN =	c.get_CATEGORY_TYPE().equals("productcategory")
				?  mapToProductCategory(
						c.get_CATEGORY_CODE(),
						new Long(c.get_CATEGORY_LEVEL()),
						c.get_CATEGORY_DESC_HK(),
						c.get_PARENT_CATEGORY_CODE(),
						Constants.localeZHHK)
				:  mapToBrandCategory(
						c.get_CATEGORY_CODE(),
						c.get_CATEGORY_DESC_HK(),
						Constants.localeZHHK);

		categoryService.save(cCN);
	}
	
	private CategoryProduct mapToProductCategory(String categoryCode,
												 Long   categoryLevel,
												 String parentCategoryCode,
												 String categoryDesc,
												 String locale
												 ) {
		Optional<Category> oc = categoryService.findByCode(categoryCode);
		
		CategoryProduct cp = (oc.isPresent()) 
							 ? (CategoryProduct) Hibernate.unproxy(oc.get())
							 : new CategoryProduct();
							 
		CategoryAttribute ca = new CategoryAttribute();
		if (oc.isPresent()) {
			Optional<CategoryAttribute> oca = cp.getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findFirst();
			ca = (oca.isPresent()) 
				? oca.get()
				: new CategoryAttribute();
		}
		
		cp.setCategoryCode(categoryCode);
		cp.setCategoryLevel(categoryLevel);
		cp.setCategoryParentCode(parentCategoryCode);
		
		ca.setCategoryDesc(categoryDesc);
		ca.setLclCd(locale);
		ca.setCategory(cp);
		cp.addCategoryAttribute(ca);
		
		return cp;
	}
	
	private CategoryBrand mapToBrandCategory(String categoryCode,
											 String categoryDesc,
											 String locale
											) {
		Optional<Category> oc = 
				categoryService.findByCode(locale, categoryCode);
		
		CategoryBrand cb = (oc.isPresent()) 
				 ? (CategoryBrand) oc.get()
				 : new CategoryBrand();

		CategoryAttribute ca = (oc.isPresent()) 
				 ? cb.getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findFirst().get()
				 : new CategoryAttribute();
		
		ca.setCategoryDesc(categoryDesc);
		ca.setLclCd(locale);
		ca.setCategory(cb);
		
		cb.setCategoryCode(categoryCode);
		cb.addCategoryAttribute(ca);
		cb.setCategoryAttribute(ca);
		
		return cb;
	}
	
	public void extractCategoryMaster(Resource resource) {
		logger.debug("called extractCategoryMaster() ");
		List<CategoryMasterSchema> lpms = new ArrayList<CategoryMasterSchema>();
	    try {
		    	List<Category> categoryList = categoryService.findAll(Constants.localeENGB)
		    							  						   .stream().collect(Collectors.toList());
		    	
		    	//create a map of categories (full list)
		    	Map<String, CategoryMasterSchema> map = categoryList
		    												.stream().collect(Collectors.toMap(c -> c.getCategoryCode(), c -> new CategoryMasterSchema()));
		 
		    	
		    	categoryList.addAll(categoryService.findAll(Constants.localeZHHK)
		    											.stream().collect(Collectors.toList()));
		    	
		    	lpms.addAll(categoryList.stream().map(c -> {
		    		
		    	CategoryMasterSchema cms = map.get(c.getCategoryCode());
		    		
			    Optional<Category> cat = categoryService.findByCode(c.getLocale(),
																c.getCategoryCode()); 
			    	
			    cms.set_CATEGORY_CODE(cat.get().getCategoryCode());
			    cms.set_CATEGORY_TYPE(cat.get().getCategoryType());
			    
			    if(cat.get().getCategoryType().equals(ProductCategory.class.getSimpleName().toLowerCase())) {
			    	cms.set_PARENT_CATEGORY_CODE(((ProductCategory) c).getParentCode());
			    	cms.set_CATEGORY_LEVEL(((ProductCategory) c).getCategoryLevel().toString());
			    }
			    	
			    if (c.getLocale().equals(Constants.localeENGB)) {
			    	cms.set_CATEGORY_DESC_EN(cat.get().getCategoryDescENGB());
			    }
			    	
			    if (c.getLocale().equals(Constants.localeZHHK)) {
			    	cms.set_CATEGORY_DESC_HK(cat.get().getCategoryDescZHHK());
			    }
			    	
			    return cms;
		    }).collect(Collectors.toSet()));
	    	
	    	CsvMapper mapper = new CsvMapper(); 
	        CsvSchema schema = mapper.schemaFor(CategoryMasterSchema.class)
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
