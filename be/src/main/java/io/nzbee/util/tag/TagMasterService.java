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
import io.nzbee.Constants;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.util.FileStorageServiceUpload;

@Service
@Transactional
public class TagMasterService {

	private static final Logger logger = LoggerFactory.getLogger(TagMasterService.class);
	
	@Autowired
	private ITagPortService tagDomainService; 
	
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
				tagDomainService.findByCode(   Constants.localeENGB, 
													Constants.currencyHKD, 
													t.get_TAG_CODE());
		
		tagDomainService.save(tDo);

	}
	
	public void extractTagMaster(Resource resource) {
		logger.debug("called extractTagMaster() ");
		List<TagMasterSchema> lpms = new ArrayList<TagMasterSchema>();
	    try {
		    	List<io.nzbee.domain.tag.Tag> tagList = tagDomainService.findAll(Constants.localeENGB,
		    														  		Constants.currencyHKD)
		    							  						   .stream().collect(Collectors.toList());
		    	
		    	//create a map of categories (full list)
		    	Map<String, TagMasterSchema> map = tagList
		    												.stream().collect(Collectors.toMap(c -> c.getTagCode(), c -> new TagMasterSchema()));
		 
		    	
		    	tagList.addAll(tagDomainService.findAll(Constants.localeZHHK,
																  Constants.currencyUSD)
		    											.stream().collect(Collectors.toList()));
		    	
		    	lpms.addAll(tagList.stream().map(t -> {
		    		
		    	TagMasterSchema tms = map.get(t.getTagCode());
		    		
			    Tag tag = tagDomainService.findByCode(t.getLocale(),
																t.getCurrency(), 
																t.getTagCode()); 
			    	
			    tms.set_TAG_CODE(tag.getTagCode());
			    	
			    if (t.getLocale().equals(Constants.localeENGB)) {
			    	tms.set_TAG_DESC_EN(tag.getTagDesc());
			    }
			    	
			    if (t.getLocale().equals(Constants.localeZHHK)) {
			    	tms.set_TAG_DESC_HK(tag.getTagDesc());
			    }
			    	
			    return tms;
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
