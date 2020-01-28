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
	private Long productTypeId;

	@Column(name="prd_typ_cd")
	private String productTypeCode;
	
	@Column(name="prd_typ_desc")
	private String productTypeDesc;

	public Long getId() {
		return productTypeId;
	}

	public void setId(Long id) {
		this.productTypeId = id;
	}
	
	public String getCode() {
		return productTypeCode;
	}

	public void setCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}

	public String getDesc() {
		return productTypeDesc;
	}

	public void setDesc(String productTypeDesc) {
		this.productTypeDesc = productTypeDesc;
	}
	
}
