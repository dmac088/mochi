package io.nzbee.util.promotion;

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
import io.nzbee.entity.promotion.Promotion;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionMasterService {

	private static final Logger logger = LoggerFactory.getLogger(PromotionMasterService.class);
	
	@Autowired
	private IPromotionService promotionService;
	
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
		
		Promotion cEN = mapToPromotion(
						pms.get
						pms.get_CATEGORY_DESC_HK());

		promotionService.save(cCN);
	}
	
	private Promotion mapToPromotion(String promotionCode,
									 String promotionDesc) {
		logger.debug("called mapToProductPromotion() ");
		
		Optional<Promotion> oc = promotionService.findByCode(promotionCode);
		
		PromotionProduct cp = (oc.isPresent()) 
							 ? (PromotionProduct) Hibernate.unproxy(oc.get())
							 : new PromotionProduct();
							 
		
		
		cp.setPromotionCode(promotionCode);
		cp.setPromotionLevel(promotionLevel);
		cp.setPromotionParentCode(parentPromotionCode);
		
		ca.setPromotionDesc(promotionDesc);
		ca.setLclCd(locale);
		ca.setPromotion(cp);
		cp.addPromotionAttribute(ca);
		
		return cp;
	}
	
	
}
