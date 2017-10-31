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
	private FollowService service1;
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
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		List<SearchVO> list = service.listSearchCriteria(cri);
		model.addAttribute("list", list);
		model.addAttribute("listcount",service.listSearchCount(cri));
		List<FollowVO> selectComid = service1.getComid(map);
		List<CompanyVO> mycomCode = servicecboard.searchcode(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			list.get(i).setMeflag(false);
			for (int j = 0; j < selectComid.size(); j++) {
				if (list.get(i).getCompanyCode().equals(selectComid.get(j).getFollowcode())) {
					list.get(i).setFlag(true);
					break;
				}
			}
			for (int j = 0; j < mycomCode.size(); j++) {
				if (list.get(i).getCompanyCode().equals(mycomCode.get(j).getCompanyCode())) {
					list.get(i).setMeflag(true);
					break;
				}
			}

		}

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
				pageMaker.setTotalCount(service.listSearchCount(cri));
		/*	model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(id));*/
		String keyword1 = cri.getKeyword();
		model.addAttribute("keyword1", keyword1);
		model.addAttribute("pageMaker", pageMaker);
/*		model.addAttribute("followercount",service1.followercount(id));
		model.addAttribute("followingcount",service1.followingcount(id));
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));*/
	}

	@RequestMapping(value = "/search_home", method = RequestMethod.GET)
	public void search_homeGet(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("code") String code,
			HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("followcode", code);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		K_homeVO vo = service.search_home(code);
		model.addAttribute(service.search_about(code));
		model.addAttribute(vo);
		List<FollowVO> selectComid = service1.getComid(map);

		vo.setFlag(false);
		for (int i = 0; i < selectComid.size(); i++) {
			if (selectComid.get(i).getFollowcode().equals(vo.getCompanyCode())) {
				vo.setFlag(true);
				break;
			}
		}
	/*	
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		
		model.addAttribute("followingComcount",service1.followingComcount(userid));*/
		model.addAttribute("keyword", cri.getKeyword());
		model.addAttribute("followerComcount",service1.followerComcount(code));
	}

	@RequestMapping(value = "/search_about", method = RequestMethod.GET)
	public void search_aboutGet(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("code") String code,
			HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("followcode", code);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		K_aboutVO vo =service.search_about(code);
		model.addAttribute(vo);
		model.addAttribute(service.search_home(code));
		List<FollowVO> selectComid = service1.getComid(map);

		vo.setFlag(false);
		for (int i = 0; i < selectComid.size(); i++) {
			if (selectComid.get(i).getFollowcode().equals(vo.getCompanyCode())) {
				vo.setFlag(true);
				break;
			}
		}
		model.addAttribute("followerComcount",service1.followerComcount(code));
/*		
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("followerComcount",service1.followerComcount(userid));
		model.addAttribute("followingComcount",service1.followingComcount(userid));*/
	}

	@RequestMapping(value = "/search_product", method = RequestMethod.GET)
	public void search_productGet(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("code") String code,
			HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("followcode", code);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		model.addAttribute(service.search_home(code));
		K_aboutVO vo =service.search_about(code);
		model.addAttribute(vo);
		List<K_productVO> pvo =service.search_product(code);
		model.addAttribute("list",pvo);
		List<FollowVO> selectComid = service1.getComid(map);

		vo.setFlag(false);
		for (int i = 0; i < selectComid.size(); i++) {
			if (selectComid.get(i).getFollowcode().equals(vo.getCompanyCode())) {
				vo.setFlag(true);
				break;
			}
		}
		model.addAttribute("followerComcount",service1.followerComcount(code));
/*		
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("followerComcount",service1.followerComcount(userid));
		model.addAttribute("followingComcount",service1.followingComcount(userid));*/
	}

	@RequestMapping(value = "/search_contact", method = RequestMethod.GET)
	public void search_contactGet(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("code") String code,
			HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("followcode", code);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		model.addAttribute(service.search_home(code));
		model.addAttribute(service.search_about(code));
		K_contactVO vo =service.search_contact(code);
		model.addAttribute(vo);
		List<FollowVO> selectComid = service1.getComid(map);

		vo.setFlag(false);
		for (int i = 0; i < selectComid.size(); i++) {
			if (selectComid.get(i).getFollowcode().equals(vo.getCompanyCode())) {
				vo.setFlag(true);
				break;
			}
		}
		model.addAttribute("followerComcount",service1.followerComcount(code));
		model.addAttribute("group",servicecboard.selectgroup(code));
/*		
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("followerComcount",service1.followerComcount(userid));
		model.addAttribute("followingComcount",service1.followingComcount(userid));*/
	}
	@RequestMapping(value = "/searchNews", method = RequestMethod.GET)
	public void newsGET(@RequestParam("code") String code, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		model.addAttribute(service.search_home(code));
		K_aboutVO vo =service.search_about(code);
		model.addAttribute(vo);
		List<FollowVO> selectComid = service1.getComid(map);

		vo.setFlag(false);
		for (int i = 0; i < selectComid.size(); i++) {
			if (selectComid.get(i).getFollowcode().equals(vo.getCompanyCode())) {
				vo.setFlag(true);
				break;
			}
		}
		model.addAttribute("followerComcount",service1.followerComcount(code));
		model.addAttribute("IndividualVO", service2.Individual(id));
		model.addAttribute("sessionidName", servicescroll.serssionidName(id));
		model.addAttribute("code", code);
		List<CompanyBoardVO> scrolllist = servicecompany.listSearchCriteria(code);
		model.addAttribute("scrolllist", scrolllist);
		String manager = servicecompany.searchmanager(code);
		model.addAttribute("manager", manager);
		List<ComReplyVO> replylist = new ArrayList<>();
		for (int i = 0; i < scrolllist.size(); i++) {
				int cbid = scrolllist.get(i).getCbid();
				replylist.addAll(servicecompany.comreplylist(cbid));
		}
		model.addAttribute("replylist", replylist);
		
	}
}
