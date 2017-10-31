package com.test.service;

import java.util.List;

import com.test.domain.JoinOne;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_productVO;

public interface CboardService {
	//joinone의 정보 (프로필사진등)
	public List<JoinOne> memberinfo(String userid) throws Exception;
	//로그인한 사람 이름 가져오기
	public List<JoinOne> serssionidName(String id) throws Exception;
	//about 정보 가져오기
	public K_aboutVO readPage_about(String userid) throws Exception;
	//product 정보 가져오기
	public List<K_productVO> readPage_product(String userid) throws Exception;
	//contact 정보 가져오기
	public K_contactVO readPage_contact(String userid) throws Exception;
	//about insert
	public void insert_about(K_aboutVO avo) throws Exception;
	//contact insert
	public void insert_contact(K_contactVO cvo) throws Exception;
	//proudct insert
	public void insert_product(K_productVO vo) throws Exception;
	//home(회사 이력, 이름 , 프로필 사진) 업데이트
	public void homeupdate(JoinOne vo) throws Exception;
	//about 업데이트
	public void update_about(K_aboutVO avo) throws Exception;
	//contact 업데이트
	public void update_contact(K_contactVO cvo) throws Exception;
	//product 수정할 것 찾아오기
	public K_productVO updateread_product(int pid) throws Exception;
	//product 업데이트
	public void update_product(K_productVO pvo) throws Exception;
	//product 삭제
	public void remove(int pid)  throws Exception;
	
	
}
