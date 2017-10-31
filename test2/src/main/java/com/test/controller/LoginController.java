package com.test.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.DTO.LoginDTO;
import com.test.domain.LoginVO;
import com.test.service.LoginService;

@Controller
@RequestMapping("/klogin/*")
public class LoginController {
	

	@Inject
	private LoginService service;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGet(@ModelAttribute("dto") LoginDTO dto){
		logger.info("get~");
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody String loginPost(@ModelAttribute("dto") LoginDTO dto, HttpServletRequest request)throws Exception{
		
		String password =dto.getPassword();
		String salt =password;
		salt = SHA256Util.generateSalt();
		String newPassword = SHA256Util.getEncrypt(password, salt);
		dto.setPassword(newPassword);
		
		try {
			if(service.login(dto) != null){
				request.getSession().setAttribute("login", dto.getEmail());
				request.getSession().setMaxInactiveInterval(86400);
				return "success";
			} else {
				return "fault";
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:./";
		
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model)throws Exception{
		
		LoginVO vo = service.login(dto);
		
		if(vo == null){
			return;
		}
		model.addAttribute("loginVO", vo);
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) throws Exception {
		session.removeAttribute("login");

		return "redirect:../";
	}
	
}

