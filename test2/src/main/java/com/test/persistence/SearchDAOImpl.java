package com.test.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;

@Repository
public class SearchDAOImpl implements SearchDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.test.mapper.SearchMapper";

	@Override
	public SearchVO search(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".search",keyword );
	}
	
	
	@Override
	public K_homeVO search_home(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".search_home",userid );
	}
	@Override
	public K_aboutVO search_about(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".search_about",userid );
	}
	@Override
	public List<K_productVO> search_product(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".search_product",userid );
	}
	@Override
	public K_contactVO search_contact(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".search_contact",userid );
	}
	@Override
	public List<SearchVO> getid(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".getid",cri);
	}
	@Override
	public List<K_homeVO> search_home_list(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".search_home_list",userid);
	}
	
////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public List<SearchVO> list(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".list",cri);
	}
	@Override
	public List<SearchVO> listsolo(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".listsolo",cri);
	}
	@Override
	public List<K_productVO> searchproduct(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".searchproduct",cri);
	}
	@Override
	public List<SearchVO> listSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".listSearch",cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".listSearchCount",cri);
	}
	@Override
	public List<K_productVO> listSearchproductCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".listSearchproductCriteria",cri);
	}
	@Override
	public int listSearchproductCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".listSearchproductCount",cri);
	}


	@Override
	public int listsoloSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".listsoloSearchCount",cri);
	}


	@Override
	public List<SearchVO> listsoloSearchCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".listsoloSearchCriteria",cri);
	}
}
