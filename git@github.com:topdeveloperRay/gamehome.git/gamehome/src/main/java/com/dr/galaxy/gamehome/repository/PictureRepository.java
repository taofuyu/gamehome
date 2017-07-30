package com.dr.galaxy.gamehome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dr.galaxy.gamehome.model.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, String>{

}
