package io.nzbee.entity.postage.insurance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="prostage_insurance")
public class PostageInsurance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pst_ins_id")
	private Long postageInsuranceId;
	
	@Column(name="pst_ins_cd")
	private String postageInsuranceCode;

	public Long getPostageInsuranceId() {
		return postageInsuranceId;
	}

	public String getPostageInsuranceCode() {
		return postageInsuranceCode;
	}
	
	
}
