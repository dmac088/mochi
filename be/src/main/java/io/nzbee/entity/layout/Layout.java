package io.nzbee.entity.layout;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.entity.category.Category;

@Entity
@Table(name = "layout", schema = "mochi")
@PrimaryKeyJoinColumn(name = "lay_id")
public class Layout {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="lay_id")
	private Long layoutId;
	
	@Column(name="lay_cd")
	private String code;
	
	@Column(name="lay_desc")
	private String desc;

	@ManyToMany(mappedBy = "layouts")
	@JsonIgnore
	private List<Category> categories;
	
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
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
