package com.dr.galaxy.gamehome.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dr.galaxy.gamehome.model.Article;
import com.dr.galaxy.gamehome.model.ArticleDetail;
import com.dr.galaxy.gamehome.model.Circle;
import com.dr.galaxy.gamehome.model.CircleFollowedStatus;
import com.dr.galaxy.gamehome.model.Comment;
import com.dr.galaxy.gamehome.model.Customer;
import com.dr.galaxy.gamehome.model.CustomerCircle;
import com.dr.galaxy.gamehome.model.GeneralUserInfo;
import com.dr.galaxy.gamehome.repository.ArticleRepository;
import com.dr.galaxy.gamehome.repository.CustomerRepository;
import com.dr.galaxy.gamehome.util.GamehomeUtil;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CircleService circleService;

	@Autowired 
	private CommentService commentService;
	
	public Customer getUserByWxId(final String wxId) {
		//return customerRepository.findByWxId(wxId);
		return customerRepository.findOne(wxId);
	}

	public Iterable<Customer> getAllUsers() {
		return customerRepository.findAll();
	}

	/*
	 * public User putUserByWxId(final User user){ return
	 * userRepository.save(user); }
	 */
	public void deleteUserByWxId(String wxId) {
		customerRepository.delete(wxId);
	}

	public void deleteAllUsers() {
		customerRepository.deleteAll();
	}

	public Customer saveUser(Customer user) {
		
		return customerRepository.save(user);
	/*	if (customerRepository.findByWxId(user.getWxId()) == null) {
			return customerRepository.save(user);
		} else {
			customerRepository.deleteByWxId(user.getWxId());
			return customerRepository.save(user);
		}*/
	}

	public ArrayList<ArticleDetail> getUserCollection(String wxId) {
		ArrayList<ArticleDetail> articleDetails = new ArrayList<>();
		HashSet<String> collectionIds =  customerRepository.findOne(wxId).getCollectionIds();
		for(String collectionId:collectionIds){
			ArticleDetail articleDetail = articleService.getArticleDetail(collectionId, wxId);
			articleDetails.add(articleDetail);
		}

		return articleDetails;
	}

	public Customer updateCircleFollowedStatus(String wxId, CircleFollowedStatus circleFollowedStatus) {
		Customer customer = getUserByWxId(wxId);
		if (circleFollowedStatus.isFollowed() == true) {
			customer.getCircleFollowedStatus().put(circleFollowedStatus.getCircleId(),
					circleFollowedStatus.isFollowed());
			saveUser(customer);
		} else {
			customer.getCircleFollowedStatus().remove(circleFollowedStatus.getCircleId());
			saveUser(customer);
		}
		// customer.getCircleFollowedStatus().put(circleFollowedStatus.getCircleId(),
		// circleFollowedStatus.isFollowed());
		// saveUser(customer);
		return customer;
	}

	public List<CustomerCircle> geCircleByWxId(String wxId) {
		Customer customer = getUserByWxId(wxId);
		List<CustomerCircle> customerCircles = new ArrayList<>();
		List<Circle> list = (List<Circle>) circleService.getAllCircles();
		// for(int i =0;i<customer.getCircleFollowedStatus().size();i++){
		for (int i = 0; i < list.size(); i++) {
			CustomerCircle customerCircle = new CustomerCircle();
			// boolean circleFollowd =
			// customer.getCircleFollowedStatus().get(i);
			customerCircle.setCircleDescription(list.get(i).getCircleDescription());
			customerCircle.setCircleId(list.get(i).getCircleId());
			customerCircle.setCircleName(list.get(i).getCircleName());
			customerCircle.setCirclePicture(list.get(i).getCirclePicture());
			customerCircle.setCircleType(list.get(i).getCircleType());

			String circleId = list.get(i).getCircleId();
			if (customer.getCircleFollowedStatus().get(circleId) != null) {
				customerCircle.setFollowed(true);
			} else {
				customerCircle.setFollowed(false);
			}
			customerCircles.add(i, customerCircle);
		}
		return customerCircles;
	}

	public void getUserOpenId(String appid, String appsecret, String code) {
		
	}

	public List<GeneralUserInfo> getUserFans(String wxId, String currentUserId) {
		//wxid means the user you see, currentUserid means cuurrent user ,they are two people
		List<GeneralUserInfo> generalUserInfoList = new ArrayList<>();
		//Customer currentCustomer = customerRepository.findByWxId(wxId);
		Customer currentCustomer = customerRepository.findOne(currentUserId);//the currentuser
		HashSet<String> fans =  customerRepository.findOne(wxId).getFansIds();
		//not cuurentUser's fans, its the fans of the user he choose, it maybe himself, maybe someone else
		for(String fansid:fans){
			Customer fan = customerRepository.findOne(fansid);
			GeneralUserInfo generalUserInfo = new GeneralUserInfo();
			generalUserInfo.setFansNum(fan.getFansIds().size());
			generalUserInfo.setFollowNum(fan.getFollowUsersIds().size());
			generalUserInfo.setPicUrl(fan.getPicUrl());
			generalUserInfo.setNickName(fan.getNickName());
			generalUserInfo.setUserId(fan.getUserId());
			if(currentCustomer.getFollowUsersIds().contains(fan.getUserId())){
				generalUserInfo.setFollowed(true);
			}else{
				generalUserInfo.setFollowed(false);
			}
			generalUserInfoList.add(generalUserInfo);
		}
		return generalUserInfoList;
	}
	
	
	public List<GeneralUserInfo> getFollowUsers(String wxId, String currentUserId) {
		
		List<GeneralUserInfo> generalUserInfoList = new ArrayList<>();
		Customer currentCustomer = customerRepository.findOne(currentUserId);//the currentuser
		HashSet<String> followUsers = customerRepository.findOne(wxId).getFollowUsersIds();
		for(String followUserId:followUsers){
			Customer followUser = customerRepository.findOne(followUserId);
			GeneralUserInfo generalUserInfo = new GeneralUserInfo();
			generalUserInfo.setFansNum(followUser.getFansIds().size());
			generalUserInfo.setFollowNum(followUser.getFollowUsersIds().size());
			generalUserInfo.setPicUrl(followUser.getPicUrl());
			generalUserInfo.setNickName(followUser.getNickName());
			generalUserInfo.setUserId(followUser.getUserId());
			
			if(currentCustomer.getFollowUsersIds().contains(followUserId)){
				generalUserInfo.setFollowed(true);
			}else{
				generalUserInfo.setFollowed(false);
			}
			generalUserInfo.setFollowed(true);
			generalUserInfoList.add(generalUserInfo);
		}
		return generalUserInfoList;
	}

	public List<Comment> getUserComments(String wxId) {
		List<Comment> comments = new ArrayList<>();
		HashSet<String> commentIds =  customerRepository.findOne(wxId).getCommentIds();
		for(String commentId:commentIds){
			Comment comment = commentService.getCommentById(commentId);
			comments.add(comment);
		}
		return comments;
	}

	public List<Article> getArticleByUserId(String wxId) {
		return null;
	}
	
	public Customer addFans(String followedId, String followId) {
		Customer customer = getUserByWxId(followedId);
		Customer fans = getUserByWxId(followId);
		if (fans.getFollowUsersIds().contains(followedId)) {
			customer.getFansIds().remove(followId);
			customer.setScore(customer.getScore()-1);
			fans.getFollowUsersIds().remove(followedId);
		} else {
			customer.getFansIds().add(followId);
			customer.setScore(customer.getScore()+1);
			fans.getFollowUsersIds().add(followedId);
		}
		saveUser(customer);
		return saveUser(fans);
	}

	public List<Customer> getRankByScore(String page, String wxId) {
		
		Pageable rankPageable = new PageRequest(GamehomeUtil.convertStringToInt(page), 20, Direction.DESC, "score");
		List<Customer> customers = customerRepository.findRankWithPageable(rankPageable);
		return customers;
	}

}
