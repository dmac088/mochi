package io.nzbee.entity.party.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.type.AddressTypeEntity;

@Entity
@Table(name = "address", schema = "mochi")
public class PartyAddressEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="addr_id")
	private Long addressId;
	
	@Column(name="addr_ln_1")
	private String addressLine1;
	
	@Column(name="addr_ln_2")
	private String addressLine2;
	
	@Column(name="addr_ln_3")
	private String addressLine3;
	
	@NotNull
	@Column(name="addr_cnty")
	private String addressCountry;
	
	@Column(name="addr_pst_cd")
	private String addressPostCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pty_id")
	private Party party;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="addr_typ_id")
	private AddressTypeEntity type;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressPostCode() {
		return addressPostCode;
	}

	public void setAddressPostCode(String addressPostCode) {
		this.addressPostCode = addressPostCode;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public AddressTypeEntity getType() {
		return type;
	}

	public void setType(AddressTypeEntity addressType) {
		this.type = addressType;
	}

}
