package com.test.service;

import java.util.List;

import com.test.domain.K_aboutVO;
import com.test.domain.K_contactVO;
import com.test.domain.K_homeVO;
import com.test.domain.K_productVO;
import com.test.domain.SearchCriteria;
import com.test.domain.SearchVO;

public interface SearchService {
	
	
	//alllist company 5개만 나오는 것
	public List<SearchVO> list(SearchCriteria cri) throws Exception;
	//alllist solo 5개만 나오는것
	public List<SearchVO> listsolo(SearchCriteria cri) throws Exception;
	//alllist product 5개만 나오는것 
	public List<K_productVO> searchproduct(SearchCriteria cri) throws Exception;
	//list,sololist에 정보 가져오기	
	public List<SearchVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	//list,sololist페이징 쓰이는 카운트
	public int listSearchCount(SearchCriteria cri) throws Exception;
	//productlist에 정보 가져오기
	public List<K_productVO> listSearchproductCriteria(SearchCriteria cri)throws Exception;
	//productlist 페이징에 쓰이는 카운트
	public int listSearchproductCount(SearchCriteria cri) throws Exception;
	//sololist에 정보 가져오기	
	public int listsoloSearchCount(SearchCriteria cri)throws Exception;
	//sololist페이징 쓰이는 카운트
	public List<SearchVO> listsoloSearchCriteria(SearchCriteria cri)throws Exception;

}
