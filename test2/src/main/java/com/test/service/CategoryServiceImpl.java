package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.CategoryVO;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;
import com.test.persistence.CategoryDAO;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Inject
	private CategoryDAO dao;

	@Override
	public List<CategoryVO> categoryget(CategoryVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.categorysearch(vo);
	}

	@Override
	public List<CategoryVO> smallcate(Integer mc_number) throws Exception {
		// TODO Auto-generated method stub
		return dao.smallcate(mc_number);
	}
	@Override
	public List<SearchVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}
	@Override
	public K_homeVO search_home(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.search_home(userid);
	}
	@Override
	public K_aboutVO search_about(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.search_about(userid);
	}
	@Override
	public List<K_productVO> search_product(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.search_product(userid);
	}
	@Override
	public K_contactVO search_contact(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.search_contact(userid);
	}
}
