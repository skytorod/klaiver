package com.test.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.CompanyVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.test.mapper.AdminMapper";

	@Override
	public List<CompanyVO> getlist() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".getlist");
	}

	@Override
	public List<CompanyVO> getmember(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".getmember",map);
	}

	@Override
	public void updatemanager(CompanyVO vo) throws Exception {
		// TODO Auto-generated method stub
		 sqlSession.update(namespace +".updatemanager",vo);
	}

}
