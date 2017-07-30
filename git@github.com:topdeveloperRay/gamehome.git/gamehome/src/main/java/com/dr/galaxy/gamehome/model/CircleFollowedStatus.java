package com.dr.galaxy.gamehome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CircleFollowedStatus {
	
	@JsonProperty("isFollowed")
	private boolean isFollowed;
	private String circleId;

	@JsonIgnore
	public boolean isFollowed() {
		return isFollowed;
	}

	public void setFollowed(boolean isFollowed) {
		this.isFollowed = isFollowed;
	}

	public String getCircleId() {
		return circleId;
	}

	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}
}
