package com.dr.galaxy.gamehome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dr.galaxy.gamehome.model.Customer;
import com.dr.galaxy.gamehome.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("rank")
public class ScoreRankController {
	
	@Autowired
	private CustomerService userService;
	
	@RequestMapping(value = "{wxId}/{page}", method = RequestMethod.GET)
	public List<Customer> getTopScoreCustomers(@PathVariable("wxId") final String wxId,@PathVariable("page") final String page){
		return userService.getRankByScore(page, wxId);
	}
	
}
