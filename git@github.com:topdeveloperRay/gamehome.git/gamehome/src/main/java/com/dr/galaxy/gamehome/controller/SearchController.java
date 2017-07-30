package com.dr.galaxy.gamehome.controller;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dr.galaxy.gamehome.model.Article;
import com.dr.galaxy.gamehome.model.GeneralPageArticle;
import com.dr.galaxy.gamehome.service.ArticleService;
import com.dr.galaxy.gamehome.util.GamehomeUtil;

@RestController
@RequestMapping("/searchArticles")
public class SearchController {
	
	@Autowired
	private ArticleService articleService;
	
    @RequestMapping(value = "/{wxId}",method = RequestMethod.GET)
    public List<GeneralPageArticle> searchTemplate(@PathVariable("wxId") final String wxId,@RequestParam(value = "q", required = false) final String q){
        
        Map map = null ;
        List<Article> articles;
        if (q == null) {
        	articles =  articleService.getAllArticles();
        }
        try {
            map = GamehomeUtil.createQueryMap(q);
            System.out.println(map.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Article article = new Article();
        if(map.get("articleTitle")!=null){
        	article.setArticleTitle(map.get("articleTitle").toString());
        }
        Example<Article> example = Example.of(article);
        articles =  articleService.searchArticle(example);
        //bug
        return articleService.convertArticleToMixedArticle(articles, wxId);
        
    }
    
    @RequestMapping(value = "//{wxId}/{title}",method = RequestMethod.GET)
    public List<GeneralPageArticle> searchArticleByTitle(@PathVariable("wxId") final String wxId,@PathVariable("title") final String title){
        //bug
    	  List<Article> articles = articleService.findArticleByTitle(title);
    	  
    	  return articleService.convertArticleToMixedArticle(articles, wxId);
        
    }
}
