package io.nzbee.util.product.shipping.destination;

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
import io.nzbee.Constants;
import io.nzbee.entity.product.shipping.destination.IShippingDestinationService;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import io.nzbee.entity.product.shipping.destination.attribute.IShippingDestinationAttributeService;
import io.nzbee.entity.product.shipping.destination.attribute.ShippingDestinationAttributeEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
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
		
		ShippingDestinationEntity tEn = mapToShippingDestination(
											sdms.getDESTINATION_CODE(),
											sdms.getDESTINATION_DESC_EN(),
											sdms.getDESTINATION_DESC_EN(),
											sdms.getDESTINATION_SHORT_CODE(),
											sdms.getDESTINATION_ZONE_CODE(),
											Constants.localeENGB
										);
		
		shippingDestinationService.save(tEn);
		
		ShippingDestinationEntity tTc = mapToShippingDestination(
				sdms.getDESTINATION_CODE(),
				sdms.getDESTINATION_DESC_EN(),
				sdms.getDESTINATION_DESC_HK(),
				sdms.getDESTINATION_SHORT_CODE(),
				sdms.getDESTINATION_ZONE_CODE(),
				Constants.localeZHHK
		);
		
		shippingDestinationService.save(tTc);

	}
	
	
	private ShippingDestinationEntity mapToShippingDestination(	 String shippingDestinationCode,
																 String shippingDestinationDescription,
																 String shippingDestinationAttributeDescription,
																 String shippingDestinationShortCode,
																 String shippingDestinationZoneCode,
																 String locale) {
		
		Optional<ShippingDestinationEntity> osd = shippingDestinationService.findByCode(shippingDestinationCode);
		
		ShippingDestinationEntity sd = 	(osd.isPresent()) 
				? (ShippingDestinationEntity) osd.get() 
				: new ShippingDestinationEntity();
						
		sd.setShippingDestinationCode(shippingDestinationCode);
		sd.setShippingDestinationDesc(shippingDestinationDescription);
		sd.setShippingDestinationShortCode(shippingDestinationShortCode);
		sd.setShippingDestinationZoneCode(shippingDestinationZoneCode);
		
		Optional<ShippingDestinationAttributeEntity> osdaen = shippingDestinationAttributeService.findByCode(locale, shippingDestinationCode);
		
		ShippingDestinationAttributeEntity sda = (osd.isPresent()) 
				? (ShippingDestinationAttributeEntity) osdaen.get() 
				: new ShippingDestinationAttributeEntity();
		
		sda.setShippingDestination(sd);
		sda.setShippingDestinationDesc(shippingDestinationAttributeDescription);
		sda.setLclCd(locale);
		
		sd.getAttributes().add(sda);
		
		return sd;
		
	}
}
