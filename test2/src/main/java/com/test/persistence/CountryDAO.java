package com.test.persistence;

import java.util.List;

import com.test.domain.CountryVO;

public interface CountryDAO {

	public List<CountryVO>  countryget(CountryVO vo) throws Exception;

	public List<CountryVO> cityGet(String country) throws Exception;

}
