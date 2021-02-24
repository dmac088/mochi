package io.nzbee;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.globals")
public class Globals {

	private String baseURL;
	private String localeENGB;
	private String localeZHHK;
	private String currencyHKD;
	private String currencyUSD;
	private String retailPriceCode;
	private String markdownPriceCode;
	private String unknownProductDesc;
	private String unknownProductImage;
	private String activeSKUCode;
	private String primaryProductRootCategoryCode;
	private int	   defaultPage;
	private int	   defaultPageSize;
	
	public String getBaseURL() {
		return baseURL;
	}
	
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}	
	
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
		return primaryProductRootCategoryCode;
	}
	public void setPrimaryRootCategoryCode(String primaryProductRootCategoryCode) {
		this.primaryProductRootCategoryCode = primaryProductRootCategoryCode;
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
