package io.nzbee.util.promotion.mechanic;

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
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanic;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionMechanicMasterService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionMechanicMasterService.class);
	
	@Autowired
	private IPromotionMechanicService pmService;
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    @Transactional
	public void writePromotionMechanicMaster(String fileName) {
		logger.debug("called writePromotionMechanicMaster with parameter {} ", fileName);
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
	        
	        readValues.readAll().stream().forEach(pm -> {
	        	this.persistPromotionMechanicMaster(pm);
	        });
	        
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistPromotionMechanicMaster(PromotionMechanicMasterSchema pms) {
		logger.debug("called persistPromotionMechanicMaster() ");
		
		PromotionMechanic pm = mapToPromotionMechanic(pms.get_PROMOTION_MECHANIC_CODE(),
													  pms.get_PROMOTION_MECHANIC_DESC());

		pmService.save(pm);
	}
	
	private PromotionMechanic mapToPromotionMechanic(	 String promotionMechanicCode,
													 String promotionMechanicDesc
												 ) {
		logger.debug("called mapToPromotionMechanic() ");
		
		Optional<PromotionMechanic> oPm = pmService.findByCode(promotionMechanicCode);
		
		PromotionMechanic pm = oPm.isPresent()
							   ? oPm.get()
							   : new PromotionMechanic();
		
		pm.setPromotionMechanicCode(promotionMechanicCode);
		pm.setPromotionMechanicDesc(promotionMechanicDesc);
		
		return pm;
	}
	
	
	
}
