package io.nzbee.entity.layout;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "layout", schema = "mochi")
public class Layout {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="lay_id")
	private Long layoutId;
	
	@NaturalId
	@Column(name="lay_cd", unique = true, updatable = false)
	private String code;
	
	@Column(name="lay_desc")
	private String desc;
	
	@ManyToMany(mappedBy = "layouts", 
				fetch = FetchType.LAZY, 
				cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Category> categories;
	
	public String getCode() {
		return code;
	}

	public void setLayoutCode(String layoutCode) {
		this.code = layoutCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String layoutDesc) {
		this.desc = layoutDesc;
	}
	
}
