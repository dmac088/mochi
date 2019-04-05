package io.javabrains.springbootstarter.domain;

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
	private String priceTypeDesc;

	public String getPriceTypeDesc() {
		return priceTypeDesc;
	}

	public void setPriceTypeDesc(String priceTypeDesc) {
		this.priceTypeDesc = priceTypeDesc;
	}
	
}
