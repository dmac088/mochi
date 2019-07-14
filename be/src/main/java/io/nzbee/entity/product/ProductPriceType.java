package io.nzbee.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "price_type", schema = "mochi")
public class ProductPriceType {
	
	@Id
	@Column(name="prc_typ_id")
	private Long priceTypeId;
	
	@Column(name="prc_typ_desc")
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String priceTypeDesc) {
		this.desc = priceTypeDesc;
	}
	
}
