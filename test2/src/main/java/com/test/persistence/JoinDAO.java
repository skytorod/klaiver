package com.test.persistence;

import java.util.List;

import com.test.domain.JoinOne;

public interface JoinDAO {
	public void insertOne(JoinOne jo) throws Exception;
	public List<JoinOne> getid(String id) throws Exception;;
	
	
}
