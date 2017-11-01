package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;
import com.test.persistence.SearchDAO;

@Service
public class SearchServiceImpl implements SearchService{
	@Inject
	private SearchDAO dao;

	////////////////////////////////////////////////////////////////////////
	@Override
	public List<SearchVO> list(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(cri);
	}
	@Override
	public List<SearchVO> listsolo(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listsolo(cri);
	}
	@Override
	public List<K_productVO> searchproduct(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchproduct(cri);
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
	public List<K_productVO> listSearchproductCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchproductCriteria(cri);
	}
	@Override
	public int listSearchproductCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchproductCount(cri);
	}

	@Override
	public int listsoloSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listsoloSearchCount(cri);
	}

	@Override
	public List<SearchVO> listsoloSearchCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listsoloSearchCriteria(cri);
	}
}
