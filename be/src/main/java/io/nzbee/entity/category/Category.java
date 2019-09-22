package io.nzbee.entity.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.layout.Layout;
import io.nzbee.entity.product.hierarchy.Hierarchy;

@Entity
@Table(name = "category", schema = "mochi")
@Inheritance
@DiscriminatorColumn(name="cat_typ_id")
//@MappedSuperclass
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.MINIMAL_CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")


	@SqlResultSetMapping(
	    name = "CategoryMapping",
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
	                        @FieldResult(name = "categoryAttribute", 			column = "cat_lcl_id")
	                    }),
	            @EntityResult(
	                    entityClass = CategoryAttribute.class,
	                    fields = {
	                        @FieldResult(name = "categoryAttributeId", 			column = "cat_lcl_id"),
	                        @FieldResult(name = "categoryId", 					column = "cat_id"),
	                        @FieldResult(name = "lclCd", 						column = "lcl_cd"),
	                        @FieldResult(name = "categoryDesc", 				column = "cat_desc")
	                    }),
	            @EntityResult(
	                    entityClass = CategoryType.class,
	                    fields = {
	                        @FieldResult(name = "categoryTypeId", 				column = "cat_typ_id"),
	                        @FieldResult(name = "categoryTypeCode", 			column = "cat_typ_cd"),
	                        @FieldResult(name = "categoryTypeDesc", 			column = "cat_typ_desc")
	                    }),
	            @EntityResult(
	                    entityClass = Hierarchy.class,
	                    fields = {
	                        @FieldResult(name = "hierarchyId", 					column = "hir_id"),
	                        @FieldResult(name = "hierarchyCode", 				column = "hir_cd"),
	                        @FieldResult(name = "hierarchyDesc", 				column = "hir_desc")
	                    }),
	            //now we initialize the parent category
	            @EntityResult(
	                    entityClass = Category.class,
	                    discriminatorColumn="cat_typ_id",
	                    fields = {
	                        @FieldResult(name = "categoryId", 					column = "cat_prnt_id"),
	                        @FieldResult(name = "categoryCode", 				column = "cat_prnt_cd"),
	                        @FieldResult(name = "categoryLevel", 				column = "cat_prnt_lvl"),
	                        @FieldResult(name = "categoryType", 				column = "cat_typ_id"),
	                        @FieldResult(name = "parent", 						column = "cat_prnt_prnt_id"),
	                        @FieldResult(name = "categoryAttribute", 			column = "cat_prnt_lcl_id")
	                    }),
	    })
public abstract class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	private Long categoryId;

	@Column(name="cat_cd")
	//@Field(analyze = Analyze.NO)
	//@Facet
	private String categoryCode;

	@Column(name="cat_lvl")
	private Long categoryLevel;

//	
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@IndexedEmbedded
//	@JoinColumn(name="hir_id", insertable=false, updatable=false)
//	@JsonBackReference
//	private Hierarchy hierarchy;
//    
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "layout_category", schema="mochi", 
//    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
//    		   inverseJoinColumns 	= @JoinColumn(name = "lay_id"))
//    @OrderBy
//    @JsonIgnore
//    private List<Layout> layouts;
//
	@ManyToOne
	@JoinColumn(name="cat_typ_id", nullable=false, updatable = false, insertable = false)
	private CategoryType categoryType;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional=false)
	@JoinColumn(name="cat_prnt_id", nullable=false)
	@IndexedEmbedded(depth = 5)
	private Category parent;
	
	@OneToOne
	@JsonIgnore
	private CategoryAttribute categoryAttribute;
//	
//	@Transient
//	private Long childCount;
//	
//	@Field(analyze = Analyze.NO)
//	@Facet
//	public String getCategoryToken() {
//		String token = createCategoryToken(this, new ArrayList<String>());
//		if(token == null || token.isEmpty()) { return "Unknown"; }
//		return token;
//	}
//	
//	private String createCategoryToken(Category category, List<String> lc) {
//		lc.add(category.getCategoryCode());
//		Optional<Category> parent = Optional.ofNullable(category.getParent());
//		if(!parent.isPresent()) {
//			StringBuilder sb = new StringBuilder();
//			Lists.reverse(lc).stream().forEach(s -> sb.append("/").append(s));
//			return sb.toString();
//		}
//		return this.createCategoryToken(parent.get(), lc);
//	}
//	
//	public Long getChildCount() {
//		return childCount;
//	}
//	
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public CategoryAttribute getCategoryAttribute() {
		return categoryAttribute;
	}

	public void setCategoryAttribute(CategoryAttribute categoryAttribute) {
		this.categoryAttribute = categoryAttribute;
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
//	
//    public Hierarchy getHierarchy() {
//		return hierarchy;
//	}
//
//	public void setHierarchy(Hierarchy hierarchy) {
//		this.hierarchy = hierarchy;
//	}
	
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
//
//	public List<Layout> getLayouts() {
//		return layouts;
//	}
//
//	public void setLayouts(List<Layout> layouts) {
//		this.layouts = layouts;
//	}
}
