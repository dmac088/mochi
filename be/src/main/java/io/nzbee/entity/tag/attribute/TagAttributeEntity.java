package io.nzbee.entity.tag.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import io.nzbee.entity.tag.TagEntity;

@Entity
@Table(name = "tag_attr_lcl", schema = "mochi")
public class TagAttributeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_lcl_id")
	private Long tagAttributeId;

	@Column(name="tag_desc")
	private String tagDesc;

	@Column(name="lcl_cd")	
	private String lclCd;

	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="tag_id")
	private TagEntity tag;
	
	public Long getTagId() {
		return tagAttributeId;
	}
	
	public Long getId() {
		return tagAttributeId;
	}

	public void setId(Long id) {
		tagAttributeId = id;
	}
	
	public TagEntity getTag() {
		return tag;
	}

	public void setTag(TagEntity tag) {
		this.tag = tag;
	}

	public String getTagDesc() {
		return tagDesc;
	}
	
	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
	
	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
	
}
