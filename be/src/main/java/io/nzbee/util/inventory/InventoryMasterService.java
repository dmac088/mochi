package io.nzbee.util.inventory;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.entity.inventory.IInventoryTransactionService;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.util.FileStorageServiceUpload;

public class InventoryMasterService {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryMasterService.class);
	
	@Autowired
	private IInventoryTransactionService inventoryTransactionService;
	
	@Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
	public void writeInventoryTransaction(String fileName) {
		logger.debug("called writeInventoryTransaction with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<InventoryMasterSchema> readValues =
	        	mapper.readerFor(InventoryMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(c -> {
	        	this.persistInventoryTransactionMaster(c);
	        });
	        
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public void persistInventoryTransactionMaster(InventoryMasterSchema ims) {
		logger.debug("called persistBrandMaster() ");
		
		InventoryTransaction i = new InventoryTransaction();
				
		inventoryTransactionService.save(i);
		
	}
	
	
}
