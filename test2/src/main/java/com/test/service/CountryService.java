package com.test.service;

import java.util.List;

import com.test.domain.CountryVO;

public interface CountryService {

	public List<CountryVO>  countryget(CountryVO vo) throws Exception;

	public List<CountryVO> cityGet(String country) throws Exception;
	
}
