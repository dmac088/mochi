package io.nzbee.entity.category.attribute;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
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
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDiscriminator;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import com.google.common.collect.Lists;
import io.nzbee.entity.LanguageDiscriminator;
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
	
	public Long getCategoryAttributeId() {
		return categoryAttributeId;
	}

	public void setCategoryAttributeId(Long categoryAttributeId) {
		this.categoryAttributeId = categoryAttributeId;
	}

	public Category getCategory() {
		return this.category;
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
	
	@Transient
	@Field(analyze = Analyze.NO, store=Store.YES)
	public String getCategoryCode() {
		return this.getCategory().getCategoryCode();
	}
	
	@Transient
	@Field(analyze = Analyze.NO, store=Store.YES)
	public Long getCategoryLevel() {
		return this.getCategory().getCategoryLevel();
	}
	
	@Transient
	@Field(analyze = Analyze.NO, store=Store.YES)
	public String getCategoryTokenField() {
		String token = createCategoryToken(this.getCategory(), new ArrayList<String>());
		if(token == null || token.isEmpty()) { return "Unknown"; }
		return token;
	}
	
	@Transient
	@Field(analyze = Analyze.NO, store=Store.YES)
	@Facet
	public String getCategoryToken() {
		return this.getCategoryTokenField();
	}
	
	@Transient
	@IndexedEmbedded(depth = 10, includeEmbeddedObjectId=true)
	public CategoryAttribute getParent() {
		Optional<Category> parent = this.getCategory().getParent();
		if(parent.isPresent()) {
			return parent.get().getAttributes().stream().filter(p -> p.getLclCd().equals(this.getLclCd())).findFirst().get();
		}
		return null;
	}
	
	private String createCategoryToken(Category category, List<String> lc) {
		lc.add(category.getCategoryCode());
		Optional<Category> parent = category.getParent();
		if(!parent.isPresent()) {
			StringBuilder sb = new StringBuilder();
			Lists.reverse(lc).stream().forEach(s -> sb.append("/").append(s));
			return sb.toString();
		}
		return this.createCategoryToken(parent.get(), lc);
	}
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getCategoryToken());
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
	    eb.append(this.getCategoryToken(), that.getCategoryToken());
	    eb.append(this.getLclCd(), that.getLclCd());
	    return eb.isEquals();
	}
}
