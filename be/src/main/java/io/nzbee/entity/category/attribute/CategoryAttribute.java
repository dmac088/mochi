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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.search.annotations.AnalyzerDiscriminator;
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

	@Column(name="cat_desc")
	private String categoryDesc;

	@Column(name="lcl_cd")	
	@AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
	private String lclCd;

	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn(name="cat_id")
	@JsonBackReference
	private Category category;

	
	public Long getCategoryAttributeId() {
		return categoryAttributeId;
	}

	public void setCategoryAttributeId(Long categoryAttributeId) {
		this.categoryAttributeId = categoryAttributeId;
	}

	public Optional<Category> getCategory() {
		return Optional.ofNullable(category);
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
        hcb.append(category.getCategoryCode());
        hcb.append(lclCd);
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
	    eb.append(category.getCategoryCode(), that.category.getCategoryCode());
	    eb.append(lclCd, that.lclCd);
	    return eb.isEquals();
	}
}
