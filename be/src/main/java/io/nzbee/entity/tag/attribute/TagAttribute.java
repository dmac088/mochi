package io.nzbee.entity.tag.attribute;

import java.util.Optional;

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
	private Long Id;
	
	@Column(name="tag_id")
	private Long tagId;

	@Column(name="tag_desc")
	private String tagDesc;

	@Column(name="lcl_cd")	
	private String lclCd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tag_id", insertable=false, updatable=false)
	private Tag tag;
	
	public Long getTagId() {
		return tagId;
	}
	
	public Optional<Tag> getTag() {
		return Optional.ofNullable(tag);
	}

	public void setProductTag(Tag productTag) {
		this.tag = productTag;
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
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getTag().get().getCode());
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
	      eb.append(this.getTag().get().getCode(), that.getTag().get().getCode());
	      eb.append(this.getLclCd(), that.getLclCd());
	      return eb.isEquals();
	}
}
