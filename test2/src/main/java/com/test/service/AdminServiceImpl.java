package com.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.CompanyVO;
import com.test.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService{
	@Inject
	private AdminDAO dao;

	@Override
	public List<CompanyVO> getlist() throws Exception {
		// TODO Auto-generated method stub
		return dao.getlist();
	}

	@Override
	public List<CompanyVO> getmember(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.getmember(map);
	}

	@Override
	public void updatemanager(CompanyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.updatemanager(vo);
	}

}
