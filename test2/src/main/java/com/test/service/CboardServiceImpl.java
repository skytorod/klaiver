package com.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.domain.CompanyVO;
import com.test.domain.IndividualVO;
import com.test.domain.JoinOne;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;
import com.test.persistence.CboardDAO;

@Service
public class CboardServiceImpl implements CboardService {
	@Inject
	private CboardDAO dao;


	@Override
	public List<JoinOne> memberinfo(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.memberinfo(userid);
	}


	@Override
	public List<JoinOne> serssionidName(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.serssionidName(id);
	}


	@Override
	public K_aboutVO readPage_about(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.readPage_about(userid);
	}


	@Override
	public List<K_productVO> readPage_product(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.readPage_product(userid);
	}


	@Override
	public K_contactVO readPage_contact(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.readPage_contact(userid);
	}


	@Override
	public void insert_about(K_aboutVO avo) throws Exception {
		// TODO Auto-generated method stub
		dao.insert_about(avo);
	}


	@Override
	public void insert_contact(K_contactVO cvo) throws Exception {
		// TODO Auto-generated method stub
		dao.insert_contact(cvo);
	}


	@Override
	public void insert_product(K_productVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.insert_product(vo);
	}


	@Override
	public void homeupdate(JoinOne vo) throws Exception {
		// TODO Auto-generated method stub
		dao.homeupdate(vo);
	}


	@Override
	public void update_about(K_aboutVO avo) throws Exception {
		// TODO Auto-generated method stub
		dao.update_about(avo);
	}


	@Override
	public void update_contact(K_contactVO cvo) throws Exception{
		// TODO Auto-generated method stub
		dao.update_contact(cvo);
	}


	@Override
	public K_productVO updateread_product(int pid) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateread_product(pid);
	}


	@Override
	public void update_product(K_productVO pvo) throws Exception {
		// TODO Auto-generated method stub
		dao.update_product(pvo);
	}


	@Override
	public void remove(int pid) throws Exception {
		// TODO Auto-generated method stub
		dao.remove(pid);
	}
}
