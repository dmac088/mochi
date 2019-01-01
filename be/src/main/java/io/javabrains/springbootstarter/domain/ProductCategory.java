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
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "category", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
@SecondaryTable(schema = "mochi", name = "category_attr_lcl", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cat_id"))
public class ProductCategory {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @OrderBy
    @JsonIgnore
    private Collection<Product> products;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	private Long categoryId;

	@Column(name="cat_cd")
	private String categoryCode;
	
	@Column(name="cat_prnt_id")
	private Long parentCategoryId;
	
	@Column(name="cat_desc", table="category_attr_lcl")
	private String categoryDesc;
	
	@Column(name="lcl_cd", table="category_attr_lcl")	
	private String lclCd;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="cat_prnt_id", insertable=false, updatable=false)
	private ProductCategory parent;

	@OneToMany(mappedBy="parent")
	private List<ProductCategory> children = new ArrayList<ProductCategory>();

	public List<ProductCategory> getChildren() {
		return children;
	}
	
	public Long getParentCategoryId() {
		return parentCategoryId;
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
	
	
}
