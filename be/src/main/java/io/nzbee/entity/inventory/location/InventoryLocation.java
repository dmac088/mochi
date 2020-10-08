package io.nzbee.entity.inventory.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_location", schema = "mochi")
public class InventoryLocation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="inv_loc_id")
	private Long locationId;
	
	@Column(name="inv_loc_cd")
	private String locationCode;
	
	@Column(name="inv_loc_desc")
	private String locationDesc;
	
	@Column(name="inv_loc_act_flg")
	private String locationIsActive;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public String getLocationIsActive() {
		return locationIsActive;
	}

	public void setLocationIsActive(String locationIsActive) {
		this.locationIsActive = locationIsActive;
	}
	
	
}
