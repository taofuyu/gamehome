package com.dr.galaxy.gamehome.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dr.galaxy.gamehome.model.ArticleDraft;
import com.dr.galaxy.gamehome.service.DraftService;

@RestController
@CrossOrigin
@RequestMapping("draft")
public class DraftController {
	
	@Autowired
	private DraftService draftService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ArticleDraft addDraft(@Valid @RequestBody final ArticleDraft articleDraft){
		return draftService.saveDraft(articleDraft);
	}
	
	@RequestMapping(value = "/{articleDraftId}",method = RequestMethod.DELETE)
	public void deleteDraft(@PathVariable("articleDraftId") final String articleDraftId){
		 draftService.deleteDraft(articleDraftId);
	}
	@RequestMapping(value = "/getAllDraft/{wxId}/{page}",method = RequestMethod.GET)
	public List<ArticleDraft> getAllDraft(@PathVariable("wxId") final String wxId,@PathVariable("page") final String page){
		return draftService.getAllDraft(wxId,page);
	}
	
	@RequestMapping(value = "/getDraftById/{articleDraftId}",method = RequestMethod.GET)
	public ArticleDraft getAllDraft(@PathVariable("articleDraftId") final String articleDraftId){
		return draftService.getDraftById(articleDraftId);
	}
	
}
