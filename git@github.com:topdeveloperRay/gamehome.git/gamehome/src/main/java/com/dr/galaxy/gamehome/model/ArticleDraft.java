package com.dr.galaxy.gamehome.model;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.UuidGenerator;

@Entity
public class ArticleDraft {
	
	@Id
	@GeneratedValue(generator ="articleDraftId")
	@UuidGenerator(name = "articleDraftId")
	private String articleDraftId;
	@NotNull
	private String articleDraftCircleId;
	@NotNull
	private String articleTitle;
	@NotNull
	private ArrayList<String> articleContent;
	@NotNull
	private ArrayList<String> imgContent;
	
	private String articleTime;//用时间戳
	// private User articleAuthor;
	@NotNull
	private String authorId;
	public String getArticleDraftId() {
		return articleDraftId;
	}
	public void setArticleDraftId(String articleDraftId) {
		this.articleDraftId = articleDraftId;
	}
	public String getArticleDraftCircleId() {
		return articleDraftCircleId;
	}
	public void setArticleDraftCircleId(String articleDraftCircleId) {
		this.articleDraftCircleId = articleDraftCircleId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public ArrayList<String> getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(ArrayList<String> articleContent) {
		this.articleContent = articleContent;
	}
	public ArrayList<String> getImgContent() {
		return imgContent;
	}
	public void setImgContent(ArrayList<String> imgContent) {
		this.imgContent = imgContent;
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
	
}
