package io.nzbee.entity.product.physical;

import java.io.Serializable;
import java.util.Map;

import io.nzbee.entity.product.ProductDTO;

public class PhysicalProductDTO extends ProductDTO implements Serializable {

	private static final long serialVersionUID = -408191039793736868L;

	public PhysicalProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		// TODO Auto-generated constructor stub
	}

}
