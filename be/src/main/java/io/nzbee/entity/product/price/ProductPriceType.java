package io.nzbee.entity.product.price;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "price_type", schema = "mochi")
public class ProductPriceType implements Serializable {
	
	private static final long serialVersionUID = -7823446575108567222L;

	@Id
	@Column(name="prc_typ_id")
	private Long Id;
	
	@NaturalId
	@Column(name="prc_typ_cd")
	private String code;
	
	@Column(name="prc_typ_desc")
	private String desc;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String priceTypeDesc) {
		this.desc = priceTypeDesc;
	}
	
}
