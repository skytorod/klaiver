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
import com.test.domain.IndividualVO;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.PageMaker;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;
import com.test.service.CboardService;
import com.test.service.CompanyboardService;
import com.test.service.FollowService;
import com.test.service.IndividualSevice;
import com.test.service.ScrollBoardService;
import com.test.service.SearchService;

@Controller
@RequestMapping("/search/*")
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(CompanyInfoController.class);
	@Inject
	private ScrollBoardService servicescroll;
	@Inject
	private IndividualSevice service2;
	@Inject
	private SearchService service;
	@Inject
	private FollowService service1;
	@Inject
	private CboardService servicecboard;
	@Inject
	private CompanyboardService servicecompany;

	@RequestMapping(value = "/alllist", method = RequestMethod.GET)
	public void alllist(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));

		List<SearchVO> list = service.list(cri);
		model.addAttribute("mycode",servicecboard.searchcode(id));
		
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
		model.addAttribute("list", list);
		
		
		List<IndividualVO> listsolo = service2.listsolo(cri);
		model.addAttribute("listsolo", listsolo);
		List<FollowVO> selectid = service1.getid(id);
		for (int i = 0; i < listsolo.size(); i++) {
			listsolo.get(i).setFlag(false);	
			
			for (int j = 0; j < selectid.size(); j++) {
				if (listsolo.get(i).getEmail().equals(selectid.get(j).getFollowid())) {
					listsolo.get(i).setFlag(true);
					break;
				}
			}

		}
		List<K_productVO> productlist = servicecboard.searchproduct(cri);
		if(productlist.size() !=0){
			for (int i = 0; i < productlist.size(); i++) {
				productlist.get(i).setPinfo(productlist.get(i).getPinfo().replace("<br>", "").trim().substring(0, 100)+".....");
			}
		}
		model.addAttribute("productlist", productlist);
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		/*
		model.addAttribute("followercount",service1.followercount(id));
		model.addAttribute("followingcount",service1.followingcount(id));	
	*/
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void CompanylistGet(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request)
			throws Exception {
		logger.info(cri.toString());

		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		model.addAttribute("listcount",service.listSearchCount(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		List<SearchVO> list = service.listSearchCriteria(cri);
		
		
		model.addAttribute("mycode",servicecboard.searchcode(id));
		
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
		model.addAttribute("list", list);
		/*model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("getid", service1.getid(id));*/
		model.addAttribute("pageMaker", pageMaker);
	}
	@RequestMapping(value = "/sololist", method = RequestMethod.GET)
	public void SololistGet(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request)
			throws Exception {
		logger.info(cri.toString());
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		model.addAttribute("sololistcount",service2.listSearchCount(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service2.listSearchCount(cri));
		List<IndividualVO> sololist = service2.listSearchCriteria(cri);
		model.addAttribute("sololist", sololist);
		List<FollowVO> selectid = service1.getid(id);

		for (int i = 0; i < sololist.size(); i++) {
			sololist.get(i).setFlag(false);
			
			for (int j = 0; j < selectid.size(); j++) {
				if (sololist.get(i).getEmail().equals(selectid.get(j).getFollowid())) {
					sololist.get(i).setFlag(true);
					break;
				}
			}

		}
		/*model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(id));*/
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("getid", service1.getid(id));
		model.addAttribute("pageMaker", pageMaker);
	}
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public void productGet(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request)
			throws Exception {
		logger.info(cri.toString());
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		model.addAttribute("productcount",service.listSearchproductCount(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchproductCount(cri));
		List<K_productVO> productlist = service.listSearchproductCriteria(cri);
		if(productlist.size() !=0){
			for (int i = 0; i < productlist.size(); i++) {
				productlist.get(i).setPinfo(productlist.get(i).getPinfo().replace("<br>", "").trim().substring(0, 100)+".....");
			}
		}
		model.addAttribute("list", productlist);
		
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("getid", service1.getid(id));
		model.addAttribute("pageMaker", pageMaker);
	}
	/*@RequestMapping(value = "/search_id", method = RequestMethod.GET)
	public String search_id(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("userid") String userid,
			K_homeVO hvo, Model model, HttpServletRequest request) throws Exception {
		if(service.search_home_list(userid).get(0).getUserid().isEmpty()){
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
			
		}else{
				return "./search_home?userid="+userid;
		}
		
	
		
	}*/
	@RequestMapping(value = "/search_home_list", method = RequestMethod.GET)
	public void search_home_list(@RequestParam("userid") String userid,Model model,HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		List<CompanyVO> list = servicecboard.getcompanylist(userid);
		model.addAttribute("list",list);
	}
	@RequestMapping(value = "/check_company", method = RequestMethod.GET)
	public String check_company(@RequestParam("code") String code,Model model,HttpServletRequest request) throws Exception {
		
		if(servicecboard.readPage_home(code).getCompanyCode() != null){
			return "redirect:../search/search_home?code="+code;
		}else{
			return "redirect:../cboard/register_home";
		}
	}
	@RequestMapping(value = "/search_home", method = RequestMethod.GET)
	public void search_homeGet(@RequestParam("code") String code,
			K_homeVO hvo, Model model, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("followcode", code);
		
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		K_homeVO vo =  service.search_home(code);
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
		model.addAttribute("followerComcount",service1.followerComcount(code));
		/*
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));;
		model.addAttribute("keyword", cri.getKeyword());*/
		
	}

	@RequestMapping(value = "/search_about", method = RequestMethod.GET)
	public void search_aboutGet(@RequestParam("code") String code,
			K_aboutVO avo, Model model, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("followcode", code);
		
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
		/*
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));*/
	}
	@RequestMapping(value = "/check_product", method = RequestMethod.GET)
	public String check_product(@RequestParam("code") String code,Model model, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		for(int i = 0; i < servicecboard.selectgroup(code).size(); i++) {
			System.out.println(servicecboard.selectgroup(code).get(i).getUserid());
			if(servicecboard.selectgroup(code).get(i).getUserid().equals(id)){
				System.out.println("여기다");
				return "redirect:../cboard/readPage_product?code="+code;
			}else if(servicecboard.selectgroup(code).get(i).getUserid()!=id){
				return "redirect:../search/search_product?code="+code;
			}
		}
		return id;
		
	}
	@RequestMapping(value = "/search_product", method = RequestMethod.GET)
	public void search_productGet(@RequestParam("code") String code,Model model, HttpServletRequest request) throws Exception {
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
		/*model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));*/
	}

	@RequestMapping(value = "/search_contact", method = RequestMethod.GET)
	public void search_contactGet(@RequestParam("code") String code,
			K_contactVO cvo, Model model, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("followcode", code);
		model.addAttribute("followcompanylist", service1.listSearchCriteriaCompany(map));
		model.addAttribute(service.search_home(code));
		model.addAttribute(service.search_about(code));
		model.addAttribute("group",servicecboard.selectgroup(code));
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
		/*
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));*/
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
