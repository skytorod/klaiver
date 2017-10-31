/*package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.test.domain.ComReplyVO;
import com.test.domain.CompanyBoardVO;
import com.test.domain.CountryVO;
import com.test.domain.IndividualVO;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.PageMaker;
import com.test.domain.ReplyVO;
import com.test.domain.ScrollBoardVO;
import com.test.domain.SearchCriteria;
import com.test.service.CategoryService;
import com.test.service.CboardService;
import com.test.service.CompanyboardService;
import com.test.service.CountryService;
import com.test.service.FollowService;
import com.test.service.IndividualSevice;
import com.test.service.ScrollBoardService;

@Controller
@RequestMapping("/cboard/*")
public class CompanyInfoController_bak {
	private static final Logger logger = LoggerFactory.getLogger(CompanyInfoController_bak.class);
	@Inject
	private ScrollBoardService servicescroll;
	@Inject
	private CboardService service;
	@Inject
	private CategoryService service1;
	@Inject
	private FollowService service2;
	@Inject
	private CountryService service3;
	@Inject
	private IndividualSevice serviceindi;
	@Inject
	private CompanyboardService servicecompany;

	private K_homeVO hvo;
	private K_aboutVO avo;
	private K_productVO pvo;
	private K_contactVO cvo;
	private IndividualVO invo;
	/// �쉶�궗�젙蹂� insertc////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/register_home", method = RequestMethod.GET)
	public void CboardhomGET(CountryVO vo, Model model, Locale locale, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("clist", service3.countryget(vo));
		logger.info("input get");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("serverTime", formattedDate);
	}

	@RequestMapping(value = "/registerhome/{country}", method = RequestMethod.GET)
	@ResponseBody
	public List<CountryVO> cityGet(@PathVariable("country") String country) throws Exception {

		return service3.cityGet(country);
	}

	@RequestMapping(value = "/register_home", method = RequestMethod.POST)
	public String CboardhomPOST(K_homeVO hvo, Model model, Locale locale, MultipartFile test1, MultipartFile test1_1,
			String test2, String test3, String test4) throws IllegalStateException, IOException, Exception {
		logger.info("post successs");
		File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
		if (!path.exists()) {
			path.mkdirs();
			path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
		}
		if (test1 != null) {
			String saveName = UUID.randomUUID().toString() + "_" + test1.getOriginalFilename().replaceAll(" ", "");
			File saveFile = new File(path, saveName);
			FileCopyUtils.copy(test1.getBytes(), saveFile);
			hvo.setCompanyimg(saveName);
		} else {
			hvo.setCompanyimg("noimg.jpg");
		}
		if (test1_1 != null) {
			String saveName1 = UUID.randomUUID().toString() + "_" + test1_1.getOriginalFilename().replaceAll(" ", "");
			File saveFile1 = new File(path, saveName1);
			FileCopyUtils.copy(test1_1.getBytes(), saveFile1);
			hvo.setPimage(saveName1);
		} else {
			hvo.setPimage("noimg.jpg");
		}

		logger.info("input post");
		this.hvo = hvo;

		return "redirect:../cboard/register_about";
	}

	@RequestMapping(value = "/register_about", method = RequestMethod.GET)
	public void CboardaboutGet(CategoryVO vo, Model model, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("list", service1.categoryget(vo));
		logger.info("input get");
	}

	@RequestMapping(value = "/register_about/{mc_number}", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryVO> categoryget(@PathVariable("mc_number") Integer mc_number) throws Exception {

		return service1.smallcate(mc_number);
	}

	@RequestMapping(value = "/register_about", method = RequestMethod.POST)
	public String CboardaboutPOST(K_homeVO hvo, K_aboutVO avo, K_productVO pvo, K_contactVO cvo, Model model)
			throws Exception {
		logger.info("input post");
		 avo.setAboutUs(avo.getAboutUs().replace("\r\n","<br>").trim()); 
		this.avo = avo;

		return "redirect:../cboard/register_contact";
	}

	@RequestMapping(value = "/register_product", method = RequestMethod.GET)
	public void CboarproductGet(@RequestParam("code") String code, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute(service.readPage_home(code));
		model.addAttribute(service.readPage_about(code));
		List<K_productVO> pvo = service.readPage_product(code);

		model.addAttribute("list", pvo);
	}

	@RequestMapping(value = "/register_product", method = RequestMethod.POST)
	public void CboardproductPOST(@RequestParam("code") String code, String companyCode, String product,String product_en, String usage,
			String pcod, String pinfo, HttpServletRequest request, Model model, MultipartFile test1, String test2,
			String test3, String test4) throws IllegalStateException, IOException, Exception {
		K_productVO vo = new K_productVO();
		vo.setCompanyCode(companyCode);
		vo.setProduct(product);
		vo.setProduct_en(product_en);
		vo.setUsage(usage);
		vo.setPcod(pcod);
		if (pinfo != null) {
			vo.setPinfo(pinfo.replace("\r\n", "<br>").trim());
		} else {
			vo.setPinfo(pinfo);
		}
		File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
		if (!path.exists()) {
			path.mkdirs();
			path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
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
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		logger.info("get");
	}

	@RequestMapping(value = "/register_contact", method = RequestMethod.POST)
	public String CboardcontactPOST(K_homeVO hvo, K_aboutVO avo, K_contactVO cvo, Model model) throws Exception {
		logger.info("input post");
		this.cvo = cvo;
		return "redirect:../cboard/register_hap";
	}

	@RequestMapping(value = "/register_hap", method = RequestMethod.GET)
	public void hapGet(Model model, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyMMddhhmmss");
		Date date = new Date();
		String code = dateFormat1.format(date).substring(0, 2) + id.substring(0, 1)
				+ dateFormat1.format(date).substring(2, 4) + id.substring(1, 2)
				+ dateFormat1.format(date).substring(4, 6) + id.substring(2, 3)
				+ dateFormat1.format(date).substring(6, 12);
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		String companycode = "";
		for (int i = 0; i < code.length(); i++) {
			a.add(code.substring(i, i + 1).toString());
		}
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).equals("1")) {
				b.add(a.get(i).replace("1", "O"));
			} else if (a.get(i).equals("2")) {
				b.add(a.get(i).replace("2", "T"));
			} else if (a.get(i).equals("3")) {
				b.add(a.get(i).replace("3", "H"));
			} else if (a.get(i).equals("4")) {
				b.add(a.get(i).replace("4", "F"));
			} else if (a.get(i).equals("5")) {
				b.add(a.get(i).replace("5", "I"));
			} else if (a.get(i).equals("6")) {
				b.add(a.get(i).replace("6", "S"));
			} else if (a.get(i).equals("7")) {
				b.add(a.get(i).replace("7", "V"));
			} else if (a.get(i).equals("8")) {
				b.add(a.get(i).replace("8", "E"));
			} else if (a.get(i).equals("9")) {
				b.add(a.get(i).replace("9", "N"));
			} else if (a.get(i).equals("0")) {
				b.add(a.get(i).replace("0", "Z"));
			} else {
				b.add(a.get(i));
			}
		}
		for (int i = 0; i < b.size(); i++) {
			companycode += b.get(i);
		}
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		logger.info("get");

		model.addAttribute("getcodeid", service.getcodeid(id));
		this.invo = service.getcodeid(id);
		this.invo.setCompanyCode(companycode);
		model.addAttribute("K_homeVO", this.hvo);
		model.addAttribute("K_aboutVO", this.avo);
		model.addAttribute("K_contactVO", this.cvo);
		this.hvo.setCompanyCode(companycode);
		this.avo.setCompanyCode(companycode);
		this.cvo.setCompanyCode(companycode);
	}

	@RequestMapping(value = "/register_hap", method = RequestMethod.POST)
	public String hapPOST(Model model, HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		logger.info("input post");
		Map<String, String> map = new HashMap<String, String>();
		map.put("comname_kr", avo.getCompname_kr());
		map.put("comname_en", avo.getCompname_en());
		map.put("comCode", hvo.getCompanyCode());
		map.put("userid", id);
		map.put("companyCode", invo.getCompanyCode());
		map.put("first", invo.getFirst());
		map.put("last", invo.getLast());
		map.put("title", invo.getTitle());
		map.put("repname", avo.getRepname());
		map.put("country", cvo.getAddress());
		service.insert_comcode(map);
		service.insert_companyuser(map);

		service.insert_home(this.hvo);
		service.insert_about(this.avo);
		service.insert_contact(this.cvo);
		service.first_product(map);
		return "redirect:../home";
	}

	/// �쉶�궗�젙蹂� select
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/companylist", method = RequestMethod.GET)
	public void Read_homGet(@RequestParam("userid") String userid, K_homeVO hvo, Model model,
			HttpServletRequest request) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute("companylist", service.getcompanylist(id));

	}

	@RequestMapping(value = "/check_company", method = RequestMethod.GET)
	public String check_company(@RequestParam("code") String code, Model model, HttpServletRequest request)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("id", id);
		System.out.println(service.checkcompany(map).size());
		if (service.checkcompany(map).size() != 0) {
			return "redirect:../cboard/news?code=" + code;
		} else {
			return "redirect:../cboard/register_home";
		}
	}

	@RequestMapping(value = "/cominsert", method = RequestMethod.POST)
	public String SboardPOST(CompanyBoardVO vo, HttpServletRequest request, Locale locale, MultipartFile test1,
			String test2, String test3, String test4, Model model)
			throws IllegalStateException, IOException, Exception {
		String id = (String) request.getSession().getAttribute("login");

		String referer = request.getHeader("Referer");
		logger.info("post successs");
		if (test1 != null) {
			File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
			if (!path.exists()) {
				path.mkdirs();
				System.out.println("cutewebi 디렉토리를 생성했습니다.");
				path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
			}

			String saveName = UUID.randomUUID().toString() + "_" + test1.getOriginalFilename().replaceAll(" ", "");
			File saveFile = new File(path, saveName);
			FileCopyUtils.copy(test1.getBytes(), saveFile);
			vo.setCbimage(saveName);
		}
		vo.setCbcontent(vo.getCbcontent().replace("\r\n", "<br>").trim());
		servicecompany.insert(vo);
		return "redirect:" + referer;
	}
	@RequestMapping(value = "/readPage_home", method = RequestMethod.GET)
	public void Read_homeGet(@RequestParam("code") String code, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute(service.readPage_about(code));
		K_homeVO hvo = service.readPage_home(code);
		if (hvo.getIntroduce() != null) {
			hvo.setIntroduce(hvo.getIntroduce().replace("\r\n", "<br>").trim());
		}
		model.addAttribute(hvo);
		model.addAttribute("followerComcount",service2.followerComcount(code));
		
		 * String id = (String) request.getSession().getAttribute("login");
		 * model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		 * 
		 * model.addAttribute("followingComcount",
		 * service2.followingComcount(userid)); model.addAttribute("followlist",
		 * service2.listSearchCriteriasearch(id));
		 * model.addAttribute("followcompanylist",
		 * service2.listSearchCriteriaCompany(id));
		 * 
		 * K_homeVO hvo = service.readPage_home(userid);
		 * if(hvo.getIntroduce()!=null){
		 * hvo.setIntroduce(hvo.getIntroduce().replace("\r\n", "<br>").trim());
		 * } model.addAttribute(hvo);
		 

	}

	@RequestMapping(value = "/readPage_about", method = RequestMethod.GET)
	public void Read_aboutGet(@RequestParam("code") String code, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute(service.readPage_home(code));
		K_aboutVO avo = service.readPage_about(code);
		if (avo.getAboutUs() != null) {
			avo.setAboutUs(avo.getAboutUs().replace("\r\n", "<br>").trim());
		}
		model.addAttribute(avo);
		model.addAttribute("followerComcount",service2.followerComcount(code));
		
		 * String id = (String) request.getSession().getAttribute("login");
		 * model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		 * model.addAttribute("followerComcount",
		 * service2.followerComcount(userid));
		 * model.addAttribute("followingComcount",
		 * service2.followingComcount(userid)); model.addAttribute("followlist",
		 * service2.listSearchCriteriasearch(id));
		 * model.addAttribute("followcompanylist",
		 * service2.listSearchCriteriaCompany(id));
		 * model.addAttribute(service.readPage_home(userid)); K_aboutVO avo =
		 * service.readPage_about(userid); if(avo.getAboutUs()!=null){
		 * avo.setAboutUs(avo.getAboutUs().replace("\r\n", "<br>").trim()); }
		 * model.addAttribute(avo);
		 
	}

	@RequestMapping(value = "/readPage_product", method = RequestMethod.GET)
	public void Read_productGet(@RequestParam("code") String code, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute("followerComcount",service2.followerComcount(code));
		
		 * model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		 * model.addAttribute("followerComcount",
		 * service2.followerComcount(userid));
		 * model.addAttribute("followingComcount",
		 * service2.followingComcount(userid)); model.addAttribute("followlist",
		 * service2.listSearchCriteriasearch(id));
		 * model.addAttribute("followcompanylist",
		 * service2.listSearchCriteriaCompany(id));
		 
		model.addAttribute(service.readPage_home(code));
		model.addAttribute(service.readPage_about(code));
		List<K_productVO> pvo = service.readPage_product(code);

		model.addAttribute("list", pvo);
		
		 * if(service.readPage_product(code).size() != 0){ }
		 
	}

	@RequestMapping(value = "/readPage_contact", method = RequestMethod.GET)
	public void Read_contactGet(@RequestParam("code") String code, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute(service.readPage_home(code));
		model.addAttribute(service.readPage_about(code));
		model.addAttribute(service.readPage_contact(code));
		model.addAttribute("group",service.selectgroup(code));
		model.addAttribute("followerComcount",service2.followerComcount(code));
		
		 * String id = (String) request.getSession().getAttribute("login");
		 * model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		 * model.addAttribute("followerComcount",
		 * service2.followerComcount(userid));
		 * model.addAttribute("followingComcount",
		 * service2.followingComcount(userid)); model.addAttribute("followlist",
		 * service2.listSearchCriteriasearch(id));
		 * model.addAttribute("followcompanylist",
		 * service2.listSearchCriteriaCompany(id));
		 * model.addAttribute(service.readPage_home(userid));
		 * model.addAttribute(service.readPage_about(userid));
		 * model.addAttribute(service.readPage_contact(userid));
		 
	}

	// �쉶�궗 �젙蹂� update
	@RequestMapping(value = "/update_home", method = RequestMethod.GET)
	public void Update_homeGEt(@RequestParam("hid") int hid, @RequestParam("code") String code, CountryVO vo,
			HttpServletRequest request, Model model, Locale locale) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute("clist", service3.countryget(vo));
		
		 * model.addAttribute("followerComcount",
		 * service2.followerComcount(id));
		 * model.addAttribute("followingComcount",
		 * service2.followingComcount(id));
		 
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute(service.readPage_home(code));
		model.addAttribute(service.readPage_about(code));
		model.addAttribute(service.readPage_contact(code));
		K_homeVO hvo = service.updateread_home(hid);
		hvo.setIntroduce(hvo.getIntroduce().replaceAll("<br>", "\r\n"));
		model.addAttribute(hvo);
		model.addAttribute("followerComcount",service2.followerComcount(code));
	}

	@RequestMapping(value = "/update_home/{country}", method = RequestMethod.GET)
	@ResponseBody
	public List<CountryVO> cityupdteGet(@PathVariable("country") String country) throws Exception {

		return service3.cityGet(country);
	}

	@RequestMapping(value = "/update_home", method = RequestMethod.POST)
	public void Update_homePOST(K_homeVO hvo, Model model, Locale locale, MultipartFile test1, MultipartFile test1_1,
			String test2, String test3, String test4) throws IllegalStateException, IOException, Exception {
		logger.info("update post");
		
		File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
		if (!path.exists()) {
			path.mkdirs();
			path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
		}
		if (test1 != null) {
			String fakeimg = hvo.getFakehimg();
			File path1 = new File("D:/spring/workspace/test2/src/main/webapp/resources/img/" + fakeimg);
			if (path1.exists() == true) {
				path1.delete();
			}
			String saveName = UUID.randomUUID().toString() + "_" + test1.getOriginalFilename().replaceAll(" ", "");
			File saveFile = new File(path, saveName);
			FileCopyUtils.copy(test1.getBytes(), saveFile);
			hvo.setCompanyimg(saveName);
		} else {
			hvo.setCompanyimg(hvo.getFakehimg());
		}
		if (test1_1 != null) {
			String fakeimg2 = hvo.getFakehimgcom();
			File path2 = new File("D:/spring/workspace/test2/src/main/webapp/resources/img/" + fakeimg2);
			if (path2.exists() == true) {
				path2.delete();
			}
			String saveName1 = UUID.randomUUID().toString() + "_" + test1_1.getOriginalFilename().replaceAll(" ", "");
			File saveFile1 = new File(path, saveName1);
			FileCopyUtils.copy(test1_1.getBytes(), saveFile1);
			hvo.setPimage(saveName1);
		} else {
			hvo.setPimage(hvo.getFakehimgcom());
		}
		hvo.setIntroduce(hvo.getIntroduce().replace("\r\n", "<br>").trim());
		service.update_home(hvo);

	}

	@RequestMapping(value = "/update_about", method = RequestMethod.GET)
	public void Update_aboutGEt(@RequestParam("aid") int aid, @RequestParam("code") String code, CategoryVO vo,
			HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		
		 * model.addAttribute("followerComcount",
		 * service2.followerComcount(id));
		 * model.addAttribute("followingComcount",
		 * service2.followingComcount(id));
		 
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute("list", service1.categoryget(vo));
		model.addAttribute(service.readPage_home(code));
		model.addAttribute(service.readPage_about(code));
		model.addAttribute(service.readPage_contact(code));
		K_aboutVO avo = service.updateread_about(aid);
		avo.setAboutUs(avo.getAboutUs().replaceAll("<br>", "\r\n"));
		model.addAttribute(avo);
		model.addAttribute("followerComcount",service2.followerComcount(code));
	}

	@RequestMapping(value = "/update_about", method = RequestMethod.POST)
	public String Update_aboutPOST(K_aboutVO avo, Model model) throws Exception {
		logger.info("update post");
		Map<String, String> map = new HashMap<String, String>();
		map.put("companyCode", avo.getCompanyCode());
		map.put("comname_kr", avo.getCompname_kr());
		map.put("comname_en", avo.getCompname_en());
		map.put("repname", avo.getRepname());
		service.update_company_about(map);
		
		String id = avo.getCompanyCode();
		System.out.println(id);
		avo.setAboutUs(avo.getAboutUs().replace("\r\n", "<br>").trim());
		service.update_about(avo);
		return "redirect:../cboard/readPage_about?code=" + id;
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
		logger.info("update post");

		if (pvo.getPinfo() != null) {
			pvo.setPinfo(pvo.getPinfo().replace("\r\n", "<br>").trim());
		}

		File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
		if (!path.exists()) {
			path.mkdirs();
			path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
		}
		if (test1 != null) {
			String fakeimg = pvo.getFakeimg();
			File path1 = new File("D:/spring/workspace/test2/src/main/webapp/resources/img/" + fakeimg);
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

	@RequestMapping(value = "/update_contact", method = RequestMethod.GET)
	public void Update_contactGEt(@RequestParam("cid") int cid, @RequestParam("code") String code, K_contactVO cvo,
			HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("updatenewpost", servicescroll.updatenewpost(id));
		model.addAttribute(service.readPage_home(code));
		model.addAttribute(service.readPage_about(code));
		model.addAttribute(service.readPage_contact(code));
		
		 * model.addAttribute("followerComcount",
		 * service2.followerComcount(id));
		 * model.addAttribute("followingComcount",
		 * service2.followingComcount(id));
		 
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute(service.updateread_contact(cid));
		model.addAttribute("followerComcount",service2.followerComcount(code));
	}

	@RequestMapping(value = "/update_contact", method = RequestMethod.POST)
	public String Update_contactPOST(K_contactVO cvo, Model model) throws Exception {
		logger.info("update post");
		String id = cvo.getCompanyCode();
		service.update_contact(cvo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("companyCode", id);
		map.put("country", cvo.getAddress());
		service.update_company_contact(map);
		return "redirect:../cboard/readPage_contact?code=" + id;
	}

	@RequestMapping(value = "/delpimage", method = RequestMethod.POST)
	public void delpimage(String fakehimgcom, String companyCode, Model model) throws Exception {
		File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img/" + fakehimgcom);
		service.delpimage(companyCode);
		if (fakehimgcom != "noimg.jsp") {
			if (path.exists() == true) {
				path.delete();
			}

		}
	}

	@RequestMapping(value = "/delcompanyimg", method = RequestMethod.POST)
	public void delcompanyimg(String fakehimg, String companyCode, Model model) throws Exception {
		File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img/" + fakehimg);
		service.delcompanyimg(companyCode);
		if (fakehimg != "noimg.jsp") {
			if (path.exists() == true) {
				path.delete();
			}

		}
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public void remove(@ModelAttribute("pid") int pid,String fakeimg, HttpServletRequest request, Model model, ScrollBoardVO vo)
			throws Exception {
		File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img/" + fakeimg);
		service.remove(pid);
		if (fakeimg != "noimg.jsp") {
			if (path.exists() == true) {
				path.delete();
			}

		}
	}
	@RequestMapping(value = "/searchcompanylist", method = RequestMethod.GET)
	public void companylist(@ModelAttribute("cri") SearchCriteria cri,Model model) throws Exception{
		System.out.println(cri.getKeyword());
		
		if(cri.getKeyword() == null){
			model.addAttribute("companylist",service.selectcompany());	
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.selectcompanycount());
			model.addAttribute("pageMaker", pageMaker);
		}else{
			model.addAttribute("companylist",service.selectKeywordcompany(cri));	
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.selectKeywordcompanycount(cri));
			model.addAttribute("pageMaker", pageMaker);
		}
		

	}
	@RequestMapping(value = "/searchcompanylist", method = RequestMethod.POST)
	public void companylistPOST(@ModelAttribute("code") String code, HttpServletRequest request,Model model) throws Exception{
		String id = (String) request.getSession().getAttribute("login");
		String companyCode = code;
		Map<String, String> map = new HashMap<String, String>();
		map.put("companyCode", companyCode);
		map.put("userid",id);
		map.put("first",service.getcodeid(id).getFirst());
		map.put("last", service.getcodeid(id).getLast());
		map.put("title", service.getcodeid(id).getTitle());
		map.put("repname",service.getaddress(companyCode).get(0).getRepname());
		map.put("country", service.getaddress(companyCode).get(0).getCountry());
		service.insert_companyuser(map);
	}
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public void newsGET(@RequestParam("code") String code, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute(service.readPage_about(code));
		model.addAttribute(service.readPage_home(code));
		model.addAttribute("followerComcount",service2.followerComcount(code));
		model.addAttribute("IndividualVO", serviceindi.Individual(id));
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
	@RequestMapping(value = "/newsDownScroll", method = RequestMethod.POST)
	public @ResponseBody List<CompanyBoardVO> Scroll(@RequestParam(value = "cbid") int cbid,@RequestParam(value = "code") String code, HttpServletRequest request)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		String sbidStart = Integer.toString(cbid-1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("cbid", sbidStart);
		map.put("code", code);
		return servicecompany.scrolllistSearchCriteria(map);
	}
	@RequestMapping(value = "/comDownScrollrep", method = RequestMethod.POST)
	public @ResponseBody List<ComReplyVO> DownScrollrep(int comboardid, String sessionid, HttpServletRequest request)
			throws Exception {
		String sbidStart = Integer.toString(comboardid - 1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("comboardid", sbidStart);
		return servicecompany.comscrollreplylist(map);
	}
	@RequestMapping(value = "/comreply", method = RequestMethod.POST)
	public String replyPOST(ComReplyVO vo, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		String referer = request.getHeader("Referer");
		vo.setComreplytext(vo.getComreplytext().replace("\r\n", "<br>").trim());
		servicecompany.comreplyinsert(vo);

		return "redirect:" + referer;
	}
	@RequestMapping(value = "/newsUpdate", method = RequestMethod.GET)
	public void newsUpdate(@ModelAttribute("cbid") int cbid, HttpServletRequest request, Model model,
			Locale locale) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("sessionimg", serviceindi.getsessionimg(id));
		List<CompanyBoardVO> vo = servicecompany.selectupdateBoard(cbid);
		vo.get(0).setCbcontent(vo.get(0).getCbcontent().replaceAll("<br>", "\r\n"));
		model.addAttribute("updateBoard", vo);
	}
	@RequestMapping(value = "/newsUpdate", method = RequestMethod.POST)
	public void newsUpdateePOST(HttpServletRequest request, Model model, CompanyBoardVO vo, Locale locale,
			MultipartFile test1, String test2, String test3, String test4)
			throws IllegalStateException, IOException, Exception {

		if (vo.getCbcontent() != null) {
			vo.setCbcontent(vo.getCbcontent().replace("\r\n", "<br>").trim());
		}
		if (test1 != null) {
			File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
			if (!path.exists()) {
				path.mkdirs();
				System.out.println("cutewebi 디렉토리를 생성했습니다.");
				path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
			}
			String saveName = UUID.randomUUID().toString() + "_" + test1.getOriginalFilename().replaceAll(" ", "");
			File saveFile = new File(path, saveName);
			FileCopyUtils.copy(test1.getBytes(), saveFile);
			vo.setCbimage(saveName);
		} else {
			vo.setCbimage(vo.getFakeimg());
		}

		servicecompany.newsUpdatee(vo);
	}
	@RequestMapping(value = "/newsremove", method = RequestMethod.POST)
	public String remove(@ModelAttribute("cbid") int cbid, HttpServletRequest request, Model model, ScrollBoardVO vo)
			throws Exception {
		String image = servicecompany.searchimage(cbid);
		List<ComReplyVO> replyid = servicecompany.searchreplyid(cbid);
		for (int i = 0; i < replyid.size(); i++) {
			System.out.println(replyid.get(i).getCrid());
			servicecompany.maindelAfterRepRemove(replyid.get(i).getCrid());
		}
		if(image != null){
			File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img/"+image);
			if (path.exists() == true) {
				path.delete();
			}
		}
		servicecompany.newsremove(cbid);
		String referer = request.getHeader("Referer");

		return "redirect:" + referer;
	}
	@RequestMapping(value = "/newsRepUpdate", method = RequestMethod.GET)
	public void newsRepsUpdateget(@ModelAttribute("crid") int crid, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("sessionimg", serviceindi.getsessionimg(id));
		List<ComReplyVO> vo = servicecompany.getnewsRepsUpdate(crid);
		vo.get(0).setComreplytext((String) vo.get(0).getComreplytext().replaceAll("<br>", "\r\n").trim());
		model.addAttribute("getReplyupdate", vo);
	}
	@RequestMapping(value = "/newsRepUpdate", method = RequestMethod.POST)
	public void updaterep(int crid, String comreplytext) throws Exception {
		String r_id = Integer.toString(crid);
		Map<String, String> map = new HashMap<String, String>();
		map.put("crid", r_id);
		map.put("comreplytext", comreplytext.replaceAll("\n", "<br>").trim());
		servicecompany.newsRepUpdate(map);
	}
	@RequestMapping(value = "/replyremove", method = RequestMethod.POST)
	public void replyremove(@ModelAttribute("crid") int crid, HttpServletRequest request, Model model)
			throws Exception {
		servicecompany.replyremove(crid);
	}
}
*/