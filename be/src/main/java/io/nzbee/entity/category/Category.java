package io.nzbee.entity.category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.layout.Layout;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.variables.GeneralVars;

@Entity
@Table(name = "category", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	private Long categoryId;

	@Column(name="cat_cd")
	@Field(analyze = Analyze.NO)
	@Facet
	private String categoryCode;

	@Column(name="cat_lvl")
	private Long categoryLevel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@IndexedEmbedded
	@JoinColumn(name="hir_id", insertable=false, updatable=false)
	@JsonBackReference
	private Hierarchy hierarchy;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @OrderBy
    @JsonIgnore
    private List<Product> products;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "brand_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "bnd_id"))
    @OrderBy
    @JsonIgnore
    private List<Brand> brands;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "layout_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "lay_id"))
    @OrderBy
    @JsonIgnore
    private List<Layout> layouts;

	@ManyToOne
	@JoinColumn(name="cat_typ_id", nullable=false, updatable = false, insertable = true)
	private CategoryType categoryType;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional=false)
	@JoinColumn(name="cat_prnt_id", nullable=false)
	@IndexedEmbedded(depth = 5)
	private Category parent;
	
	@OneToMany(mappedBy="category",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CategoryAttribute> attributes;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="cat_prnt_id")
	})
	private List<Category> children;	

	
	@Field(analyze = Analyze.NO)
	@Facet
	public String getCategoryToken() {
		String token = createCategoryToken(this, new ArrayList<String>());
		if(token == null || token.isEmpty()) { return "Unknown"; }
		return token;
	}
	
	
	private String createCategoryToken(Category category, List<String> lc) {
		lc.add(category.getCategoryCode());
		Optional<Category> parent = Optional.ofNullable(category.getParent());
		if(!parent.isPresent()) {
			StringBuilder sb = new StringBuilder();
			Lists.reverse(lc).stream().forEach(s -> sb.append("/").append(s));
			return sb.toString();
		}
		return this.createCategoryToken(parent.get(), lc);
	}
	
	@Field(analyze = Analyze.YES, analyzer = @Analyzer(definition = GeneralVars.LANGUAGE_ENGLISH))
	public String getPrimaryCategoryDescENGB() {
		Optional<CategoryAttribute> pca = this.getAttributes().stream().filter(ca -> {
		 			return ca.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH);
		 		}).collect(Collectors.toList()).stream().findFirst();
		if(!pca.isPresent()) { return "Unknown"; }
		return pca.get().getCategoryDesc();
	}
	
	
	@Field(analyze = Analyze.YES, analyzer = @Analyzer(definition = GeneralVars.LANGUAGE_HK))
	public String getPrimaryCategoryDescZHHK() {
		Optional<CategoryAttribute> pca = this.getAttributes().stream().filter(ca -> {
		 			return ca.getLclCd().equals(GeneralVars.LANGUAGE_HK);
		 		}).collect(Collectors.toList()).stream().findFirst();
		if(!pca.isPresent()) { return "Unknown"; }
		return pca.get().getCategoryDesc();
	}
	
	@Field(analyze = Analyze.YES, analyzer = @Analyzer(definition = GeneralVars.LANGUAGE_ENGLISH))
	public String getSecondaryCategoryDescENGB() {
		Optional<CategoryAttribute> pca = this.getAttributes().stream().filter(ca -> {
		 			return ca.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH);
		 		}).collect(Collectors.toList()).stream().findFirst();
		if(!pca.isPresent()) { return "Unknown"; }
		return pca.get().getCategoryDesc();
	}

	@Field(analyze = Analyze.YES, analyzer = @Analyzer(definition = GeneralVars.LANGUAGE_HK))
	public String getSecondaryCategoryDescZHHK() {
		Optional<CategoryAttribute> pca = this.getAttributes().stream().filter(ca -> {
		 			return ca.getLclCd().equals(GeneralVars.LANGUAGE_HK);
		 		}).collect(Collectors.toList()).stream().findFirst();
		if(!pca.isPresent()) { return "Unknown"; }
		return pca.get().getCategoryDesc();
	}
	
	public Long getChildCategoryCount() {
		return new Long(this.children.size());
	}

	public List<Category> getChildren() {
		return children;
	}
	
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	public List<CategoryAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<CategoryAttribute> categoryAttributes) {
		this.attributes = categoryAttributes;
	}

	public Long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Long categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	
	public Collection<Product> getProducts() {
		return products;
	}
	
    public Hierarchy getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Hierarchy hierarchy) {
		this.hierarchy = hierarchy;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	public Long getProductCount() {
		return new Long(products.size());
	}
	
	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public List<Layout> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}
}
