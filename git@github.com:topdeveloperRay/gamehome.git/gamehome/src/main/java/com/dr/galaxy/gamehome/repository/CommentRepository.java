package com.dr.galaxy.gamehome.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dr.galaxy.gamehome.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String>{

	public List<Comment> findByCommentArticleId(String commentArticleId);

	@Query(value="select c from Comment c where c.commentArticleId =?1")
	public List<Comment> findHotCommentWithPageable( String articleId,Pageable hotFivePagable);
	
	@Query(value="select c from Comment c where c.commentArticleId =?1")
	public List<Comment> findNewCommentWithPageable(String articleId, Pageable newFivePageable);
	
	/*@Query("select u.id, LENGTH(u.firstname) as fn_len from User u where u.articleId  ?1%")
	List<Object[]> findByAsArrayAndSort(String articleId, Sort sort);*/
	
}
