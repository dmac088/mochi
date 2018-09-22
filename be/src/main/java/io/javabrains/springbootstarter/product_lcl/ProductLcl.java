package io.javabrains.springbootstarter.product_lcl;

import io.javabrains.springbootstarter.product.Product;

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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "product_attr_lcl")
@PrimaryKeyJoinColumn(name = "prd_id")
public class ProductLcl {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_lcl_id")
	private Long ProductLclId;

	@Column(name="prd_rrp")
	private double ProductRrp;

	@Column(name="prd_desc")
	private String ProductDesc;
	
	@Column(name="prd_img_pth")
	private String ProductImage;
	
	@Column(name="prd_cat_desc")
	private String ProductCategory;
	
	@Column(name="lcl_cd")
	private String lclCd;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="prd_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("prd_id")
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getProductLclId() {
		return ProductLclId;
	}
	
	public String lclCd() {
		return lclCd;
	}

	public void setlclCd(String productLcl) {
		lclCd = productLcl;
	}

	public void setProductLclId(Long productLclId) {
		ProductLclId = productLclId;
	}

	public String getCategory() {
		return ProductCategory;
	}

	public void setCategory(String productCategory) {
		ProductCategory = productCategory;
	}
	
	public double getProductRrp() {
		return this.ProductRrp;
	}
	
	public void setProductRrp(double rrp) {
		this.ProductRrp = rrp;
	}

	public String getProductDesc() {
		return this.ProductDesc;
	}

	public void setProductDesc(String productDesc) {
		this.ProductDesc = productDesc;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}
	
}
