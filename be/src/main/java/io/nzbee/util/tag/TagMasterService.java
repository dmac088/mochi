package io.nzbee.util.tag;

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
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.entity.tag.attribute.TagAttributeEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
@Transactional
public class TagMasterService {

	private static final Logger logger = LoggerFactory.getLogger(TagMasterService.class);
	
	@Autowired
	private ITagService tagService; 
	
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
		
		TagEntity tCN = mapToTag(	t.get_TAG_CODE(),
						 			t.get_TAG_DESC_HK(),
						 		    Constants.localeZHHK);
		
		tagService.save(tCN);
		
		TagEntity tEN = mapToTag(	 t.get_TAG_CODE(),
									 t.get_TAG_DESC_EN(),
									 Constants.localeENGB);
	
		tagService.save(tEN);
	}
	
	private TagEntity mapToTag(
			String tagCode,
			String tagDesc,
			String locale
			) {
		
		Optional<TagEntity> ot = tagService.findByCode(tagCode);
		
		TagEntity t = 
				(ot.isPresent())
				? ot.get() 
				: new TagEntity();
				
		TagAttributeEntity ta = new TagAttributeEntity();
		
		if(ot.isPresent()) {
			Optional<TagAttributeEntity> ota =
			ot.get().getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findFirst();
			ta = (ota.isPresent()) 
			? ota.get()
			: new TagAttributeEntity();
		}		
		
		ta.setTagDesc(tagDesc);
		ta.setLclCd(locale);
		ta.setTag(t);
		
		t.setTagCode(tagCode.toUpperCase());
		t.setLocale(locale);
		t.addTagAttribute(ta);
		
		return t;
	}
	
	public void extractTagMaster(Resource resource) {
		logger.debug("called extractTagMaster() ");
		List<TagMasterSchema> lpms = new ArrayList<TagMasterSchema>();
	    try {
		    	List<TagEntity> tagList = new ArrayList<TagEntity>(tagService.findAll());
		    	
		    	//create a map of categories (full list)
		    	Map<String, TagMasterSchema> map = tagList.stream().collect(Collectors.toMap(c -> c.getTagCode(), c -> new TagMasterSchema()));
		 
		    	lpms.addAll(tagList.stream().map(t -> {
		    		
		    	TagMasterSchema tms = map.get(t.getTagCode());
		    		
			    Optional<TagEntity> tag = tagService.findByCode(t.getTagCode()); 
			    	
			    tms.set_TAG_CODE(tag.get().getTagCode());
			    tms.set_TAG_DESC_EN(tag.get().getTagDescENGB());
			    tms.set_TAG_DESC_HK(tag.get().getTagDescZHHK());
			    	
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
