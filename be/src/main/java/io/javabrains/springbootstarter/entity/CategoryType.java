package io.javabrains.springbootstarter.entity;

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
	private String code;
	
	@Column(name="cat_typ_desc")
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String categoryTypeCode) {
		this.code = categoryTypeCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String categoryTypeDesc) {
		this.desc = categoryTypeDesc;
	}
	
}
