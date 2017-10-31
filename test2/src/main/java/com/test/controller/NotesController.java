package com.test.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.NotesVO;
import com.test.service.FollowService;
import com.test.service.NotesService;

@Controller
@RequestMapping("/notes/*")
public class NotesController {
	/*private static final Logger logger = LoggerFactory.getLogger(SingController.class);
	*/
	@Inject
	private NotesService service;	
	@Inject
	private FollowService service2;
	private NotesVO nvo;
	
	// 보관함 으로 이동
	@RequestMapping(value="/storage",method=RequestMethod.GET)
	public void storageGET(HttpServletRequest request, Model model)throws Exception{
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("getlist",service.storagelist(id));
	}
	
	@RequestMapping(value="/storage",method=RequestMethod.POST)
	public String storagePOST(HttpServletRequest request, Model model)throws Exception{
		
		

		return "redirect:../notes/storage";
	}
	// 휴지통 으로 이동
	@RequestMapping(value="/recycle",method=RequestMethod.GET)
	public void recycleGET(HttpServletRequest request, Model model)throws Exception{

		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("getlist",service.recyclelist(id));

	}
	
	@RequestMapping(value="/recycle",method=RequestMethod.POST)
	public String recyclePOST(HttpServletRequest request, Model model)throws Exception{
		
		return "redirect:../notes/recycle";
	}
	
	@RequestMapping(value="/recymove",method=RequestMethod.POST)
	public String recyclePOST(@RequestParam(value="notes_id[]") List<Integer> recyclelist, Model model
			)throws Exception{
		
		for(int i=0;i<recyclelist.size();i++){
			service.recymove(recyclelist.get(i));
		}
		return "redirect:../notes/senderlist";
	}
	
	
	
	
	@RequestMapping(value="/storagemove",method=RequestMethod.POST)
	public String storagePOST(@RequestParam(value="notes_id[]") List<Integer> storagelist, Model model
			)throws Exception{
		
		for(int i=0;i<storagelist.size();i++){
			service.storagemove(storagelist.get(i));
		}
		return "redirect:../notes/senderlist";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public void updatenotesGET(String notes_id, Model model, String updatenote, 
			String revcvid, HttpServletRequest request)throws Exception{
		
		int notes = Integer.parseInt(notes_id); 
		
		service.updatenotes(notes);
	}
	/* 삭제 부분 휴지통 */
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(@RequestParam(value="notes_id[]") List<Integer> list,Model model)throws Exception {
		
		for(int i=0;i<list.size();i++){
			service.remove(list.get(i));
		}
		return "redirect:../notes/recycle";
	}
	
	@RequestMapping(value="/addresslist",method=RequestMethod.GET)
	public void addresslistGET(String userid, HttpServletRequest request, NotesVO nvo,Model model)throws Exception{
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("addresslist", service.senderlist(id));
		model.addAttribute("followlist", service2.listSearchCriteriasearch(id));
		model.addAttribute("followcompanylist", service2.listSearchCriteriaCompany(id));
	}
	@RequestMapping(value="/addresslist",method=RequestMethod.POST)
	public String addresslistPOST(String recvid, NotesVO nvo, Model model)throws Exception{
		
		service.sendnote(nvo);
		model.addAttribute("sendform",this.nvo);
		model.addAttribute("recv_idlist",recvid);
		System.out.println("++:"+recvid);
		return "redirect:../notes/addresslist";
	}
	
	@RequestMapping(value="/senderlist",method=RequestMethod.GET)
	public void senderlistGET(String senderid,HttpServletRequest request,NotesVO nvo, Model model)throws Exception{
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("getlist",service.senderlist(id));
		
		/*List<NotesVO> getlistcount = service.senderlist(id);
		for(int i=0 ;i < getlistcount.size();i++){
			System.out.println(getlistcount);
		}*/
	}
	@RequestMapping(value="/senderlist",method=RequestMethod.POST)
	public String senderlistPOST(NotesVO nvo, Model model)throws Exception{
		
		service.sendnote(nvo);
		model.addAttribute("sendform",this.nvo);
		return "redirect:../notes/senderlist";
	}
	
	@RequestMapping(value="/sender",method=RequestMethod.GET)
	public void senderGET(HttpServletRequest request,String userid,NotesVO nvo,Model model)throws Exception{
		String id = (String) request.getSession().getAttribute("login");
		model.addAttribute("getlist",service.senderlist(id));
		model.addAttribute("senderid",userid);
		
	}
	
	@RequestMapping(value="/sender",method=RequestMethod.POST)
	public String snderPOST(String senderid,NotesVO nvo, Model model)throws Exception{

		service.sendnote(nvo);
		model.addAttribute("sendform",this.nvo);
				
		return "redirect:../notes/senderlist";
	}
	
	@RequestMapping(value="/fsender", method=RequestMethod.GET)
	public void fsenderGET(HttpServletRequest request,String email,NotesVO nvo,Model model)throws Exception{
	 
		request.setAttribute("useremail",email);
		System.out.println("보낼값:"+email);// 이 값이 지워 진다는 뜻........
	   
	}
	@RequestMapping(value="/fsender", method=RequestMethod.POST)
	public String fsenderPOST(NotesVO nvo, Model model) throws Exception{
		
		service.sendnote(nvo);
		System.out.println(nvo);
		model.addAttribute("rsendform", this.nvo);
		
		System.out.println("??");
		
		return "redirect:../notes/fsender";
	}
	
	@RequestMapping(value="/recipient",method=RequestMethod.GET)
	public void recipientGET(HttpServletRequest request,String sentid,NotesVO nvo,Model model,String recvid)throws Exception{
		model.addAttribute("getlist",sentid);
		model.addAttribute("recvid",recvid);		
	}
	@RequestMapping(value="/recipient",method=RequestMethod.POST)
	public String recipientPOST(NotesVO nvo, Model model)throws Exception{
		service.sendnote(nvo);
		model.addAttribute("recvform",this.nvo);
		
		return "redirect:../notes/recipient";
	}	
}
