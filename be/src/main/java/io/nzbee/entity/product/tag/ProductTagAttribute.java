package io.nzbee.entity.product.tag;

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

@Entity
@Table(name = "tag_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "tag_lcl_id")
public class ProductTagAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_lcl_id")
	private Long Id;
	
	@Column(name="tag_id")
	private Long tagId;

	@Column(name="tag_desc")
	@Field(analyze = Analyze.YES)
	private String tagDesc;

	@Column(name="lcl_cd")	
	@Field
	@AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
	private String lclCd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tag_id", insertable=false, updatable=false)
	@JsonBackReference
	private ProductTag tag;
	
	public Long getTagId() {
		return tagId;
	}
	
	public Optional<ProductTag> getTag() {
		return Optional.ofNullable(tag);
	}

	public void setCategory(Optional<ProductTag> productTag) {
		this.tag = productTag.get();
	}

	public String getTagDesc() {
		return tagDesc;
	}
	
	public void setCategoryDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
	
	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
}
