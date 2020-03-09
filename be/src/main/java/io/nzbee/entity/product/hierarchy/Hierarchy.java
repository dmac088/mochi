package io.nzbee.entity.product.hierarchy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hierarchy", schema = "mochi")
public class Hierarchy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="hir_id")
	private Long hierarchyId;

	@Column(name="hir_cd")
	private String hierarchyCode;
	
	@Column(name="hir_desc")
	private String hierarchyDesc;

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
	
}
