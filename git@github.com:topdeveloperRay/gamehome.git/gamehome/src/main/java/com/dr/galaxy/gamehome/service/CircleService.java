package com.dr.galaxy.gamehome.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dr.galaxy.gamehome.model.Circle;
import com.dr.galaxy.gamehome.repository.CircleRepository;

@Service
public class CircleService {

	@Autowired
	private CircleRepository circleRepository;

	public Iterable<Circle> getAllCircles() {
		return circleRepository.findAll();
	}

	public Circle getCircleById(String circleId) {
		return circleRepository.findOne(circleId);
	}

	public Circle saveCircle(final Circle circle) {
		if (circleRepository.findOne(circle.getCircleId()) == null) {
			return circleRepository.save(circle);
		} else {
			deleteCircleById(circle.getCircleId());
			return circleRepository.save(circle);
		}
	}

	public void deleteCircleById(String circleId) {
		circleRepository.delete(circleId);
	}
}
