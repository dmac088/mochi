package io.javabrains.springbootstarter.entity;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.SortableField;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_id")
	private Long productId;

	@Column(name="upc_cd")
	private String productUPC;
	
	@Column(name="prd_crtd_dt")
	private Date productCreateDt;
	
	@Column(name="prd_prev_flg")
	private Long previewFlag;

	@Column(name="prd_feat_flg")
	private Long featuredFlag;
	
	@ManyToMany(mappedBy = "products")
	@IndexedEmbedded
	@JsonIgnore
	private List<Category> categories;

	@OneToMany(mappedBy="product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductAttribute> attributes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@IndexedEmbedded
	@JoinColumn(name="bnd_id", insertable=false, updatable=false)
	@JsonBackReference
	private Brand brand;
	
	@OneToMany(mappedBy="product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexedEmbedded
	@JsonManagedReference
	List<ProductPrice> prices;

	@Field
	@SortableField
	@Transient
	public Double getCurrentMarkdownPriceHKD() {
		 return this.prices.stream().filter(p ->
		 	p.getStartDate().before(Calendar.getInstance().getTime())
		 	&& p.getEndDate().after(Calendar.getInstance().getTime())
		 	&& p.getCurrency().getCode().equals("HKD")
		 	&& p.getType().getDesc().equals("markdown")
		 ).collect(Collectors.toList()).get(0).getPriceValue();     
	}
	
	
	public Long getProductId() {
		return productId;
	}

	public Collection<Category> getCategories() {
		return this.categories;
	}
	
	
	public List<ProductAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ProductAttribute> productAttributes) {
		this.attributes = productAttributes;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
	}

	public Date getProductCreateDt() {
		return productCreateDt;
	}

	public void setProductCreateDt(Date productCreateDt) {
		this.productCreateDt = productCreateDt;
	}
	
	public List<ProductPrice> getPrices() {
		return this.prices;
	}

	public void setPrices(List<ProductPrice> productPrices) {
		this.prices = productPrices;
	}
	
	public Long getPreviewFlag() {
		return previewFlag;
	}

	public void setPreviewFlag(Long previewFlag) {
		this.previewFlag = previewFlag;
	}
	
	public Long getFeaturedFlag() {
		return featuredFlag;
	}

	public void setFeaturedFlag(Long featuredFlag) {
		this.featuredFlag = featuredFlag;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
			
}
