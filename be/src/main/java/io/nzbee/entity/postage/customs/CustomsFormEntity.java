package io.nzbee.entity.postage.customs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="prostage_customs_form")
public class CustomsFormEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pst_cst_id")
	private Long customsFormId;
	
	@Column(name="pst_cst_cd")
	private String customsFormCode;

	public Long getCustomsFormId() {
		return customsFormId;
	}

	public String getCustomsFormCode() {
		return customsFormCode;
	}
	
}
