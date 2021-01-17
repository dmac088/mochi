package io.nzbee.entity.product.currency;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency", schema = "mochi")
public class Currency implements Serializable {

	private static final long serialVersionUID = 4095677302158716156L;

	@Id
	@Column(name="ccy_id")
	private Long currencyId;

	@Column(name="ccy_cd")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String currencyCode) {
		this.code = currencyCode;
	}
	
}
