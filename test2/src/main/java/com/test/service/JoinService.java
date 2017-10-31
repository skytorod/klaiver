package com.test.service;

import java.util.List;

import com.test.domain.JoinOne;

public interface JoinService {
	public void insertOne(JoinOne jo)throws Exception;
	public List<JoinOne> getid(String id) throws Exception;;
	
	
}
