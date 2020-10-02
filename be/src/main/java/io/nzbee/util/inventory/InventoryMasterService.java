package io.nzbee.util.inventory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.entity.inventory.IInventoryTransactionService;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.entity.inventory.type.IInventoryTypeService;
import io.nzbee.entity.inventory.type.InventoryType;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.organization.Organization;

public class InventoryMasterService {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryMasterService.class);
	
	@Autowired
	private IInventoryTransactionService inventoryTransactionService;
	
	@Autowired
	private IInventoryLocationService inventoryLocationService;
	
	@Autowired
	private IProductService productservice;
	
	@Autowired 
	private IInventoryTypeService inventoryTypeService;
	
	@Autowired
	private IPartyService partyService;
	
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
		logger.debug("called persistInventory() ");
		
		InventoryTransaction i = new InventoryTransaction();
				
		Optional<InventoryLocation> il = inventoryLocationService.findByCode(ims.get_INVENTORY_LOCATION_CODE());
		
		Optional<Product> p = productservice.findByCode(ims.get_INVENTORY_PRODUCT_UPC());
		
		Optional<InventoryType> it = inventoryTypeService.findByCode(ims.get_INVENTORY_TYPE_CODE());
		
		Optional<Party> pty = partyService.findByCode(ims.get_INVENTORY_SUPPLIER_CODE());
		
		Organization supp = (Organization) pty.get();
		
		LocalDateTime trxDate = LocalDateTime.parse(ims.get_INVENTORY_TRANSACTION_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		i.setInventoryTransactionDate(trxDate);
		
		i.setInventoryLocation(il.get());
		i.setProduct(p.get());
		i.setInventoryType(it.get());
		i.setSupplier(supp);
		i.setInventoryTransactionDate(trxDate);
		
		inventoryTransactionService.save(i);
		
	}
	
	
}
