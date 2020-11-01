package io.nzbee.entity.category;

import java.util.ArrayList;
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import io.nzbee.Constants;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.search.ISearchDimension;

@Entity
@Table(name = "category", schema = "mochi")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="cat_typ_id")
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = CategoryProductEntity.class, 	name = "categoryproduct"),
			    @JsonSubTypes.Type(value = CategoryBrandEntity.class, 	name = "categorybrand")})
//@SqlResultSetMapping(
//    name = "CategoryMapping",
//    columns = {
//    		@ColumnResult(name = "object_count"),
//    		@ColumnResult(name = "child_cat_count")
//    },
//    entities = {
//            @EntityResult(
//                    entityClass = CategoryEntity.class,
//                    discriminatorColumn="cat_typ_id",
//                    fields = {
//                        @FieldResult(name = "categoryId", 					column = "cat_id"),
//                        @FieldResult(name = "categoryCode", 				column = "cat_cd"),
//                        @FieldResult(name = "categoryLevel", 				column = "cat_lvl"),	
//                        @FieldResult(name = "categoryType", 				column = "cat_typ_id"),
//                        @FieldResult(name = "parent", 						column = "cat_prnt_id"),
//                        @FieldResult(name = "categoryParentId",				column = "cat_prnt_id"),
//                        @FieldResult(name = "categoryParentCode", 			column = "cat_prnt_cd"),
//                        @FieldResult(name = "categoryAttribute", 			column = "cat_lcl_id"),
//                        @FieldResult(name = "attributes", 					column = "cat_id")
//                    }),
//            @EntityResult(
//                    entityClass = CategoryAttributeEntity.class,
//                    fields = {
//                        @FieldResult(name = "categoryAttributeId", 			column = "cat_lcl_id"),
//                        @FieldResult(name = "categoryId", 					column = "cat_id"),
//                        @FieldResult(name = "lclCd", 						column = "lcl_cd"),
//                        @FieldResult(name = "categoryDesc", 				column = "cat_desc"),
//                        @FieldResult(name = "category", 					column = "cat_id")
//                    }),
//	        @EntityResult(
//	                entityClass = CategoryType.class,
//	                fields = {
//	                    @FieldResult(name = "categoryTypeId",				column = "cat_typ_id"),
//	                    @FieldResult(name = "categoryTypeCode", 			column = "cat_typ_cd"),
//	                    @FieldResult(name = "categoryTypeDesc", 			column = "cat_typ_desc")
//	                }),
//	            //now we initialize the parent category
//	        @EntityResult(
//	                entityClass = CategoryEntity.class,
//	                discriminatorColumn="cat_typ_id",
//	                fields = {
//	                    @FieldResult(name = "categoryId", 					column = "cat_prnt_id"),
//	                    @FieldResult(name = "categoryCode", 				column = "cat_prnt_cd"),
//                        @FieldResult(name = "categoryLevel", 				column = "cat_prnt_lvl"),
//                        @FieldResult(name = "categoryType", 				column = "cat_prnt_typ_id"),
//                        @FieldResult(name = "parent", 						column = "cat_prnt_prnt_id"),
//                        @FieldResult(name = "categoryParentId",				column = "cat_prnt_prnt_id"),
//                        @FieldResult(name = "categoryParentCode", 			column = "cat_prnt_prnt_cd"),	
//                        @FieldResult(name = "categoryAttribute", 			column = "cat_prnt_lcl_id"),
//                        @FieldResult(name = "attributes", 					column = "cat_prnt_id")
//	                }),
//	        @EntityResult(
//	                entityClass = CategoryAttributeEntity.class,
//	                fields = {
//	                    @FieldResult(name = "categoryAttributeId", 			column = "cat_prnt_lcl_id"),
//	                    @FieldResult(name = "categoryId", 					column = "cat_prnt_id"),
//	                    @FieldResult(name = "lclCd", 						column = "cat_prnt_lcl_cd"),
//	                    @FieldResult(name = "categoryDesc", 				column = "cat_prnt_desc"),
//	                    @FieldResult(name = "category", 					column = "cat_prnt_id")
//	                }),
//	        @EntityResult(
//                    entityClass = CategoryType.class,
//	                fields = {
//	                    @FieldResult(name = "categoryTypeId", 				column = "cat_prnt_typ_id"),
//	                    @FieldResult(name = "categoryTypeCode", 			column = "cat_prnt_typ_cd"),
//	                    @FieldResult(name = "categoryTypeDesc", 			column = "cat_prnt_typ_desc")
//	                }),
//	    })
public abstract class CategoryEntity implements ISearchDimension {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	protected Long categoryId; 

	@NaturalId
	@Column(name="cat_cd", unique = true, updatable = false)
	protected String categoryCode;

	@Column(name="cat_lvl")
	private Long categoryLevel;
	
	@Column(name="cat_prnt_cd")
	private String categoryParentCode;
	
	@Column(name="cat_prnt_id")
	private Long categoryParentId;

	public Long getCategoryParentId() {
		return categoryParentId;
	}

	public void setCategoryParentId(Long categoryParentId) {
		this.categoryParentId = categoryParentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cat_typ_id",
				nullable = false,  
				updatable = false, 
				insertable = false)
	private CategoryType categoryType;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="cat_prnt_id", nullable=false, insertable = false, updatable = false)
	@IndexedEmbedded(depth = 10, includeEmbeddedObjectId=true)
	private CategoryEntity parent;
	
	@OneToMany(	mappedBy="category",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<CategoryAttributeEntity> attributes = new HashSet<CategoryAttributeEntity>();

	@Transient 
	private CategoryAttributeEntity categoryAttribute;

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
	
	private String createCategoryToken(CategoryEntity category, List<String> lc) {
		lc.add(category.getCategoryCode());
		Optional<CategoryEntity> parent = category.getParent();
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

	public abstract Long getObjectCount();
	
	public abstract void setObjectCount(Long count);
	
	public Long getChildCount() {
		return childCount;
	}
	
	public void setChildCount(Long childCount) {
		this.childCount = childCount;
	}
	
	public Optional<CategoryEntity> getParent() {
		return Optional.ofNullable(parent);
	}

	public void setParent(CategoryEntity parent) {
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
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeENGB))
	public String getCategoryDescENGB() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals(Constants.localeENGB)).findFirst().get().getCategoryDesc();
	}
	
	@Transient
	@JsonIgnore
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeZHHK))
	public String getCategoryDescZHHK() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals(Constants.localeZHHK)).findFirst().get().getCategoryDesc();
	}
	
	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Long categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	
	public String getCategoryParentCode() {
		return categoryParentCode;
	}

	public void setCategoryParentCode(String categoryParentCode) {
		this.categoryParentCode = categoryParentCode;
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
	
	public Set<CategoryAttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<CategoryAttributeEntity> attributes) {
		this.attributes = attributes;
	}
	
	public CategoryAttributeEntity getCategoryAttribute() {
		return categoryAttribute;
	}

	public void setCategoryAttribute(CategoryAttributeEntity categoryAttribute) {
		this.categoryAttribute = categoryAttribute;
	}

	public void addCategoryAttribute(CategoryAttributeEntity categoryAttribute) {
		this.getAttributes().add(categoryAttribute);
		categoryAttribute.setCategory(this);		
	}
	
	public void removeCategoryAttribute(CategoryAttributeEntity categoryAttribute) {
		this.getAttributes().remove(categoryAttribute);
		categoryAttribute.setCategory(null);
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
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     CategoryEntity pcDto = (CategoryEntity) o;
	     return this.getCategoryCode() == pcDto.getCategoryCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getCategoryCode());
	}

}