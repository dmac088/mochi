package io.nzbee.entity.category.attribute;

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
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "category_attr_lcl", schema = "mochi")
public class CategoryAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_lcl_id")
	private Long categoryAttributeId;

	@Column(name="cat_desc")
	@Field(analyze = Analyze.YES, store=Store.YES)
	private String categoryDesc;

	@Column(name="lcl_cd")	
	private String lclCd;

	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="cat_id")
	private Category category;
	
	public Category getCategory() {
		return category;
	}
	
	public Long getCategoryAttributeId() {
		return categoryAttributeId;
	}

	public void setCategory(Category productCategory) {
		this.category = productCategory;
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
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getCategoryDesc());
        hcb.append(this.getLclCd());
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof CategoryAttribute)) {
	            return false;
	    }
	    CategoryAttribute that = (CategoryAttribute) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.getCategoryDesc(), that.getCategoryDesc());
	      eb.append(this.getLclCd(), that.getLclCd());
	      return eb.isEquals();
	}

	
}
