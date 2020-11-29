package io.nzbee.util.inventory;

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
import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class InventoryLocationMasterService {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryLocationMasterService.class);
	
	@Autowired
	private IInventoryLocationService inventoryLocationService;
	
	@Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
	@Transactional
	public void writeInventoryLocation(String fileName) {
		logger.debug("called writeInventoryLocation with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<InventoryLocationMasterSchema> readValues =
	        	mapper.readerFor(InventoryLocationMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(c -> {
	        	this.persistInventoryLocationMaster(c);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistInventoryLocationMaster(InventoryLocationMasterSchema ims) {
		logger.debug("called persistInventory() ");
		
		Optional<InventoryLocation> oil = inventoryLocationService.findByCode(ims.get_INVENTORY_LOCATION_CODE());
				
		InventoryLocation iL = oil.isPresent() 
							   ? oil.get()
							   : new InventoryLocation();
		
		iL.setLocationCode(ims.get_INVENTORY_LOCATION_CODE());					   
		iL.setLocationDesc(ims.get_INVENTORY_LOCATION_DESC());
		iL.setLocationIsActive(ims.get_INVENTORY_LOCATION_IS_ACTIVE().equals("Y"));
							   
		inventoryLocationService.save(iL);
		
	}
	
	
}
