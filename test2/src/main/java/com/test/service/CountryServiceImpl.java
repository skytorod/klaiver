package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.CountryVO;
import com.test.persistence.CountryDAO;

@Service
public class CountryServiceImpl implements CountryService{
	@Inject
	private CountryDAO dao;

	@Override
	public List<CountryVO> countryget(CountryVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.countryget(vo);
	}
	
	@Override
	public List<CountryVO> cityGet(String country) throws Exception {
		// TODO Auto-generated method stub
		return dao.cityGet(country);
	}
}
