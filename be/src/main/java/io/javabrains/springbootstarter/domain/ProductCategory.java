package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "category", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
public class ProductCategory {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @OrderBy
    //@JsonIgnore
    private List<Product> products;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	private Long categoryId;

	@Column(name="cat_cd")
	private String categoryCode;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional=false)
	@JoinColumn(name="cat_prnt_id", nullable=false)
	private ProductCategory parent;
	
	@OneToMany(mappedBy="productCategory",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	//@Where(clause = " lcl_cd = 'ENG' ")
	//@Filter(name = "lclCdFilter")
	private List<ProductCategoryAttribute> productCategoryAttribute;

	//@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="cat_prnt_id")
	private List<ProductCategory> children = new ArrayList<ProductCategory>();	
	

	public List<ProductCategory> getChildren() {
		return children;
	}
	
	public ProductCategory getParent() {
		return parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}
	
	public List<ProductCategoryAttribute> getProductCategoryAttribute() {
		return productCategoryAttribute;
	}

	public void setProductCategoryAttribute(List<ProductCategoryAttribute> productCategoryAttribute) {
		this.productCategoryAttribute = productCategoryAttribute;
	}

	public Long getCategoryId() {
		return categoryId;
	}
	
	public Collection<Product> getProducts() {
		return products;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
}
