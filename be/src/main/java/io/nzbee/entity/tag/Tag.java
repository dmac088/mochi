package io.nzbee.entity.tag;

import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.tag.attribute.TagAttribute;
import io.nzbee.search.ISearchDimension;

@Entity
@Table(name = "tag", schema = "mochi")
@SqlResultSetMapping(
	    name = "TagMapping",
	    columns = {
	    		@ColumnResult(name = "object_count")
	    },
	    entities = {
	            @EntityResult(
	                    entityClass = Tag.class,
	                    fields = {
	                        @FieldResult(name = "tagId", 			column = "tag_id"),
	                        @FieldResult(name = "tagCode", 			column = "tag_cd")
	                    }),
	            @EntityResult(
	                    entityClass = TagAttribute.class,
	                    fields = {
	                        @FieldResult(name = "tagAttributeId", 	column = "tag_lcl_id"),
	                        @FieldResult(name = "tagDesc", 			column = "tag_desc"),
	                        @FieldResult(name = "lclCd", 			column = "lcl_cd"),
	                        @FieldResult(name = "tag", 				column = "tag_id")
	                    })
		    })
public class Tag implements ISearchDimension {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_id")
	private Long tagId;
	
	@NaturalId
	@Column(name="tag_cd", unique = true, updatable = false)
	private String tagCode;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_tag", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "tag_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @OrderBy
    private Set<Product> products;

	@OneToMany(	mappedBy="tag", 
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<TagAttribute> attributes = new HashSet<TagAttribute>();
	
	@Transient
	private String locale;
	
	@Transient
	private String currency;
	
	@Transient 
	private TagAttribute tagAttribute;
	
	@Transient
	private int objectCount;

	public Long getTagId() {
		return tagId;
	}
	
	@Field(analyze = Analyze.NO, store=Store.YES)
	@Facet
	public String getTagToken() {
		return getCode();
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	
	public String getCode() {
		return tagCode;
	}

	public void setCode(String tagCode) {
		this.tagCode = tagCode;
	}
	
	public TagAttribute getTagAttribute() {
		return tagAttribute;
	}

	public void setTagAttribute(TagAttribute attribute) {
		this.tagAttribute = attribute;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public Set<TagAttribute> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(Set<TagAttribute> attributes) {
		this.attributes = attributes;
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getLocale() {
		return locale;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public int getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(int objectCount) {
		this.objectCount = objectCount;
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "en-GB"))
	public String getTagDescENGB() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals("en-GB")).findFirst().get().getTagDesc();
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "zh-HK"))
	public String getTagDescZHHK() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals("zh-HK")).findFirst().get().getTagDesc();
	}
	
	public void addTagAttribute(TagAttribute tagAttribute) {
		this.getAttributes().add(tagAttribute);
		tagAttribute.setTag(this);
	}
	
	public void removeTagAttribute(TagAttribute tagAttribute) {
		this.getAttributes().remove(tagAttribute);
		tagAttribute.setTag(null);
	}

	@Override
	public String getDesc() {
		return this.getTagAttribute().getTagDesc();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return false;
	}

}
