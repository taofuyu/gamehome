package com.dr.galaxy.gamehome.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Circle {

	@Id
	private String circleId;// （int，自增长，PK）

	private String circleName;// (String)

	private String circleType;

	private String circleDescription;

	private String circlePicture;

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

}
