package io.javabrains.springbootstarter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "category_type", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_typ_id")
public class CategoryType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_typ_id")
	private Long categoryTypeId;
	
	@Column(name="cat_typ_cd")
	private String categoryTypeCode;
	
	@Column(name="cat_typ_desc")
	private String categoryTypeDesc;

	public String getCategoryTypeCode() {
		return categoryTypeCode;
	}

	public void setCategoryTypeCode(String categoryTypeCode) {
		this.categoryTypeCode = categoryTypeCode;
	}

	public String getCategoryTypeDesc() {
		return categoryTypeDesc;
	}

	public void setCategoryTypeDesc(String categoryTypeDesc) {
		this.categoryTypeDesc = categoryTypeDesc;
	}
	
}
