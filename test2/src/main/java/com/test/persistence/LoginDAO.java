package com.test.persistence;

import com.test.DTO.LoginDTO;
import com.test.domain.LoginVO;

public interface LoginDAO {
	
	public LoginVO login(LoginDTO dto)throws Exception;

	
}
