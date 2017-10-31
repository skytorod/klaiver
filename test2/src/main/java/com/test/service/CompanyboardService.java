package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.domain.ComReplyVO;
import com.test.domain.CompanyBoardVO;

public interface CompanyboardService {
	// 메인 게시물 insert
	public void insert(CompanyBoardVO vo) throws Exception;
	// 첫 3개 게시물 가져오기
	public List<CompanyBoardVO> listSearchCriteria(String code) throws Exception;
	//관리자 아이디찾기
	public String searchmanager(String code) throws Exception;
	//메인 게시물 스크롤 가져오기
	public List<CompanyBoardVO> scrolllistSearchCriteria(Map<String, String> map) throws Exception;
	//리플 insert
	public void comreplyinsert(ComReplyVO vo) throws Exception;
	//리플 가져오기
	public List<ComReplyVO> comreplylist(int cbid) throws Exception;
	//스크롤 리플 가져오기
	public List<ComReplyVO> comscrollreplylist(Map<String, String> map) throws Exception;
	//업데이트 폼 정보 
	public List<CompanyBoardVO> selectupdateBoard(int cbid) throws Exception;
	//게시물 업데이트
	public void newsUpdatee(CompanyBoardVO vo) throws Exception;
	//게시물 삭제
	public void newsremove(int cbid) throws Exception;
	//게시물 삭제될때 이미지 삭제를 위해 이미지 이름 가져오기
	public String searchimage(int cbid) throws Exception;
	//게시물 삭제될때 리플도 삭제를 위해 리플 id 가져오기
	public List<ComReplyVO> searchreplyid(int cbid) throws Exception;
	//게시물 삭제될때 리플도 삭제
	public void maindelAfterRepRemove(int cbid)  throws Exception;
	//리플 수정 폼 정보
	public List<ComReplyVO> getnewsRepsUpdate(int crid)  throws Exception;
	//리플 수정
	public void newsRepUpdate(Map<String, String> map)  throws Exception;
	//리플 삭제
	public void replyremove(int crid) throws Exception;


}
