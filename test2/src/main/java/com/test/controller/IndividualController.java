package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.domain.CategoryVO;
import com.test.domain.CountryVO;
import com.test.domain.FollowVO;
import com.test.domain.IndividualVO;
import com.test.domain.Re_ReplyVO;
import com.test.domain.ReplyVO;
import com.test.domain.ScrollBoardVO;
import com.test.domain.SearchCriteria;
import com.test.service.CategoryService;
import com.test.service.CboardService;
import com.test.service.CountryService;
import com.test.service.FollowService;
import com.test.service.IndividualSevice;
import com.test.service.NotesService;
import com.test.service.ScrollBoardService;

@Controller
@RequestMapping("/member/*")
public class IndividualController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyInfoController.class);

	@Inject
	private NotesService servicef;
	@Inject
	private IndividualSevice service;
	@Inject
	private CategoryService service1;
	@Inject
	private FollowService service2;
	@Inject
	private ScrollBoardService services;
	
	@Inject
	private CountryService service3;
	@Inject
	private CboardService servicecboard;

	@RequestMapping(value = "/individual", method = RequestMethod.GET)
	public void individualGet(@ModelAttribute("cri") SearchCriteria cri, HttpServletRequest request, Model model)
			throws Exception {

		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		model.addAttribute("sessionidName", services.serssionidName(id));
		model.addAttribute("IndividualVO", service.Individual(id));
	
		model.addAttribute("followercount", service2.followercount(id));
		model.addAttribute("followingcount", service2.followingcount(id));
		model.addAttribute("getlistcount",servicef.sebdercount(id));
		model.addAttribute("updatenewpost", services.updatenewpost(id));
		service.updatepost(id);
		List<ReplyVO> replylist = new ArrayList<>();
		// List<Re_ReplyVO> re_replylist = new ArrayList<>();
		if (service2.listSearchCriteriasearch(id).size() != 0) {
			List<ScrollBoardVO> scrolllist = services.listSearchCriteria(cri);
			model.addAttribute("scrolllist", scrolllist);
			for (int i = 0; i < scrolllist.size(); i++) {
				int sbid = scrolllist.get(i).getSbid();
				replylist.addAll(service.replylist(sbid));
			}
			model.addAttribute("replylist", replylist);
		} else {
			List<ScrollBoardVO> scrolllist = services.zerolistSearchCriteria(cri);
			model.addAttribute("scrolllist", scrolllist);
			for (int i = 0; i < scrolllist.size(); i++) {
				int sbid = scrolllist.get(i).getSbid();
				replylist.addAll(service.replylist(sbid));
			}
			model.addAttribute("replylist", replylist);
		}

	}

	@RequestMapping(value = "/individual", method = RequestMethod.POST)

	public String individualPost(HttpServletRequest request, IndividualVO vo, Model model) throws Exception {
		if (vo.getIntroduce() != null) {
			vo.setIntroduce(vo.getIntroduce().replace("\r\n", "<br>").trim());
		}
		service.introduceinsert(vo);
		String id = (String) request.getSession().getAttribute("login");
		return "redirect:/member/individual?keyword=" + id;
	}

	@RequestMapping(value = "/individualBupdate", method = RequestMethod.GET)
	public void individualBupdate(@ModelAttribute("sbid") int sbid, HttpServletRequest request, Model model,
			Locale locale) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("sessionimg", service.getsessionimg(id));
		List<ScrollBoardVO> vo = services.updateBoard(sbid);
		vo.get(0).setContent(vo.get(0).getContent().replaceAll("<br>", "\r\n"));
		model.addAttribute("updateBoard", vo);
	}

	@RequestMapping(value = "/individualBupdate", method = RequestMethod.POST)
	public void individualBupdatePOST(HttpServletRequest request, Model model, ScrollBoardVO vo, Locale locale,
			MultipartFile test1, String test2, String test3, String test4)
			throws IllegalStateException, IOException, Exception {

		if (vo.getContent() != null) {
			vo.setContent(vo.getContent().replace("\r\n", "<br>").trim());
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
			vo.setImage(saveName);
		} else {
			vo.setImage(vo.getFakeimg());
		}

		services.individualBupdatePOST(vo);
	}

	@RequestMapping(value = "/updaterep", method = RequestMethod.GET)
	public void updaterepget(@ModelAttribute("rid") int rid, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("sessionimg", service.getsessionimg(id));
		List<ReplyVO> vo = services.getReplyupdate(rid);
		vo.get(0).setReplytext((String) vo.get(0).getReplytext().replaceAll("<br>", "\r\n").trim());
		model.addAttribute("getReplyupdate", vo);
	}

	@RequestMapping(value = "/updaterep", method = RequestMethod.POST)
	public void updaterep(int rid, String replytext) throws Exception {
		String r_id = Integer.toString(rid);
		Map<String, String> map = new HashMap<String, String>();
		map.put("r_id", r_id);
		map.put("replytext", replytext.replaceAll("\n", "<br>").trim());
		services.updaterep(map);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@ModelAttribute("sbid") int sbid, HttpServletRequest request, Model model, ScrollBoardVO vo)
			throws Exception {
		services.remove(sbid);
		String referer = request.getHeader("Referer");

		return "redirect:" + referer;
	}

	@RequestMapping(value = "/replyremove", method = RequestMethod.POST)
	public String replyremove(@ModelAttribute("rid") int rid, HttpServletRequest request, Model model, ScrollBoardVO vo)
			throws Exception {
		services.replyremove(rid);
		String referer = request.getHeader("Referer");

		return "redirect:" + referer;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute("sbid") int sbid, HttpServletRequest request, Model model, ScrollBoardVO vo)
			throws Exception {
		services.remove(sbid);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/individualfollow", method = RequestMethod.GET)
	public void individualfollowGet(@ModelAttribute("email") String email, HttpServletRequest request, Model model)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("sololist", services.sololistSearchCriteria(email));
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map1));
		model.addAttribute("sessionimg", service.getsessionimg(id));
		model.addAttribute("sessionidName", services.serssionidName(id));
		model.addAttribute("followercount", service2.followercount(email));
		model.addAttribute("followingcount", service2.followingcount(email));
		
		//model.addAttribute("updatenewpost", services.updatenewpost(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("id", id);
		service2.flasenewpost(map);
		

		List<IndividualVO> vo = service.followIndividual(email);
		model.addAttribute("IndividualVO", vo);

		List<FollowVO> selectid = service2.getid(id);
		for (int i = 0; i < vo.size(); i++) {
			vo.get(i).setFlag(false);

			for (int j = 0; j < selectid.size(); j++) {
				if (vo.get(i).getEmail().equals(selectid.get(j).getFollowid())) {
					vo.get(i).setFlag(true);
					break;
				}
			}

		}
		List<ReplyVO> replylist = new ArrayList<>();
		// List<Re_ReplyVO> re_replylist = new ArrayList<>();
		if (service2.listSearchCriteriasearch(id).size() != 0) {
			List<ScrollBoardVO> scrolllist = services.sololistSearchCriteria(email);
			model.addAttribute("scrolllist", scrolllist);
			for (int i = 0; i < scrolllist.size(); i++) {
				int sbid = scrolllist.get(i).getSbid();
				replylist.addAll(service.replylist(sbid));
			} /*
				 * for (int i = 0; i < replylist.size(); i++) { int rid =
				 * replylist.get(i).getRid();
				 * re_replylist.addAll(service.re_replylist(rid)); }
				 */

			model.addAttribute("replylist", replylist);
			// model.addAttribute("re_replylist", re_replylist);
		} else {
			List<ScrollBoardVO> scrolllist = services.sololistSearchCriteria(email);
			model.addAttribute("scrolllist", scrolllist);
			for (int i = 0; i < scrolllist.size(); i++) {
				int sbid = scrolllist.get(i).getSbid();
				model.addAttribute("replylist", service.zroreplylist(sbid));

			}
			/*
			 * for (int i = 0; i < replylist.size(); i++) { int rid =
			 * replylist.get(i).getRid(); model.addAttribute("replylist",
			 * service.zrore_replylist(rid)); }
			 */
		}

	}

	@RequestMapping(value = "/individual_update", method = RequestMethod.GET)
	public void individualUpdateGet(CountryVO cvo, CategoryVO vo, HttpServletRequest request, Model model,
			Locale locale) throws Exception {

		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(map));
		List<IndividualVO> ivo = service.Individual(id);
		if (ivo.get(0).getIntroduce() != null) {
			ivo.get(0).setIntroduce(ivo.get(0).getIntroduce().replaceAll("<br>", "\r\n"));
		}
		model.addAttribute("IndividualVO", ivo);
		model.addAttribute("followercount", service2.followercount(id));
		model.addAttribute("followingcount", service2.followingcount(id));
		model.addAttribute("updatenewpost", services.updatenewpost(id));
		/*------------移댄뀒怨좊━----------*/
		model.addAttribute("list", service1.categoryget(vo));
		model.addAttribute("clist", service3.countryget(cvo));
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

	}

	@RequestMapping(value = "/category/individual_update/{mc_number}", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryVO> categoryget(@PathVariable("mc_number") Integer mc_number) throws Exception {

		return service1.smallcate(mc_number);
	}

	@RequestMapping(value = "/country/individual_update/{country}", method = RequestMethod.GET)
	@ResponseBody
	public List<CountryVO> cityGet(@PathVariable("country") String country) throws Exception {

		return service3.cityGet(country);
	}

	@RequestMapping(value = "/individual_delimg", method = RequestMethod.POST)
	public void individual_delimg(String fakeimg,int three, HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {

		File path = new File("D:/spring/workspace/test2/src/main/webapp/resources/img/"+fakeimg);
		service.delimg(three);
		if (fakeimg != "noimg.jsp") {
			if (path.exists() == true) {
				path.delete();
			}
			
		}
	}

	@RequestMapping(value = "/individual_update", method = RequestMethod.POST)
	public void individualUpdatePost(HttpServletRequest request, IndividualVO vo, Model model, Locale locale,
			MultipartFile testu1, String testu2, String testu3, String testu4)
			throws IllegalStateException, IOException, Exception {

		if (testu1 != null) {
			File path_update = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
			if (!path_update.exists()) {
				path_update.mkdirs();
				System.out.println("cutewebi 디렉토리를 생성했습니다.");
				path_update = new File("D:/spring/workspace/test2/src/main/webapp/resources/img");
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
		service.introduceupdate(vo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", vo.getEmail());
		map.put("first", vo.getFirst());
		map.put("last", vo.getLast());
		map.put("title", vo.getTitle());
		servicecboard.company_indi(map);
		
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void Sboardget(@ModelAttribute("cri") SearchCriteria cri, ScrollBoardVO vo, Locale locale,
			HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		logger.info("get success");
		model.addAttribute("getid", services.categoryget(vo));
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(id));
		model.addAttribute("followercount", service2.followercount(id));
		model.addAttribute("followingcount", service2.followingcount(id));

		if (service.listSearchCriteria(cri).size() != 0) {
			model.addAttribute("list", services.listSearchCriteria(cri));
		} else {
			model.addAttribute("list", services.zerolistSearchCriteria(cri));
		}

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String SboardPOST(ScrollBoardVO vo, HttpServletRequest request, Locale locale, MultipartFile test1,
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
			vo.setImage(saveName);
		}
		vo.setContent(vo.getContent().replace("\r\n", "<br>").trim());
		services.insert(vo);
		service2.newpost(id);
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String replyPOST(ReplyVO vo, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		vo.setReplytext(vo.getReplytext().replace("\r\n", "<br>").trim());
		service.replyinsert(vo);

		return "redirect:../member/individual?keyword=" + id;
	}

	@RequestMapping(value = "/followreply", method = RequestMethod.POST)
	public String followreplyPOST(ReplyVO vo, HttpServletRequest request, Model model) throws Exception {
		String referer = request.getHeader("Referer");
		vo.setReplytext(vo.getReplytext().replace("\r\n", "<br>").trim());
		service.replyinsert(vo);

		return "redirect:" + referer;
	}

	@RequestMapping(value = "/re_reply", method = RequestMethod.POST)
	public String re_replyPOST(Re_ReplyVO vo, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		service.re_replyinsert(vo);
		// vo.setReplytext(vo.getReplytext().replace("\r\n", "<br>").trim());

		return "redirect:../member/individual?keyword=" + id;
	}

	@RequestMapping(value = "/DownScroll", method = RequestMethod.POST)
	public @ResponseBody List<ScrollBoardVO> Scroll(int sbid, String sessionid, HttpServletRequest request)
			throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		String sbidStart = Integer.toString(sbid - 1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sbid", sbidStart);
		map.put("sessionid", sessionid);

		if (service2.listSearchCriteriasearch(id).size() != 0) {

			return services.infiniteScrillDown(map);
		} else {
			return services.zeroinfiniteScrillDown(map);
		}

	}

	@RequestMapping(value = "/DownScrollrep", method = RequestMethod.POST)
	public @ResponseBody List<ReplyVO> DownScrollrep(int boardid, String sessionid, HttpServletRequest request)
			throws Exception {
		String sbidStart = Integer.toString(boardid - 1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sbid", sbidStart);
		map.put("sessionid", sessionid);
		services.searchsbid(map);
		int sbid = services.searchsbid(map).get(0).getSbid();
		return service.replylist(sbid);
	}

	@RequestMapping(value = "/DownScroll_re_rep", method = RequestMethod.POST)
	public @ResponseBody List<Re_ReplyVO> DownScroll_re_rep(int re_boardid, HttpServletRequest request)
			throws Exception {
		/*
		 * String sbidStart = Integer.toString(re_boardid - 1); int sbid =
		 * Integer.parseInt(sbidStart); System.out.println("re_boardid : " +
		 * re_boardid);
		 */

		return service.re_Downreplylist(re_boardid);
	}

	@RequestMapping(value = "/soloDownScroll", method = RequestMethod.POST)
	public @ResponseBody List<ScrollBoardVO> SoloScroll(int sbid, String sessionid) throws Exception {
		String sbidStart = Integer.toString(sbid - 1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sbid", sbidStart);
		map.put("sessionid", sessionid);

		return services.soloinfiniteScrillDown(map);

	}

	/////////////////////////////////////// 개인페이지 팔로워보기////////////////
	@RequestMapping(value = "/soloViewfollower", method = RequestMethod.GET)
	public void soloViewfollower(String email, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service2.getid(id);
		List<FollowVO> list = service2.soloViewfollower(email);
		model.addAttribute("soloViewfollower", list);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);

			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getMyid().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}
	}

	@RequestMapping(value = "/soloViewfollowerScroll", method = RequestMethod.POST)
	public @ResponseBody List<FollowVO> soloViewfollowerScroll(HttpServletRequest request, String fid, String email)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", fid);
		map.put("email", email);
		List<FollowVO> list = service2.soloViewfollowerScroll(map);
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service2.getid(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getMyid().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}
		return list;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/soloViewfollowing", method = RequestMethod.GET)
	public void soloViewfollowing(String email, HttpServletRequest request, Model model) throws Exception {

		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service2.getid(id);
		List<FollowVO> list = service2.soloViewfollowing(email);
		model.addAttribute("soloViewfollowing", list);
		model.addAttribute("userid", email);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(true);
			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getMyid().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(false);
					break;
				}
			}
		}
	}

	@RequestMapping(value = "/soloViewfollowingScroll", method = RequestMethod.POST)
	public @ResponseBody List<FollowVO> soloViewfollowingScroll(HttpServletRequest request, String fid, String email)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", fid);
		map.put("email", email);
		List<FollowVO> list = service2.soloViewfollowingScroll(map);
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service2.getid(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(true);
			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getMyid().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(false);
					break;
				}
			}

		}
		return list;
	}

	///////////////////////////// 상대방 페이지 팔로잉 보기//////////
	@RequestMapping(value = "/viewfollower", method = RequestMethod.GET)
	public void viewfollower(String email, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service2.getid(id);
		List<FollowVO> list = service2.soloViewfollower(email);
		model.addAttribute("soloViewfollower", list);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);

			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getMyid().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}
	}

	@RequestMapping(value = "/viewfollowerScroll", method = RequestMethod.POST)
	public @ResponseBody List<FollowVO> viewfollowerScroll(HttpServletRequest request, String fid, String email)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", fid);
		map.put("email", email);
		List<FollowVO> list = service2.soloViewfollowerScroll(map);
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service2.getid(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getMyid().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}
		return list;
	}

	@RequestMapping(value = "/viewfollowing", method = RequestMethod.GET)
	public void viewfollowing(String email, HttpServletRequest request, Model model) throws Exception {

		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service2.getid(id);
		List<FollowVO> list = service2.soloViewfollowing(email);
		model.addAttribute("soloViewfollowing", list);
		model.addAttribute("userid", email);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getFollowid().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}
	}

	@RequestMapping(value = "/viewfollowingScroll", method = RequestMethod.POST)
	public @ResponseBody List<FollowVO> ViewfollowingScroll(HttpServletRequest request, String fid, String email)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", fid);
		map.put("email", email);
		List<FollowVO> list = service2.soloViewfollowingScroll(map);
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service2.getid(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getFollowid().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}

		}
		return list;
	}

	///////////////////////////// 회사 페이지 팔로워 보기//////////
	@RequestMapping(value = "/soloComfollower", method = RequestMethod.GET)

	public void soloComfollower(String code, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectid = service2.getid(id);
		List<FollowVO> list = service2.myCompanyfolloer(code);
		model.addAttribute("myCompanyfolloer", list);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectid.size(); j++) {
				if (list.get(i).getFcmyid().equals(selectid.get(j).getFollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}
	}

	@RequestMapping(value = "/soloComfollowerScroll", method = RequestMethod.POST)
	public @ResponseBody List<FollowVO> soloComfollowerScroll(HttpServletRequest request, String fid, String email)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", fid);
		map.put("email", email);
		List<FollowVO> list = service2.soloComfollowerScroll(map);
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectComid = service2.getComid(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectComid.size(); j++) {
				if (list.get(i).getFcmyid().equals(selectComid.get(j).getFcfollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}
		return list;
	}

	/////////////////////////////////////////////////// 회사페이지
	/////////////////////////////////////////////////// 팔로잉보기///////////////////
	@RequestMapping(value = "/soloComfollowing", method = RequestMethod.GET)
	public void soloComfollowing(String email, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectComid = service2.getComid(id);
		List<FollowVO> list = service2.myCompanyfolloing(email);
		model.addAttribute("myCompanyfolloer", list);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);

			for (int j = 0; j < selectComid.size(); j++) {
				if (list.get(i).getFcmyid().equals(selectComid.get(j).getFcfollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}

		}
	}

	@RequestMapping(value = "/soloComfollowingScroll", method = RequestMethod.POST)
	public @ResponseBody List<FollowVO> soloComfollowingScroll(HttpServletRequest request, String fid, String email)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", fid);
		map.put("email", email);
		List<FollowVO> list = service2.soloComfollowingScroll(map);
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectComid = service2.getComid(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectComid.size(); j++) {
				if (list.get(i).getFcmyid().equals(selectComid.get(j).getFcfollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}

		}
		return list;
	}

	@RequestMapping(value = "/comViewfollower", method = RequestMethod.GET)
	public void comViewfollower(String email, HttpServletRequest request, Model model) throws Exception {

		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectComid = service2.getComid(id);
		List<FollowVO> list = service2.comViewfollower(email);
		model.addAttribute("comViewfollower", list);
		model.addAttribute("userid", email);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectComid.size(); j++) {
				if (list.get(i).getFcmyid().equals(selectComid.get(j).getFcfollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}
	}

	@RequestMapping(value = "/comViewfollowerScroll", method = RequestMethod.POST)
	public @ResponseBody List<FollowVO> comViewfollowerScroll(HttpServletRequest request, String fid, String email)
			throws Exception {

		String id = (String) request.getSession().getAttribute("login");
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", fid);
		map.put("email", email);
		List<FollowVO> list = service2.comViewfollowerScroll(map);
		List<FollowVO> selectComid = service2.getComid(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectComid.size(); j++) {
				if (list.get(i).getFcmyid().equals(selectComid.get(j).getFcfollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}
		}
		return list;
	}

	////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/comViewfollowing", method = RequestMethod.GET)
	public void comViewfollowing(String email, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectComid = service2.getComid(id);
		List<FollowVO> list = service2.comViewfollowing(email);
		model.addAttribute("myCompanyfolloer", list);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);

			for (int j = 0; j < selectComid.size(); j++) {
				if (list.get(i).getFcfollowid().equals(selectComid.get(j).getFcfollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}

		}
	}

	@RequestMapping(value = "/comViewfollowingScroll", method = RequestMethod.POST)
	public @ResponseBody List<FollowVO> comViewfollowingScroll(HttpServletRequest request, String fid, String email)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", fid);
		map.put("email", email);
		List<FollowVO> list = service2.comViewfollowingScroll(map);
		String id = (String) request.getSession().getAttribute("login");
		List<FollowVO> selectComid = service2.getComid(id);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFlag(false);
			for (int j = 0; j < selectComid.size(); j++) {
				if (list.get(i).getFcfollowid().equals(selectComid.get(j).getFcfollowid())) {
					list.get(i).setFlag(true);
					break;
				}
			}

		}
		return list;
	}
}
