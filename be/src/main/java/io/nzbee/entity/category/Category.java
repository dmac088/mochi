package io.nzbee.entity.category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.layout.Layout;

@Entity
@Table(name = "category", schema = "mochi")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="cat_typ_id")
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = CategoryProduct.class, 	name = "categoryproduct"),
			    @JsonSubTypes.Type(value = CategoryBrand.class, 	name = "categorybrand")})
@SqlResultSetMapping(
    name = "CategoryMapping",
    columns = {
    		@ColumnResult(name = "object_count"),
    		@ColumnResult(name = "child_cat_count"),
    		@ColumnResult(name = "category_layouts")
    },
    entities = {
            @EntityResult(
                    entityClass = Category.class,
                    discriminatorColumn="cat_typ_id",
                    fields = {
                        @FieldResult(name = "categoryId", 					column = "cat_id"),
                        @FieldResult(name = "categoryCode", 				column = "cat_cd"),
                        @FieldResult(name = "categoryLevel", 				column = "cat_lvl"),	
                        @FieldResult(name = "categoryType", 				column = "cat_typ_id"),
                        @FieldResult(name = "parent", 						column = "cat_prnt_id"),
                        @FieldResult(name = "categoryAttribute", 			column = "cat_lcl_id"),
                        @FieldResult(name = "hierarchy", 					column = "hir_id"),
                        @FieldResult(name = "productCount", 				column = "object_count"),
                        @FieldResult(name = "brandCount", 					column = "object_count"),
                        @FieldResult(name = "maxRetailPrice", 				column = "max_retail_price"),
                        @FieldResult(name = "maxMarkdownPrice", 			column = "max_markdown_price"),
                        @FieldResult(name = "attributes", 					column = "cat_id")
                    }),
            @EntityResult(
                    entityClass = CategoryAttribute.class,
                    fields = {
                        @FieldResult(name = "categoryAttributeId", 			column = "cat_lcl_id"),
                        @FieldResult(name = "categoryId", 					column = "cat_id"),
                        @FieldResult(name = "lclCd", 						column = "lcl_cd"),
                        @FieldResult(name = "categoryDesc", 				column = "cat_desc"),
                        @FieldResult(name = "category", 					column = "cat_id")
                    }),
	        @EntityResult(
	                entityClass = CategoryType.class,
	                fields = {
	                    @FieldResult(name = "categoryTypeId",			column = "cat_typ_id"),
	                    @FieldResult(name = "categoryTypeCode", 			column = "cat_typ_cd"),
	                    @FieldResult(name = "categoryTypeDesc", 			column = "cat_typ_desc")
	                }),
	            //now we initialize the parent category
	        @EntityResult(
	                entityClass = Category.class,
	                discriminatorColumn="cat_typ_id",
	                fields = {
	                    @FieldResult(name = "categoryId", 					column = "cat_prnt_id"),
	                    @FieldResult(name = "categoryCode", 				column = "cat_prnt_cd"),
                        @FieldResult(name = "categoryLevel", 				column = "cat_prnt_lvl"),
                        @FieldResult(name = "categoryType", 				column = "cat_prnt_typ_id"),
                        @FieldResult(name = "parent", 						column = "cat_prnt_prnt_id"),
                        @FieldResult(name = "categoryAttribute", 			column = "cat_prnt_lcl_id"),
                        @FieldResult(name = "hierarchy", 					column = "cat_prnt_hir_id"),
                        @FieldResult(name = "productCount", 				column = "cat_prnt_object_count"),
                        @FieldResult(name = "brandCount", 					column = "cat_prnt_object_count"),
                        @FieldResult(name = "maxRetailPrice", 				column = "cat_prnt_max_retail_price"),
                        @FieldResult(name = "maxMarkdownPrice", 			column = "cat_prnt_max_markdown_price"),
                        @FieldResult(name = "attributes", 					column = "cat_prnt_id")
	                }),
	        @EntityResult(
	                entityClass = CategoryAttribute.class,
	                fields = {
	                    @FieldResult(name = "categoryAttributeId", 			column = "cat_prnt_lcl_id"),
	                    @FieldResult(name = "categoryId", 					column = "cat_prnt_id"),
	                    @FieldResult(name = "lclCd", 						column = "cat_prnt_lcl_cd"),
	                    @FieldResult(name = "categoryDesc", 				column = "cat_prnt_desc"),
	                    @FieldResult(name = "category", 					column = "cat_prnt_id")
	                }),
	        @EntityResult(
                    entityClass = CategoryType.class,
	                fields = {
	                    @FieldResult(name = "categoryTypeId", 				column = "cat_prnt_typ_id"),
	                    @FieldResult(name = "categoryTypeCode", 			column = "cat_prnt_typ_cd"),
	                    @FieldResult(name = "categoryTypeDesc", 			column = "cat_prnt_typ_desc")
	                }),
	    })
public abstract class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	protected Long categoryId; 

	@NaturalId
	@Column(name="cat_cd", unique = true, updatable = false)
	@Field(analyze = Analyze.NO, store=Store.YES)
	private String categoryCode;

	@Column(name="cat_lvl")
	@Field(analyze = Analyze.NO, store=Store.YES)
	private Long categoryLevel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cat_typ_id",
				nullable = false,  
				updatable = false, 
				insertable = false)
	private CategoryType categoryType;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "layout_category", schema="mochi", 
	    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
	    		   inverseJoinColumns 	= @JoinColumn(name = "lay_id"))
	@OrderBy
	private List<Layout> layouts;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="cat_prnt_id", nullable=false)
	@IndexedEmbedded(depth = 10, includeEmbeddedObjectId=true)
	private Category parent;
	
	@OneToMany(	mappedBy="category",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<CategoryAttribute> attributes = new HashSet<CategoryAttribute>();
	
	@Transient 
	private CategoryAttribute categoryAttribute;
	
	@Transient
	@JsonIgnore
	private String[] categoryLayouts;

	@Transient
	@JsonIgnore
	private Long childCount;
	
	@Transient
	@JsonIgnore
	private Long maxRetailPrice;
	
	@Transient
	@JsonIgnore
	private Long maxMarkdownPrice;
	
	@Transient
	@JsonIgnore
	private String locale;
	
	@Transient
	@JsonIgnore
	private String currency;
	
	public abstract String getType();
	
	public abstract void setType(String type);
	
	@Transient
	@JsonIgnore
	@Field(analyze = Analyze.NO, store=Store.YES)
	public String getCategoryTokenField() {
		String token = createCategoryToken(this, new ArrayList<String>());
		if(token == null || token.isEmpty()) { return "Unknown"; }
		return token;
	}
	
	@Transient
	@Field(analyze = Analyze.NO, store=Store.YES)
	@JsonIgnore
	@Facet
	public String getCategoryToken() {
		return this.getCategoryTokenField();
	}
	
	private String createCategoryToken(Category category, List<String> lc) {
		lc.add(category.getCategoryCode());
		Optional<Category> parent = category.getParent();
		if(!parent.isPresent()) {
			StringBuilder sb = new StringBuilder();
			Lists.reverse(lc).stream().forEach(s -> sb.append("/").append(s));
			return sb.toString();
		}
		return this.createCategoryToken(parent.get(), lc);
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public abstract int getObjectCount();
	
	public abstract void setObjectCount(int count);
	
	public Long getChildCount() {
		return childCount;
	}
	
	public void setChildCount(Long childCount) {
		this.childCount = childCount;
	}
	
	public Optional<Category> getParent() {
		return Optional.ofNullable(parent);
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	@Transient
	@JsonIgnore
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "en-GB"))
	public String getCategoryDescENGB() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals("en-GB")).findFirst().get().getCategoryDesc();
	}
	
	@Transient
	@JsonIgnore
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "zh-HK"))
	public String getCategoryDescZHHK() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals("zh-HK")).findFirst().get().getCategoryDesc();
	}
	
	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Long categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
		
	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public Long getMaxRetailPrice() {
		return maxRetailPrice;
	}

	public void setMaxRetailPrice(Long maxRetailPrice) {
		this.maxRetailPrice = maxRetailPrice;
	}

	public Long getMaxMarkdownPrice() {
		return maxMarkdownPrice;
	}

	public void setMaxMarkdownPrice(Long maxMarkdownPrice) {
		this.maxMarkdownPrice = maxMarkdownPrice;
	} 
	
	public Set<CategoryAttribute> getAttributes() {
		return attributes;
	}
	
	public CategoryAttribute getCategoryAttribute() {
		return categoryAttribute;
	}

	public void setCategoryAttribute(CategoryAttribute categoryAttribute) {
		this.categoryAttribute = categoryAttribute;
	}
	
	public List<Layout> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}

	public void addAttribute(CategoryAttribute categoryAttribute) {
		this.getAttributes().add(categoryAttribute);
		categoryAttribute.setCategory(this);		
	}
	
	public void removeAttribute(CategoryAttribute categoryAttribute) {
		this.getAttributes().remove(categoryAttribute);
		categoryAttribute.setCategory(null);
	}
	
	public String[] getCategoryLayouts() {
		return categoryLayouts;
	}

	public void setCategoryLayouts(String[] categoryLayouts) {
		this.categoryLayouts = categoryLayouts;
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
	
	@JsonIgnore
	public String getTypeDiscriminator() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

}
