package io.nzbee.entity;

import java.util.List;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tag", schema = "mochi")
public class ProductTag {
	
	@Id
	@Column(name="tag_id")
	private Long productTagId;
	
	@Column(name="tag_cd")
	private String productTagCode;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_tag", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "tag_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @OrderBy
    @JsonIgnore
    private List<Product> products;

	@OneToMany(mappedBy="tag",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductTagAttribute> attributes;
	
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

	public List<Product> getProducts() {
		return products;
	}

	public List<ProductTagAttribute> getAttributes() {
		return attributes;
	}
}
