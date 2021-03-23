package io.nzbee.entity.product.physical;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import io.nzbee.entity.product.ProductDTO;

public class PhysicalProductDTO extends ProductDTO implements Serializable {

	private static final long serialVersionUID = -408191039793736868L;
	
    public static final String HEIGHT_ALIAS = "height";
    
    public static final String WIDTH_ALIAS = "width";
    
    public static final String LENGTH_ALIAS = "length";
    
    public static final String WEIGHT_ALIAS = "weight";
	
	//physical dimensions
	private Integer height;
		
	private Integer width;
		
	private Integer length;
		
	private Double weight;
		

	public PhysicalProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.height	= ((BigInteger) tuple[aliasToIndexMap.get(HEIGHT_ALIAS)]).intValue();
		this.width	= ((BigInteger) tuple[aliasToIndexMap.get(WIDTH_ALIAS)]).intValue();
		this.length	= ((BigInteger) tuple[aliasToIndexMap.get(LENGTH_ALIAS)]).intValue();
		this.weight	= ((BigDecimal) tuple[aliasToIndexMap.get(WEIGHT_ALIAS)]).doubleValue();
	}


	public Integer getHeight() {
		return height;
	}


	public Integer getWidth() {
		return width;
	}


	public Integer getLength() {
		return length;
	}


	public Double getWeight() {
		return weight;
	}
	

}
