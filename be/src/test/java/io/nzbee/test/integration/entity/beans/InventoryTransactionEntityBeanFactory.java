package io.nzbee.test.integration.entity.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.entity.inventory.type.IInventoryTypeService;
import io.nzbee.entity.inventory.type.InventoryType;
import io.nzbee.entity.party.organization.Organization;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.role.supplier.ISupplierService;
import io.nzbee.entity.role.supplier.Supplier;


@Service(value = "inventoryTransactionEntityBeanFactory")
@Profile(value = "tst")
public class InventoryTransactionEntityBeanFactory {

	@Autowired
	private IInventoryLocationService inventoryLocationService;
	
	@Autowired
	private IInventoryTypeService inventoryTypeService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private ISupplierService supplierService;
	
	@Autowired
	private IProductService productService;
	
	@Bean
	public final InventoryTransaction getInventoryTransactionEntityBean() {
		final InventoryTransaction inventoryTransaction = new InventoryTransaction();
		
		Optional<InventoryLocation> iL 	= inventoryLocationService.findByCode("LCK01");
		Optional<InventoryType> iT 		= inventoryTypeService.findByCode("IN");
		Optional<Currency> iC 			= currencyService.findByCode(Constants.currencyHKD);
		Optional<Supplier> iS 			= supplierService.findByCode("1000000002");
		Optional<ProductEntity> iP 		= productService.findByCode("30833030");
		
		inventoryTransaction.setInventoryLocation(iL.get());
		
		inventoryTransaction.setInventoryType(iT.get());
		
		inventoryTransaction.setCurrency(iC.get());
		
		inventoryTransaction.setSupplier((Organization) Hibernate.unproxy(iS.get().getRoleParty()));
		
		inventoryTransaction.setProduct(iP.get());
		
		inventoryTransaction.setQuantity(new Long(5));
		
		inventoryTransaction.setPrice(new Double(15.20));
		
		inventoryTransaction.setInventoryTransactionDate(LocalDateTime.parse("2020-10-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		return inventoryTransaction;
	}
	
}
