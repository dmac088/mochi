package io.nzbee.util.category;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.domain.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
@Transactional
public class CategoryMasterService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryMasterService.class);
	
	@Autowired
	private ICategoryPortService categoryDomainService;
	
	@Autowired
	private ICategoryService categoryEntityService;
	
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
	        
	        
	        List<io.nzbee.entity.category.Category> lc = categoryEntityService.findAll();
	        lc.stream().forEach(c -> {
	        	if(!(c.getCategoryParentCode() == null)) {
	        		Optional<io.nzbee.entity.category.Category> opc = categoryEntityService.findByCode(c.getCategoryParentCode());
	        		if(opc.isPresent()) {
	        			c.setCategoryParentId(opc.get().getCategoryId());
	        			categoryEntityService.save(c);
	        		}
	        	}
	        });
	        
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistCategoryMaster(CategoryMasterSchema c) {
		logger.debug("called persistCategoryMaster() ");
		
		Category cEN =	c.get_CATEGORY_TYPE().equals("productcategory")
						? new ProductCategory(
								c.get_CATEGORY_CODE(),
								c.get_CATEGORY_DESC_EN(),
								new Long(c.get_CATEGORY_LEVEL()),
								c.get_PARENT_CATEGORY_CODE(),
								Constants.localeENGB)
						: new BrandCategory(
								c.get_CATEGORY_CODE(),
								c.get_CATEGORY_DESC_EN(),
								Constants.localeENGB
								);
		
		categoryDomainService.save(cEN);
		
		Category cCN =	c.get_CATEGORY_TYPE().equals("productcategory")
				? new ProductCategory(
						c.get_CATEGORY_CODE(),
						c.get_CATEGORY_DESC_HK(),
						new Long(c.get_CATEGORY_LEVEL()),
						c.get_PARENT_CATEGORY_CODE(),
						Constants.localeZHHK)
				: new BrandCategory(
						c.get_CATEGORY_CODE(),
						c.get_CATEGORY_DESC_HK(),
						Constants.localeZHHK);

		categoryDomainService.save(cCN);
	}
	
	public void extractCategoryMaster(Resource resource) {
		logger.debug("called extractCategoryMaster() ");
		List<CategoryMasterSchema> lpms = new ArrayList<CategoryMasterSchema>();
	    try {
		    	List<Category> categoryList = categoryDomainService.findAll(Constants.localeENGB)
		    							  						   .stream().collect(Collectors.toList());
		    	
		    	//create a map of categories (full list)
		    	Map<String, CategoryMasterSchema> map = categoryList
		    												.stream().collect(Collectors.toMap(c -> c.getCategoryCode(), c -> new CategoryMasterSchema()));
		 
		    	
		    	categoryList.addAll(categoryDomainService.findAll(Constants.localeZHHK)
		    											.stream().collect(Collectors.toList()));
		    	
		    	lpms.addAll(categoryList.stream().map(c -> {
		    		
		    	CategoryMasterSchema cms = map.get(c.getCategoryCode());
		    		
			    Category cat = categoryDomainService.findByCode(c.getLocale(),
																c.getCategoryCode()); 
			    	
			    cms.set_CATEGORY_CODE(cat.getCategoryCode());
			    cms.set_CATEGORY_TYPE(cat.getCategoryType());
			    
			    if(cat.getCategoryType().equals(ProductCategory.class.getSimpleName().toLowerCase())) {
			    	cms.set_PARENT_CATEGORY_CODE(((ProductCategory) c).getParentCode());
			    	cms.set_CATEGORY_LEVEL(((ProductCategory) c).getCategoryLevel().toString());
			    }
			    	
			    if (c.getLocale().equals(Constants.localeENGB)) {
			    	cms.set_CATEGORY_DESC_EN(cat.getCategoryDesc());
			    }
			    	
			    if (c.getLocale().equals(Constants.localeZHHK)) {
			    	cms.set_CATEGORY_DESC_HK(cat.getCategoryDesc());
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
