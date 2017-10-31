package com.test.persistence;

import java.util.List;
import java.util.Map;

import com.test.domain.CompanyVO;
import com.test.domain.IndividualVO;
import com.test.domain.JoinOne;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;

public interface CboardDAO {
	public List<JoinOne> memberinfo(String userid) throws Exception;

	public List<JoinOne> serssionidName(String id) throws Exception;

	public K_aboutVO readPage_about(String userid) throws Exception;

	public List<K_productVO> readPage_product(String userid) throws Exception;

	public K_contactVO readPage_contact(String userid) throws Exception;

	public void insert_about(K_aboutVO avo) throws Exception;

	public void insert_contact(K_contactVO cvo) throws Exception;

	public void insert_product(K_productVO vo) throws Exception;

	public void homeupdate(JoinOne vo) throws Exception;

	public void update_about(K_aboutVO avo) throws Exception;

	public void update_contact(K_contactVO cvo) throws Exception;

	public K_productVO updateread_product(int pid)  throws Exception;

	public void update_product(K_productVO pvo)  throws Exception;

	public void remove(int pid) throws Exception;
	
	
}
