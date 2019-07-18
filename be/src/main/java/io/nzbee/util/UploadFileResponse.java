package io.nzbee.util;

public class UploadFileResponse {
    private String fileName;
    private String fileUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileUri, String fileType, long size) {
        this.fileName = fileName;
        this.setFileUri(fileUri);
        this.fileType = fileType;
        this.size = size;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	
}
