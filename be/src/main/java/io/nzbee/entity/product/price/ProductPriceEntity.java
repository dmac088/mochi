package io.nzbee.entity.product.price;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.currency.Currency;

@Entity
@Table(name = "price", schema = "mochi")
public class ProductPriceEntity implements Serializable {

	private static final long serialVersionUID = 8207922937454779518L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prc_id")
	private Long id;
	
	@Column(name="prc_val")
	private Double priceValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prc_typ_id", nullable=false, updatable = false, insertable = true)
	private ProductPriceType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ccy_id", nullable=false, updatable = false, insertable = true)
	private Currency currency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prd_id", nullable=false, updatable = false, insertable = true)
	private ProductEntity product;

    public Long getId() {
		return id;
	}
	
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
	
	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        return id != null && id.equals(((ProductPriceEntity) o).getId());
    }


	@Override
    public int hashCode() {
    	return 31;
    }
}
