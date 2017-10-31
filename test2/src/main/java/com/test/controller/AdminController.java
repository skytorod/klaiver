package com.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.domain.CompanyVO;
import com.test.service.AdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Inject
	private AdminService admin;
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public void member(String userid,String code, HttpServletRequest request, Model model)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("code", code);
		model.addAttribute("mlist", admin.getmember(map));
	}
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public String memberPOSt(CompanyVO vo, Model model)
			throws Exception {
		admin.updatemanager(vo);
		System.out.println(vo.getManager());
		System.out.println(vo.getUserid());
		System.out.println(vo.getCompanyCode());
		return "redirect:../home";
	}

}
