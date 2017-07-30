package com.dr.galaxy.gamehome.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dr.galaxy.gamehome.model.Article;
import com.dr.galaxy.gamehome.model.Comment;
import com.dr.galaxy.gamehome.model.Customer;
import com.dr.galaxy.gamehome.model.UnionComment;
import com.dr.galaxy.gamehome.repository.CommentRepository;
import com.dr.galaxy.gamehome.util.GamehomeUtil;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ArticleService articleService;

	public Comment saveComment(Comment comment) {
		// 根据commentReplyId去查replyComment
		comment.setCommentTime(GamehomeUtil.getCurrentTime());
		String articleId = comment.getCommentArticleId();
		Article article = articleService.getArticleById(articleId);
		if (article == null) {
			throw new RuntimeException("not found article for this article id:" + articleId);
		}
		Customer commentCustomer = customerService.getUserByWxId(comment.getCommentUserId());
		

		Customer author = customerService.getUserByWxId(article.getAuthorId());
		
		if (commentCustomer == null) {
			throw new RuntimeException("not found customer for this id:" + comment.getCommentUserId());
		}
		if (author == null) {
			throw new RuntimeException("not found customer for this id:" + author.getUserId());
		}
		Comment newComment = commentRepository.save(comment);
		article.getArticleCommentsIds().add(newComment.getCommentId());
		articleService.saveArticle(article);
		commentCustomer.getCommentIds().add(newComment.getCommentId());
		
		commentCustomer.setScore(commentCustomer.getScore()+1);
		customerService.saveUser(commentCustomer);
		
		author.setScore(author.getScore()+1);
		customerService.saveUser(author);
		
		return newComment;
	}

	public void deleteComment(String wxId, String commentId, String articleId) {
		//delete check
		Customer commentCustomer = customerService.getUserByWxId(wxId);
		String authorId = articleService.getArticleById(articleId).getAuthorId();
		Customer author = customerService.getUserByWxId(authorId);
		if (commentCustomer == null) {
			throw new RuntimeException("not found customer for this id:" + wxId);
		}
		commentCustomer.getCommentIds().remove(commentId);

		commentCustomer.setScore(commentCustomer.getScore()-1);
		
		author.setScore(author.getScore()-1);
		
		customerService.saveUser(author);
		
		customerService.saveUser(commentCustomer);
		Article article = articleService.getArticleById(articleId);

		if (article == null) {
			throw new RuntimeException("not found article for this article id:" + articleId);
		}
		article.getArticleCommentsIds().remove(commentId);
		articleService.saveArticle(article);
		commentRepository.delete(commentId);
	}

	public Comment getCommentById(String commentId) {
		return commentRepository.findOne(commentId);
	}

	public Comment addCommentLikeUser(String commentId, String likeUserId) {
		Comment comment = getCommentById(commentId);
		if (comment == null) {
			throw new RuntimeException("not found comment for the comment id:" + commentId);
		}
		if (comment.getCommentLiked() == null) {
			comment.setCommentLiked(new HashSet<>());
		}
		if (comment.getCommentLiked().contains(likeUserId)) {
			comment.getCommentLiked().remove(likeUserId);
			comment.setCommentLikedNum(comment.getCommentLiked().size());
		} else {
			comment.getCommentLiked().add(likeUserId);
			comment.setCommentLikedNum(comment.getCommentLiked().size());
		}
		return saveComment(comment);
	}

	public List<Comment> getCommentByArticleId(String articleId, String wxId) {
		Article article = articleService.getArticleById(articleId);
		if (article == null) {
			throw new RuntimeException("not found article for this articleId:" + articleId);
		}
		return commentRepository.findByCommentArticleId(articleId);
	}

	public UnionComment getHotAndNewCommentByArticleId(String articleId, String wxId) {
		UnionComment unionComment = new UnionComment();
		unionComment.setHotComments(getHotCommentByArticleId(articleId,wxId));
		unionComment.setNewComments(getNewCommentByArticleId(articleId,wxId));
		return unionComment;
	}

	private List<Comment> getHotCommentByArticleId(String articleId,String wxId) {
		Pageable hotFivePageable = new PageRequest(0, 5, Direction.DESC, "commentLikedNum");
		List<Comment> comments = commentRepository.findHotCommentWithPageable(articleId, hotFivePageable);
		judgeIsliked(wxId, comments);
		/*
		 * List<Comment> allComments =
		 * commentRepository.findByCommentArticleId(articleId); for(int i
		 * =0;i<allComments.size();i++){ int first =
		 * allComments.get(i).getCommentLiked().size();
		 * 
		 * }
		 */
		return comments;
	}

	private List<Comment> getNewCommentByArticleId(String articleId,String wxId) {
		Pageable newFivePageable = new PageRequest(0, 5, Direction.DESC, "commentTime");
		List<Comment> comments = commentRepository.findNewCommentWithPageable(articleId, newFivePageable);
		judgeIsliked(wxId, comments);
		return comments;
	}

	public List<Comment> getAllNewCommentByArticleId(String articleId, String wxId) {
		Pageable newFivePageable = new PageRequest(0, 20, Direction.DESC, "commentTime");
		List<Comment> comments = commentRepository.findNewCommentWithPageable(articleId, newFivePageable);
		judgeIsliked(wxId, comments);
		return comments;
	}

	public List<Comment> getAllHotCommentByArticleId(String articleId, String wxId) {
		Pageable newFivePageable = new PageRequest(0, 20, Direction.DESC, "commentLikedNum");
		List<Comment> comments = commentRepository.findNewCommentWithPageable(articleId, newFivePageable);
		judgeIsliked(wxId, comments);
		return comments;
	}
	
	private void judgeIsliked(String wxId, List<Comment> comments) {
		for (int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			if((comment.getCommentLiked()!=null)&&(comment.getCommentLiked().contains(wxId))){
				comment.setLiked(true);
			}else{
				comment.setLiked(false);
			}
		}
	}
}
