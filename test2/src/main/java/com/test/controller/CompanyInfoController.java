package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.domain.CategoryVO;
import com.test.domain.JoinOne;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_productVO;
import com.test.domain.ScrollBoardVO;
import com.test.service.CategoryService;
import com.test.service.CboardService;
import com.test.service.FollowService;

@Controller
@RequestMapping("/cboard/*")
public class CompanyInfoController {
	@Inject
	private CboardService service;
	@Inject
	private CategoryService serviceCate;
	@Inject
	private FollowService serviceFollow;

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public void newsGET(@RequestParam("userid") String userid, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist",serviceFollow.listSearchCriteriasearch(id));
		model.addAttribute("IndividualVO", service.memberinfo(userid));
		model.addAttribute("sessionidName", service.serssionidName(id));
		if(service.readPage_about(userid) !=null){
			model.addAttribute(service.readPage_about(userid));
		}
		if(service.readPage_contact(userid) !=null){
			model.addAttribute(service.readPage_contact(userid));
		}
		
		
		/*
		 *  Map<String, String> map = new
		 * HashMap<String, String>(); map.put("userid", id);
		 * model.addAttribute("followcompanylist",
		 * serviceFollow.listSearchCriteriaCompany(map));
		 * model.addAttribute(service.readPage_about(code));
		 * model.addAttribute(service.readPage_home(code));
		 * model.addAttribute("followerComcount",serviceFollow.followerComcount(code)
		 * );
		 * 
		 * 
		 * model.addAttribute("code", code); List<CompanyBoardVO> scrolllist =
		 * servicecompany.listSearchCriteria(code);
		 * model.addAttribute("scrolllist", scrolllist); String manager =
		 * servicecompany.searchmanager(code); model.addAttribute("manager",
		 * manager);
		 * 
		 * List<ComReplyVO> replylist = new ArrayList<>(); for (int i = 0; i <
		 * scrolllist.size(); i++) { int cbid = scrolllist.get(i).getCbid();
		 * replylist.addAll(servicecompany.comreplylist(cbid)); }
		 * model.addAttribute("replylist", replylist);
		 */

	}

	@RequestMapping(value = "/check_about", method = RequestMethod.GET)
	public String check_about(@RequestParam("userid") String userid, HttpServletRequest request, Model model)
			throws Exception {
		if (service.readPage_about(userid) != null) {
			String id = (String) request.getSession().getAttribute("login");
			return "redirect:./readPage_about?userid=" + id;
		} else {
			return "redirect:./register_about";
		}

	}

	@RequestMapping(value = "/readPage_about", method = RequestMethod.GET)
	public void Read_aboutGet(@RequestParam("userid") String userid, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist",serviceFollow.listSearchCriteriasearch(id));
		model.addAttribute("IndividualVO", service.memberinfo(userid));
		model.addAttribute("sessionidName", service.serssionidName(id));
		K_aboutVO avo = service.readPage_about(userid);
		if (avo.getAboutUs() != null) {
			avo.setAboutUs(avo.getAboutUs().replace("\r\n", "<br>").trim());
		}
		model.addAttribute(avo);
		if(service.readPage_about(userid) !=null){
			model.addAttribute(service.readPage_about(userid));
		}
		if(service.readPage_contact(userid) !=null){
			model.addAttribute(service.readPage_contact(userid));
		}

	}

	@RequestMapping(value = "/readPage_product", method = RequestMethod.GET)
	public void Read_productGet(@RequestParam("userid") String userid, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist",serviceFollow.listSearchCriteriasearch(id));
		model.addAttribute("IndividualVO", service.memberinfo(userid));
		model.addAttribute("sessionidName", service.serssionidName(id));
		List<K_productVO> pvo = service.readPage_product(userid);

		model.addAttribute("list", pvo);
		if(service.readPage_about(userid) !=null){
			model.addAttribute(service.readPage_about(userid));
		}
		if(service.readPage_contact(userid) !=null){
			model.addAttribute(service.readPage_contact(userid));
		}
	}

	@RequestMapping(value = "/check_contact", method = RequestMethod.GET)
	public String check_contact(@RequestParam("userid") String userid, HttpServletRequest request, Model model)
			throws Exception {
		if (service.readPage_contact(userid) != null) {
			String id = (String) request.getSession().getAttribute("login");
			return "redirect:./readPage_contact?userid=" + id;
		} else {
			return "redirect:./register_contact";
		}

	}

	@RequestMapping(value = "/readPage_contact", method = RequestMethod.GET)
	public void Read_contactGet(@RequestParam("userid") String userid, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist",serviceFollow.listSearchCriteriasearch(id));
		model.addAttribute("IndividualVO", service.memberinfo(userid));
		model.addAttribute("sessionidName", service.serssionidName(id));
		if(service.readPage_about(userid) !=null){
			model.addAttribute(service.readPage_about(userid));
		}
		if(service.readPage_contact(userid) !=null){
			model.addAttribute(service.readPage_contact(userid));
		}
	}

	@RequestMapping(value = "/register_about", method = RequestMethod.GET)
	public void CboardaboutGet(CategoryVO vo, Model model, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("list", serviceCate.categoryget(vo));
	}

	@RequestMapping(value = "/register_about/{mc_number}", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryVO> categoryget(@PathVariable("mc_number") Integer mc_number) throws Exception {

		return serviceCate.smallcate(mc_number);
	}

	@RequestMapping(value = "/register_about", method = RequestMethod.POST)
	public String CboardaboutPOST(HttpServletRequest request, K_aboutVO avo, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		avo.setAboutUs(avo.getAboutUs().replace("\r\n", "<br>").trim());
		service.insert_about(avo);

		return "redirect:../cboard/readPage_about?userid=" + id;
	}

	@RequestMapping(value = "/register_product", method = RequestMethod.GET)
	public void CboarproductGet(@RequestParam("userid") String userid, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		List<K_productVO> pvo = service.readPage_product(userid);

		model.addAttribute("list", pvo);
	}

	@RequestMapping(value = "/register_product", method = RequestMethod.POST)
	public void CboardproductPOST(@RequestParam("userid") String userid, String companyCode, String product,
			String product_en, String usage, String pcod, String pinfo, HttpServletRequest request, Model model,
			MultipartFile test1, String test2, String test3, String test4)
			throws IllegalStateException, IOException, Exception {
		K_productVO vo = new K_productVO();
		vo.setUserid(userid);
		vo.setProduct(product);
		vo.setProduct_en(product_en);
		vo.setUsage(usage);
		vo.setPcod(pcod);
		if (pinfo != null) {
			vo.setPinfo(pinfo.replace("\r\n", "<br>").trim());
		} else {
			vo.setPinfo(pinfo);
		}
		File path = new File("C:/Users/GyeoungJae/git/klaiver/test2/src/main/webapp/resources/img");
		if (!path.exists()) {
			path.mkdirs();
			path = new File("C:/Users/GyeoungJae/git/klaiver/test2/src/main/webapp/resources/img");
		}
		if (test1 != null) {
			String saveName = UUID.randomUUID().toString() + "_" + test1.getOriginalFilename().replaceAll(" ", "");
			File saveFile = new File(path, saveName);
			FileCopyUtils.copy(test1.getBytes(), saveFile);
			vo.setImage(saveName);
		} else {
			vo.setImage("noimg.jpg");
		}
		service.insert_product(vo);
	}

	@RequestMapping(value = "/register_contact", method = RequestMethod.GET)
	public void CboarcontactGet(Model model, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
	}

	@RequestMapping(value = "/register_contact", method = RequestMethod.POST)
	public String CboardcontactPOST(HttpServletRequest request, K_contactVO cvo, Model model) throws Exception {
		service.insert_contact(cvo);
		String id = (String) request.getSession().getAttribute("login");
		return "redirect:../cboard/readPage_contact?userid=" + id;
	}

	@RequestMapping(value = "/update_home", method = RequestMethod.GET)
	public void Update_homeGEt(@RequestParam("userid") String userid, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist",serviceFollow.listSearchCriteriasearch(id));
		List<JoinOne> ivo =service.memberinfo(userid);
		if (ivo.get(0).getIntroduce() != null) {
			ivo.get(0).setIntroduce(ivo.get(0).getIntroduce().replaceAll("<br>", "\r\n"));
		}
		model.addAttribute("IndividualVO",ivo);
		model.addAttribute("sessionidName", service.serssionidName(id));
		if(service.readPage_about(userid) !=null){
			model.addAttribute(service.readPage_about(userid));
		}
		if(service.readPage_contact(userid) !=null){
			model.addAttribute(service.readPage_contact(userid));
		}
	}

	@RequestMapping(value = "/update_home", method = RequestMethod.POST)
	public void update_homePost(HttpServletRequest request, JoinOne vo, Model model, Locale locale,
			MultipartFile testu1, String testu2, String testu3, String testu4)
			throws IllegalStateException, IOException, Exception {
		String id = (String) request.getSession().getAttribute("login");
		if (testu1 != null) {
			String fakeimg = vo.getFakeimg();
			System.out.println(fakeimg);
			File path_update = new File("C:/Users/GyeoungJae/git/klaiver/test2/src/main/webapp/resources/img");

			if (!path_update.exists()) {
				path_update.mkdirs();
				System.out.println("cutewebi 디렉토리를 생성했습니다.");
				path_update = new File("C:/Users/GyeoungJae/git/klaiver/test2/src/main/webapp/resources/img");
			}
			File path = new File("C:/Users/GyeoungJae/git/klaiver/test2/src/main/webapp/resources/img/" + fakeimg);
			if (fakeimg != "noimg.jsp") {
				if (path.exists() == true) {
					path.delete();
				}

			}
			String saveNameupdate = UUID.randomUUID().toString() + "_"
					+ testu1.getOriginalFilename().replaceAll(" ", "");
			File saveFileudpate = new File(path_update, saveNameupdate);
			FileCopyUtils.copy(testu1.getBytes(), saveFileudpate);

			vo.setProfimg(saveNameupdate);
		} else {
			vo.setProfimg(vo.getFakeimg());
		}
		vo.setIntroduce(vo.getIntroduce().replace("\r\n", "<br>").trim());
		service.homeupdate(vo);
	}
	
	@RequestMapping(value = "/update_about", method = RequestMethod.GET)
	public void Update_aboutGEt(@RequestParam("aid") int aid, @RequestParam("userid") String userid, CategoryVO vo,
			HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist",serviceFollow.listSearchCriteriasearch(id));
		model.addAttribute("IndividualVO", service.memberinfo(userid));
		model.addAttribute("sessionidName", service.serssionidName(id));
		model.addAttribute("list", serviceCate.categoryget(vo));
		model.addAttribute(service.readPage_about(userid));
		model.addAttribute(service.readPage_contact(userid));
		K_aboutVO avo = service.readPage_about(userid);
		avo.setAboutUs(avo.getAboutUs().replaceAll("<br>", "\r\n"));
		model.addAttribute(avo);
	}

	@RequestMapping(value = "/update_about", method = RequestMethod.POST)
	public String Update_aboutPOST(HttpServletRequest request,K_aboutVO avo, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		avo.setAboutUs(avo.getAboutUs().replace("\r\n", "<br>").trim());
		service.update_about(avo);
		return "redirect:../cboard/check_about?userid=" + id;
	}
	@RequestMapping(value = "/update_product", method = RequestMethod.GET)
	public void Update_productGEt(@RequestParam("pid") int pid, HttpServletRequest request, Model model, Locale locale)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		K_productVO pvo = service.updateread_product(pid);
		if (pvo.getPinfo() != null) {
			pvo.setPinfo(pvo.getPinfo().replace("<br>", "\r\n").trim());
		}

		model.addAttribute("k_product", pvo);
	}

	@RequestMapping(value = "/update_product", method = RequestMethod.POST)
	public void Update_productPOST(K_productVO pvo, Model model, Locale locale, MultipartFile test1, String test2,
			String test3, String test4) throws IllegalStateException, IOException, Exception {

		if (pvo.getPinfo() != null) {
			pvo.setPinfo(pvo.getPinfo().replace("\r\n", "<br>").trim());
		}

		File path = new File("C:/Users/GyeoungJae/git/klaiver/test2/src/main/webapp/resources/img");
		if (!path.exists()) {
			path.mkdirs();
			path = new File("C:/Users/GyeoungJae/git/klaiver/test2/src/main/webapp/resources/img");
		}
		if (test1 != null) {
			String fakeimg = pvo.getFakeimg();
			File path1 = new File("C:/Users/GyeoungJae/git/klaiver/test2/src/main/webapp/resources/img/" + fakeimg);
			if (path1.exists() == true) {
				path1.delete();
			}
			String saveName = UUID.randomUUID().toString() + "_" + test1.getOriginalFilename().replaceAll(" ", "");
			File saveFile = new File(path, saveName);
			FileCopyUtils.copy(test1.getBytes(), saveFile);
			pvo.setImage(saveName);
		} else {
			pvo.setImage(pvo.getFakeimg());
		}
		service.update_product(pvo);
	}
	@RequestMapping(value = "/productremove", method = RequestMethod.POST)
	public void remove(@ModelAttribute("pid") int pid,String fakeimg, HttpServletRequest request, Model model, ScrollBoardVO vo)
			throws Exception {
		File path = new File("C:/Users/GyeoungJae/git/klaiver/test2/src/main/webapp/resources/img/" + fakeimg);
		service.remove(pid);
		if (fakeimg != "noimg.jsp") {
			if (path.exists() == true) {
				path.delete();
			}

		}
	}
	@RequestMapping(value = "/update_contact", method = RequestMethod.GET)
	public void Update_contactGEt(@RequestParam("cid") int cid, @RequestParam("userid") String userid, K_contactVO cvo,
			HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist",serviceFollow.listSearchCriteriasearch(id));
		model.addAttribute("IndividualVO", service.memberinfo(userid));
		model.addAttribute("sessionidName", service.serssionidName(id));
		model.addAttribute(service.readPage_about(userid));
		model.addAttribute(service.readPage_contact(userid));
	}

	@RequestMapping(value = "/update_contact", method = RequestMethod.POST)
	public String Update_contactPOST(HttpServletRequest request,K_contactVO cvo, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		service.update_contact(cvo);
		return "redirect:../cboard/check_contact?userid=" + id;
	}
}
