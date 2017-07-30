package com.dr.galaxy.gamehome.model;

import java.util.HashMap;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.UuidGenerator;

@Entity
public class Customer {
	/*
	 * userId: ( UUID,key) wxId: String，微信Id userPic:String ,头像 nickName :String
	 * 昵称 article：{ [ articleId:”1” articleId： ] } comment：{}，评论
	 * circleFollowedStatus[｛ “circleid”:”123”, “isFollowed”:true, ｝, {
	 * “circleid”:”123”, “isFollowed”:true, }] followUsers，关注用户，object fans：对象
	 * collection::对象 收藏 score:积分
	 */

	@Id
	@GeneratedValue(generator ="userId")
	@UuidGenerator(name = "userId")
	private String userId;

	@NotNull
	private String wxId;
	@NotNull
	private String picUrl;
	@NotNull
	private String nickName;

	/*
	 * private List<User> followUsers; private List<User> fans; private
	 * List<Article> collection; private List<Article> article; private
	 * List<Comment> comment;
	 */
	private HashSet<String> followUsersIds;// object
	private HashSet<String> fansIds;
	private HashSet<String> collectionIds;
	private HashSet<String> articleIds;
	private HashSet<String> commentIds;
	private HashSet<String> draftIds;
	private HashMap<String,Boolean> circleFollowedStatus;

	private int score;
	
	private int contributionScore;//贡献
	
	private int reputationScore;//声誉
	
	
	public int getContributionScore() {
		return contributionScore;
	}

	public void setContributionScore(int contributionScore) {
		this.contributionScore = contributionScore;
	}

	public int getReputationScore() {
		return reputationScore;
	}

	public void setReputationScore(int reputationScore) {
		this.reputationScore = reputationScore;
	}

	public HashSet<String> getDraftIds() {
		return draftIds;
	}

	public void setDraftIds(HashSet<String> draftIds) {
		this.draftIds = draftIds;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public HashSet<String> getFollowUsersIds() {
		return followUsersIds;
	}

	public void setFollowUsersIds(HashSet<String> followUsersIds) {
		this.followUsersIds = followUsersIds;
	}

	public HashSet<String> getFansIds() {
		return fansIds;
	}

	public void setFansIds(HashSet<String> fansIds) {
		this.fansIds = fansIds;
	}

	public HashSet<String> getCollectionIds() {
		return collectionIds;
	}

	public void setCollectionIds(HashSet<String> collectionIds) {
		this.collectionIds = collectionIds;
	}

	public HashSet<String> getArticleIds() {
		return articleIds;
	}

	public void setArticleIds(HashSet<String> articleIds) {
		this.articleIds = articleIds;
	}

	public HashSet<String> getCommentIds() {
		return commentIds;
	}

	public void setCommentIds(HashSet<String> commentIds) {
		this.commentIds = commentIds;
	}

	public HashMap<String, Boolean> getCircleFollowedStatus() {
		return circleFollowedStatus;
	}

	public void setCircleFollowedStatus(HashMap<String, Boolean> circleFollowedStatus) {
		this.circleFollowedStatus = circleFollowedStatus;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
