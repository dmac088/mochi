package io.javabrains.springbootstarter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "brand_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "bnd_lcl_id")
public class BrandAttribute {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_lcl_id")
	private Long Id;
	
	@Column(name="bnd_id")
	private Long brandId;

	@Column(name="bnd_desc")
	@Field(analyze = Analyze.YES)
	private String brandDesc;
	
	@Column(name="lcl_cd")	
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bnd_id", insertable=false, updatable=false)
	@JsonBackReference
	private Brand brand;
	
	public Long getbrandId() {
		return brandId;
	}
	
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getBrandDesc() {
		return brandDesc;
	}
	
	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
}
