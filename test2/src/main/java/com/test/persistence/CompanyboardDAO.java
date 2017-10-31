package com.test.persistence;

import java.util.List;
import java.util.Map;

import com.test.domain.ComReplyVO;
import com.test.domain.CompanyBoardVO;

public interface CompanyboardDAO {

	public void insert(CompanyBoardVO vo) throws Exception;

	public List<CompanyBoardVO> listSearchCriteria(String code) throws Exception;

	public String searchmanager(String code) throws Exception;

	public List<CompanyBoardVO> scrolllistSearchCriteria(Map<String, String> map) throws Exception;

	public void comreplyinsert(ComReplyVO vo) throws Exception;

	public List<ComReplyVO> comreplylist(int cbid) throws Exception;

	public List<ComReplyVO> comscrollreplylist(Map<String, String> map) throws Exception;

	public List<CompanyBoardVO> selectupdateBoard(int cbid) throws Exception;

	public void newsUpdatee(CompanyBoardVO vo) throws Exception;

	public void newsremove(int cbid) throws Exception;

	public String searchimage(int cbid)  throws Exception;

	public List<ComReplyVO> searchreplyid(int cbid) throws Exception;

	public void maindelAfterRepRemove(int cbid) throws Exception;

	public List<ComReplyVO> getnewsRepsUpdate(int crid) throws Exception;

	public void newsRepUpdate(Map<String, String> map) throws Exception;

	public void replyremove(int crid) throws Exception;


}
