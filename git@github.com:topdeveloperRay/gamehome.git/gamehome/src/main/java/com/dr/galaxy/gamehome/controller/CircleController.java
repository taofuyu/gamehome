package com.dr.galaxy.gamehome.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dr.galaxy.gamehome.model.Circle;
import com.dr.galaxy.gamehome.service.CircleService;

@RestController
@CrossOrigin
@RequestMapping("circle")
public class CircleController {
	
	@Autowired
	private CircleService circleService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Circle saveCircle(@Valid @RequestBody final Circle circle){
		return circleService.saveCircle(circle);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Circle> getAllCircle(){
		return circleService.getAllCircles();
	}
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Circle getCircleById(@PathVariable("id") final String id,HttpServletResponse response) throws IOException {
		  return circleService.getCircleById(id);  
	}
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteCircleById(@PathVariable("id") final String id,HttpServletResponse response) throws IOException {
		   circleService.deleteCircleById(id);  
	}
}
