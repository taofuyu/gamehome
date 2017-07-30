package com.dr.galaxy.gamehome.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.UuidGenerator;

@Entity
public class Article {
	/*
	 * articleId （key） articleCicleId (key) articleTitle， String类型
	 * articleContent(object类型) articleLiked:对象 [｛ “likedBy”:”123”, “isReaded”:
	 * boolean } ]， articleComment:{ [] }首页展示推荐文章，根据文章评论数量，数量大于10的为推荐文章
	 * articleScanCount articleTime 下拉刷新传Page ，根据Page来确定拿最新的哪几篇 articleAuthor:{
	 * “authorId”:”123”, } collectionUsesCount:
	 */
	
	@Id
	@GeneratedValue(generator ="articleId")
	@UuidGenerator(name = "articleId")
	private String articleId;
	@NotNull
	private String articleCircleId;
	@NotNull
	private String articleTitle;
	@NotNull
	private ArrayList<Object> articleContent;
	
	private String articleDraftId;
	
	/*@NotNull
	private ArrayList<String> imgContent;*/
	
	private HashSet<String> articleLikedUsers;
	private HashSet<String> articleCommentsIds;
	private int articleScanCount;
	
	private String articleTime;//用时间戳
	// private User articleAuthor;
	@NotNull
	private String authorId;
	private HashSet<String> collectionUserIds;

	public HashSet<String> getArticleLikedUsers() {
		return articleLikedUsers;
	}

	public void setArticleLikedUsers(HashSet<String> articleLikedUsers) {
		this.articleLikedUsers = articleLikedUsers;
	}

	public HashSet<String> getArticleCommentsIds() {
		return articleCommentsIds;
	}

	public void setArticleCommentsIds(HashSet<String> articleCommentsIds) {
		this.articleCommentsIds = articleCommentsIds;
	}

	public String getArticleId() {
		return articleId;
	}

	public String getArticleCircleId() {
		return articleCircleId;
	}

	public void setArticleCircleId(String articleCircleId) {
		this.articleCircleId = articleCircleId;
	}


	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public ArrayList<Object> getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(ArrayList<Object> articleContent) {
		this.articleContent = articleContent;
	}

	/*public ArrayList<String> getImgContent() {
		return imgContent;
	}

	public void setImgContent(ArrayList<String> imgContent) {
		this.imgContent = imgContent;
	}
*/
	public int getArticleScanCount() {
		return articleScanCount;
	}

	public void setArticleScanCount(int articleScanCount) {
		this.articleScanCount = articleScanCount;
	}

	public String getArticleTime() {
		return articleTime;
	}

	public void setArticleTime(String articleTime) {
		this.articleTime = articleTime;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public HashSet<String> getCollectionUserIds() {
		return collectionUserIds;
	}

	public void setCollectionUserIds(HashSet<String> collectionUserIds) {
		this.collectionUserIds = collectionUserIds;
	}

	public String getArticleDraftId() {
		return articleDraftId;
	}

	public void setArticleDraftId(String articleDraftId) {
		this.articleDraftId = articleDraftId;
	}
}
