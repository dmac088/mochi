package io.nzbee.entity.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.NamedNativeQuery;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.MINIMAL_CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
@NamedNativeQuery(
	    name = "getAllCategories",
	    query =
	    		"WITH RECURSIVE  " +
	    				"descendants AS " +
	    				"( " +
	    				"  SELECT 	t.cat_id,  " +
	    				"			t.hir_id, " +
	    				"			t.cat_cd, " +
	    				"			t.cat_lvl, " +
	    				"			t.cat_prnt_id,  " +
	    				"			t.cat_typ_id, " +
	    				"			cast('/' || cast(t.cat_id as text) || '/' as text) node " +
	    				"  FROM mochi.category AS t " +
	    				"  WHERE cat_prnt_id iS NULL " +
	    				"  UNION ALL " +
	    				"  SELECT 	t.cat_id,  " +
	    				"			t.hir_id, " +
	    				"			t.cat_cd,  " +
	    				"			t.cat_lvl, " +
	    				"			t.cat_prnt_id,  " +
	    				"			t.cat_typ_id, " +
	    				"			cast(d.node || CAST(t.cat_id as text) || '/' as text) node " +
	    				"  FROM mochi.category AS t  " +
	    				"  JOIN descendants AS d  " +
	    				"  ON t.cat_prnt_id = d.cat_id " +
	    				"), " +
	    				"categories AS  " +
	    				"( " +
	    				"  SELECT 	descendants.cat_id des_cat_id, " +
	    				"			descendants.hir_id des_hir_id, " +
	    				"			descendants.cat_cd des_cat_cd, " +
	    				"			descendants.cat_lvl des_cat_lvl, " +
	    				"			descendants.cat_prnt_id des_cat_prnt_id, " +
	    				"			descendants.cat_typ_id des_cat_type_id, " +
	    				"			descendants.node " +
	    				"FROM descendants " +
	    				"), summaries_pta " +
	    				"AS " +
	    				"( " +
	    				"select " +
	    				"    cc.des_cat_id 					AS cat_id, " +
	    				"    cc.des_hir_id 					AS hir_id, " +
	    				"    cc.des_cat_cd 					AS cat_cd, " +
	    				"    cc.des_cat_lvl 				AS cat_lvl, " +
	    				"    cc.des_cat_prnt_id 			AS prnt_id, " +
	    				"    cc.des_cat_type_id 			AS cat_type_id, " +
	    				"    cc.node, " +
	    				"    COUNT(DISTINCT prd.upc_cd) AS product_count, " +
	    				"    MAX(markdown_price.prc_val) AS max_markdown_price,  " +
	    				"    MAX(retail_price.prc_val) AS max_retail_price " +
	    				"FROM categories cc " +
	    				"LEFT JOIN mochi.product_category pc ON cc.des_cat_id = pc.cat_id " +
	    				"LEFT JOIN 	( " +
	    				"		 SELECT prd.prd_id,  " +
	    				"			upc_cd " +
	    				"		 FROM mochi.product prd " +
	    				"		  " +
	    				"		 INNER JOIN mochi.product_status ps " +
	    				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
	    				"		  " +
	    				"		 WHERE prd_sts_cd = :activeProductCode " +
	    				"		 ) prd  " +
	    				"		 ON pc.prd_id = prd.prd_id " +
	    				"		  " +
	    				"LEFT JOIN 	( " +
	    				"		 SELECT prd.prd_id,  " +
	    				"			prc_typ_cd, " +
	    				"			prc_val  " +
	    				"		 FROM mochi.product prd " +
	    				
	    				"		 INNER JOIN mochi.price prc  " +
	    				"		 ON prd.prd_id = prc.prd_id " +
	    				"		  " +
	    				"		 INNER JOIN mochi.currency curr  " +
	    				"		 ON prc.ccy_id = curr.ccy_id  " +
	    				"		  " +
	    				"		 INNER JOIN mochi.price_type pt  " +
	    				"		 ON prc.prc_typ_id = pt.prc_typ_id " +

	    				"		 INNER JOIN mochi.product_status ps " +
	    				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
	    				"		  " +
	    				"		 WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt " +
	    				"		 AND curr.ccy_cd = :currency " +
	    				"		 AND prc_typ_cd = :retailPriceCode " +
	    				"		 AND prd_sts_cd = :activeProductCode " +
	    				"		 ) retail_price " +
	    				"		 ON pc.prd_id = retail_price.prd_id " +
	    				"		  " +
	    				"LEFT JOIN 	(SELECT prd.prd_id,  " +
	    				"			prc_typ_cd, " +
	    				"			prc_val  " +
	    				"		 FROM mochi.product prd " +

	    				"		 INNER JOIN mochi.price prc  " +
	    				"		 ON prd.prd_id = prc.prd_id " +
	    				"		  " +
	    				"		 INNER JOIN mochi.currency curr  " +
	    				"		 ON prc.ccy_id = curr.ccy_id  " +
	    				"		  " +
	    				"		 INNER JOIN mochi.price_type pt  " +
	    				"		 ON prc.prc_typ_id = pt.prc_typ_id " +

	    				"		 INNER JOIN mochi.product_status ps " +
	    				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
	    				"		  " +
	    				"		 WHERE now() >= prc.prc_st_dt  " +
	    				"		 AND now() <= prc.prc_en_dt " +
	    				"		 AND curr.ccy_cd = :currency " +
	    				"		 AND prc_typ_cd = :markdownPriceCode " +
	    				"		 AND prd_sts_cd = :activeProductCode " +
	    				"		 )  markdown_price		  " +
	    				"		 ON pc.prd_id = markdown_price.prd_id " +
	    				"	 " +
	    				"WHERE cc.des_cat_type_id = 1 " +
	    				"GROUP BY  " +
	    				"	 cc.des_cat_id, " +
	    				"    cc.des_hir_id, " +
	    				"	 cc.des_cat_cd, " +
	    				"	 cc.des_cat_lvl, " +
	    				"	 cc.des_cat_prnt_id, " +
	    				"	 cc.des_cat_type_id, " +
	    				"	 cc.node " +
	    				"UNION ALL " +
	    				"SELECT  " +
	    				"    cc.des_cat_id 		AS cat_id, " +
	    				"    cc.des_hir_id 					AS hir_id, " +
	    				"    cc.des_cat_cd 		AS cat_cd, " +
	    				"    cc.des_cat_lvl 		AS cat_lvl, " +
	    				"    cc.des_cat_prnt_id 	AS prnt_id, " +
	    				"    cc.des_cat_type_id 	AS cat_type_id, " +
	    				"    cc.node, " +
	    				"    count(DISTINCT prd.bnd_cd) 	AS product_count, " +
	    				"    max(markdown_price.prc_val) AS max_markdown_price,  " +
	    				"    max(retail_price.prc_val) 	AS max_retail_price " +
	    				"FROM categories cc " +
	    				"LEFT JOIN mochi.brand_category pc  " +
	    				"ON cc.des_cat_id = pc.cat_id " +

	    				"LEFT JOIN 	( " +
	    				"			SELECT  prd.bnd_id,  " +
	    				"					bnd_cd " +
	    				"			FROM mochi.brand bnd " +

	    				"			INNER JOIN mochi.product prd " +
	    				"			ON bnd.bnd_id =  prd.bnd_id " +
	    		
	    				"			INNER JOIN mochi.product_status ps " +
	    				"			ON prd.prd_sts_id = ps.prd_sts_id " +
	    		
	    				"			WHERE prd_sts_cd = :activeProductCode " +
	    				"			) prd  " +
	    				"			ON pc.bnd_id = prd.bnd_id " +
	    			
	    				"LEFT JOIN 	( " +
	    				"			SELECT 	prd.bnd_id, " +
	    				"					prc_typ_cd, " +
	    				"					prc_val  " +
	    				"			FROM mochi.brand bnd " +

	    				"			INNER JOIN mochi.product prd " +
	    				"			ON bnd.bnd_id =  prd.bnd_id " +

	    				"			INNER JOIN mochi.price prc  " +
	    				"			ON prd.prd_id = prc.prd_id " +
	    		
	    				"			INNER JOIN mochi.currency curr  " +
	    				"			ON prc.ccy_id = curr.ccy_id  " +
	    		
	    				"			INNER JOIN mochi.price_type pt  " +
	    				"			ON prc.prc_typ_id = pt.prc_typ_id " +

	    				"			INNER JOIN mochi.product_status ps " +
	    				"			ON prd.prd_sts_id = ps.prd_sts_id " +
	    	
	    				"			WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt " +
	    				"			AND curr.ccy_cd = :currency " +
	    				"			AND prc_typ_cd = :retailPriceCode " +
	    				"			AND prd_sts_cd = :activeProductCode " +
	    				"			) retail_price " +
	    				"			ON pc.bnd_id = retail_price.bnd_id " +

	    				"LEFT JOIN 	( " +
	    				"			SELECT prd.bnd_id,  " +
	    				"					prc_typ_cd, " +
	    				"					prc_val  " +
	    				"			FROM mochi.brand bnd " +

	    				"			INNER JOIN mochi.product prd " +
	    				"			ON bnd.bnd_id =  prd.bnd_id " +
	    	
	    				"			INNER JOIN mochi.price prc  " +
	    				"			ON prd.prd_id = prc.prd_id " +

	    				"			INNER JOIN mochi.currency curr  " +
	    				"			ON prc.ccy_id = curr.ccy_id  " +
	    	
	    				"			INNER JOIN mochi.price_type pt  " +
	    				"			ON prc.prc_typ_id = pt.prc_typ_id " +

	    				"			INNER JOIN mochi.product_status ps " +
	    				"			ON prd.prd_sts_id = ps.prd_sts_id " +

	    				"			WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt " +
	    				"			AND curr.ccy_cd = :currency " +
	    				"			AND prc_typ_cd = :markdownPriceCode " +
	    				"			AND prd_sts_cd = :activeProductCode " +
	    				"		 )  markdown_price		  " +
	    				"		 ON pc.bnd_id = markdown_price.bnd_id " +

	    				"WHERE cc.des_cat_type_id = 2 " +
	    				"GROUP BY  " +
	    				"	cc.des_cat_id, " +
	    				"   cc.des_hir_id, " +
	    				"	cc.des_cat_cd, " +
	    				"	cc.des_cat_lvl, " +
	    				"	cc.des_cat_prnt_id, " +
	    				"	cc.des_cat_type_id, " +
	    				"	cc.node " +
	    				"), summaries_ptb AS " +
	    				"( " +
	    				"SELECT 	 " +
	    				"	s1.cat_id, " +
	    				"	s1.hir_id, " +
	    				"	s1.cat_cd, " +
	    				"	s1.cat_lvl, " +
	    				"	s1.prnt_id, " +
	    				"	s1.cat_type_id, " +
	    				"	coalesce(s1.product_count, 0) + " +
	    				"	sum(coalesce(s2.product_count,0)) as object_count, " +
	    				"	greatest(0, s1.max_retail_price, max(s2.max_retail_price)) as max_retail_price, " +
	    				"	greatest(0, s1.max_markdown_price, max(s2.max_markdown_price)) as max_markdown_price " +
	    				"FROM summaries_pta s1 " +
	    				"LEFT JOIN summaries_pta s2 " +
	    				"ON s1.node <> s2.Node and left(s2.node, length(s1.node)) = s1.node " +
	    				"GROUP BY " +
	    				"	s1.cat_id, " +
	    				"	s1.hir_id, " +
	    				"	s1.cat_cd, " +
	    				"	s1.cat_lvl, " +
	    				"	s1.prnt_id, " +
	    				"	s1.cat_type_id, " +
	    				"	s1.product_count, " +
	    				"	s1.max_retail_price, " +
	    				"	s1.max_markdown_price " +
	    				") " +
	    				"SELECT s.cat_id, " +
	    				"       s.hir_id, " +
	    				"       h.hir_cd, " +
	    				"       h.hir_desc, " +
	    				"       s.cat_cd, " +
	    				"       s.cat_lvl, " +
	    				"       s.prnt_id as cat_prnt_id, " +
	    				"       parent.cat_cd as prnt_cd, " +
	    				"       parent.cat_lvl as prnt_lvl, " +
	    				"		a.cat_lcl_id as categoryAttribute_cat_lcl_id, "	+	
	    				"       a.cat_desc, " +
	    				"       a.cat_img_pth, " +
	    				"       ct.cat_typ_id, " +
	    				"       ct.cat_typ_cd, " +
	    				"       a.lcl_cd, " +
	    				"       s.object_count, " +
	    				"       s.max_retail_price, " +
	    				"       s.max_markdown_price, " +
	    				"		1 as clazz_ " +		

	    				"FROM summaries_ptb s " +

	    				"INNER JOIN mochi.category_attr_lcl a " +
	    				"ON s.cat_id = a.cat_id " +

	    				"LEFT JOIN mochi.category parent " +
	    				"ON s.prnt_id = parent.cat_id  " +

	    				"INNER JOIN mochi.category_type ct " +
	    				"ON s.cat_type_id = ct.cat_typ_id " +
	    				
						"INNER JOIN mochi.hierarchy h " +
						"ON s.hir_id = h.hir_id " +
	    				
	    				"WHERE a.lcl_cd = :locale " +
	    				"AND case " +
	    				"	 when :parentCategoryCode = '-1' "
	    				+ "  then '0' "
	    				+ "  else parent.cat_cd"
	    				+ "	 end = "
	    				+ "	 case"
	    				+ "  when :parentCategoryCode = '-1' "
	    				+ "  then '0' "
	    				+ "  else :parentCategoryCode"
	    				+ "  end",
	        resultClass=Category.class
	)

	@SqlResultSetMapping(
	    name = "CategoryMapping",
	    entities = {
	            @EntityResult(
	                    entityClass = Category.class,
	                    fields = {
	                        @FieldResult(name = "categoryId", 			column = "id"),
	                        @FieldResult(name = "categoryCode", 		column = "cat_cd"),
	                        @FieldResult(name = "categoryLevel", 		column = "cat_lvl")
	                    }),
	            @EntityResult(
	                    entityClass = CategoryAttribute.class,
	                    fields = {
	                        @FieldResult(name = "categoryAttributeId", 			column = "cat_lcl_id"),
	                        @FieldResult(name = "categoryId", 					column = "cat_id"),
	                        @FieldResult(name = "categoryDesc", 				column = "cat_desc"),
	                        @FieldResult(name = "lclCd", 						column = "lcl_cd")
	                     }),
	            @EntityResult(
			            entityClass = Category.class,
		                fields = {
		                    @FieldResult(name = "categoryId", 			column = "cat_prnt_id"),
		                    @FieldResult(name = "categoryCode", 		column = "prnt_cd"),
		                    @FieldResult(name = "categoryLevel", 		column = "prnt_lvl")
		                }),
	            @EntityResult(
			            entityClass = CategoryType.class,
		                fields = {
		                    @FieldResult(name = "categoryTypeId", 			column = "cat_typ_id"),
		                    @FieldResult(name = "categoryTypeCode", 		column = "cat_typ_cd"),
		                    @FieldResult(name = "categoryTypeDesc", 		column = "cat_typ_desc")
		                }),
	            @EntityResult(
			            entityClass = Hierarchy.class,
		                fields = {
		                    @FieldResult(name = "hierarchyId", 				column = "hir_id"),
		                    @FieldResult(name = "hierarchyCode", 			column = "hir_cd"),
		                    @FieldResult(name = "hierarchyDesc", 			column = "hir_desc")
		                })
	    })
	
public abstract class Category {

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
	
	@OneToOne
	@JsonIgnore
	private CategoryAttribute categoryAttribute;
	

	@Transient
	private Long childCount;
	
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
	
	public Long getChildCount() {
		return childCount;
	}
	
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
