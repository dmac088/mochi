package io.nzbee.entity.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import com.google.common.collect.Lists;
import io.nzbee.Constants;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.search.ISearchDimension;

@Entity
@Table(name = "category", schema = "mochi")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="cat_typ_id")
public abstract class CategoryEntity implements ISearchDimension, Serializable {

	private static final long serialVersionUID = 3858871432012784690L;

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

	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="cat_prnt_id", nullable=false, insertable = false, updatable = false)
	@IndexedEmbedded(depth = 10, includeEmbeddedObjectId=true)
	private CategoryEntity parent;
	
	@OneToMany(	mappedBy="category",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<CategoryAttributeEntity> attributes = new HashSet<CategoryAttributeEntity>();

	@ManyToMany(mappedBy = "categories")
    private Set<PromotionEntity> promotions = new HashSet<PromotionEntity>();
	
	@Transient 
	private CategoryAttributeEntity categoryAttribute;

	@Transient
	private Long childCount;
	
	@Transient
	private Long maxRetailPrice;
	
	@Transient
	private Long maxMarkdownPrice;
	
	@Transient
	private String locale;
	
	@Transient
	private String currency;

	public abstract String getType();
	
	public abstract void setType(String type);

	@Transient
	@Field(analyze = Analyze.NO, store=Store.YES)
	public String getCategoryTokenField() {
		String token = createCategoryToken(this, new ArrayList<String>());
		if(token == null || token.isEmpty()) { return "Unknown"; }
		return token;
	}
	
	@Transient
	@Field(analyze = Analyze.NO, store=Store.YES)
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
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeENGB))
	public String getCategoryDescENGB() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals(Constants.localeENGB)).findFirst().get().getCategoryDesc();
	}
	
	@Transient
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
	
	public Set<PromotionEntity> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<PromotionEntity> promotions) {
		this.promotions = promotions;
	}

	public void addPromotion(PromotionEntity promotion) {
		this.getPromotions().add(promotion);
		promotion.getCategories().add(this);
	}
	
	public void removePromotion(PromotionEntity promotion) {
		this.getPromotions().remove(promotion);
		promotion.removeCategory(this);
	}
	
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
