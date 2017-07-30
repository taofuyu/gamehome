package com.dr.galaxy.gamehome.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dr.galaxy.gamehome.model.ArticleDraft;
import com.dr.galaxy.gamehome.model.Circle;
import com.dr.galaxy.gamehome.model.Customer;
import com.dr.galaxy.gamehome.model.LoginSecret;
import com.dr.galaxy.gamehome.model.LoginUserInfo;
import com.dr.galaxy.gamehome.service.LoginService;

@RestController
@CrossOrigin
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(method = RequestMethod.POST)
	public Customer login(@Valid @RequestBody final LoginUserInfo loginUserInfo) {
		// Map<String, LoginSecret> map= new ConcurrentHashMap<>();
		return loginService.login(loginUserInfo);
	}
}
