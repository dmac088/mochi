package io.nzbee.entity.tag;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import io.nzbee.entity.product.Product;
import io.nzbee.entity.tag.attribute.TagAttribute;

@Entity
@Table(name = "tag", schema = "mochi")
public class Tag {
	
	@Id
	@Column(name="tag_id")
	private Long productTagId;
	
	@NaturalId
	@Column(name="tag_cd", unique = true, updatable = false)
	private String productTagCode;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_tag", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "tag_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @OrderBy
    private Set<Product> products;

	@OneToMany(	mappedBy="tag", 
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private List<TagAttribute> attributes;
	
	public Long getTagId() {
		return productTagId;
	}
	
	@Field(analyze = Analyze.NO, store=Store.YES)
	@Facet
	public String getTagToken() {
		return getCode();
	}

	public void setTagId(Long productTagId) {
		this.productTagId = productTagId;
	}
	
	public String getCode() {
		return productTagCode;
	}

	public void setCode(String productTagCode) {
		this.productTagCode = productTagCode;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public List<TagAttribute> getAttributes() {
		return attributes;
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "en-GB"))
	public String getTagDescENGB() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals("en-GB")).findFirst().get().getTagDesc();
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "zh-HK"))
	public String getTagDescZHHK() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals("zh-HK")).findFirst().get().getTagDesc();
	}
	
	public void addTagAttribute(TagAttribute tagAttribute) {
		this.getAttributes().add(tagAttribute);
		tagAttribute.setProductTag(this);
	}
	
	public void removeTagAttribute(TagAttribute tagAttribute) {
		this.getAttributes().remove(tagAttribute);
		tagAttribute.setProductTag(null);
	}

}
