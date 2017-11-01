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
	
}
