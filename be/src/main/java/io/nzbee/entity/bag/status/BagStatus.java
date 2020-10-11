package io.nzbee.entity.bag.status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bag_status", schema = "mochi")
public class BagStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bag_sts_id")
	private Long bagStatusId;

	@Column(name="bag_sts_cd")
	private String bagStatusCode;
	
	@Column(name="bag_sts_desc")
	private String bagStatusDesc;

	public Long getbagStatusId() {
		return bagStatusId;
	}

	public void setId(Long bagStatusId) {
		this.bagStatusId = bagStatusId;
	}

	public String getCode() {
		return bagStatusCode;
	}

	public void setCode(String bagStatusCode) {
		this.bagStatusCode = bagStatusCode;
	}

	public String getDesc() {
		return bagStatusDesc;
	}

	public void setDesc(String bagStatusDesc) {
		this.bagStatusDesc = bagStatusDesc;
	}

}
