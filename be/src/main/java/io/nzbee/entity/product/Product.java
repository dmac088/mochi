package io.nzbee.entity.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.type.ProductType;
import io.nzbee.entity.tag.Tag;
import io.nzbee.variables.ProductVars;

@Entity
@Table(name = "product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
@SqlResultSetMappings({
		@SqlResultSetMapping(
	    name = "ProductMapping",
	    columns = {
	    		@ColumnResult(name = "retail_price"),
	    		@ColumnResult(name = "markdown_price")
	    },		
	    entities = {
	            @EntityResult(
	                    entityClass = Product.class,
	                    fields = {
	                        @FieldResult(name = "productId", 		column = "prd_id"),
	                        @FieldResult(name = "productUPC", 		column = "upc_cd"),
	                        @FieldResult(name = "productCreateDt", 	column = "prd_crtd_dt"),
	                        @FieldResult(name = "brand", 			column = "bnd_id"),
	                        @FieldResult(name = "productStatus", 	column = "prd_sts_id"),
	                        @FieldResult(name = "productAttribute", column = "prd_lcl_id"),	      
	                        @FieldResult(name = "attributes", 		column = "prd_id"),
	                    }),
	            @EntityResult(
		                entityClass = ProductStatus.class,
		                fields = {
		                	@FieldResult(name = "productStatusId", 		column = "prd_sts_id"),
		                    @FieldResult(name = "productStatusCode", 	column = "prd_sts_cd"),
		                    @FieldResult(name = "productStatusDesc", 	column = "prd_sts_desc")
                }),
	            @EntityResult(
	            		entityClass = ProductAttribute.class,
		                fields = {
		                    @FieldResult(name = "Id", 				column = "prd_lcl_id"),
		                    @FieldResult(name = "productId", 		column = "prd_id"),
		                    @FieldResult(name = "productDesc", 		column = "prd_desc"),
		                    @FieldResult(name = "ProductImage", 	column = "prd_img_pth"),
		                    @FieldResult(name = "lclCd", 			column = "lcl_cd"),
		                    @FieldResult(name = "product", 			column = "prd_id")
		        }),	            
	            @EntityResult(
	                    entityClass = Brand.class,
	                    fields = {
	                    	@FieldResult(name = "brandId", 			column = "bnd_id"),
		                    @FieldResult(name = "brandCode", 		column = "bnd_cd"),
		                    @FieldResult(name = "brandAttribute", 	column = "bnd_lcl_id"),
		                    @FieldResult(name = "brandAttributes", 	column = "bnd_lcl_id"),
		                    @FieldResult(name = "products", 		column = "prd_id")
	                    }),
	            @EntityResult(
	                    entityClass = BrandAttribute.class,
	                    fields = {
	                    	@FieldResult(name = "Id", 				column = "bnd_lcl_id"),
		                    @FieldResult(name = "brandId", 			column = "bnd_id"),
		                    @FieldResult(name = "brandDesc", 		column = "bnd_desc"),
		                    @FieldResult(name = "lclCd", 			column = "lcl_cd"),
		                    @FieldResult(name = "brand", 			column = "bnd_id")
	                    }),

	    }),
		@SqlResultSetMapping(
			    name = "ProductMapping.count",
			    columns = {
			    	@ColumnResult(name = "product_count")
			    })
})

public class Product { 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_id")
	private Long productId;

	@NaturalId
	@Column(name="upc_cd", unique = true, updatable = false)
	@Field(store=Store.YES)
	private String productUPC;
	
	@Column(name="prd_crtd_dt")
	@Field(store=Store.YES)
	private Date productCreateDt;

	@ManyToMany(fetch = FetchType.LAZY,
				mappedBy = "products",
		    	cascade = {
		            CascadeType.PERSIST,
		            CascadeType.MERGE
		        })
	@IndexedEmbedded
	private Set<CategoryProduct> categories = new HashSet<>();
	
	@ManyToMany(mappedBy = "products", 
				cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Tag> tags;

	@OneToMany(	mappedBy="product",  
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	@JsonManagedReference
	private List<ProductAttribute> attributes = new ArrayList<ProductAttribute>();
	
	@Transient
	private ProductAttribute productAttribute;
	
	@Transient
	private Double retailPrice;
	
	@Transient
	private Double markdownPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bnd_id")
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prd_typ_id")
	private ProductType productType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prd_sts_id")
	private ProductStatus productStatus;

	@OneToMany(	mappedBy="product",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	List<ProductPrice> prices;

	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentRetailPriceHKD() {
		 return this.prices.stream().filter(p ->
		 	p.getCurrency().getCode().equals(ProductVars.CURRENCY_HONG_KONG)
		 	&& p.getType().getDesc().equals(ProductVars.RETAIL_SKU_DESCRIPTION)
		 ).collect(Collectors.toList()).get(0).getPriceValue();     
	}
	
	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentRetailPriceUSD() {
		 return this.prices.stream().filter(p ->
		 	p.getCurrency().getCode().equals(ProductVars.CURRENCY_US)
		 	&& p.getType().getDesc().equals(ProductVars.RETAIL_SKU_DESCRIPTION)
		 ).collect(Collectors.toList()).get(0).getPriceValue();     
	}
	
	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentMarkdownPriceHKD() {
		 return this.prices.stream().filter(p ->
		 	p.getCurrency().getCode().equals(ProductVars.CURRENCY_HONG_KONG)
		 	&& p.getType().getDesc().equals(ProductVars.MARKDOWN_SKU_DESCRIPTION)
		 ).collect(Collectors.toList()).get(0).getPriceValue();     
	}
	
	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentMarkdownPriceUSD() {
		 return this.prices.stream().filter(p ->
		 	p.getCurrency().getCode().equals(ProductVars.CURRENCY_US)
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

	public ProductAttribute getProductAttribute() {
		return productAttribute;
	} 

	public void setProductAttribute(ProductAttribute productAttribute) {
		this.productAttribute = productAttribute;
	}
	
	public Long getProductId() {
		return productId;
	}

	public Set<CategoryProduct> getCategories() {
		return this.categories;
	}
	
	public void setCategories(Set<CategoryProduct> categories) {
		this.categories = categories;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}
	
	public List<ProductAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ProductAttribute> productAttributes) {
		this.attributes = productAttributes;
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
	
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
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
	
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getMarkdownPrice() {
		return markdownPrice;
	}

	public void setMarkdownPrice(Double markdownPrice) {
		this.markdownPrice = markdownPrice;
	}
	
	public void addProductCategory(CategoryProduct categoryProduct) {
		this.getCategories().add(categoryProduct);
		categoryProduct.getProducts().add(this);
	}
	
	public void removeProductCategory(CategoryProduct categoryProduct) {
		this.getCategories().remove(categoryProduct);
		categoryProduct.removeProduct(this);
	}
	
	public void addProductAttribute(ProductAttribute productAttribute) {
		this.getAttributes().add(productAttribute);
		productAttribute.setProduct(this);
	}
	
	public void removeProductAttribute(ProductAttribute productAttribute) {
		this.getAttributes().remove(productAttribute);
		productAttribute.setProduct(null);
	}
	
	public void addProductPrice(ProductPrice productPrice) {
		this.getPrices().add(productPrice);
		productPrice.setProduct(this);
	}
	
	public void removeProductPrice(ProductPrice productPrice) {
		this.getPrices().remove(productPrice);
		productPrice.setProduct(null);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return productId != null && productId.equals(((Product) o).getProductId());
    }
 
    @Override
    public int hashCode() {
        return 31;
    }
			
}
