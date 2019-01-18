package io.javabrains.springbootstarter.domain;

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
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.TermVector;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Indexed
@Table(name = "product_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_lcl_id")
public class ProductAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_lcl_id")
	private Long Id;

	@Column(name="prd_id")
	private Long productId;
	
	@Column(name="prd_desc")
	@Field(termVector = TermVector.YES)
	private String productDesc;
	
	@SortableField
	@Field(termVector = TermVector.YES)
	@Column(name="prd_rrp")
	private double productRrp;
	
	@Column(name="prd_img_pth")
	private String ProductImage;
	
	@Column(name="lcl_cd")	
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@IndexedEmbedded
	@JoinColumn(name="prd_id", insertable=false, updatable=false)
	@JsonBackReference
	private Product product;
	
	public Long getProductId() {
		return productId;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
	
	public double getProductRrp() {
		return productRrp;
	}

	public void setProductRrp(double productRrp) {
		this.productRrp = productRrp;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}
}
