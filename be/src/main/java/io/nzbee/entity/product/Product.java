package io.nzbee.entity.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.lucene.analysis.cjk.CJKBigramFilterFactory;
import org.apache.lucene.analysis.cjk.CJKWidthFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Normalizer;
import org.hibernate.search.annotations.NormalizerDef;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.department.Department;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.tag.Tag;

@Entity
@Table(name = "product", schema = "mochi")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="dept_id")
@AnalyzerDef(name = "en-GB",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, 
  params = {
	      @Parameter(name = "language", value = "English")
  })
})
@AnalyzerDef(name = "zh-HK",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = CJKWidthFilterFactory.class),
  @TokenFilterDef(factory = CJKBigramFilterFactory.class),
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = StopFilterFactory.class)
})

@AnalyzerDef(name = "autocompleteEdgeAnalyzerENGB",
//Split input into tokens according to tokenizer
tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
filters = {
// Normalize token text to lowercase, as the user is unlikely to
// care about casing when searching for matches
@TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
@Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
@Parameter(name = "replacement", value = " "),
@Parameter(name = "replace", value = "all") }),
@TokenFilterDef(factory = LowerCaseFilterFactory.class),
@TokenFilterDef(factory = StopFilterFactory.class),
// Index partial words starting at the front, so we can provide
// Autocomplete functionality
@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
@Parameter(name = "minGramSize", value = "3"),
@Parameter(name = "maxGramSize", value = "50") }) })


@AnalyzerDef(name = "autocompleteNGramAnalyzerENGB",
//Split input into tokens according to tokenizer
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
// Normalize token text to lowercase, as the user is unlikely to
// care about casing when searching for matches
@TokenFilterDef(factory = WordDelimiterFilterFactory.class),
@TokenFilterDef(factory = LowerCaseFilterFactory.class),
@TokenFilterDef(factory = NGramFilterFactory.class, params = {
@Parameter(name = "minGramSize", value = "3"),
@Parameter(name = "maxGramSize", value = "5") }),
@TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
@Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
@Parameter(name = "replacement", value = " "),
@Parameter(name = "replace", value = "all") })})

@AnalyzerDef(name = "autocompleteEdgeAnalyzerZHHK",
//Split input into tokens according to tokenizer
tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
filters = {
//Normalize token text to lowercase, as the user is unlikely to
//care about casing when searching for matches
@TokenFilterDef(factory = CJKWidthFilterFactory.class),
@TokenFilterDef(factory = CJKBigramFilterFactory.class),
@TokenFilterDef(factory = StopFilterFactory.class),
//Index partial words starting at the front, so we can provide
//Autocomplete functionality
@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
@Parameter(name = "minGramSize", value = "1"),
@Parameter(name = "maxGramSize", value = "50") }) })


@AnalyzerDef(name = "autocompleteNGramAnalyzerZHHK",
//Split input into tokens according to tokenizer
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
//Normalize token text to lowercase, as the user is unlikely to
//care about casing when searching for matches
@TokenFilterDef(factory = CJKWidthFilterFactory.class),
@TokenFilterDef(factory = CJKBigramFilterFactory.class),
@TokenFilterDef(factory = NGramFilterFactory.class, params = {
@Parameter(name = "minGramSize", value = "1"),
@Parameter(name = "maxGramSize", value = "5") })})


@SqlResultSetMappings({
		@SqlResultSetMapping(
	    name = "ProductMapping",
	    columns = {
	    		@ColumnResult(name = "retail_price"),
	    		@ColumnResult(name = "markdown_price"),
	    		@ColumnResult(name = "prd_img_pth")
	    },		
	    entities = {
	            @EntityResult(
	                    entityClass = Product.class,
	                    		discriminatorColumn="dept_id",
	                    fields = {
	                        @FieldResult(name = "productId", 			column = "prd_id"),
	                        @FieldResult(name = "productUPC", 			column = "upc_cd"),
	                        @FieldResult(name = "productCreateDt", 		column = "prd_crtd_dt"),
	                        @FieldResult(name = "brand", 				column = "bnd_id"),
	                        @FieldResult(name = "productStatus", 		column = "prd_sts_id"),
	                        @FieldResult(name = "department", 			column = "dept_id"),	      
	                        @FieldResult(name = "countryOfOrigin",  	column = "ctry_of_orig"),
	                        @FieldResult(name = "expiryDate",  			column = "exp_dt"),
	                        @FieldResult(name = "locale",  				column = "lcl_cd"),
	                        @FieldResult(name = "currency",  			column = "ccy_cd"),
	                        @FieldResult(name = "primaryCategory", 		column = "prm_cat_id"),
	                        @FieldResult(name = "primaryCategoryIndex", column = "prm_cat_id")
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
	                    	@FieldResult(name = "brandAttributeId", column = "bnd_lcl_id"),
		                    @FieldResult(name = "brandId", 			column = "bnd_id"),
		                    @FieldResult(name = "brandDesc", 		column = "bnd_desc"),
		                    @FieldResult(name = "lclCd", 			column = "lcl_cd"),
		                    @FieldResult(name = "brand", 			column = "bnd_id")
	                    }),
	            @EntityResult(
	                    entityClass = Department.class,
	                    fields = {
	                    	@FieldResult(name = "departmentId", 	column = "dept_id"),
		                    @FieldResult(name = "departmentCode", 	column = "dept_cd"),
		                    @FieldResult(name = "departmentClass", 	column = "dept_class")
	                    }),
	            @EntityResult(
	                    entityClass = DepartmentAttribute.class,
	                    fields = {
	                    	@FieldResult(name = "Id", 				column = "dept_lcl_id"),
		                    @FieldResult(name = "departmentDesc", 	column = "dept_desc"),
		                    @FieldResult(name = "lclCd", 			column = "lcl_cd"),
		                    @FieldResult(name = "department", 		column = "dept_id")
	                    }),
	            @EntityResult(
	            		entityClass = CategoryProduct.class,
		                fields = {
		                    @FieldResult(name = "categoryId", 					column = "prm_cat_id"),
		                    @FieldResult(name = "categoryCode", 				column = "cat_cd"),
		                    @FieldResult(name = "categoryLevel", 				column = "cat_lvl"),	
		                    @FieldResult(name = "categoryType", 				column = "cat_typ_id"),
		                    @FieldResult(name = "parent", 						column = "cat_prnt_id"),
		                    @FieldResult(name = "categoryParentId",				column = "cat_prnt_id"),
	                        @FieldResult(name = "categoryParentCode", 			column = "cat_prnt_cd"),
		                }),
	            @EntityResult(
	                    entityClass = CategoryAttribute.class,
	                    fields = {
	                        @FieldResult(name = "categoryAttributeId", 			column = "cat_lcl_id"),
	                        @FieldResult(name = "categoryId", 					column = "prm_cat_id"),
	                        @FieldResult(name = "lclCd", 						column = "lcl_cd"),
	                        @FieldResult(name = "categoryDesc", 				column = "cat_desc"),
	                        @FieldResult(name = "category", 					column = "prm_cat_id")
	                    }),
	            @EntityResult(
	                    entityClass = CategoryType.class,
		                fields = {
		                    @FieldResult(name = "categoryTypeId",				column = "cat_typ_id"),
		                    @FieldResult(name = "categoryTypeCode", 			column = "cat_typ_cd"),
		                    @FieldResult(name = "categoryTypeDesc", 			column = "cat_typ_desc")
		                }),
	            @EntityResult(
	            		entityClass = CategoryProduct.class,
		                fields = {
		                    @FieldResult(name = "categoryId", 					column = "cat_prnt_id"),
		                    @FieldResult(name = "categoryCode", 				column = "cat_prnt_cd"),
		                    @FieldResult(name = "categoryLevel", 				column = "cat_prnt_lvl"),
		                    @FieldResult(name = "categoryType", 				column = "cat_typ_id"),
		                    @FieldResult(name = "parent", 						column = "cat_prnt_prnt_id"),
		                    @FieldResult(name = "categoryParentId",				column = "cat_prnt_prnt_id"),
	                        @FieldResult(name = "categoryParentCode", 			column = "cat_prnt_prnt_cd"),
		                }),
	    }),
		@SqlResultSetMapping(
			    name = "ProductMapping.count",
			    columns = {
			    	@ColumnResult(name = "product_count")
			    })
})
@NormalizerDef(
		name = "sortNormalizer",
		filters = {
			@TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // To handle diacritics such as "é"
			@TokenFilterDef(factory = LowerCaseFilterFactory.class)
		}
)
public abstract class Product { 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_id")
	protected Long productId;

	@NaturalId
	@Column(name="upc_cd", unique = true, updatable = false)
	@Field(store=Store.YES,analyze=Analyze.NO)
	private String productUPC;
	
	@Column(name="prd_crtd_dt")
	@Field(store=Store.YES)
	private Date productCreateDt;

	@ManyToMany(fetch = FetchType.LAZY,
				mappedBy = "products")
	@IndexedEmbedded(	prefix="product.categories.", 
						includeEmbeddedObjectId=true)
	private Set<CategoryProduct> categories = new HashSet<CategoryProduct>();
	
	@ManyToMany(fetch = FetchType.LAZY,
				mappedBy = "products") 
	@IndexedEmbedded(	prefix="product.tags.", 
						includeEmbeddedObjectId=true)
	private Set<Tag> tags = new HashSet<Tag>();

	@OneToMany(	mappedBy="product",  
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<ProductAttribute> attributes = new HashSet<ProductAttribute>();
	
	@Transient
	private ProductAttribute productAttribute;

	@Transient
	@Field(store=Store.YES,analyze=Analyze.NO)
	private Double retailPrice;
	
	@Transient
	@Field(store=Store.YES,analyze=Analyze.NO)
	private Double markdownPrice;
	
	@Transient 
	private String locale;
	
	@Transient
	private String currency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bnd_id")
	@IndexedEmbedded(prefix="product.brand.")
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dept_id",
				nullable = false,  
				updatable = false, 
				insertable = false)
	@IndexedEmbedded(prefix="product.department.")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prd_sts_id")
	@IndexedEmbedded(prefix="product.status.")
	private ProductStatus productStatus;

	@OneToMany(	mappedBy="product",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	List<ProductPrice> prices = new ArrayList<ProductPrice>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prm_cat_id")
	@IndexedEmbedded(prefix="product.primarycategory.")
	private CategoryProduct primaryCategoryIndex; 

	@Transient
	private CategoryProduct primaryCategory;
	
	public Product() {
	}

	public CategoryProduct getPrimaryCategory() {
		return primaryCategory;
	}
	
	public CategoryProduct getPrimaryCategoryIndex() {
		return primaryCategoryIndex;
	}

	public void setPrimaryCategory(CategoryProduct primaryCategoryIndex) {
		this.primaryCategoryIndex = primaryCategoryIndex; 
		this.primaryCategory = primaryCategoryIndex;
	}


	
	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentRetailPriceHKD() {
		 Optional<ProductPrice> priceVal = 
				 this.prices.stream().filter(p ->
										 	p.getCurrency().getCode().equals("HKD")
										 	&& p.getType().getCode().equals("RET01")
						 			).findFirst();
				 
		 return (priceVal.isPresent()) 
					? priceVal.get().getPriceValue()
					: new Double(0);     
	}
	
	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentRetailPriceUSD() {
		Optional<ProductPrice> priceVal = 
				 this.prices.stream().filter(p ->
										 	p.getCurrency().getCode().equals("USD")
										 	&& p.getType().getCode().equals("RET01")
						 			).findFirst();
				 
		return (priceVal.isPresent()) 
				? priceVal.get().getPriceValue()
				: new Double(0);     
	}
	
	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentMarkdownPriceHKD() {
		Optional<ProductPrice> priceVal = 
				 this.prices.stream().filter(p ->
										 	p.getCurrency().getCode().equals("HKD") && 
										 	p.getType().getCode().equals("MKD01")
						 			).findFirst();
				 
		return (priceVal.isPresent()) 
				? priceVal.get().getPriceValue()
				: new Double(0);        
	}
	
	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentMarkdownPriceUSD() {
		Optional<ProductPrice> priceVal = 
				 this.prices.stream().filter(p ->
										 	p.getCurrency().getCode().equals("USD") && 
										 	p.getType().getCode().equals("MKD01")
						 			).findFirst();
				 
		return (priceVal.isPresent()) 
				? priceVal.get().getPriceValue()
				: new Double(0);           
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
		return this.getCurrentMarkdownPriceUSD();
	}
	
	@Transient
	@Fields({
			  @Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "en-GB")),
			  @Field(name = "edgeNGramTitleENGB", index = Index.YES, store = Store.NO,
			  			analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteEdgeAnalyzerENGB")),
			  @Field(name = "nGramTitleENGB", index = Index.YES, store = Store.NO,
			  			analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteNGramAnalyzerENGB"))
	})
	public String getProductDescENGB() {
		Optional<ProductAttribute> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals("en-GB")).findFirst();
		return (pa.isPresent()) ? pa.get().getProductDesc() : "Not Applicable"; 
	}
	
	@Transient
	@Fields({
		@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "zh-HK")),
		@Field(name = "edgeNGramTitleZHHK", index = Index.YES, store = Store.NO,
			analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteEdgeAnalyzerZHHK")),
		@Field(name = "nGramTitleZHHK", index = Index.YES, store = Store.NO,
			analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteNGramAnalyzerZHHK"))
	})
	public String getProductDescZHHK() {
		Optional<ProductAttribute> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals("zh-HK")).findFirst();
		return (pa.isPresent()) ? pa.get().getProductDesc() : "Not Applicable"; 
	}
	
	@Field(analyze=Analyze.YES, normalizer = @Normalizer(definition = "sortNormalizer"))
	@SortableField
	public String getProductDescSortENGB() {
		return this.getProductDescENGB();
	}
	
	@Field(analyze=Analyze.YES)
	@SortableField
	public String getProductDescSortZHHK() {
		return this.getProductDescZHHK();
	}
	
	@Field(analyze=Analyze.NO, store=Store.YES)
	public String getProductImageENGB() {
		Optional<ProductAttribute> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals("en-GB")).findFirst();
		return (pa.isPresent()) ? pa.get().getProductImage() : "unknown.jpg";
	}
	
	@Field(analyze=Analyze.NO, store=Store.YES)
	public String getProductImageZHHK() {
		Optional<ProductAttribute> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals("zh-HK")).findFirst();
		return (pa.isPresent()) ? pa.get().getProductImage() : "unknown.jpg";
	}
	

	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
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
		return this.tags;
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	private Set<ProductAttribute> getAttributes() {
		return attributes;
	}
	
	public ProductAttribute getProductAttribute() {
		return productAttribute;
	}

	public void setProductAttribute(ProductAttribute productAttribute) {
		this.productAttribute = productAttribute;
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
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
	
	public void addTag(Tag tag) {
		this.getTags().add(tag);
		tag.getProducts().add(this);
	}
	
	public void removeTag(Tag tag) {
		this.getTags().remove(tag);
		tag.getProducts().remove(this);
	}
	
	public void addProductCategory(CategoryProduct categoryProduct) {
		this.getCategories().add(categoryProduct);
		categoryProduct.getProducts().add(this);
	}
	
	public void removeProductCategory(CategoryProduct categoryProduct) {
		this.getCategories().remove(categoryProduct);
		categoryProduct.getProducts().remove(this);
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
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public void addCategory(CategoryProduct category) {
		this.getCategories().add(category);
		category.getProducts().add(this);
	}
	
	public void removeCategory(CategoryProduct category) {
		this.getCategories().remove(category);
		category.removeProduct(this);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return productUPC != null && productUPC.equals(((Product) o).getProductUPC());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getProductUPC());
    }
    
    public String getTypeDiscriminator() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
			
}
