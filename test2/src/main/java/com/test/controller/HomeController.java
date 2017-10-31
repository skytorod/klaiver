package com.test.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.domain.CategoryVO;
import com.test.domain.JoinOne;
import com.test.domain.PageMaker;
import com.test.domain.SearchCriteria;
import com.test.service.AdminService;
import com.test.service.CategoryService;
import com.test.service.CboardService;
import com.test.service.CountryService;
import com.test.service.FollowService;
import com.test.service.JoinService;
import com.test.service.ScrollBoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	private ScrollBoardService servicescroll;
	@Inject
	private CategoryService serviceCate;
	@Inject
	private FollowService serviceFollow;
	@Inject
	private CboardService servicecompany;
	@Inject
	private JoinService service;
	@Inject
	private CategoryService service1;
	@Inject
	private CountryService service2;
	@Inject
	private AdminService admin;
	
	private JoinOne jo;
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(CategoryVO vo, Locale locale, HttpServletRequest request, Model model, HttpSession session)
			throws Exception {
		model.addAttribute("list", service1.categoryget(vo));
		return "namepage";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String namePost(JoinOne jo, Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = jo.getEmail();
		List<JoinOne> one = service.getid(id);
			for (int i = 0; i < one.size(); i++) {
				if (id.equals(one.get(i).getEmail())) {
					response.setCharacterEncoding("EUC-KR");
					PrintWriter writer = response.getWriter();
					writer.println("<script type='text/javascript'>");
					writer.println("alert('이미 가입된 아이디입니다.');");
					writer.println("history.back();");
					writer.println("</script>");
					writer.flush();
					return home(null, null, request, model, null);
				}
			}
			this.jo = jo;
			String password =jo.getPassword();
			String salt =password;
			salt = SHA256Util.generateSalt();
			String newPassword = SHA256Util.getEncrypt(password, salt);
			jo.setPassword(newPassword);
			jo.setSalt(salt);
			return "redirect:./signhap";

	}
	/*
	 * 5page view controller
	 */

	@RequestMapping(value = "/signhap", method = RequestMethod.GET)
	public void signhapGet(Model model) throws Exception {
		
		model.addAttribute("jo", this.jo);

	}

	@RequestMapping(value = "/signhap", method = RequestMethod.POST)
	public String signhapPost(Model model) throws Exception {
		
		 service.insertOne(jo); 
		 

		return "redirect:./";
	}

	@RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginhome(CategoryVO vo, Locale locale, HttpServletRequest request, Model model, HttpSession session)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("list", serviceCate.categoryget(vo));
		model.addAttribute("followlist", serviceFollow.listSearchCriteriasearch(id));
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", serviceFollow.listSearchCriteriaCompany(map));
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("mlist", admin.getlist());*/
		return "/home";
	}

	@RequestMapping(value = "/home/{mc_number}", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryVO> categoryget(@PathVariable("mc_number") Integer mc_number) throws Exception {

		return serviceCate.smallcate(mc_number);
	}
	@RequestMapping(value = "/companylist", method = RequestMethod.GET)
	public void companylist(@ModelAttribute("cri") SearchCriteria cri,Model model) throws Exception{
		
		if(cri.getKeyword() == null){
			model.addAttribute("companylist",servicecompany.selectcompany());	
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(servicecompany.selectcompanycount());
			model.addAttribute("pageMaker", pageMaker);
		}else{
			model.addAttribute("companylist",servicecompany.selectKeywordcompany(cri));	
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(servicecompany.selectKeywordcompanycount(cri));
			model.addAttribute("pageMaker", pageMaker);
		}
	}

}
