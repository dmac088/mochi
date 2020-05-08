package io.nzbee.entity.layout;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;
import io.nzbee.entity.category.layout.CategoryLayout;

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
	
	@OneToMany(mappedBy = "layout")
    private Set<CategoryLayout> categoryLayouts = new HashSet<>();
	
	public Set<CategoryLayout> getCategoryLayouts() {
		return categoryLayouts;
	}

	public void setCategoryLayouts(Set<CategoryLayout> categoryLayouts) {
		this.categoryLayouts = categoryLayouts;
	}

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
