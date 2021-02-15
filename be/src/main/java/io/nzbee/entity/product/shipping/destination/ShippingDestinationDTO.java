package io.nzbee.entity.product.shipping.destination;

import java.util.Map;

public class ShippingDestinationDTO {

	public static final String ID_ALIAS = "shp_dst_id";
	
	public static final String CODE_ALIAS = "shp_dst_cd";
    
    public static final String DESC_ALIAS = "shp_dst_desc";
    
    public static final String ZONE_ALIAS = "shp_zne_cd";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
    
	private Long shippingDestinationId;
	
	private String shippingDestinationCode;
	
	private String shippingDestinationZoneCode;
	
	private String shippingDestinationDesc;
	
	private String locale;
    
	public ShippingDestinationDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.shippingDestinationId 			= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.shippingDestinationCode 		= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.shippingDestinationZoneCode 	= tuple[aliasToIndexMap.get(ZONE_ALIAS)].toString();
		this.shippingDestinationDesc 		= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
	}

	public Long getShippingDestinationId() {
		return shippingDestinationId;
	}

	public String getShippingDestinationCode() {
		return shippingDestinationCode;
	}

	public String getShippingDestinationZoneCode() {
		return shippingDestinationZoneCode;
	}

	public String getShippingDestinationDesc() {
		return shippingDestinationDesc;
	}

	public String getLocale() {
		return locale;
	}
	
	
}
