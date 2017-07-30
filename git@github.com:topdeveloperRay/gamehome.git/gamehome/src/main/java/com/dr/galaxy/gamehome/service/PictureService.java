package com.dr.galaxy.gamehome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dr.galaxy.gamehome.model.Picture;
import com.dr.galaxy.gamehome.repository.PictureRepository;

@Service
public class PictureService {

	@Autowired
	private PictureRepository pictureRepository;

	public Picture insertPicture(Picture picture) {
		return pictureRepository.save(picture);
	}

	public Picture queryById(String pictureId) {
		return pictureRepository.findOne(pictureId);
	}

	public List<Picture> queryAll() {
		return (List<Picture>) pictureRepository.findAll();
	}
}
