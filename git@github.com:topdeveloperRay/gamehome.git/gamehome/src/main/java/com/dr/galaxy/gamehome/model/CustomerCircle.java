package com.dr.galaxy.gamehome.model;

public class CustomerCircle {
	
	private String circleId;// （int，自增长，PK）

	private String circleName;// (String)

	private String circleType;

	private String circleDescription;

	private String circlePicture;
	
	private boolean isFollowed;

	public String getCircleId() {
		return circleId;
	}

	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getCircleType() {
		return circleType;
	}

	public void setCircleType(String circleType) {
		this.circleType = circleType;
	}

	public String getCircleDescription() {
		return circleDescription;
	}

	public void setCircleDescription(String circleDescription) {
		this.circleDescription = circleDescription;
	}

	public String getCirclePicture() {
		return circlePicture;
	}

	public void setCirclePicture(String circlePicture) {
		this.circlePicture = circlePicture;
	}

	public boolean isFollowed() {
		return isFollowed;
	}

	public void setFollowed(boolean isFollowed) {
		this.isFollowed = isFollowed;
	}
	
}
