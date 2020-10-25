package io.nzbee.util.promotion.type;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.entity.promotion.type.IPromotionTypeService;
import io.nzbee.entity.promotion.type.PromotionType;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionTypeMasterService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionTypeMasterService.class);
	
	@Autowired
	private IPromotionTypeService pmService;
	
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
	        MappingIterator<PromotionTypeMasterSchema> readValues =
	        	mapper.readerFor(PromotionTypeMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(pm -> {
	        	this.persistPromotionTypeMaster(pm);
	        });
	        
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistPromotionTypeMaster(PromotionTypeMasterSchema pts) {
		logger.debug("called persistPromotionTypeMaster() ");
		
		io.nzbee.entity.promotion.type.PromotionType pm = mapToPromotionType(pts.get_PROMOTION_TYPE_CODE(),
																			 pts.get_PROMOTION_TYPE_DESC());

		pmService.save(pm);
	}
	
	private PromotionType mapToPromotionType(	 String promotionTypeCode,
												 String promotionTypeDesc
												 ) {
		logger.debug("called mapToPromotionType() ");
		
		Optional<PromotionType> oPm = pmService.findByCode(promotionTypeCode);
		
		PromotionType pm = oPm.isPresent()
							   ? oPm.get()
							   : new PromotionType();
		
		pm.setPromotionTypeCode(promotionTypeCode);
		pm.setPromotionTypeDesc(promotionTypeDesc);
		
		return pm;
	}
	
	
	
}
