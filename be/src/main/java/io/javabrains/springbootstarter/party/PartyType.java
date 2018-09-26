package io.javabrains.springbootstarter.party;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "party_type")
public class PartyType {

	@Id
	@Column(name="pty_typ_id")
	private Long PartyTypeId;
	
	@Column(name="pty_typ_desc")
	private String partyTypeDesc;

	public String getPartyTypeDesc() {
		return partyTypeDesc;
	}

	public void setPartyTypeDesc(String partyTypeDesc) {
		this.partyTypeDesc = partyTypeDesc;
	}


}
