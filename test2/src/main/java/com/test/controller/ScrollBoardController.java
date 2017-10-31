/*package com.test.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.domain.ScrollBoardVO;
import com.test.domain.SearchCriteria;
import com.test.service.FollowService;
import com.test.service.ScrollBoardService;

@Controller                                                                                                                    
@RequestMapping("/scrollboard/*")
public class ScrollBoardController {
	private static final Logger logger = LoggerFactory.getLogger(CompanyInfoController.class);
	
	@Inject
	private ScrollBoardService service;
	@Inject
	private FollowService service1;
	
	
	@RequestMapping(value="/insert", method = RequestMethod.GET)
	public void Sboardget(@ModelAttribute("cri") SearchCriteria cri, ScrollBoardVO vo,HttpServletRequest request,   Model model)throws Exception{
		String id=(String)request.getSession().getAttribute("login");
		logger.info("get success");
		model.addAttribute("getid",service.categoryget(vo));
		model.addAttribute("followlist", service1.listSearchCriteriasearch(id));
		model.addAttribute("followercount",service1.followercount(id));
		model.addAttribute("followingcount",service1.followingcount(id));
		if(service.listSearchCriteria(cri).size()!=0){
			model.addAttribute("list", service.listSearchCriteria(cri));
		}else{
			model.addAttribute("list", service.zerolistSearchCriteria(cri));
		}
		
	}
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String SboardPOST(ScrollBoardVO vo,HttpServletRequest request)throws Exception{
		service.insert(vo);
		String referer = request.getHeader("Referer");
		logger.info("post successs");
		return "redirect:" + referer;
	}@RequestMapping(value="/infiniteScrollDown", method = RequestMethod.POST)
	public @ResponseBody List<ScrollBoardVO> Scroll(int sbid,String sessionid)throws Exception{
		String sbidStart = Integer.toString(sbid -1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sbid", sbidStart);
		map.put("sessionid", sessionid);
		if(service.infiniteScrillDown(map).size() !=0){
			return service.infiniteScrillDown(map);
		}else{
			return service.zeroinfiniteScrillDown(map);
		}
		
	}
	
}
*/