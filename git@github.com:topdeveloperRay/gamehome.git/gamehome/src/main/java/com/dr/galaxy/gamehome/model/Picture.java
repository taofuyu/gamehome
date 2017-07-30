package com.dr.galaxy.gamehome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.UuidGenerator;

@Entity
public class Picture {
	@Id
	@GeneratedValue(generator = "pictureId")
	@UuidGenerator(name = "pictureId")
	private String pictureId;
	
	@NotNull
    private byte[] picContent;

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public byte[] getPicContent() {
		return picContent;
	}

	public void setPicContent(byte[] picContent) {
		this.picContent = picContent;
	}
}
