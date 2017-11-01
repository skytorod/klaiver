package com.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.ComReplyVO;
import com.test.domain.CompanyBoardVO;
import com.test.domain.CompanyVO;
import com.test.domain.FollowVO;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.PageMaker;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;
import com.test.service.CategoryService;
import com.test.service.CboardService;
import com.test.service.CompanyboardService;
import com.test.service.FollowService;
import com.test.service.IndividualSevice;
import com.test.service.ScrollBoardService;

@Controller
@RequestMapping("/category/*")
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CompanyInfoController.class);
	@Inject
	private ScrollBoardService servicescroll;
	@Inject
	private CategoryService service;
	@Inject
	private FollowService serviceFollow;
	@Inject
	private CboardService servicecboard;
	@Inject
	private IndividualSevice service2;
	@Inject
	private CompanyboardService servicecompany;
	@RequestMapping(value = "/catelist", method = RequestMethod.GET)
	public void CategoryGet(@ModelAttribute("cri") SearchCriteria cri, FollowVO vo, HttpServletRequest request,
			Model model) throws Exception {
		logger.info(cri.toString());
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", serviceFollow.listSearchCriteriasearch(id));
		
		List<SearchVO> list = service.listSearchCriteria(cri);
		model.addAttribute("list", list);
		model.addAttribute("listcount",service.listSearchCount(cri));
		List<FollowVO> selectid = serviceFollow.getid(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getEmail().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
				pageMaker.setTotalCount(service.listSearchCount(cri));
		String keyword1 = cri.getKeyword();
		model.addAttribute("keyword1", keyword1);
		model.addAttribute("pageMaker", pageMaker);
	}

}
