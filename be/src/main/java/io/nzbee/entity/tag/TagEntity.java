package io.nzbee.entity.tag;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import io.nzbee.Constants;
import io.nzbee.entity.tag.attribute.TagAttributeEntity;

@Entity
@Table(name = "tag", schema = "mochi")
@SqlResultSetMapping(
	    name = "TagMapping",
	    columns = {
	    		@ColumnResult(name = "object_count")
	    },
	    entities = {
	            @EntityResult(
	                    entityClass = TagEntity.class,
	                    fields = {
	                        @FieldResult(name = "tagId", 			column = "tag_id"),
	                        @FieldResult(name = "tagCode", 			column = "tag_cd")
	                    }),
	            @EntityResult(
	                    entityClass = TagAttributeEntity.class,
	                    fields = {
	                        @FieldResult(name = "tagAttributeId", 	column = "tag_lcl_id"),
	                        @FieldResult(name = "tagDesc", 			column = "tag_desc"),
	                        @FieldResult(name = "lclCd", 			column = "lcl_cd"),
	                        @FieldResult(name = "tag", 				column = "tag_id")
	                    })
		    })
public class TagEntity implements Serializable {
	
	private static final long serialVersionUID = 5869651036409976394L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_id")
	private Long tagId;
	
	@NaturalId
	@Column(name="tag_cd", unique = true, updatable = false)
	private String tagCode;

	@OneToMany(mappedBy="tag",
			   cascade = CascadeType.ALL)
	private Set<TagAttributeEntity> attributes = new HashSet<TagAttributeEntity>();
	
	@Transient
	private String locale;
	
	@Transient
	private String currency;
	
	@Transient 
	private TagAttributeEntity tagAttribute;
	
	@Transient
	private Long objectCount;

	public Long getTagId() {
		return tagId;
	}
	
	@Field(analyze = Analyze.NO, store=Store.YES)
	@Facet
	public String getTagToken() {
		return this.getTagCode();
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
	
	public TagAttributeEntity getTagAttribute() {
		return tagAttribute;
	}

	public void setTagAttribute(TagAttributeEntity attribute) {
		this.tagAttribute = attribute;
	}

	public Set<TagAttributeEntity> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(Set<TagAttributeEntity> attributes) {
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
	
	public Long getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(Long objectCount) {
		this.objectCount = objectCount;
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeENGB))
	public String getTagDescENGB() {
		Optional<TagAttributeEntity> ota = this.getAttributes().stream().filter(pa -> pa.getLclCd().equals(Constants.localeENGB)).findFirst();
		return (ota.isPresent()) 
				? ota.get().getTagDesc()
				: "Empty"; 
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeZHHK))
	public String getTagDescZHHK() {
		Optional<TagAttributeEntity> ota = this.getAttributes().stream().filter(pa -> pa.getLclCd().equals(Constants.localeZHHK)).findFirst();
		return (ota.isPresent()) 
				? ota.get().getTagDesc()
				: "Empty"; 
	}
	
	public void addTagAttribute(TagAttributeEntity tagAttribute) {
		this.getAttributes().add(tagAttribute);
		tagAttribute.setTag(this);
	}
	
	public void removeTagAttribute(TagAttributeEntity tagAttribute) {
		this.getAttributes().remove(tagAttribute);
		tagAttribute.setTag(null);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagEntity)) return false;
        return tagCode != null && tagCode.equals(((TagEntity) o).getTagCode());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getTagCode());
    }
}
