package io.nzbee.entity.product.department;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.nzbee.Constants;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute;

@Entity
@Table(name = "department", schema = "mochi")
public class DepartmentEntity implements Serializable {

	private static final long serialVersionUID = 6646482082898774605L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dept_id")
	private Long departmentId;

	@NaturalId
	@Column(name="dept_cd", unique = true, updatable = false)
	private String departmentCode;
	
	@Column(name="dept_class")
	private String departmentClass;
	
	@OneToMany(	mappedBy="department",  
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JsonManagedReference
	private List<DepartmentAttribute> attributes = new ArrayList<DepartmentAttribute>();

	@Transient
	private DepartmentAttribute attribute;
	
	@Transient
	private String locale;
	
	@Transient
	private String currency;

	public Long getId() {
		return departmentId;
	}

	public void setId(Long id) {
		this.departmentId = id;
	}
	
	public void setAttribute(DepartmentAttribute attribute) {
		this.attribute = attribute;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeENGB))
	public String getDepartmentDescENGB() {
		Optional<DepartmentAttribute> d = this.getAttributes().stream()
				.filter(a -> a.getLclCd().equals(Constants.localeENGB)).findFirst();
		return ((d.isPresent()) ? d.get().getDesc() : "novalue");
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeZHHK))
	public String getDepartmentDescZHHK() {
		Optional<DepartmentAttribute> d = this.getAttributes().stream()
				.filter(a -> a.getLclCd().equals(Constants.localeZHHK)).findFirst();
		return ((d.isPresent()) ? d.get().getDesc() : "novalue");
		
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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
	
	public DepartmentAttribute getAttribute() {
		return attribute;
	}
	
	@Transient
	@Facet
	public String getDepartmentToken() {
		return this.getDepartmentCode();
	}
	
}
