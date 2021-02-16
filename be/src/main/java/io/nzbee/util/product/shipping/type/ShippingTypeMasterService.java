package io.nzbee.util.product.shipping.type;

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
import io.nzbee.entity.product.shipping.type.IShippingTypeService;
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;
import io.nzbee.entity.product.shipping.type.attribute.IShippingTypeAttributeService;
import io.nzbee.entity.product.shipping.type.attribute.ShippingTypeAttributeEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class ShippingTypeMasterService {

	private static final Logger logger = LoggerFactory.getLogger(ShippingTypeMasterService.class);
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    @Autowired
    private IShippingTypeService shippingTypeService;
    
    @Autowired
    private IShippingTypeAttributeService shippingTypeAttributeService;
    
    @Transactional
	public void writeShippingTypeMaster(String fileName) {
		logger.debug("called writeShippingTypeMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<ShippingTypeMasterSchema> readValues =
	        	mapper.readerFor(ShippingTypeMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(sdms -> {
	        	this.persistShippingTypeMaster(sdms);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
    
    public void persistShippingTypeMaster(ShippingTypeMasterSchema sdms) {
		logger.debug("called persistShippingTypeMaster");
		
		ShippingTypeEntity tEn = mapToShippingType(
											sdms.get_TYPE_CODE(),
											sdms.get_TYPE_DESC_EN(),
											Constants.localeENGB
										);
		
		shippingTypeService.save(tEn);
		
		ShippingTypeEntity tTc = mapToShippingType(
											sdms.get_TYPE_CODE(),
											sdms.get_TYPE_DESC_HK(),
											Constants.localeZHHK
										);
		
		shippingTypeService.save(tTc);

	}
	
	
	private ShippingTypeEntity mapToShippingType(	String shippingTypeCode,
													String shippingTypeDescription,
													String locale) {
		
		Optional<ShippingTypeEntity> osd = shippingTypeService.findByCode(shippingTypeCode);
		
		ShippingTypeEntity sd = 	(osd.isPresent()) 
									? (ShippingTypeEntity) osd.get() 
									: new ShippingTypeEntity();
						
		sd.setShippingTypeCode(shippingTypeCode);
		
		Optional<ShippingTypeAttributeEntity> osdaen = shippingTypeAttributeService.findByCode(locale, shippingTypeCode);
		
		ShippingTypeAttributeEntity sda = 	(osdaen.isPresent()) 
											? (ShippingTypeAttributeEntity) osdaen.get() 
											: new ShippingTypeAttributeEntity();
		
		sda.setShippingType(sd);
		sda.setShippingTypeDesc(shippingTypeDescription);
		sda.setLclCd(locale);
		
		System.out.println("printing - " + sda.getShippingTypeDesc());
	
		sd.getAttributes().add(sda);
		
		return sd;
		
	}
    
}
