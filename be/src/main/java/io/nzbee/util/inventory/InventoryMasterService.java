package io.nzbee.util.inventory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.entity.inventory.IInventoryTransactionService;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.entity.inventory.type.IInventoryTypeService;
import io.nzbee.entity.inventory.type.InventoryType;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.role.supplier.Supplier;
import io.nzbee.util.FileStorageServiceUpload;

public class InventoryMasterService {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryMasterService.class);
	
	@Autowired
	private IInventoryTransactionService inventoryTransactionService;
	
	@Autowired
	private IProductService productservice;
	
	@Autowired IInventoryTypeService inventoryTypeService;
	
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
				
		Optional<InventoryTransaction> il = inventoryTransactionService.findByCode(ims.get_INVENTORY_LOCATION_CODE());
		
		Optional<Product> p = productservice.findByCode(ims.get_INVENTORY_PRODUCT_UPC());
		
		Optional<InventoryType> it = inventoryTypeService.findByCode(ims.get_INVENTORY_TYPE_CODE());
		
		
		
/*		
		
		@Column(name="inv_qty")
		private Double quantity;
		
		@Column(name="inv_prc")
		private Double price;
		
		@ManyToOne
		@JoinColumn(name="inv_ccy_id")
		private Currency currency;
		
		@ManyToOne
		@JoinColumn(name="inv_trx_typ_id")
		private InventoryType inventoryType;
		
		@ManyToOne
		@JoinColumn(name="inv_pty_id")
		private Supplier supplier;
		
		@ManyToOne
		@Column(name="inv_trx_dt")
		private LocalDateTime inventoryTransactionDate;
*/
		
		inventoryTransactionService.save(i);
		
	}
	
	
}
