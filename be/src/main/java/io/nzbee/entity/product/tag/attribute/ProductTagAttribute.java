package io.nzbee.entity.product.tag.attribute;

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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDiscriminator;
import org.hibernate.search.annotations.Field;
import io.nzbee.entity.LanguageDiscriminator;
import io.nzbee.entity.product.tag.ProductTag;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tag_id", insertable=false, updatable=false)
	private ProductTag tag;
	
	public Long getTagId() {
		return tagId;
	}
	
	public Optional<ProductTag> getTag() {
		return Optional.ofNullable(tag);
	}

	public void setProductTag(ProductTag productTag) {
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
        hcb.append(tag.getCode());
        hcb.append(lclCd);
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof ProductTagAttribute)) {
	            return false;
	    }
	    ProductTagAttribute that = (ProductTagAttribute) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(tag.getCode(), that.tag.getCode());
	      eb.append(lclCd, that.lclCd);
	      return eb.isEquals();
	}
}
