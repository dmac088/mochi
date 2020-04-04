package io.nzbee.entity.product.department.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import io.nzbee.entity.product.department.Department;

@Entity
@Table(name = "department_attr_lcl", schema = "mochi")
public class DepartmentAttribute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dept_lcl_id")
	private Long Id;

	@Column(name="dept_id")
	private Long departmentId;
	
	@Column(name="dept_desc")
	private String departmentDesc;

	@Column(name="lcl_cd")
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dept_id", insertable=false, updatable=false)
	private Department department;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}

	public String getDesc() {
		return departmentDesc;
	}

	public void setDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getLclCd());
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof DepartmentAttribute)) {
	            return false;
	    }
	    DepartmentAttribute that = (DepartmentAttribute) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.getLclCd(), that.getLclCd());
	      return eb.isEquals();
	}
	
}
