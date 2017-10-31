package com.test.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.domain.IndividualVO;
import com.test.domain.K_productVO;
import com.test.domain.PageMaker;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;
import com.test.service.CboardService;
import com.test.service.IndividualSevice;
import com.test.service.SearchService;

@Controller
@RequestMapping("/unsearch/*")
public class UnSearchController {

	@Inject
	private IndividualSevice service2;
	@Inject
	private SearchService service;
	@Inject
	private CboardService servicecboard;

	@RequestMapping(value = "/alllist", method = RequestMethod.GET)
	public void CategoryGet(@ModelAttribute("cri") SearchCriteria cri, HttpServletRequest request,
			Model model) throws Exception {
		List<SearchVO> list = service.list(cri);
		List<IndividualVO> listsolo = service2.listsolo(cri);
		model.addAttribute("list", list);
		model.addAttribute("listsolo", listsolo);
		List<K_productVO> productlist = servicecboard.searchproduct(cri);
		if(productlist.size() !=0){
			for (int i = 0; i < productlist.size(); i++) {
				productlist.get(i).setPinfo(productlist.get(i).getPinfo().replace("<br>", "").trim().substring(0, 100)+".....");
			}
		}
		model.addAttribute("productlist", productlist);
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void CompanylistGet(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request)
			throws Exception {
		model.addAttribute("listcount",service.listSearchCount(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		List<SearchVO> list = service.listSearchCriteria(cri);
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
	}
	@RequestMapping(value = "/sololist", method = RequestMethod.GET)
	public void SololistGet(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request)
			throws Exception {
		model.addAttribute("sololistcount",service2.listSearchCount(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service2.listSearchCount(cri));
		List<IndividualVO> sololist = service2.listSearchCriteria(cri);
		model.addAttribute("sololist", sololist);
		model.addAttribute("pageMaker", pageMaker);
	}
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public void productGet(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
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
		model.addAttribute("pageMaker", pageMaker);
	}
}
