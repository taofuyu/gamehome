package com.dr.galaxy.gamehome.controller;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.dr.galaxy.gamehome.model.Picture;
import com.dr.galaxy.gamehome.service.PictureService;

@RestController
@CrossOrigin
@RequestMapping("picture")
public class PictureController {

	@Autowired
	private PictureService pictureService;

	@RequestMapping(method = RequestMethod.POST)
	public String postPic(@RequestPart("pictureFile") MultipartFile pictureFile, HttpServletRequest request)
			throws IOException {

		/*
		 * if (!picContent.getContentType().startsWith("image/")) { throw new
		 * FileNotSupportedException("image file is not present"); }
		 */
		Picture picture = new Picture();
		picture.setPicContent(pictureFile.getBytes());
		Picture pictureSave = pictureService.insertPicture(picture);

		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ "/picture/" + pictureSave.getPictureId();
		//URI location = UriComponentsBuilder.fromHttpUrl(url).build().toUri();

		return url;//url + id
	}

	@RequestMapping(value = "/{pictureId}", method = RequestMethod.GET)
	public Picture getPicInternal(@PathVariable("pictureId") String pictureId, HttpServletResponse response)
			throws IOException {

		byte[] picture = pictureService.queryById(pictureId).getPicContent();
		response.setContentLength(picture.length);
		response.getOutputStream().write(picture);
		response.getOutputStream().close();

		return pictureService.queryById(pictureId);
		
	}
	//delete
}
