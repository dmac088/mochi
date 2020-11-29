package io.nzbee.util.category;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Constants;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class CategoryMasterService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryMasterService.class);
	
	@Autowired
	private ICategoryService categoryService;
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    @Transactional
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
	        
	        
	        List<CategoryEntity> lc = categoryService.findAll();
	        lc.stream().forEach(c -> {
	        	if(!(c.getCategoryParentCode() == null)) {
	        		Optional<io.nzbee.entity.category.CategoryEntity> opc = categoryService.findByCode(c.getCategoryParentCode());
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
	
	public void persistCategoryMaster(CategoryMasterSchema c) {
		logger.debug("called persistCategoryMaster() ");
		
		CategoryEntity cEN = c.get_CATEGORY_TYPE().equals("productcategory")
						? mapToProductCategory(c.get_CATEGORY_CODE(),
											   new Long(c.get_CATEGORY_LEVEL()),
											   c.get_PARENT_CATEGORY_CODE(),
											   c.get_CATEGORY_DESC_EN(),
											   Constants.localeENGB)
						: mapToBrandCategory(c.get_CATEGORY_CODE(),
											 c.get_CATEGORY_DESC_EN(),
											 Constants.localeENGB);

		categoryService.save(cEN);
		
		CategoryEntity cCN =	c.get_CATEGORY_TYPE().equals("productcategory")
				?  mapToProductCategory(
						c.get_CATEGORY_CODE(),
						new Long(c.get_CATEGORY_LEVEL()),
						c.get_PARENT_CATEGORY_CODE(),
						c.get_CATEGORY_DESC_HK(),
						Constants.localeZHHK)
				:  mapToBrandCategory(
						c.get_CATEGORY_CODE(),
						c.get_CATEGORY_DESC_HK(),
						Constants.localeZHHK);

		categoryService.save(cCN);
	}
	
	private CategoryProductEntity mapToProductCategory(String categoryCode,
												 Long   categoryLevel,
												 String parentCategoryCode,
												 String categoryDesc,
												 String locale
												 ) {
		logger.debug("called mapToProductCategory() ");
		
		Optional<CategoryEntity> oc = categoryService.findByCode(categoryCode);
		
		CategoryProductEntity cp = (oc.isPresent()) 
							 ? (CategoryProductEntity) Hibernate.unproxy(oc.get())
							 : new CategoryProductEntity();
							 
		CategoryAttributeEntity ca = new CategoryAttributeEntity();
		if (oc.isPresent()) {
			Optional<CategoryAttributeEntity> oca = cp.getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findFirst();
			ca = (oca.isPresent()) 
				? oca.get()
				: new CategoryAttributeEntity();
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
	
	private CategoryBrandEntity mapToBrandCategory(	String categoryCode,
											 		String categoryDesc,
											 		String locale
											) {
		logger.debug("called mapToBrandCategory() ");
		
		Optional<CategoryEntity> oc = categoryService.findByCode(categoryCode);
		
		CategoryBrandEntity cb = (oc.isPresent()) 
				 ? (CategoryBrandEntity) oc.get()
				 : new CategoryBrandEntity();

		CategoryAttributeEntity ca = (oc.isPresent()) 
				 ? cb.getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findFirst().get()
				 : new CategoryAttributeEntity();
		
		ca.setCategoryDesc(categoryDesc);
		ca.setLclCd(locale);
		ca.setCategory(cb);
		
		cb.setCategoryCode(categoryCode);
		cb.addCategoryAttribute(ca);
		cb.setCategoryAttribute(ca);
		
		return cb;
	}
	
//	public void extractCategoryMaster(Resource resource) {
//		logger.debug("called extractCategoryMaster() ");
//		List<CategoryMasterSchema> lpms = new ArrayList<CategoryMasterSchema>();
//	    try {
//		    	List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>(categoryService.findAll());
//		    	
//		    	//create a map of categories (full list)
//		    	Map<String, CategoryMasterSchema> map = categoryList
//		    												.stream().collect(Collectors.toMap(c -> c.getCategoryCode(), c -> new CategoryMasterSchema()));
//		 
//		    	lpms.addAll(categoryList.stream().map(c -> {
//		    		
//		    		CategoryMasterSchema cms = map.get(c.getCategoryCode());
//		    		
//				    cms.set_CATEGORY_CODE(c.getCategoryCode());
//				    cms.set_CATEGORY_TYPE(c.getCategoryType().getCategoryTypeCode());
//			    
//				    if(c.getCategoryType().getCategoryTypeDesc().equals(CategoryProductEntity.class.getSimpleName().toLowerCase())) {
//				    	cms.set_PARENT_CATEGORY_CODE(((CategoryProductEntity) c).getCategoryParentCode());
//				    	cms.set_CATEGORY_LEVEL(((CategoryProductEntity) c).getCategoryLevel().toString());
//				    }
//			    	
//				    cms.set_CATEGORY_DESC_EN(c.getCategoryDescENGB());
//				    cms.set_CATEGORY_DESC_HK(c.getCategoryDescZHHK());
//			    	
//				    return cms;
//		    	}).collect(Collectors.toSet()));
//	    	
//		    	CsvMapper mapper = new CsvMapper(); 
//		    	CsvSchema schema = mapper.schemaFor(CategoryMasterSchema.class)
//	        		.withHeader()
//	        		.withColumnSeparator('\t')
//	        		.withQuoteChar('"');
//	        
//		        ObjectWriter myObjectWriter = mapper.writer(schema);
//		        String ow = myObjectWriter.writeValueAsString(lpms);
//		        PrintWriter out = new PrintWriter(resource.getFile().getAbsolutePath());
//		        out.write(ow);
//		        out.flush();
//		        out.close();
//	        
//	    } catch (Exception e) {
//	        logger.error("Error occurred while loading object list from file " + resource.getFilename(), e);
//	    }
//	}
	
}
