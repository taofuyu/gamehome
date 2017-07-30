package com.dr.galaxy.gamehome.controller;

import java.io.IOException;
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

import com.dr.galaxy.gamehome.model.Article;
import com.dr.galaxy.gamehome.model.ArticleDetail;
import com.dr.galaxy.gamehome.model.ArticleScan;
import com.dr.galaxy.gamehome.model.GeneralPageArticle;
import com.dr.galaxy.gamehome.service.ArticleService;

@RestController
@CrossOrigin
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(method = RequestMethod.POST)
	public Article saveArticle(@Valid @RequestBody final Article article) {
		return articleService.saveArticle(article);
	}//发生一篇文章之后就跳转到它的detail界面
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Article> getAllArticles() {
		return articleService.getAllArticles();
	}

	@RequestMapping(value = "/addArticleScanCount", method = RequestMethod.POST)
	public Article addArticleScanCount(@RequestBody final ArticleScan articleScan) {
		return articleService.addScanCount(articleScan.getArticleId(), articleScan.getScanCount());
	}

	/*
	 * @RequestMapping(value="/newest/{circleId}/{page}",method =
	 * RequestMethod.GET) public List<Article>
	 * getNewestTenArticlesByCircleIdAndPage(@PathVariable("circleId") final
	 * String circleId,@PathVariable("page") final String page){ return
	 * articleService.getLatestArticlesByPage(circleId,page); }
	 */
	@RequestMapping(value = "{wxId}/latest/{circleId}/{page}", method = RequestMethod.GET)
	public List<GeneralPageArticle> getLatestFiveArticlesByPage(@PathVariable("circleId") final String circleId,
			@PathVariable("page") final String page, @PathVariable("wxId") final String wxId) {
		return articleService.getLatestArticlesByPage(circleId, page, wxId);
	}

	@RequestMapping(value = "/circle/{id}", method = RequestMethod.GET)
	public Article getArticleById(@PathVariable("id") final String id, HttpServletResponse response)
			throws IOException {
		return articleService.getArticleById(id);
	}

	@RequestMapping(value = "/{articleCircleId}", method = RequestMethod.GET)
	public List<Article> getArticleByCircleId(@PathVariable("articleCircleId") final String articleCircleId,
			HttpServletResponse response) throws IOException {
		return articleService.getArticleByCircleId(articleCircleId);
	}

	@RequestMapping(value = "{wxId}/hotest/{page}", method = RequestMethod.GET)
	public List<GeneralPageArticle> getHotestArticle(@PathVariable("page") final String page,
			@PathVariable("wxId") final String wxId) throws IOException {
		return articleService.getHotestArticleByScanCount(page, wxId);
	}

	// 未登录,是否能用相同的逻辑吗
	/*@RequestMapping(value = "/hotest/{page}", method = RequestMethod.GET)
	public List<GeneralPageArticle> getHotestArticleByDefault(@PathVariable("page") final String page,
			@PathVariable("wxId") final String wxId) throws IOException {
		return articleService.getHotestArticleByScanCount(page, wxId); //
	}*/

	// 获取具体文章，登录之后
	@RequestMapping(value = "{wxId}/articleDetail/{articleId}", method = RequestMethod.GET)
	public ArticleDetail getArticleDetail(@PathVariable("articleId") final String articleId,
			@PathVariable("wxId") final String wxId) throws IOException {
		return articleService.getArticleDetail(articleId, wxId);
		//返回的东西,只包括文章的东西，比General多两个字段
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteArticleById(@PathVariable("id") final String id, HttpServletResponse response)
			throws IOException {
		articleService.deleteArticleById(id);
	}

	@RequestMapping(value = "/like/{articleId}/{likeUserId}", method = RequestMethod.POST)
	public Article articleLiked(@PathVariable("articleId") final String articleId,
			@PathVariable("likeUserId") final String likeUserId, HttpServletResponse response) throws IOException {
		return articleService.addArticleLikeUser(articleId, likeUserId);
	}

	@RequestMapping(value = "/scan/{id}", method = RequestMethod.POST)
	public Article articleScanned(@PathVariable("id") final String id, HttpServletResponse response)
			throws IOException {
		return articleService.getArticleById(id);
	}

	@RequestMapping(value = "/collection/{articleId}/{wxId}", method = RequestMethod.POST)
	public Article articleCollected(@PathVariable("articleId") final String articleId,
			@PathVariable("wxId") final String wxId) throws IOException {
		return articleService.articleCollected(articleId, wxId);
	}
}
