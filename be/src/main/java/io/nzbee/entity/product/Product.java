package io.nzbee.entity.product;

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
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.SortableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.tag.ProductTag;
import io.nzbee.variables.GeneralVars;
import io.nzbee.variables.ProductVars;

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

	@ManyToMany(mappedBy = "products")
	@IndexedEmbedded
	@JsonIgnore
	private List<Category> categories;
	
	@ManyToMany(mappedBy = "products")
	@IndexedEmbedded
	@JsonIgnore
	private List<ProductTag> tags;

	@OneToMany(mappedBy="product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductAttribute> attributes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@IndexedEmbedded
	@JoinColumn(name="bnd_id", insertable=false, updatable=false)
	@JsonBackReference
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@IndexedEmbedded
	@JoinColumn(name="prd_sts_id", insertable=false, updatable=false)
	@JsonBackReference
	private ProductStatus productStatus;

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
		 	&& p.getCurrency().getCode().equals(ProductVars.CURRENCY_HONG_KONG)
		 	&& p.getType().getDesc().equals(ProductVars.MARKDOWN_SKU_DESCRIPTION)
		 ).collect(Collectors.toList()).get(0).getPriceValue();     
	}
	
	@Field
	@SortableField
	@Transient
	public Double getCurrentMarkdownPriceUSD() {
		 return this.prices.stream().filter(p ->
		 	p.getStartDate().before(Calendar.getInstance().getTime())
		 	&& p.getEndDate().after(Calendar.getInstance().getTime())
		 	&& p.getCurrency().getCode().equals(ProductVars.CURRENCY_US)
		 	&& p.getType().getDesc().equals(ProductVars.MARKDOWN_SKU_DESCRIPTION)
		 ).collect(Collectors.toList()).get(0).getPriceValue();     
	}
	
	@Facet
	@Field(analyze=Analyze.NO)
	@Transient
	public Double getCurrentMarkdownPriceHKDFacet() {
		return this.getCurrentMarkdownPriceHKD();
	}
	
	@Facet
	@Field(analyze=Analyze.NO)
	@Transient
	public Double getCurrentMarkdownPriceUSDFacet() {
		return this.getCurrentMarkdownPriceHKD();
	}
	
	@Transient
	@Field(analyze = Analyze.NO)
	@SortableField
	private String getProductDesc() {
		return this.attributes.stream().filter(p -> p.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH)).collect(Collectors.toList()).get(0).getProductDesc().toLowerCase();  
	}
	
	
	public Long getProductId() {
		return productId;
	}

	public Collection<Category> getCategories() {
		return this.categories;
	}
	
	public List<ProductTag> getTags() {
		return tags;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public ProductStatus getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}
			
}
