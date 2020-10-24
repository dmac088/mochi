package io.nzbee.entity.promotion;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "promotion", schema = "mochi")
public class Promotion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prm_id")
	private Long promotionId;
	
	@NaturalId
	@Column(name="prm_cd", unique = true, updatable = false)
	private String promotionCode;
	
	@Column(name="prm_sht_desc")
	private String promotionShortDesc;
	
	@Column(name="prm_lng_desc")
	private String promotionLongDesc;
	
	@Column(name="prm_st_dt")
	private LocalDateTime promotionStartDate;
	
	@Column(name="prm_en_dt")
	private LocalDateTime promotionEndDate;
	
	
	
}
