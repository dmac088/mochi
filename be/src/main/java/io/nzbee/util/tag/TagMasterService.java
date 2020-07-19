package io.nzbee.util.tag;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import io.nzbee.Globals;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.util.FileStorageServiceUpload;

@Service
@Transactional
public class TagMasterService {

	private static final Logger logger = LoggerFactory.getLogger(TagMasterService.class);
	
	@Autowired
	private Globals globalVars;
	
	@Autowired
	private ITagPortService categoryDomainService; 
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
	public void writeTagMaster(String fileName) {
		logger.debug("called writeTagMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<TagMasterSchema> readValues =
	        	mapper.readerFor(TagMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(c -> {
	        	this.persistTagMaster(c);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistTagMaster(TagMasterSchema t) {
		logger.debug("called persistTagMaster() ");
		
		Tag tDo = 
				categoryDomainService.findByCode(   globalVars.getLocaleENGB(), 
													globalVars.getCurrencyHKD(), 
													t.get_TAG_CODE());
		
		categoryDomainService.save(tDo);

	}
	
	public void extractTagMaster(Resource resource) {
		logger.debug("called extractTagMaster() ");
		List<TagMasterSchema> lpms = new ArrayList<TagMasterSchema>();
	    try {
		    	List<io.nzbee.domain.tag.Tag> categoryList = categoryDomainService.findAll(globalVars.getLocaleENGB(),
		    														  		globalVars.getCurrencyHKD())
		    							  						   .stream().collect(Collectors.toList());
		    	
		    	//create a map of categories (full list)
		    	Map<String, TagMasterSchema> map = categoryList
		    												.stream().collect(Collectors.toMap(c -> c.getTagCode(), c -> new TagMasterSchema()));
		 
		    	
		    	categoryList.addAll(categoryDomainService.findAll(globalVars.getLocaleZHHK(),
																  globalVars.getCurrencyUSD())
		    											.stream().collect(Collectors.toList()));
		    	
		    	lpms.addAll(categoryList.stream().map(c -> {
		    		
		    	TagMasterSchema cms = map.get(c.getTagCode());
		    		
			    Tag cat = categoryDomainService.findByCode(c.getLocale(),
																c.getCurrency(), 
																c.getTagCode()); 
			    	
			    cms.set_TAG_CODE(cat.getTagCode());
			    	
			    if (c.getLocale().equals(globalVars.getLocaleENGB())) {
			    	cms.set_TAG_DESC_EN(cat.getTagDesc());
			    }
			    	
			    if (c.getLocale().equals(globalVars.getLocaleZHHK())) {
			    	cms.set_TAG_DESC_HK(cat.getTagDesc());
			    }
			    	
			    return cms;
		    }).collect(Collectors.toSet()));
	    	
	    	CsvMapper mapper = new CsvMapper(); 
	        CsvSchema schema = mapper.schemaFor(TagMasterSchema.class)
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
