package com.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.ComReplyVO;
import com.test.domain.CompanyBoardVO;
import com.test.persistence.CompanyboardDAO;

@Service
public class CompanyboardServiceImpl implements CompanyboardService{
	@Inject
	private CompanyboardDAO dao;

	@Override
	public void insert(CompanyBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public List<CompanyBoardVO> listSearchCriteria(String code) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCriteria(code);
	}

	@Override
	public String searchmanager(String code) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchmanager(code);
	}

	@Override
	public List<CompanyBoardVO> scrolllistSearchCriteria(Map<String, String> map) throws Exception{
		// TODO Auto-generated method stub
		return dao.scrolllistSearchCriteria(map);
	}

	@Override
	public void comreplyinsert(ComReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.comreplyinsert(vo);
	}

	@Override
	public List<ComReplyVO> comreplylist(int cbid) throws Exception {
		// TODO Auto-generated method stub
		return dao.comreplylist(cbid);
	}

	@Override
	public List<ComReplyVO> comscrollreplylist(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.comscrollreplylist(map);
	}

	@Override
	public List<CompanyBoardVO> selectupdateBoard(int cbid) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectupdateBoard(cbid);
	}

	@Override
	public void newsUpdatee(CompanyBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.newsUpdatee(vo);
	}

	@Override
	public void newsremove(int cbid) throws Exception {
		// TODO Auto-generated method stub
		dao.newsremove(cbid);
	}

	@Override
	public String searchimage(int cbid) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchimage(cbid);
	}

	@Override
	public List<ComReplyVO> searchreplyid(int cbid) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchreplyid(cbid);
	}

	@Override
	public void maindelAfterRepRemove(int cbid) throws Exception {
		// TODO Auto-generated method stub
		dao.maindelAfterRepRemove(cbid);
	}

	@Override
	public List<ComReplyVO> getnewsRepsUpdate(int crid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getnewsRepsUpdate(crid);
	}

	@Override
	public void newsRepUpdate(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		dao.newsRepUpdate(map);
	}

	@Override
	public void replyremove(int crid) throws Exception {
		// TODO Auto-generated method stub
		dao.replyremove(crid);
	}


}
