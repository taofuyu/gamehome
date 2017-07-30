package com.dr.galaxy.gamehome.model;

import java.util.List;

public class UnionComment {
	
	private List<Comment> hotComments;
	private List<Comment> newComments;
	
	public List<Comment> getHotComments() {
		return hotComments;
	}
	public void setHotComments(List<Comment> hotComments) {
		this.hotComments = hotComments;
	}
	public List<Comment> getNewComments() {
		return newComments;
	}
	public void setNewComments(List<Comment> newComments) {
		this.newComments = newComments;
	}
	
	
}
