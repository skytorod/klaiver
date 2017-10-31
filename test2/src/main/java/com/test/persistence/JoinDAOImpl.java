package com.test.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.JoinOne;

@Repository
public class JoinDAOImpl implements JoinDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	

	
	private static final String namespace = "com.klaiverjoin.mappers.JoinMapper";
	
	
	@Override
	public void insertOne(JoinOne jo) throws Exception {
		sqlSession.insert(namespace+".k_joinone",jo);
		
	}


	@Override
	public List<JoinOne> getid(String id) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("dao"+id);
		return sqlSession.selectList(namespace+".getid",id);
	}


	
	
}
