package io.nzbee.entity.category.attribute;

import java.util.Optional;

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
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDiscriminator;
import org.hibernate.search.annotations.Field;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.nzbee.entity.LanguageDiscriminator;
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "category_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_lcl_id")
public class CategoryAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_lcl_id")
	private Long categoryAttributeId;
	
	@Column(name="cat_id")
	private Long categoryId;

	@Column(name="cat_desc")
	@Field(analyze = Analyze.YES)
	private String categoryDesc;

	@Column(name="lcl_cd")	
	@Field
	@AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
	private String lclCd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cat_id", insertable=false, updatable=false)
	@JsonBackReference
	private Category category;

	
	public Long getCategoryAttributeId() {
		return categoryAttributeId;
	}

	public void setCategoryAttributeId(Long categoryAttributeId) {
		this.categoryAttributeId = categoryAttributeId;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Optional<Category> getCategory() {
		return Optional.ofNullable(category);
	}

	public void setCategory(Optional<Category> productCategory) {
		this.category = productCategory.get();
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
