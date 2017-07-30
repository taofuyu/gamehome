package com.dr.galaxy.gamehome.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dr.galaxy.gamehome.model.ArticleDraft;

@Repository
public interface DraftRepository extends CrudRepository<ArticleDraft,String>{
	
	@Query(value="select a from ArticleDraft a where a.authorId =?1")
	List<ArticleDraft> findWithPageable(String wxId, Pageable topTenPageable);

}
