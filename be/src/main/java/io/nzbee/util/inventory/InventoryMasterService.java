package io.nzbee.util.inventory;

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
import io.nzbee.entity.inventory.IInventoryTransactionService;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.entity.inventory.type.IInventoryTypeService;
import io.nzbee.entity.inventory.type.InventoryType;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.physical.PhysicalProductEntity;
import io.nzbee.entity.role.supplier.ISupplierService;
import io.nzbee.entity.role.supplier.Supplier;
import io.nzbee.entity.stock.IStockOnHandService;
import io.nzbee.entity.stock.StockOnHandEntity;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.entity.party.organization.Organization;

@Service
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
	private ISupplierService supplierService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
	@Autowired
	private IStockOnHandService stockOnHandService;
	
	@Transactional
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
		logger.debug("called persistInventoryTransactionMaster() ");
		
		InventoryTransaction i = new InventoryTransaction();
				
		Optional<InventoryLocation> il = inventoryLocationService.findByCode(ims.get_INVENTORY_LOCATION_CODE());
		
		Optional<ProductEntity> p = productservice.findByCode(ims.get_INVENTORY_PRODUCT_UPC());
		
		Optional<InventoryType> it = inventoryTypeService.findByCode(ims.get_INVENTORY_TYPE_CODE());
		
		Optional<Supplier> osup = supplierService.findByCode(ims.get_INVENTORY_SUPPLIER_ID());
		
		Optional<Currency> ocurr = currencyService.findByCode(ims.get_INVENTORY_CURRENCY_CODE());

		Supplier sup = (Supplier) osup.get();
		
		Organization supp = (Organization) Hibernate.unproxy(sup.getRoleParty());
		
		LocalDateTime trxDate = LocalDateTime.parse(ims.get_INVENTORY_TRANSACTION_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		i.setInventoryTransactionDate(trxDate);
		i.setInventoryLocation(il.get());
		i.setProduct(p.get());
		i.setInventoryType(it.get());
		i.setSupplier(supp);
		i.setInventoryTransactionDate(trxDate);
		i.setQuantity(ims.get_INVENTORY_QUANTITY());
		i.setPrice(ims.get_INVENTORY_PRICE());
		i.setCurrency(ocurr.get());
		
		//ensure the SOH is updated
		Optional<StockOnHandEntity> osoh = stockOnHandService.findByProductCode(ims.get_INVENTORY_PRODUCT_UPC()); 
		
		StockOnHandEntity soh = osoh.isPresent()
						  ? osoh.get()
						  : new StockOnHandEntity();
						  
		soh.setProduct((PhysicalProductEntity) p.get());
		soh.setStockOnHand(((soh.getStockOnHand() == null) ? 0 : soh.getStockOnHand()) + ims.get_INVENTORY_QUANTITY());

		inventoryTransactionService.save(i);
		stockOnHandService.save(soh);
	}
	
	
}
