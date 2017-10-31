package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.domain.CompanyVO;

public interface AdminService {

	public List<CompanyVO> getlist() throws Exception;

	public List<CompanyVO> getmember(Map<String, String> map) throws Exception;

	public void updatemanager(CompanyVO vo)throws Exception;

}
