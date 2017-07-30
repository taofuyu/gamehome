package com.dr.galaxy.gamehome.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dr.galaxy.gamehome.model.Article;
import com.dr.galaxy.gamehome.model.ArticleDetail;
import com.dr.galaxy.gamehome.model.CircleFollowedStatus;
import com.dr.galaxy.gamehome.model.Comment;
import com.dr.galaxy.gamehome.model.Customer;
import com.dr.galaxy.gamehome.model.CustomerCircle;
import com.dr.galaxy.gamehome.model.GeneralUserInfo;
import com.dr.galaxy.gamehome.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService userService;

	@RequestMapping(method = RequestMethod.POST)
	public Customer createUser(@Valid @RequestBody final Customer user) {
		UUID uuid = UUID.randomUUID();
		user.setUserId(uuid.toString());
		user.setArticleIds(new HashSet<>());
		user.setCollectionIds(new HashSet<>());
		user.setFansIds(new HashSet<>());
		user.setFollowUsersIds(new HashSet<>());
		user.setCommentIds(new HashSet<>());
		user.setDraftIds(new HashSet<>());
		user.setCircleFollowedStatus(new HashMap<>());
		return userService.saveUser(user);
	};

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Customer> getAllUsers(final HttpServletRequest request) {
		return userService.getAllUsers();
	};

	@RequestMapping(value = "/{wxId}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getUserByWxId(@PathVariable("wxId") final String wxId) {
		return ResponseEntity.ok(userService.getUserByWxId(wxId));
	}

	@RequestMapping(value = "/getCustomerCircleStatus/{wxId}", method = RequestMethod.GET)
	public List<CustomerCircle> getCustomerCircleStatus(@PathVariable("wxId") final String wxId) {
		return userService.geCircleByWxId(wxId);
	}

	// 关注或者取消关注圈子
	@RequestMapping(value = "/addFollowCircle/{wxId}", method = RequestMethod.POST)
	public Customer addFollowCircle(@PathVariable("wxId") final String wxId,//dont need to post json ,can be done in the url 
			@Valid @RequestBody final CircleFollowedStatus circleFollowedStatus) {
		return userService.updateCircleFollowedStatus(wxId, circleFollowedStatus);
		// return ResponseEntity.ok(userService.getUserByWxId(wxId));
	}

	// 关注或者取消关注作者
	@RequestMapping(value = "/addFollowCustomer/{followedId}/{followId}", method = RequestMethod.POST)
	public Customer addFollowUser(@PathVariable("followedId") final String followedId,
			@PathVariable("followId") final String followId) {
		return userService.addFans(followedId, followId);
	}

	@RequestMapping(value = "/{wxId}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateUserByWxId(@PathVariable("wxId") final String wxId,
			@Valid @RequestBody final Customer user) {
		return ResponseEntity.ok(userService.saveUser(user));
	}

	@RequestMapping(value = "/collection/{wxId}", method = RequestMethod.GET)
	public ArrayList<ArticleDetail> getUserCollection(@PathVariable("wxId") final String wxId) {
		return userService.getUserCollection(wxId);
	}

/*	@RequestMapping(value = "/getFans/{wxId}", method = RequestMethod.GET)
	public List<GeneralUserInfo> getUserFans(@PathVariable("wxId") final String wxId) {
		return userService.getUserFans(wxId);
		//this method and get followd users need to be updated, get fans need to id, one is user's id , the other is current user's id
	}*/
	
	/*@RequestMapping(value = "/getFollowUsers/{wxId}", method = RequestMethod.GET)
	public List<GeneralUserInfo> getFollowUsers(@PathVariable("wxId") final String wxId) {
		return userService.getFollowUsers(wxId);
	}*/
	@RequestMapping(value = "/getFans/{wxId}/{currentUserId}", method = RequestMethod.GET)
	public List<GeneralUserInfo> getUserFans(@PathVariable("wxId") final String wxId,@PathVariable("currentUserId") final String currentUserId) {
		return userService.getUserFans(wxId,currentUserId);
		//this method and get followd users need to be updated, get fans need to id, one is user's id , the other is current user's id
	}
	@RequestMapping(value = "/getFollowUsers/{wxId}/{currentUserId}", method = RequestMethod.GET)
	public List<GeneralUserInfo> getFollowUsers(@PathVariable("wxId") final String wxId,@PathVariable("currentUserId") final String currentUserId) {
		return userService.getFollowUsers(wxId,currentUserId);
	}
	
	@RequestMapping(value = "/getComments/{wxId}", method = RequestMethod.GET)
	public List<Comment> getUserComments(@PathVariable("wxId") final String wxId) {
		return userService.getUserComments(wxId);
	}
	
	@RequestMapping(value = "/getArticleByUserId/{wxId}", method = RequestMethod.GET)
	public List<Article> getArticleByUserId(@PathVariable("wxId") final String wxId) {
		return userService.getArticleByUserId(wxId);
	}
	
	@RequestMapping(value = "/{wxId}", method = RequestMethod.DELETE)
	public ResponseEntity deleteUserByWxId(@PathVariable("wxId") final String wxId) {
		userService.deleteUserByWxId(wxId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity deleteAllUsers() {
		userService.deleteAllUsers();
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	//getAllCollectionOf a user, if collection is delete ,return []
	
}
