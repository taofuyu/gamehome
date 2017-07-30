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
import com.dr.galaxy.gamehome.model.Comment;
import com.dr.galaxy.gamehome.model.UnionComment;
import com.dr.galaxy.gamehome.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	private CommentService commentSevice;
	
	@RequestMapping(method = RequestMethod.POST)
	public Comment addComment(@Valid @RequestBody final Comment comment){
		return commentSevice.saveComment(comment);
	}
	
	@RequestMapping(value="/{wxId}/{articleId}/{commentId}",method = RequestMethod.DELETE)
	public void delete(@PathVariable("wxId") final String wxId,@PathVariable("commentId") final String commentId,
			@PathVariable("articleId") final String articleId){
		 commentSevice.deleteComment(wxId,commentId,articleId);
	}
	
	@RequestMapping(value="/{articleId}/{wxId}",method = RequestMethod.GET)
	public List<Comment> getCommentByArticleId(@PathVariable("wxId") final String wxId,@PathVariable("articleId") final String articleId){
		return commentSevice.getCommentByArticleId(articleId,wxId);
	}
	
	@RequestMapping(value="/{commentId}",method = RequestMethod.GET)
	public Comment getCommentById(@PathVariable("commentId") final String commentId){
		
		return commentSevice.getCommentById(commentId);
	}
	//加两个接口 一个根据评论时间返回，一个根据点赞数量返回
	@RequestMapping(value="/getHotAndNewCommentByArticleId/{articleId}/{wxId}",method = RequestMethod.GET)
	public UnionComment getHotAndNewCommentByArticleId(@PathVariable("wxId") final String wxId,@PathVariable("articleId") final String articleId){
		return commentSevice.getHotAndNewCommentByArticleId(articleId,wxId);
	}
	@RequestMapping(value="/getAllHotCommentByArticleId/{articleId}/{wxId}",method = RequestMethod.GET)
	public List<Comment> getAllHotCommentsByArticleId(@PathVariable("wxId") final String wxId,@PathVariable("articleId") final String articleId){
		return commentSevice.getAllNewCommentByArticleId(articleId,wxId);
	}
	@RequestMapping(value="/getAllNewCommentByArticleId/{articleId}/{wxId}",method = RequestMethod.GET)
	public List<Comment> getAllNewCommentsByArticleId(@PathVariable("wxId") final String wxId,@PathVariable("articleId") final String articleId){
		return commentSevice.getAllHotCommentByArticleId(articleId,wxId);
	}
	@RequestMapping(value="/like/{commentId}/{likeUserId}",method = RequestMethod.POST)
	public Comment commetLiked(@PathVariable("commentId") final String commentId,@PathVariable("likeUserId") final String likeUserId,HttpServletResponse response) throws IOException {
		return commentSevice.addCommentLikeUser(commentId,likeUserId);  
	}
	
}
