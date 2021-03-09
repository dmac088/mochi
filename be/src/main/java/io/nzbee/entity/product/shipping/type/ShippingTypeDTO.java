package io.nzbee.entity.product.shipping.type;

import java.io.Serializable;
import java.util.Map;

public class ShippingTypeDTO implements Serializable {

	private static final long serialVersionUID = 8789098400131215884L;

	public static final String ID_ALIAS = "shp_typ_id";
	
	public static final String CODE_ALIAS = "shp_typ_cd";
	
    public static final String DESC_ALIAS = "shp_typ_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
    
	private Long shippingTypeId;
	
	private String shippingTypeCode;
	
	private String shippingTypeDesc;
	
	private String locale;	
	
	public ShippingTypeDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.shippingTypeId 				= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.shippingTypeCode 				= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.shippingTypeDesc 				= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale							= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
	}
	
	public ShippingTypeDTO(Long 		shippingTypeId,
						   String 		shippingTypeCode, 
						   String 		shippingTypeDesc,
						   String 		locale) {
		this.shippingTypeId 			= shippingTypeId;
		this.shippingTypeCode 			= shippingTypeCode;
		this.shippingTypeDesc 			= shippingTypeDesc;
		this.locale 					= locale; 
	}

	public String getShippingTypeCode() {
		return shippingTypeCode;
	}

	public String getShippingTypeDesc() {
		return shippingTypeDesc;
	}

	public String getLocale() {
		return locale;
	}

	public Long getShippingTypeId() {
		return shippingTypeId;
	}
	
	
}
