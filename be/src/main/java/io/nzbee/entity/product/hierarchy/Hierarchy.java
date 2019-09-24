package io.nzbee.entity.product.hierarchy;

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
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "hierarchy", schema = "mochi")
@PrimaryKeyJoinColumn(name = "hir_id")
public class Hierarchy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="hir_id")
	private Long hierarchyId;

	@Column(name="hir_cd")
	private String hierarchyCode;
	
	@Column(name="hir_desc")
	private String hierarchyDesc;
	
	@OneToMany(mappedBy="hierarchy",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Category> categories;

	public Long getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(Long hierarchyId) {
		this.hierarchyId = hierarchyId;
	}
	
	public String getHierarchyCode() {
		return hierarchyCode;
	}

	public void setHierarchyCode(String hierarchyCode) {
		this.hierarchyCode = hierarchyCode;
	}

	public String getDesc() {
		return hierarchyDesc;
	}

	public void setDesc(String hierarchyDesc) {
		this.hierarchyDesc = hierarchyDesc;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
