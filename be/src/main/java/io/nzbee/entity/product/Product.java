package io.nzbee.entity.product;

import java.util.Calendar;
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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.variables.GeneralVars;
import io.nzbee.variables.ProductVars;

@Entity
@Table(name = "product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "getProducts",
	resultSetMapping = "ProductMapping",
	query = 
		"WITH RECURSIVE " + 
		"descendants AS " + 
		"( " + 
		"  SELECT 	t.cat_id, " +  
		"			t.hir_id, " + 
		"			t.cat_cd, " + 
		"			t.cat_lvl, " + 
		"			t.cat_prnt_id, " +
		"			t.cat_typ_id " +
		" FROM mochi.category AS t " +
		"	INNER JOIN mochi.category  AS pc " +
		"	ON t.cat_prnt_id = pc.cat_id " +
			
		" WHERE pc.cat_cd = :categoryCode " +
		" UNION ALL " + 
		" SELECT 	t.cat_id, " +  
		"			t.hir_id, " + 
		"			t.cat_cd, " +  
		"			t.cat_lvl, " + 
		"			t.cat_prnt_id, " +
		"			t.cat_typ_id " +
		"  FROM mochi.category AS t  " +
		"  JOIN descendants AS d " +
		"  ON t.cat_prnt_id = d.cat_id " + 
		") " +
		"select prd.prd_id, " +
		"	   prd.upc_cd, " +
		"	   prd.prd_crtd_dt, " +
		"	   prdt.prd_typ_cd, " +
		"	   prdt.prd_typ_desc, " +
		"	   bnd.bnd_id, " +
		"	   bnd.bnd_cd, " +
		"	   bal.bnd_desc " +
		
		"FROM descendants cc " + 
		"	INNER JOIN mochi.product_category pc " + 
		"	ON cc.cat_id = pc.cat_id " + 
		
		"	INNER JOIN mochi.product prd " + 
		"	ON pc.prd_id = prd.prd_id " +
			
		"	INNER JOIN mochi.product_type prdt " +
		"	ON prd.prd_typ_id = prdt.prd_typ_id " +
			
		"	INNER JOIN mochi.brand bnd " +
		"	ON prd.bnd_id = bnd.bnd_id " +
			
		"	INNER JOIN mochi.brand_attr_lcl bal " +
		"	ON bnd.bnd_id = bal.bnd_id " +
			
		"	INNER JOIN mochi.price prc " +  
		"	ON prd.prd_id = prc.prd_id " + 
				  
		"	INNER JOIN mochi.currency curr " +  
		"	ON prc.ccy_id = curr.ccy_id " +
				  
		"	INNER JOIN mochi.price_type pt " +
		"	ON prc.prc_typ_id = pt.prc_typ_id " +
		
		"	INNER JOIN mochi.product_status ps " + 
		"	ON prd.prd_sts_id = ps.prd_sts_id " +
				  
		"WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt " + 
		"AND curr.ccy_cd = 	:currency  " +
		"AND prc_typ_cd = 	:retailPriceCode " +
		"AND prd_sts_cd = 	:activeProductCode  " +
		"AND bal.lcl_cd = 	:locale "
		)
	})

public class Product { 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_id")
	private Long productId;

	@Column(name="upc_cd")
	@Field(store=Store.YES)
	private String productUPC;
	
	@Column(name="prd_crtd_dt")
	@Field(store=Store.YES)
	private Date productCreateDt;

	@ManyToMany(mappedBy = "products")
	@IndexedEmbedded
	@JsonIgnore
	private List<CategoryProduct> categories;
	
	@ManyToMany(mappedBy = "products")
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
	@JsonManagedReference
	List<ProductPrice> prices;

	@Field(store=Store.YES)
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
	
	@Field(store=Store.YES)
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
	@Field(analyze=Analyze.NO, store=Store.YES)
	@Transient
	public Double getCurrentMarkdownPriceHKDFacet() {
		return this.getCurrentMarkdownPriceHKD();
	}
	
	@Facet
	@Field(analyze=Analyze.NO, store=Store.YES)
	@Transient
	public Double getCurrentMarkdownPriceUSDFacet() {
		return this.getCurrentMarkdownPriceHKD();
	}
	
	@Transient
	@Field(analyze = Analyze.NO, store=Store.YES)
	@SortableField
	private String getProductDesc() {
		return this.attributes.stream().filter(p -> p.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH)).collect(Collectors.toList()).get(0).getProductDesc().toLowerCase();  
	}
	
	
	public Long getProductId() {
		return productId;
	}

	public List<CategoryProduct> getCategories() {
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

	public void setCategories(List<CategoryProduct> categories) {
		this.categories = categories;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getUPC() {
		return productUPC;
	}

	public void setUPC(String productUPC) {
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
