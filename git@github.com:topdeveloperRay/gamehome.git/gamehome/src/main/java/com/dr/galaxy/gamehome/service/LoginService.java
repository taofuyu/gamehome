package com.dr.galaxy.gamehome.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dr.galaxy.gamehome.controller.CustomerController;
import com.dr.galaxy.gamehome.model.Customer;
import com.dr.galaxy.gamehome.model.LoginUserInfo;
import com.dr.galaxy.gamehome.util.GamehomeUtil;

@Service
public class LoginService {
	
	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private CustomerController customerController;
	
	@Value("${appid}")
	private String appid;

	@Value("${secret}")
	private String secret;

	// code=013KrQDE01nP1g2gD8CE0a3SDE0KrQDy
			//?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

	public Customer login(LoginUserInfo loginUserInfo) {
		//LoginSecret loginSecret = restTemplate.getForObject(url+code, LoginSecret.class);
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&grant_type=authorization_code&js_code=";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		ResponseEntity<String> result = restTemplate.exchange(url+loginUserInfo.getCode(), HttpMethod.POST, entity, String.class);
		
		Customer currentUser = new Customer();
		currentUser.setNickName(loginUserInfo.getNickName());
		currentUser.setPicUrl(loginUserInfo.getAvatarUrl());
		
		Map map = null;
		try {
				map = GamehomeUtil.createQueryMap(result.getBody().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(map.get("openid")!=null){
			currentUser.setWxId(map.get("openid").toString());
		}
		System.out.println(result);
		return customerController.createUser(currentUser);
		
	}
}
