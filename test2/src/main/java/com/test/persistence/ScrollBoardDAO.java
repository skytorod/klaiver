package com.test.persistence;

import java.util.List;
import java.util.Map;

import com.test.domain.ReplyVO;
import com.test.domain.ScrollBoardVO;
import com.test.domain.SearchCriteria;

public interface ScrollBoardDAO {

	public void insert(ScrollBoardVO vo) throws Exception;
	
	public List<ScrollBoardVO> listSearch(SearchCriteria cri) throws Exception;

	public List<ScrollBoardVO> infiniteScrillDown(Map<String, String> map) throws Exception;

	public List<ScrollBoardVO> categoryget(ScrollBoardVO vo) throws Exception;

	public List<ScrollBoardVO> zerolistSearch(SearchCriteria cri)throws Exception;

	public List<ScrollBoardVO> zeroinfiniteScrillDown(Map<String, String> map)throws Exception;

	public List<ScrollBoardVO> sololistSearchCriteria(String email)throws Exception;

	public List<ScrollBoardVO> soloinfiniteScrillDown(Map<String, String> map) throws Exception;

	public List<ScrollBoardVO> serssionidName(String id) throws Exception;

	public List<ScrollBoardVO> updateBoard(int sbid)throws Exception;

	public void individualBupdatePOST(ScrollBoardVO vo)throws Exception;

	public void remove(int sbid)throws Exception;

	public List<ReplyVO> getReplyupdate(int rid) throws Exception;

	public void updaterep(Map<String, String> map) throws Exception;

	public void replyremove(int rid) throws Exception;

	public List<ReplyVO> searchsbid(Map<String, String> map) throws Exception;

	public List<ScrollBoardVO> updatenewpost(String id) throws Exception;

}
