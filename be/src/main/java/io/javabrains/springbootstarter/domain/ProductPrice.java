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
	private Date startDate;
	
	@Column(name="prc_en_dt")
	private Date endDate; 

	@ManyToOne
	@JoinColumn(name="prc_typ_id", nullable=false, updatable = false, insertable = true)
	private ProductPriceType type;
	
	@ManyToOne
	@JoinColumn(name="ccy_id", nullable=false, updatable = false, insertable = true)
	private Currency currency;
	
	@ManyToOne
	@JoinColumn(name="prd_id", nullable=false, updatable = false, insertable = true)
	private Product product;

	public ProductPriceType getType() {
		return this.type;
	}

	public void setType(ProductPriceType priceType) {
		this.type = priceType;
	}

	public Long getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(Long priceValue) {
		this.priceValue = priceValue;
	}
	
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency priceCurrency) {
		this.currency = priceCurrency;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date priceStartDate) {
		this.startDate = priceStartDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date priceEndDate) {
		this.endDate = priceEndDate;
	}
	
}
