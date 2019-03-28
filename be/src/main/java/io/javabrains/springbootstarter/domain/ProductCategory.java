package io.javabrains.springbootstarter.domain;

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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
public class ProductCategory {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @OrderBy
    @JsonIgnore
    private List<Product> products;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	private Long categoryId;

	@Column(name="cat_cd")
	@Field
	private String categoryCode;
	
	@Column(name="cat_prev_flg")
	private Long previewFlag;
	
	@Column(name="cat_menu_flg")
	private Long menuDisplayFlag;
	
	@Column(name="lnd_disp_flg")
	private Long landingDisplayFlag;

	@Column(name="cat_lvl")
	private Long categoryLevel;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional=false)
	@JoinColumn(name="cat_prnt_id", nullable=false)
	@IndexedEmbedded(depth = 4)
	private ProductCategory parent;
	
	@OneToMany(mappedBy="productCategory",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexedEmbedded
	@JsonIgnore
	private List<ProductCategoryAttribute> productCategoryAttribute;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="cat_prnt_id")
	})
	private List<ProductCategory> children;	

	
	public Long getChildCategoryCount() {
		return new Long(this.children.size());
	}

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
	
	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Long categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	
	public Long getPreviewFlag() {
		return previewFlag;
	}

	public void setPreviewFlag(Long previewFlag) {
		this.previewFlag = previewFlag;
	}

	public Long getMenuDisplayFlag() {
		return menuDisplayFlag;
	}

	public void setMenuDisplayFlag(Long menuDisplayFlag) {
		this.menuDisplayFlag = menuDisplayFlag;
	}
	
	public Long getLandingDisplayFlag() {
		return landingDisplayFlag;
	}

	public void setLandingDisplayFlag(Long landingDisplayFlag) {
		this.landingDisplayFlag = landingDisplayFlag;
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
	
	public Long getProductCount() {
		return new Long(products.size());
	}
	
}
