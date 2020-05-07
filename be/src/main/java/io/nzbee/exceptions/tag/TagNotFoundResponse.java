package io.nzbee.exceptions.tag;

public class TagNotFoundResponse {

	private String tagNotFound;

	public TagNotFoundResponse(String tagNotFound) {
		this.setTagNotFound(tagNotFound);
	}

	public String getTagNotFound() {
		return tagNotFound;
	}

	public void setTagNotFound(String tagNotFound) {
		this.tagNotFound = tagNotFound;
	}
	
}
