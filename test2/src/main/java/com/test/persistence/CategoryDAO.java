package com.test.persistence;

import java.util.List;

import com.test.domain.CategoryVO;
import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;

public interface CategoryDAO {

	public List<CategoryVO> categorysearch(CategoryVO vo) throws Exception;

	public List<CategoryVO> smallcate(Integer mc_number) throws Exception;

	public List<SearchVO> listSearch(SearchCriteria cri) throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;
	

}
