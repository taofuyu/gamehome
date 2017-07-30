package com.dr.galaxy.gamehome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dr.galaxy.gamehome.model.ArticleDraft;
import com.dr.galaxy.gamehome.model.Customer;
import com.dr.galaxy.gamehome.repository.DraftRepository;
import com.dr.galaxy.gamehome.util.GamehomeUtil;

@Service
public class DraftService {

	@Autowired
	private DraftRepository draftRepository;

	@Autowired
	private CustomerService customerService;

	public ArticleDraft saveDraft(ArticleDraft articleDraft) {
		Customer customer = customerService.getUserByWxId(articleDraft.getAuthorId());
		articleDraft.setArticleTime(GamehomeUtil.getCurrentTime());
		if (articleDraft.getArticleDraftId() != null) {
			return draftRepository.save(articleDraft);
		}else{
			ArticleDraft draft = draftRepository.save(articleDraft);
			customer.getDraftIds().add(draft.getArticleDraftId());
			customerService.saveUser(customer);
			return draft;
		}
	}

	public void deleteDraft(String articleDraftId) {
		ArticleDraft draft = draftRepository.findOne(articleDraftId);
		Customer customer = customerService.getUserByWxId(draft.getAuthorId());
		customer.getDraftIds().remove(articleDraftId);
		customerService.saveUser(customer);
		draftRepository.delete(articleDraftId);
	}

	public List<ArticleDraft> getAllDraft(String wxId, String page) {

		Pageable topTenPageable = new PageRequest(convertStringToInt(page), 10, Direction.DESC, "articleTime");
		List<ArticleDraft> articles = draftRepository.findWithPageable(wxId, topTenPageable);
		return articles;
	}

	private Integer convertStringToInt(String s) {
		int a = 0;
		try {
			a = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return a;
	}

	public ArticleDraft getDraftById(String articleDraftId) {
		return draftRepository.findOne(articleDraftId);
	}
}
