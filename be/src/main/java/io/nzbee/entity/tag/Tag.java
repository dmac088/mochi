package io.nzbee.entity.tag;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.Constants;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.tag.attribute.TagAttribute;
import io.nzbee.search.ISearchDimension;

@Entity
@Table(name = "tag", schema = "mochi")
@PrimaryKeyJoinColumn(name = "tag_id")
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

	@ManyToMany(mappedBy = "tags")
	@JsonIgnore
    private Set<ProductEntity> products = new HashSet<ProductEntity>();

	@OneToMany(mappedBy="tag",
			   cascade = CascadeType.ALL)
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
	
	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}
	
	public TagAttribute getTagAttribute() {
		return tagAttribute;
	}

	public void setTagAttribute(TagAttribute attribute) {
		this.tagAttribute = attribute;
	}

	public Set<ProductEntity> getProducts() {
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
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeENGB))
	public String getTagDescENGB() {
		Optional<TagAttribute> ota = this.getAttributes().stream().filter(pa -> pa.getLclCd().equals(Constants.localeENGB)).findFirst();
		return (ota.isPresent()) 
				? ota.get().getTagDesc()
				: "Empty"; 
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeZHHK))
	public String getTagDescZHHK() {
		Optional<TagAttribute> ota = this.getAttributes().stream().filter(pa -> pa.getLclCd().equals(Constants.localeZHHK)).findFirst();
		return (ota.isPresent()) 
				? ota.get().getTagDesc()
				: "Empty"; 
	}
	
	public void addProduct(ProductEntity product) {
		this.getProducts().add(product);
		product.addTag(this);
	}
	
	public void removeProduct(ProductEntity product) {
		this.getProducts().remove(product);
		product.removeTag(this);
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
		return this.objectCount;
	}

	@Override
	public boolean isHierarchical() {
		return false;
	}
	
	@Override
	public String getCode() {
		return this.tagCode;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        return tagCode != null && tagCode.equals(((Tag) o).getTagCode());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getTagCode());
    }
}
