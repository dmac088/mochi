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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.nzbee.entity.tag.Tag;

@Entity
@Table(name = "tag_attr_lcl", schema = "mochi")
public class TagAttribute {

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
	private Tag tag;
	
	public Long getTagId() {
		return tagAttributeId;
	}
	
	public Long getId() {
		return tagAttributeId;
	}

	public void setId(Long id) {
		tagAttributeId = id;
	}
	
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
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
	
	@Override
    public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getTag().getCode());
        hcb.append(this.getLclCd());
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof TagAttribute)) {
	            return false;
	    }
	    TagAttribute that = (TagAttribute) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.getTag().getCode(), that.getTag().getCode());
	      eb.append(this.getLclCd(), that.getLclCd());
	      return eb.isEquals();
	}
}
