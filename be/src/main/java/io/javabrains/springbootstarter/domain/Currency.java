package io.javabrains.springbootstarter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency", schema = "mochi")
public class Currency {

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
