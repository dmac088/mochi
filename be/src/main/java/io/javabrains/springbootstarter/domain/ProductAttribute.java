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
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDiscriminator;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.TermVector;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "product_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_lcl_id")
public class ProductAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_lcl_id")
	private Long Id;

	@Column(name="prd_id")
	private Long productId;
	
	
	@Field(termVector = TermVector.YES, analyze = Analyze.YES)
	@Column(name="prd_desc")
	private String productDesc;
	
	@Transient
	@Field(analyze = Analyze.NO)
	@SortableField
	private String productSortDesc;
	
	@Column(name="prd_img_pth")
	private String ProductImage;
	
	@Column(name="lcl_cd")
	@Field
	@AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
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
		this.productSortDesc = productDesc;
	}
	
	public String getProductSortDesc() {
		return productSortDesc;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}
}
