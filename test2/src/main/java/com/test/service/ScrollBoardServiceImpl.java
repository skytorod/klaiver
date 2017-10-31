package com.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.ReplyVO;
import com.test.domain.ScrollBoardVO;
import com.test.domain.SearchCriteria;
import com.test.persistence.ScrollBoardDAO;

@Service
public class ScrollBoardServiceImpl implements ScrollBoardService {
	@Inject
	private ScrollBoardDAO dao;
	@Override
	public void insert(ScrollBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}
	@Override
	public List<ScrollBoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}
	@Override
	public List<ScrollBoardVO> infiniteScrillDown(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.infiniteScrillDown(map);
	}
	@Override
	public List<ScrollBoardVO> categoryget(ScrollBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.categoryget(vo);
	}
	@Override
	public List<ScrollBoardVO> zerolistSearchCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.zerolistSearch(cri);
	}
	@Override
	public List<ScrollBoardVO> zeroinfiniteScrillDown(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.zeroinfiniteScrillDown(map);
	}
	@Override
	public List<ScrollBoardVO> sololistSearchCriteria(String email) throws Exception {
		// TODO Auto-generated method stub
		return dao.sololistSearchCriteria(email);
	}
	@Override
	public List<ScrollBoardVO> soloinfiniteScrillDown(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.soloinfiniteScrillDown(map);
	}
	@Override
	public List<ScrollBoardVO> serssionidName(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.serssionidName(id);
	}
	@Override
	public List<ScrollBoardVO> updateBoard(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateBoard(sbid);
	}
	@Override
	public void individualBupdatePOST(ScrollBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		 dao.individualBupdatePOST(vo);
	}
	@Override
	public void remove(int sbid) throws Exception {
		// TODO Auto-generated method stub
		dao.remove(sbid);
	}
	@Override
	public List<ReplyVO> getReplyupdate(int rid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getReplyupdate(rid);
	}
	@Override
	public void updaterep(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		dao.updaterep(map);
	}
	@Override
	public void replyremove(int rid) throws Exception {
		// TODO Auto-generated method stub
		dao.replyremove(rid);
	}
	@Override
	public List<ReplyVO>  searchsbid(Map<String, String> map) throws Exception {
		return dao.searchsbid(map);
	}
	@Override
	public List<ScrollBoardVO> updatenewpost(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.updatenewpost(id);
	}

}
