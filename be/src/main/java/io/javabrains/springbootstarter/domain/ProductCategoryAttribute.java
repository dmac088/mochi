package io.javabrains.springbootstarter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "category_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_lcl_id")
public class ProductCategoryAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_lcl_id")
	private Long Id;

	
	@Column(name="cat_id")
	private Long categoryId;
	
	@Column(name="cat_desc")
	private String categoryDesc;
	
	@Column(name="lcl_cd")	
	private String lclCd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cat_id", insertable=false, updatable=false)
	@JsonManagedReference
	private ProductCategory productCategory;
	
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
	
	
}
