package io.nzbee.entity.product.price;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.currency.Currency;

@Entity
@Table(name = "price", schema = "mochi")
public class ProductPrice {

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
	private Product product;

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
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return id != null && id.equals(((ProductPrice) o).getId());
    }


	@Override
    public int hashCode() {
    	return 31;
    }
}
