package io.nzbee.entity.product.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_type", schema = "mochi")
public class ProductType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_typ_id")
	private Long Id;

	@Column(name="prd_typ_cd")
	private String code;
	
	@Column(name="prd_typ_desc")
	private String desc;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String productTypeCode) {
		this.code = productTypeCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String productTypeDesc) {
		this.desc = productTypeDesc;
	}
	
}
