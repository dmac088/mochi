package io.nzbee.entity.product.physical;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;
import io.nzbee.entity.product.ProductEntity;

@Indexed
@Entity
@Table(name = "product_basic", schema = "mochi")
@DiscriminatorValue("2")
public class PhysicalProductEntity extends ProductEntity {

	private static final long serialVersionUID = -2844690299028235684L;

	@Column(name="width")
	private Integer widthDimension;
	
	@Column(name="length")
	private Integer lengthDimension;
	
	@Column(name="height")
	private Integer heightDimension;
	
	@Column(name="weight")
	private Double weightDimension;


	public Integer getWidthDimension() {
		return widthDimension;
	}

	public void setWidthDimension(Integer widthDimension) {
		this.widthDimension = widthDimension;
	}

	public Integer getLengthDimension() {
		return lengthDimension;
	}

	public void setLengthDimension(Integer lengthDimension) {
		this.lengthDimension = lengthDimension;
	}

	public Integer getHeightDimension() {
		return heightDimension;
	}

	public void setHeightDimension(Integer heightDimension) {
		this.heightDimension = heightDimension;
	}

	public Double getWeightDimension() {
		return weightDimension;
	}

	public void setWeightDimension(Double weightDimension) {
		this.weightDimension = weightDimension;
	}
	
}
