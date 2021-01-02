package io.nzbee.entity.party.address.type;

import java.util.Map;

public class AddressTypeDTO {

	public static final String ID_ALIAS = "addr_typ_id";
	
	public static final String ADDRESS_TYPE_CODE_ALIAS = "addr_typ_cd";
	
	public static final String ADDRESS_TYPE_DESC_ALIAS = "addr_typ_desc";
		
	private Long addressTypeId;
	
	private String addressTypeCode;
	
	private String addressTypeDesc;
	
	public AddressTypeDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.addressTypeId 		= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.addressTypeCode	= tuple[aliasToIndexMap.get(ADDRESS_TYPE_CODE_ALIAS)].toString();
		this.addressTypeDesc	= tuple[aliasToIndexMap.get(ADDRESS_TYPE_DESC_ALIAS)].toString();
	}
	
	public AddressTypeDTO(Long addressTypeId, String addressTypeCode, String addressTypeDesc) {
		super();
		this.addressTypeId = addressTypeId;
		this.addressTypeCode = addressTypeCode;
		this.addressTypeDesc = addressTypeDesc;
	}

	public Long getAddressTypeId() {
		return addressTypeId;
	}

	public String getAddressTypeCode() {
		return addressTypeCode;
	}

	public String getAddressTypeDesc() {
		return addressTypeDesc;
	}
	
}
