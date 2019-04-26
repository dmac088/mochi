package io.javabrains.springbootstarter.entity;

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
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "price", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prc_id")
public class ProductPrice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prc_id")
	private Long id;
	
	@Column(name="prc_val")
	@Field(store=Store.NO,index=Index.YES,analyze=Analyze.NO)
	@SortableField
	@NumericField
	private Double priceValue;

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

	public Double getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(Double priceValue) {
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
