package io.nzbee.util.promotion;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.entity.promotion.Promotion;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanic;
import io.nzbee.entity.promotion.type.IPromotionTypeService;
import io.nzbee.entity.promotion.type.PromotionType;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionMasterService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionMasterService.class);
	
	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private IPromotionMechanicService promotionTypeService;
	
	@Autowired
	private IPromotionMechanicService promotionMechanicService;
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    @Transactional
	public void writePromotionMaster(String fileName) {
		logger.debug("called writePromotionMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<PromotionMasterSchema> readValues =
	        	mapper.readerFor(PromotionMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(c -> {
	        	this.persistPromotionMaster(c);
	        });
	        
	        
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistPromotionMaster(PromotionMasterSchema pms) {
		logger.debug("called persistPromotionMaster() ");
		
		Promotion p = mapToPromotion(pms.get_PROMOTION_CODE(),
									 pms.get_PROMOTION_DESC(),
									 pms.get_PROMOTION_START_DATE(),
									 pms.get_PROMOTION_END_DATE(),
									 pms.get_PROMOTION_ACTIVE(),
									 pms.get_PROMOTION_MECHANIC_CODE(),
									 pms.get_PROMOTION_TYPE_CODE());

		promotionService.save(p);
	}
	
	private Promotion mapToPromotion(String 		promotionCode,
									 String 		promotionDesc,
									 String 		promotionStartDate,
									 String 		promotionEndDate,
									 String 		promotionActive,
									 String 		promotionMechanicCode,
									 String 		promotionTypeCode) {
		logger.debug("called mapToPromotion() ");
		
		Optional<Promotion> op = promotionService.findByCode(promotionCode);
		
		Promotion p = (op.isPresent()) 
					  ? (Promotion) Hibernate.unproxy(op.get())
					  : new Promotion();
					  
		LocalDateTime psd = LocalDateTime.parse(promotionStartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime ped = LocalDateTime.parse(promotionEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					  
		Optional<PromotionMechanic> pt = promotionTypeService.findByCode(promotionTypeCode);
		Optional<PromotionMechanic> pm = promotionMechanicService.findByCode(promotionMechanicCode);
		
		p.setPromotionCode(promotionCode);
		p.setPromotionShortDesc(promotionDesc);
		p.setPromotionStartDate(psd);
		p.setPromotionEndDate(ped);
		p.setPromotionType(pt.get());
		p.setPromotionMechanic(pm.get());
		
		return p;
	}
	
	
}
