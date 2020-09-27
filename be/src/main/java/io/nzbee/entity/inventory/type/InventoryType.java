package io.nzbee.entity.inventory.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_type", schema = "mochi")
public class InventoryType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="inv_trx_typ_id")
	private Long inventoryTypeId;
	
	@Column(name="inv_trx_typ_desc")
	private String inventoryTypeDesc;

	public Long getInventoryTypeId() {
		return inventoryTypeId;
	}

	public void setInventoryTypeId(Long inventoryTypeId) {
		this.inventoryTypeId = inventoryTypeId;
	}

	public String getInventoryTypeDesc() {
		return inventoryTypeDesc;
	}

	public void setInventoryTypeDesc(String inventoryTypeDesc) {
		this.inventoryTypeDesc = inventoryTypeDesc;
	}
	
}
