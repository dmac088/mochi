package io.nzbee.entity.product.status;

import java.io.Serializable;
import java.util.Map;

public class ProductStatusDTO implements Serializable {

	private static final long serialVersionUID = 6141098704509628986L;

	public static final String ID_ALIAS = "prd_sts_id";
	
	public static final String CODE_ALIAS = "prd_sts_cd";
    
    public static final String DESC_ALIAS = "prd_sts_desc";
	
	private Long productStatusId;

	private String productStatusCode;
	
	private String productStatusDesc;

	public ProductStatusDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.productStatusId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.productStatusCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.productStatusDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
	}
	
	public Long getProductStatusId() {
		return productStatusId;
	}

	public String getProductStatusCode() {
		return productStatusCode;
	}

	public String getProductStatusDesc() {
		return productStatusDesc;
	}
	
}
