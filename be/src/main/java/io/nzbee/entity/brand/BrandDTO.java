package io.nzbee.entity.brand;

import java.util.Map;

public class BrandDTO {

	public static final String ID_ALIAS = "bnd_id";
	
	public static final String CODE_ALIAS = "bnd_cd";
    
    public static final String DESC_ALIAS = "bnd_desc";
	
	private Long brandId;
	
	private String brandCode;
	
	private String brandDesc;

	public BrandDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.brandId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.brandCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.brandDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
	}
	
	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	
}
