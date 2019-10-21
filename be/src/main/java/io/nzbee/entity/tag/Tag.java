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

import org.hibernate.annotations.NaturalId;

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
	
	public void addTagAttribute(TagAttribute tagAttribute) {
		this.attributes.add(tagAttribute);
		tagAttribute.setProductTag(this);
	}
	
	public void removeTagAttribute(TagAttribute tagAttribute) {
		this.attributes.remove(tagAttribute);
		tagAttribute.setProductTag(null);
	}
}
