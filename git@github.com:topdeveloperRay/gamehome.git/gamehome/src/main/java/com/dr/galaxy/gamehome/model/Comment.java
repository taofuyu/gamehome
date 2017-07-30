package com.dr.galaxy.gamehome.model;

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
public class Comment {
	
	@Id
	@GeneratedValue(generator ="commentId")
	@UuidGenerator(name = "commentId")
	private String commentId;
	
	private String commentReplyId;
	
	private String commentReplyUserId;
	
	//commentReplyUserName;
	//commentUserName;
	//commentUserPic;
	@NotNull
	private String commentArticleId;
	//private String commentCircleId;
	@NotNull
	private String commentUserId;
	
	@NotNull
	private HashMap commentContent;//评论
	
	private HashMap replycommentContent;//回复的那条评论的commentContent,
	//若不是回复直接评论，则此字段为空
	//private String commentPicutueUrl;
	private HashSet<String> commentLiked;
	
	private int commentLikedNum;
	
	private String commentTime;
	
	private boolean isReaded;
	
	private boolean isLiked;
	
	public boolean isLiked() {
		return isLiked;
	}
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	public int getCommentLikedNum() {
		return commentLikedNum;
	}
	public void setCommentLikedNum(int commentLikedNum) {
		this.commentLikedNum = commentLikedNum;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCommentReplyId() {
		return commentReplyId;
	}
	public void setCommentReplyId(String commentReplyId) {
		this.commentReplyId = commentReplyId;
	}
	public String getCommentReplyUserId() {
		return commentReplyUserId;
	}
	public void setCommentReplyUserId(String commentReplyUserId) {
		this.commentReplyUserId = commentReplyUserId;
	}
	public String getCommentArticleId() {
		return commentArticleId;
	}
	public void setCommentArticleId(String commentArticleId) {
		this.commentArticleId = commentArticleId;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	public HashMap getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(HashMap commentContent) {
		this.commentContent = commentContent;
	}
	public HashMap getReplycommentContent() {
		return replycommentContent;
	}
	public void setReplycommentContent(HashMap replycommentContent) {
		this.replycommentContent = replycommentContent;
	}
	public HashSet<String> getCommentLiked() {
		return commentLiked;
	}
	public void setCommentLiked(HashSet<String> commentLiked) {
		this.commentLiked = commentLiked;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public boolean isReaded() {
		return isReaded;
	}
	public void setReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}
	
}
