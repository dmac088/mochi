package io.javabrains.springbootstarter.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "hierarchy", schema = "mochi")
@PrimaryKeyJoinColumn(name = "hir_id")
public class Hierarchy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="hir_id")
	private Long hierarchyId;

	@Column(name="hir_cd")
	private String Code;
	
	@Column(name="hir_desc")
	private String Desc;
	
	@OneToMany(mappedBy="hierarchy",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Category> categories;

	public String getCode() {
		return Code;
	}

	public void setCode(String hierarchyCode) {
		this.Code = hierarchyCode;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String hierarchyDesc) {
		this.Desc = hierarchyDesc;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
