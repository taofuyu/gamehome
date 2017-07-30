package com.dr.galaxy.gamehome.model;

public class ResourceCreatedMessage {
	private String id;
	private String link;

	public String getId() {
		return id;
	}

	public ResourceCreatedMessage(String id, String link) {
		super();
		this.id = id;
		this.link = link;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
