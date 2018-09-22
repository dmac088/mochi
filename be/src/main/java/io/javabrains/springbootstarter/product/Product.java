package io.javabrains.springbootstarter.product;

import io.javabrains.springbootstarter.product_lcl.ProductLcl;
import io.javabrains.springbootstarter.role.Role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
@PrimaryKeyJoinColumn(name = "prd_id")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_id")
	private Long productId;
	
	
	@OneToMany(fetch = FetchType.LAZY,
            mappedBy = "product")
	private List<ProductLcl> lclAttr = new ArrayList<ProductLcl>();
	
	
	public List<ProductLcl> getLclAttr() {
		return lclAttr;
	}

	public void setLclAttr(List<ProductLcl> lclAttr) {
		this.lclAttr = lclAttr;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
	
}
