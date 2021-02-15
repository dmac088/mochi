package io.nzbee.util.product.shipping;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Constants;
import io.nzbee.entity.product.shipping.destination.IShippingDestinationService;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import io.nzbee.entity.product.shipping.destination.attribute.IShippingDestinationAttributeService;
import io.nzbee.entity.product.shipping.destination.attribute.ShippingDestinationAttributeEntity;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.util.product.shipping.destination.ShippingDestinationMasterSchema;

public class ShippingDestinationMasterService {

	private static final Logger logger = LoggerFactory.getLogger(ShippingDestinationMasterService.class);
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
    
    @Autowired
    private IShippingDestinationService shippingDestinationService;
    
    @Autowired
    private IShippingDestinationAttributeService shippingDestinationAttributeService;
	
	@Transactional
	public void writeShippingDestinationMaster(String fileName) {
		logger.debug("called writeShippingDestinationMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<ShippingDestinationMasterSchema> readValues =
	        	mapper.readerFor(ShippingDestinationMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(sdms -> {
	        	this.persistShippingDestinationMaster(sdms);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistShippingDestinationMaster(ShippingDestinationMasterSchema sdms) {
		logger.debug("called persistShippingDestinationMaster");
		
		Optional<ShippingDestinationEntity> osd = shippingDestinationService.findByCode(sdms.getDESTINATION_CODE());
		
		ShippingDestinationEntity sd = 	(osd.isPresent()) 
				? (ShippingDestinationEntity) osd.get() 
				: new ShippingDestinationEntity();
		
		Optional<ShippingDestinationAttributeEntity> osdaen = shippingDestinationAttributeService.findByCode( Constants.localeENGB, sdms.getDESTINATION_CODE());
		
		ShippingDestinationAttributeEntity sdaen = (osd.isPresent()) 
				? osdaen.get()
				: (new ShippingDestinationAttributeEntity());
				
				
		sd.setShippingDestinationCode(sdms.getDESTINATION_CODE());
		sd.setShippingDestinationDesc(sdms.getDESTINATION_DESC_EN());
		sd.setShippingZoneCode(sdms.getDESTINATION_ZONE_CODE());
		sd.setShippingDestinationShortCode(sdms.getDESTINATION_SHORT_CODE());
		
		
		
	}
}
