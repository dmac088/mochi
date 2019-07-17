package io.nzbee.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
    
    private String downloadDir;
    
    private String productMasterFile;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

	public String getDownloadDir() {
		return downloadDir;
	}

	public void setDownloadDir(String downloadDir) {
		this.downloadDir = downloadDir;
	}

	public String getProductMasterFile() {
		return productMasterFile;
	}

	public void setProductMasterFile(String productMasterFile) {
		this.productMasterFile = productMasterFile;
	}
}