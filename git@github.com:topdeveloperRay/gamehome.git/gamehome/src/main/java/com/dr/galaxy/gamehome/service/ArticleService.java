package com.dr.galaxy.gamehome.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dr.galaxy.gamehome.model.Article;
import com.dr.galaxy.gamehome.model.ArticleDetail;
import com.dr.galaxy.gamehome.model.Circle;
import com.dr.galaxy.gamehome.model.Customer;
import com.dr.galaxy.gamehome.model.GeneralPageArticle;
import com.dr.galaxy.gamehome.repository.ArticleRepository;
import com.dr.galaxy.gamehome.util.GamehomeUtil;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CustomerService userService;

	@Autowired
	private CircleService circleService;

	@Autowired 
	private DraftService draftService;
	
	public Article saveArticle(Article article) {
		//if update ,need to be modify,currentlty is good
		article.setArticleTime(GamehomeUtil.getCurrentTime());
		if(article.getArticleCommentsIds()==null){
			article.setArticleCommentsIds(new HashSet<>());
		}
		if(article.getArticleLikedUsers()==null){
			article.setArticleLikedUsers(new HashSet<>());
		}
		if(article.getCollectionUserIds()==null){
			article.setCollectionUserIds(new HashSet<>());
		}
		Customer user = userService.getUserByWxId(article.getAuthorId());
		if (user == null) {
			throw new RuntimeException("not found customer for this id:" + article.getAuthorId());
		}
		if ((article.getArticleId() == null) || (articleRepository.findOne(article.getArticleId()) == null)) {
			// 把articleLikedUsers，articleCommentsIds，collectionUserIds初始化为［］
			Article saveArticle = articleRepository.save(article);
			if(article.getArticleDraftId()!=null){
				draftService.deleteDraft(article.getArticleDraftId());
			}
			updateUserArticleInfo(saveArticle.getArticleId(),user);
			return saveArticle;
		} else {
			//deleteArticleById(article.getArticleId());
			return articleRepository.save(article);
		}
	}

	private void updateUserArticleInfo(String articleId,Customer user) {
		user.getArticleIds().add(articleId);
		//send an article ,add 5 points
		user.setScore(user.getScore()+5);
		userService.saveUser(user);
	}

	public void deleteArticleById(String articleId) {
		// also delete the user infor
		Article article = getArticleById(articleId);
		Customer user = userService.getUserByWxId(article.getAuthorId());
		user.getArticleIds().remove(articleId);
		user.setScore(user.getScore()-5);
		userService.saveUser(user);
		articleRepository.delete(articleId);
	}

	public Article getArticleById(String id) {
		return articleRepository.findOne(id);
	}

	public List<Article> getArticleByCircleId(String articleCircleId) {
		return articleRepository.findByArticleCircleId(articleCircleId);
	}

	public List<Article> getAllArticles() {
		return (List<Article>) articleRepository.findAll();
	}

	public Article addArticleLikeUser(String articleId, String likeUserId) {
		Article article = getArticleById(articleId);
		
		if (article == null) {
			throw new RuntimeException("wrong article id");
		}
		Customer author = userService.getUserByWxId(article.getAuthorId());
		if (article.getArticleLikedUsers().contains(likeUserId)) {
			article.getArticleLikedUsers().remove(likeUserId);
			author.setScore(author.getScore()-1);
		} else {
			author.setScore(author.getScore()+1);
			article.getArticleLikedUsers().add(likeUserId);
		}
		userService.saveUser(author);
		saveArticle(article);
		return article;
	}

	/*
	 * public Article removeArticleLikeUser(String articleId, String likeUserId)
	 * { Article article = getArticleById(articleId);
	 * article.getArticleLikedUsers().remove(likeUserId); saveArticle(article);
	 * return article; }
	 */

	public List<GeneralPageArticle> getLatestArticlesByPage(String circleId, String page, String wxId) {
		// String[] properties =new String[2];
		// properties[0]=circleId;
		// properties[1]="articleTime";
		Pageable topTenPageable = new PageRequest(GamehomeUtil.convertStringToInt(page), 10, Direction.DESC, "articleTime");
		// return articleRepository.findTop10ByArticleCircleId(circleId,topTen);
		List<Article> articles = articleRepository.findWithPageable(circleId, topTenPageable);
		return convertArticleToMixedArticle(articles, wxId);
	}

	// 增加article浏览量
	public Article addScanCount(String articleId, String count) {

		Article article = getArticleById(articleId);
		if (article == null) {
			throw new RuntimeException("not found article for articleId:" + articleId);
		}
		int newScanCount = article.getArticleScanCount() + GamehomeUtil.convertStringToInt(count);
		article.setArticleScanCount(newScanCount);
		return saveArticle(article);
	}

	public List<GeneralPageArticle> getHotestArticleByScanCount(String page, String wxId) {
		Pageable hotestTenPageable = new PageRequest(GamehomeUtil.convertStringToInt(page), 10, Direction.DESC, "articleScanCount");
		// return articleRepository.findTop10ByArticleCircleId(circleId,topTen);
		List<Article> articles = articleRepository.findHotestArticleWithPageable(hotestTenPageable);
		return convertArticleToMixedArticle(articles, wxId);
	}

	public List<GeneralPageArticle> convertArticleToMixedArticle(List<Article> articles, String wxId) {
		List<GeneralPageArticle> generalPageArticleList = new ArrayList<>();
		// GeneralPageArticle generalPageArticle = new
		// GeneralPageArticle();//should not insiante here
		GeneralPageArticle generalPageArticle = null;
		for (Article article : articles) {
			generalPageArticle = new GeneralPageArticle();
			String authorId = article.getAuthorId();
			Customer author = userService.getUserByWxId(authorId);
			Customer currentUser = userService.getUserByWxId(wxId);
			boolean isFollowAuthor = false;
			boolean isUserCollectArticle = false;
			boolean isFolloArticleCircle = false;
			boolean isAuthorSelf = false;
			boolean isUserLiked = false;
			if(article.getAuthorId().equals(wxId)){
				isAuthorSelf=true;	
			}
			if (currentUser.getFansIds().contains(authorId)) {
				isFollowAuthor = true;
			}
			if (currentUser.getCollectionIds().contains(article.getArticleId())) {
				isUserCollectArticle = true;
			}
			if(article.getArticleLikedUsers().contains(wxId)){
				isUserLiked =true;
			}
			if(currentUser.getCircleFollowedStatus().containsKey(article.getArticleCircleId())){
				isFolloArticleCircle=true;
			}
			String circleId = article.getArticleCircleId();
			Circle articleCircle = circleService.getCircleById(circleId);
			generalPageArticle.setFolloArticleCircle(isFolloArticleCircle);
			generalPageArticle.setArticleCircleId(circleId);
			generalPageArticle.setArticleContent(article.getArticleContent());
			//generalPageArticle.setImgContent(article.getImgContent());
			generalPageArticle.setArticleId(article.getArticleId());
			generalPageArticle.setArticleScanCount(article.getArticleScanCount());
			generalPageArticle.setArticleTime(article.getArticleTime());
			generalPageArticle.setArticleTitle(article.getArticleTitle());
			generalPageArticle.setAuthorId(authorId);
			generalPageArticle.setCircleName(articleCircle.getCircleName());
			generalPageArticle.setComment_num(article.getArticleCommentsIds().size());
			generalPageArticle.setLike_num(article.getArticleLikedUsers().size());
			generalPageArticle.setNickName(author.getNickName());
			generalPageArticle.setPicUrl(author.getPicUrl());
			generalPageArticle.setCollection_num(article.getCollectionUserIds().size());
			generalPageArticle.setFollowAuthor(isFollowAuthor);
			generalPageArticle.setUserCollectArticle(isUserCollectArticle);
			generalPageArticle.setAuthorSelf(isAuthorSelf);
			generalPageArticle.setUserLiked(isUserLiked);
			generalPageArticleList.add(generalPageArticle);
		}
		return generalPageArticleList;
	}

	public Article articleCollected(String articleId, String wxId) {
		Customer customer = userService.getUserByWxId(wxId);
		Article article = getArticleById(articleId);

		if (article == null) {
			throw new RuntimeException("wrong article id:" + articleId);
		}
		if (customer == null) {
			throw new RuntimeException("not found customer for this id:" + article.getAuthorId());
		}
		if (customer.getCollectionIds().contains(articleId)) {
			customer.getCollectionIds().remove(articleId);
			article.getCollectionUserIds().remove(wxId);
		} else {
			customer.getCollectionIds().add(articleId);
			article.getCollectionUserIds().add(wxId);
		}
		userService.saveUser(customer);
		return saveArticle(article);
	}

	public ArticleDetail getArticleDetail(String articleId, String wxId) {
		// TODO Auto-generated method stub
		Article article = getArticleById(articleId);
		if (article == null) {
			throw new RuntimeException("wrong article id:" + articleId);
		}
		return convertArticleToDetailArticle(article,wxId);
	}

	public ArticleDetail convertArticleToDetailArticle(Article article, String wxId) {
		ArticleDetail articleDetail = new ArticleDetail();
		// GeneralPageArticle generalPageArticle = null;
		// generalPageArticle = new GeneralPageArticle();
		String authorId = article.getAuthorId();
		Customer author = userService.getUserByWxId(authorId);
		Customer currentUser = userService.getUserByWxId(wxId);
		boolean isFollowAuthor = false;
		boolean isUserCollectArticle = false;
		boolean isUserLiked = false;
		boolean isAuthorSelf = false;
		boolean isFolloArticleCircle = false;
		
		if (currentUser.getFansIds().contains(authorId)) {
			isFollowAuthor = true;
		}
		if (currentUser.getCollectionIds().contains(article.getArticleId())) {
			isUserCollectArticle = true;
		}
		if(article.getAuthorId().equals(wxId)){
			isAuthorSelf=true;	
		}
		if(article.getArticleLikedUsers().contains(wxId)){
			isUserLiked =true;
		}
		if(currentUser.getCircleFollowedStatus().containsKey(article.getArticleCircleId())){
			isFolloArticleCircle=true;
		}
		String circleId = article.getArticleCircleId();
		Circle articleCircle = circleService.getCircleById(circleId);
		articleDetail.setArticleCircleId(circleId);
		articleDetail.setArticleContent(article.getArticleContent());
		//articleDetail.setImgContent(article.getImgContent());
		articleDetail.setArticleId(article.getArticleId());
		articleDetail.setArticleScanCount(article.getArticleScanCount());
		articleDetail.setArticleTime(article.getArticleTime());
		articleDetail.setArticleTitle(article.getArticleTitle());
		articleDetail.setAuthorId(authorId);
		articleDetail.setCircleName(articleCircle.getCircleName());
		articleDetail.setComment_num(article.getArticleCommentsIds().size());
		articleDetail.setLike_num(article.getArticleLikedUsers().size());
		articleDetail.setNickName(author.getNickName());
		articleDetail.setPicUrl(author.getPicUrl());
		articleDetail.setCollection_num(article.getCollectionUserIds().size());
		articleDetail.setFollowAuthor(isFollowAuthor);
		articleDetail.setUserCollectArticle(isUserCollectArticle);
		articleDetail.setUserLiked(isUserLiked);
		articleDetail.setAuthorSelf(isAuthorSelf);
		articleDetail.setFolloArticleCircle(isFolloArticleCircle);
		
		return articleDetail;
	}

	public List<Article> searchArticle(Example example) {
		return articleRepository.findAll(example);
	}

	public List<Article> findArticleByTitle(String title) {
		return articleRepository.findByTitleMatch(title);
	}

}
