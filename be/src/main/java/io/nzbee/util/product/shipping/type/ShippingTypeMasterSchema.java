package io.nzbee.util.product.shipping.type;

public class ShippingTypeMasterSchema {

	private String TYPE_CODE;
	
	private String TYPE_DESC_EN;
	
	private String TYPE_DESC_HK;
	
	private String TYPE_DESC_CN;

	public String get_TYPE_CODE() {
		return TYPE_CODE;
	}

	public void set_TYPE_CODE(String sHIPPING_TYPE_CODE) {
		TYPE_CODE = sHIPPING_TYPE_CODE;
	}

	public String get_TYPE_DESC_EN() {
		return TYPE_DESC_EN;
	}

	public void set_TYPE_DESC_EN(String sHIPPING_TYPE_DESC_EN) {
		TYPE_DESC_EN = sHIPPING_TYPE_DESC_EN;
	}

	public String get_TYPE_DESC_HK() {
		return TYPE_DESC_HK;
	}

	public void set_TYPE_DESC_HK(String sHIPPING_TYPE_DESC_HK) {
		TYPE_DESC_HK = sHIPPING_TYPE_DESC_HK;
	}

	public String get_TYPE_DESC_CN() {
		return TYPE_DESC_CN;
	}

	public void set_TYPE_DESC_CN(String sHIPPING_TYPE_DESC_CN) {
		TYPE_DESC_CN = sHIPPING_TYPE_DESC_CN;
	}
	
}
