package io.nzbee.util.promotion.mechanic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.promotion.mechanic.PromotionMechanic;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionMechanicMasterService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionMechanicMasterService.class);
	
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
	        MappingIterator<PromotionMechanicMasterSchema> readValues =
	        	mapper.readerFor(PromotionMechanicMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
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
	
	public void persistPromotionMechanicMaster(PromotionMechanicMasterSchema pm) {
		logger.debug("called persistCategoryMaster() ");
		
		PromotionMechanic promotionMechanic;

		categoryService.save(cCN);
	}
	
	private CategoryProduct mapToProductCategory(String categoryCode,
												 Long   categoryLevel,
												 String parentCategoryCode,
												 String categoryDesc,
												 String locale
												 ) {
		logger.debug("called mapToProductCategory() ");
		
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
		logger.debug("called mapToBrandCategory() ");
		
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
		List<PromotionMechanicMasterSchema> lpms = new ArrayList<PromotionMechanicMasterSchema>();
	    try {
		    	List<Category> categoryList = new ArrayList<Category>(categoryService.findAll());
		    	
		    	//create a map of categories (full list)
		    	Map<String, PromotionMechanicMasterSchema> map = categoryList
		    												.stream().collect(Collectors.toMap(c -> c.getCategoryCode(), c -> new PromotionMechanicMasterSchema()));
		 
		    	lpms.addAll(categoryList.stream().map(c -> {
		    		
		    		PromotionMechanicMasterSchema cms = map.get(c.getCategoryCode());
		    		
				    cms.set_CATEGORY_CODE(c.getCategoryCode());
				    cms.set_CATEGORY_TYPE(c.getCategoryType().getCategoryTypeCode());
			    
				    if(c.getCategoryType().getCategoryTypeDesc().equals(CategoryProduct.class.getSimpleName().toLowerCase())) {
				    	cms.set_PARENT_CATEGORY_CODE(((CategoryProduct) c).getCategoryParentCode());
				    	cms.set_CATEGORY_LEVEL(((CategoryProduct) c).getCategoryLevel().toString());
				    }
			    	
				    cms.set_CATEGORY_DESC_EN(c.getCategoryDescENGB());
				    cms.set_CATEGORY_DESC_HK(c.getCategoryDescZHHK());
			    	
				    return cms;
		    	}).collect(Collectors.toSet()));
	    	
		    	CsvMapper mapper = new CsvMapper(); 
		    	CsvSchema schema = mapper.schemaFor(PromotionMechanicMasterSchema.class)
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
