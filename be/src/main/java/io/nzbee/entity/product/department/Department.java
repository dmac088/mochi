package io.nzbee.entity.product.department;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute;

@Entity
@Table(name = "department", schema = "mochi")
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dept_id")
	private Long departmentId;

	@Column(name="dept_cd")
	private String departmentCode;
	
	@Column(name="dept_class")
	private String departmentClass;
	
	@OneToMany(	mappedBy="department",  
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JsonManagedReference
	private List<DepartmentAttribute> attributes = new ArrayList<DepartmentAttribute>();

	public Long getId() {
		return departmentId;
	}

	public void setId(Long id) {
		this.departmentId = id;
	}
	
	public String getCode() {
		return departmentCode;
	}

	public void setCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentClass() {
		return departmentClass;
	}

	public void setDepartmentClass(String departmentClass) {
		this.departmentClass = departmentClass;
	}
	
	public List<DepartmentAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<DepartmentAttribute> attributes) {
		this.attributes = attributes;
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "en-GB"))
	public String getDepartmentDescENGB() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals("en-GB")).findFirst().get().getDesc();
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "zh-HK"))
	public String getDepartmentDescZHHK() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals("zh-HK")).findFirst().get().getDesc();
	}
	
	@Transient
	@Facet
	public String getDepartmentToken() {
		return this.getCode();
	}
	
}
