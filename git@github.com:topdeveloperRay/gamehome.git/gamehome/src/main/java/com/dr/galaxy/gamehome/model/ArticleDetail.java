package com.dr.galaxy.gamehome.model;

import java.util.ArrayList;

public class ArticleDetail {
	
	private String articleId;
	private String articleCircleId;
	private String authorId;
	private String nickName;
	private String circleName;
	private String picUrl;
	private String articleTitle;
	private ArrayList<Object> articleContent;
	//private ArrayList<String> imgContent;
	private int articleScanCount;
	private int like_num;
	private int comment_num;
	private int collection_num;
	private boolean isFollowAuthor;
	private boolean isUserCollectArticle;
	private boolean isAuthorSelf;//是否本人
	private boolean isUserLiked;//是否点赞
	private boolean isFolloArticleCircle;
	private String articleTime;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getArticleCircleId() {
		return articleCircleId;
	}
	public void setArticleCircleId(String articleCircleId) {
		this.articleCircleId = articleCircleId;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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
	}*/
	public int getArticleScanCount() {
		return articleScanCount;
	}
	public void setArticleScanCount(int articleScanCount) {
		this.articleScanCount = articleScanCount;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getCollection_num() {
		return collection_num;
	}
	public void setCollection_num(int collection_num) {
		this.collection_num = collection_num;
	}
	public boolean isFollowAuthor() {
		return isFollowAuthor;
	}
	public void setFollowAuthor(boolean isFollowAuthor) {
		this.isFollowAuthor = isFollowAuthor;
	}
	public boolean isUserCollectArticle() {
		return isUserCollectArticle;
	}
	public void setUserCollectArticle(boolean isUserCollectArticle) {
		this.isUserCollectArticle = isUserCollectArticle;
	}
	public String getArticleTime() {
		return articleTime;
	}
	public void setArticleTime(String articleTime) {
		this.articleTime = articleTime;
	}
	public boolean isAuthorSelf() {
		return isAuthorSelf;
	}
	public void setAuthorSelf(boolean isAuthorSelf) {
		this.isAuthorSelf = isAuthorSelf;
	}
	public boolean isUserLiked() {
		return isUserLiked;
	}
	public void setUserLiked(boolean isUserLiked) {
		this.isUserLiked = isUserLiked;
	}
	public boolean isFolloArticleCircle() {
		return isFolloArticleCircle;
	}
	public void setFolloArticleCircle(boolean isFolloArticleCircle) {
		this.isFolloArticleCircle = isFolloArticleCircle;
	}
}
