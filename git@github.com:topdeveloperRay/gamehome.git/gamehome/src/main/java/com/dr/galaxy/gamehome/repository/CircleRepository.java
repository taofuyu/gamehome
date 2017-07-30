package com.dr.galaxy.gamehome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dr.galaxy.gamehome.model.Circle;
@Repository
public interface CircleRepository extends CrudRepository<Circle, String> {
	
}
