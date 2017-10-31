package com.test.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.DTO.LoginDTO;
import com.test.domain.LoginVO;

@Repository
public class LoginDAOImpl implements LoginDAO{
	
	@Inject
	private SqlSession session;
	
	private static final String namespace = "com.klaiverjoin.mappers.LoginMapper";

	@Override
	public LoginVO login(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".loginCheck", dto);
	}

	
}
