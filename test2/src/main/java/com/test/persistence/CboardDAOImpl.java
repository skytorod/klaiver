package com.test.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.CompanyVO;
import com.test.domain.IndividualVO;
import com.test.domain.JoinOne;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;

@Repository
public class CboardDAOImpl implements CboardDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.test.mapper.CboardMapper";


	@Override
	public List<JoinOne> memberinfo(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".memberinfo",userid);
	}

	@Override
	public List<JoinOne> serssionidName(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".serssionidName",id);
	}

	@Override
	public K_aboutVO readPage_about(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".readPage_about",userid);
	}

	@Override
	public List<K_productVO> readPage_product(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".readPage_product",userid);
	}

	@Override
	public K_contactVO readPage_contact(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".readPage_contact",userid);
	}

	@Override
	public void insert_about(K_aboutVO avo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace +".insert_about",avo);
	}

	@Override
	public void insert_contact(K_contactVO cvo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace +".insert_contact",cvo);
	}

	@Override
	public void insert_product(K_productVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace +".insert_product",vo);
	}

	@Override
	public void homeupdate(JoinOne vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace +".homeupdate",vo);
	}

	@Override
	public void update_about(K_aboutVO avo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace +".update_about",avo);
	}

	@Override
	public void update_contact(K_contactVO cvo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace +".update_contact",cvo);
	}

	@Override
	public K_productVO updateread_product(int pid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".updateread_product",pid);
	}

	@Override
	public void update_product(K_productVO pvo) throws Exception {
		// TODO Auto-generated method stub
		 sqlSession.selectOne(namespace +".update_product",pvo);
	}

	@Override
	public void remove(int pid) throws Exception {
		// TODO Auto-generated method stub
		 sqlSession.delete(namespace +".remove",pid);
	}
}
