package com.test.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.CountryVO;

@Repository
public class CountryDAOImpl implements CountryDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.test.mapper.CountryMapper";

	@Override
	public List<CountryVO> countryget(CountryVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList(namespace +".countryget", vo);
	}

	@Override
	public List<CountryVO> cityGet(String country) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(country);
		return sqlSession.selectList(namespace +".cityGet", country);
	}
}
