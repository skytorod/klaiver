package com.test.persistence;

import java.util.List;

import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;

public interface SearchDAO {
	public List<SearchVO> list(SearchCriteria cri)throws Exception;
	
	public List<SearchVO> listsolo(SearchCriteria cri) throws Exception;
	
	public List<K_productVO> searchproduct(SearchCriteria cri) throws Exception;
	
	public List<SearchVO> listSearch(SearchCriteria cri) throws Exception;
	
	public int listSearchCount(SearchCriteria cri)throws Exception;
	
	public List<K_productVO> listSearchproductCriteria(SearchCriteria cri) throws Exception;
	
	public int listSearchproductCount(SearchCriteria cri) throws Exception;

	public int listsoloSearchCount(SearchCriteria cri)throws Exception;

	public List<SearchVO> listsoloSearchCriteria(SearchCriteria cri)throws Exception;
}
