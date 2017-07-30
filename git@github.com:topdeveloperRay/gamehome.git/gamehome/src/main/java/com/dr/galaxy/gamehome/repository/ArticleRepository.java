package com.dr.galaxy.gamehome.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dr.galaxy.gamehome.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String>,JpaSpecificationExecutor {
	public List<Article> findByArticleCircleId(String articleCircleId);

	@Query("SELECT a from Article a where a.articleCircleId=?1")
	public List<Article> getByArticleTime();

	
	List<Article> findFirst10ByArticleCircleId(String articleCircleId);//this method return the oldest ten value

	List<Article> findTop10ByArticleCircleId(String articleCircleId, Pageable pageable);

	//List<Article> findWithPageable(new PageRequest(0, 10,Direction.ASC,"articleCircleId"));

	@Query(value="select a from Article a where a.articleCircleId =?1")
	public List<Article>  findWithPageable(String articleCircleId,Pageable pageable);
	
	@Query(value="select a from Article a")
	public List<Article>  findHotestArticleWithPageable(Pageable pageable);

	@Query(value = "select a from Article a where a.articleTitle like %:title%")
	public List<Article> findByTitleMatch(@Param("title") String title);
	
}
