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
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDiscriminator;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "category_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_lcl_id")
public class CategoryAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_lcl_id")
	private Long Id;
	
	@Column(name="cat_id")
	private Long categoryId;

	@Column(name="cat_desc")
	@Field(analyze = Analyze.YES)
	private String categoryDesc;
	
	@Transient
	@Facet
	@Field(analyze = Analyze.NO)
	private String categoryDescFacet;

	@Column(name="lcl_cd")	
	@Field
	@AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
	private String lclCd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cat_id", insertable=false, updatable=false)
	@JsonBackReference
	private Category category;
	
	public Long getCategoryId() {
		return categoryId;
	}
	
	public Category getCategory() {
		return category;
	}

	public void seCategory(Category productCategory) {
		this.category = productCategory;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
		this.categoryDescFacet = categoryDesc;
	}
	
	public String getCategoryDescFacet() {
		return this.categoryDescFacet;
	}
	
	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
}
