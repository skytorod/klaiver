package com.test.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.FollowVO;
import com.test.domain.ScrollBoardVO;
import com.test.service.FollowService;
import com.test.service.SearchService;

@Controller
@RequestMapping("/follow/*")
public class FollowController {
	@Inject
	private FollowService service;
	
	@RequestMapping(value = "/insertFollow", method = RequestMethod.GET)
	public String insertFollow(String userid, String followid, ScrollBoardVO vo, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service.getid(id);
		String referer = request.getHeader("Referer");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("followid", followid);
		if (id != null) {
			for (int i = 0; i < selectid.size(); i++) {
				if (selectid.get(i).getFollowid().equals(followid)) {
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script type='text/javascript'>");
					writer.println("alert('팔로우중입니다.');");
					writer.println("history.back();");
					writer.println("</script>");
					writer.flush();
					return referer;
				}
			}
			service.insertfollow(map);
			return "redirect:" + referer;
		} else {
			return "redirect:../klogin/login";
		}

	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String remove(String userid, String followid,HttpServletRequest request, RedirectAttributes rttr)throws Exception {
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("userid", userid);
		map1.put("followid", followid);
		String referer = request.getHeader("Referer");
		service.delete(map1);
		return "redirect:" + referer;
	}
/*
	@RequestMapping(value = "/insertComFollow", method = RequestMethod.GET)
	public String insertComFollow(String userid, String followcode, ScrollBoardVO vo, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		String referer = request.getHeader("Referer");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("followcode", followcode);
		System.out.println(userid+":"+followcode);
		List<FollowVO> selectid = service.getComid(map);
		
		if (id != null) {
			for (int i = 0; i < selectid.size(); i++) {
				if (selectid.get(i).getFollowcode().equals(followcode)) {
					System.out.println(selectid.get(i).getFollowcode()+":"+followcode);
					response.setCharacterEncoding("EUC-KR");
					PrintWriter writer = response.getWriter();
					writer.println("<script type='text/javascript'>");
					writer.println("alert('팔로우 중 입니다.');");
					writer.println("history.back();");
					writer.println("</script>");
					writer.flush();
					return referer;
				}
			}
			service.insertComfollow(map);
			return "redirect:" + referer;
		} else {
			return "redirect:../klogin/login";
		}

	}
	@RequestMapping(value = "/deleteCom", method = RequestMethod.GET)
	public String removeCom(String userid, String followcode,HttpServletRequest request, RedirectAttributes rttr)throws Exception {
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("userid", userid);
		map1.put("followcode", followcode);
		String referer = request.getHeader("Referer");
		service.deleteCom(map1);
		return "redirect:" + referer;
	}*/
}
