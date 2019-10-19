package io.nzbee.entity.product.hierarchy;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
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
	@Field(analyze = Analyze.NO, store=Store.YES)
	private String hierarchyCode;
	
	@Column(name="hir_desc")
	@Field(analyze = Analyze.NO, store=Store.YES)
	private String hierarchyDesc;
	
	@OneToMany(	mappedBy="hierarchy", 
				cascade = CascadeType.ALL,
				orphanRemoval = true)
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
	
	public void addCategory(Category category) {
		this.categories.add(category);
		category.setHierarchy(this);
	}
	
	public void removeCategory(Category category) {
		this.categories.remove(category);
		category.setHierarchy(null);
	}
}
