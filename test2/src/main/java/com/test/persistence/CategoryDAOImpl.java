package com.test.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.CategoryVO;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.test.mapper.CategoryMapper";

	@Override
	public List<CategoryVO> categorysearch(CategoryVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList(namespace +".main", vo);
	}

	@Override
	public List<CategoryVO> smallcate(Integer mc_number) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(mc_number);
		return sqlSession.selectList(namespace +".small", mc_number);
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

}
