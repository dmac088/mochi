package io.nzbee;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.globals")
public class Globals {

	private String localeENGB;
	private String localeZHHK;
	private String currencyHKD;
	private String currencyUSD;
	private String retailPriceCode;
	private String markdownPriceCode;
	private String unknownProductDesc;
	private String unknownProductImage;
	private String activeSKUCode;
	private String primaryRootCategoryCode;
	private int	   defaultPage;
	private int	   defaultPageSize;
	
	public String getLocaleENGB() {
		return localeENGB;
	}
	public void setLocaleENGB(String localeENGB) {
		this.localeENGB = localeENGB;
	}
	public String getLocaleZHHK() {
		return localeZHHK;
	}
	public void setLocaleZHHK(String localeZHHK) {
		this.localeZHHK = localeZHHK;
	}
	public String getCurrencyHKD() {
		return currencyHKD;
	}
	public void setCurrencyHKD(String currencyHKD) {
		this.currencyHKD = currencyHKD;
	}
	public String getCurrencyUSD() {
		return currencyUSD;
	}
	public void setCurrencyUSD(String currencyUSD) {
		this.currencyUSD = currencyUSD;
	}
	public String getRetailPriceCode() {
		return retailPriceCode;
	}
	public void setRetailPriceCode(String retailPriceCode) {
		this.retailPriceCode = retailPriceCode;
	}
	public String getMarkdownPriceCode() {
		return markdownPriceCode;
	}
	public void setMarkdownPriceCode(String markdownPriceCode) {
		this.markdownPriceCode = markdownPriceCode;
	}
	public String getUnknownProductDesc() {
		return unknownProductDesc;
	}
	public void setUnknownProductDesc(String unknownProductDesc) {
		this.unknownProductDesc = unknownProductDesc;
	}
	public String getUnknownProductImage() {
		return unknownProductImage;
	}
	public void setUnknownProductImage(String unknownProductImage) {
		this.unknownProductImage = unknownProductImage;
	}
	public String getActiveSKUCode() {
		return activeSKUCode;
	}
	public void setActiveSKUCode(String activeSKUCode) {
		this.activeSKUCode = activeSKUCode;
	}
	public String getPrimaryRootCategoryCode() {
		return primaryRootCategoryCode;
	}
	public void setPrimaryRootCategoryCode(String primaryRootCategoryCode) {
		this.primaryRootCategoryCode = primaryRootCategoryCode;
	}
	public int getDefaultPage() {
		return defaultPage;
	}
	public void setDefaultPage(int defaultPage) {
		this.defaultPage = defaultPage;
	}
	public int getDefaultPageSize() {
		return defaultPageSize;
	}
	public void setDefaultPageSize(int defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}
	
}
