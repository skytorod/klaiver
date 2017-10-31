package com.test.service;

import com.test.DTO.LoginDTO;
import com.test.domain.LoginVO;

public interface LoginService{
	public LoginVO login(LoginDTO dto)throws Exception;

}