package io.nzbee.entity.category.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "category_type", schema = "mochi")
public class CategoryType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_typ_id")
	private Long Id;

	@NaturalId
	@Column(name="cat_typ_cd")
	private String categoryTypeCode;
	
	@Column(name="cat_typ_desc")
	private String categoryTypeDesc;

	public Long getId() {
		return Id;
	}

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
