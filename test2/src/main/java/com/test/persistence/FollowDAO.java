package com.test.persistence;

import java.util.List;
import java.util.Map;

import com.test.domain.FollowVO;

public interface FollowDAO {

	
	
	public List<FollowVO> listSearchCriteriaCompany(Map<String, String> map)throws Exception;



	

	

	public int followercount(String id) throws Exception;

	public int followingcount(String id) throws Exception;
	
	public int followerComcount(String id) throws Exception;

	public int followingComcount(String id) throws Exception;

	public List<FollowVO> getComid(Map<String, String> map) throws Exception;

	public void insertComfollow(Map<String, String> map) throws Exception;

	public void deleteCom(Map<String, String> map1) throws Exception;

	public List<FollowVO> soloViewfollower(String email) throws Exception;

	public List<FollowVO> soloViewfollowerScroll(Map<String, String> map) throws Exception;

	public List<FollowVO> soloViewfollowing(String email) throws Exception;

	public List<FollowVO> soloViewfollowingScroll(Map<String, String> map) throws Exception;

	public List<FollowVO> myCompanyfolloer(String code) throws Exception;

	public List<FollowVO> soloComfollowerScroll(Map<String, String> map) throws Exception;

	public List<FollowVO> myCompanyfolloing(String email) throws Exception;

	public List<FollowVO> soloComfollowingScroll(Map<String, String> map) throws Exception;

	public List<FollowVO> comViewfollower(String email) throws Exception;

	public List<FollowVO> comViewfollowerScroll(Map<String, String> map) throws Exception;

	public List<FollowVO> comViewfollowing(String email) throws Exception;

	public List<FollowVO> comViewfollowingScroll(Map<String, String> map) throws Exception;

	public void newpost(String id) throws Exception;

	public void flasenewpost(Map<String, String> map)  throws Exception;

	///////////////////////////////////////////////////////////////////
	public List<FollowVO> listSearchsearch(String id) throws Exception;
	
	public List<FollowVO> getid(String id)throws Exception;
	
	public void insertfollow(Map<String, String> map) throws Exception;
	
	public void delete(Map<String, String> map1) throws Exception;
}
