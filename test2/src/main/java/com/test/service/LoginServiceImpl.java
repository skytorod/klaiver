package com.test.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.DTO.LoginDTO;
import com.test.domain.LoginVO;
import com.test.persistence.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Inject
	private LoginDAO dao;

	@Override
	public LoginVO login(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}


	

}
