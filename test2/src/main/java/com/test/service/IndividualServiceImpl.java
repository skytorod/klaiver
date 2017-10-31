package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.IndividualVO;
import com.test.domain.Re_ReplyVO;
import com.test.domain.ReplyVO;
import com.test.domain.SearchCriteria;
import com.test.persistence.IndividualDAO;

@Service
public class IndividualServiceImpl implements IndividualSevice {
	
	@Inject
	private IndividualDAO dao;

	@Override
	public List<IndividualVO> Individual(String id) throws Exception {
		
		return dao.Individual(id);
	}

	/*@Override
	public void introduceinsert(IndividualVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.introduceinsert(vo);
	}

	@Override
	public void introduceupdate(IndividualVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.introduceupdate(vo);
	}

	@Override
	public List<IndividualVO> followIndividual(String email) throws Exception {
		// TODO Auto-generated method stub
		return dao.followIndividual(email);
	}

	@Override
	public List<IndividualVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}

	@Override
	public List<IndividualVO> getsessionimg(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.getsessionimg(id);
	}

	@Override
	public void replyinsert(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.replyinsert(vo);
	}

	@Override
	public List<ReplyVO> replylist(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return dao.replylist(sbid);
	}

	@Override
	public List<ReplyVO> zroreplylist(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return dao.zroreplylist(sbid);
	}

	@Override
	public void re_replyinsert(Re_ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.re_replyinsert(vo);
	}

	@Override
	public List<Re_ReplyVO> re_replylist(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return dao.re_replylist(sbid);
	}

	@Override
	public List<Re_ReplyVO> zrore_replylist(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return dao.zrore_replylist(sbid);
	}

	@Override
	public List<Re_ReplyVO> re_Downreplylist(int re_boardid) throws Exception {
		// TODO Auto-generated method stub
		return dao.re_Downreplylist(re_boardid);
	}

	@Override
	public void delimg(int three) throws Exception {
		// TODO Auto-generated method stub
		dao.delimg(three);
	}

	@Override
	public List<IndividualVO> listsolo(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listsolo(cri);
	}

	@Override
	public void updatepost(String id) throws Exception {
		// TODO Auto-generated method stub
		dao.updatepost(id);
	}
	*/
	

}
