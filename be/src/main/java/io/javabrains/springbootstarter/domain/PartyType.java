package io.javabrains.springbootstarter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "party_type", schema = "mochi")
public class PartyType {

	@Id
	@Column(name="pty_typ_id")
	private Long PartyTypeId;

	@Column(name="pty_typ_desc")
	private String partyTypeDesc;

	public PartyType() {
	}
	
	public PartyType(Long partyTypeId) {
		this.PartyTypeId = partyTypeId;
	}

	public Long getPartyTypeId() {
		return PartyTypeId;
	}

	public void setPartyTypeId(Long partyTypeId) {
		PartyTypeId = partyTypeId;
	}

	public String getPartyTypeDesc() {
		return partyTypeDesc;
	}

	public void setPartyTypeDesc(String partyTypeDesc) {
		this.partyTypeDesc = partyTypeDesc;
	}

}
