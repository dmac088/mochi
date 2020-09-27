package io.nzbee.entity.inventory;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.entity.inventory.type.InventoryType;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.inventory.supplier.Supplier;

@Entity
@Table(name = "inventory_transaction", schema = "mochi")
public class InventoryTransaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="inv_trx_id")
	private Long inventroyTransactionId;
	
	@ManyToOne
	@JoinColumn(name="inv_loc_id")
	private InventoryLocation inventoryLocation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="inv_prd_id")
	private Product product;
	
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
	@JoinColumn(name="inv_sup_id")
	private Supplier supplier;
	
	@ManyToOne
	@Column(name="inv_trx_dt")
	private LocalDateTime inventoryTransactionDate;
	
	public Long getInventroyTransactionId() {
		return inventroyTransactionId;
	}

	public void setInventroyTransactionId(Long inventroyTransactionId) {
		this.inventroyTransactionId = inventroyTransactionId;
	}

	public InventoryLocation getInventoryLocation() {
		return inventoryLocation;
	}

	public void setInventoryLocation(InventoryLocation inventoryLocation) {
		this.inventoryLocation = inventoryLocation;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public LocalDateTime getInventoryTransactionDate() {
		return inventoryTransactionDate;
	}

	public void setInventoryTransactionDate(LocalDateTime inventoryTransactionDate) {
		this.inventoryTransactionDate = inventoryTransactionDate;
	}
	
	
}
