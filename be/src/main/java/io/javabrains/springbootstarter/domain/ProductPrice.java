package io.javabrains.springbootstarter.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "price", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prc_id")
public class ProductPrice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prc_id")
	private Long id;
	
	@Column(name="prc_val")
	private Long priceValue;
	
	@Column(name="prc_st_dt")
	private Date priceStartDate;
	
	@Column(name="prc_en_dt")
	private Date priceEndDate; 

	@ManyToOne
	@JoinColumn(name="prc_typ_id", nullable=false, updatable = false, insertable = true)
	private ProductPriceType priceType;
	
	@ManyToOne
	@JoinColumn(name="ccy_id", nullable=false, updatable = false, insertable = true)
	private Currency priceCurrency;
	
	@ManyToOne
	@JoinColumn(name="prd_id", nullable=false, updatable = false, insertable = true)
	private Product product;

	public ProductPriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(ProductPriceType priceType) {
		this.priceType = priceType;
	}

	public Long getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(Long priceValue) {
		this.priceValue = priceValue;
	}
	
	public Currency getPriceCurrency() {
		return priceCurrency;
	}

	public void setPriceCurrency(Currency priceCurrency) {
		this.priceCurrency = priceCurrency;
	}
	
	public Date getPriceStartDate() {
		return priceStartDate;
	}

	public void setPriceStartDate(Date priceStartDate) {
		this.priceStartDate = priceStartDate;
	}

	public Date getPriceEndDate() {
		return priceEndDate;
	}

	public void setPriceEndDate(Date priceEndDate) {
		this.priceEndDate = priceEndDate;
	}
	
}
