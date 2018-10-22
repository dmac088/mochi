package io.javabrains.springbootstarter.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "person", schema = "mochi")
@PrimaryKeyJoinColumn(name = "psn_id")
public class Person extends Party implements Serializable {

	@Column(name="psn_gvn_nm_en")
	private String GivenNameEn;
	
	@Column(name="psn_fml_nm_en")
	private String FamilyNameEn;
	
	@Column(name="psn_nm_cn")
	private String NameCn;
	
	public Person() {
		super();
	}
	
	public String getGivenNameEn() {
		return GivenNameEn; 
	}	
	
	public void setGivenNameEn(String givenNameEn) {
		GivenNameEn = givenNameEn;
	}

	public String getFamilyNameEn() {
		return FamilyNameEn;
	}

	public void setFamilyNameEn(String familyNameEn) {
		FamilyNameEn = familyNameEn;
	}

	public String getNameCn() {
		return NameCn;
	}

	public void setNameCn(String NameCn) {
		this.NameCn = NameCn;
	}
	
}
