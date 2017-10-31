package com.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.FollowVO;
import com.test.persistence.FollowDAO;

@Service
public class FollowServiceImpl implements FollowService {
	@Inject
	private FollowDAO dao;
//////회사정보 insert

	@Override
	public void insertfollow(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		dao.insertfollow(map);
	}

	@Override
	public List<FollowVO> listSearchCriteriaCompany(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCriteriaCompany(map);
	}

	@Override
	public List<FollowVO> listSearchCriteriasearch(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchsearch(id);
	}

	@Override
	public List<FollowVO> getid(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.getid(id);
	}

	@Override
	public void delete(Map<String, String> map1) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(map1);
	}

	@Override
	public int followercount(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.followercount(id);
	}

	@Override
	public int followingcount(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.followingcount(id);
	}
	@Override
	public int followerComcount(String code) throws Exception {
		// TODO Auto-generated method stub
		return dao.followerComcount(code);
	}

	@Override
	public int followingComcount(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.followingComcount(id);
	}
	@Override
	public List<FollowVO> getComid(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.getComid(map);
	}

	@Override
	public void insertComfollow(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		dao.insertComfollow(map);
	}
	@Override
	public void deleteCom(Map<String, String> map1) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteCom(map1);
	}

	@Override
	public List<FollowVO> soloViewfollower(String email) throws Exception {
		// TODO Auto-generated method stub
		return dao.soloViewfollower(email);
	}

	@Override
	public List<FollowVO> soloViewfollowerScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.soloViewfollowerScroll(map);
	}

	@Override
	public List<FollowVO> soloViewfollowing(String email) throws Exception {
		// TODO Auto-generated method stub
		return dao.soloViewfollowing(email);
	}

	@Override
	public List<FollowVO> soloViewfollowingScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.soloViewfollowingScroll(map);
	}

	@Override
	public List<FollowVO> myCompanyfolloer(String code) throws Exception {
		// TODO Auto-generated method stub
		return dao.myCompanyfolloer(code);
	}

	@Override
	public List<FollowVO> soloComfollowerScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.soloComfollowerScroll(map);
	}

	@Override
	public List<FollowVO> myCompanyfolloing(String email) throws Exception {
		// TODO Auto-generated method stub
		return dao.myCompanyfolloing(email);
	}

	@Override
	public List<FollowVO> soloComfollowingScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.soloComfollowingScroll(map);
	}

	@Override
	public List<FollowVO> comViewfollower(String email) throws Exception {
		// TODO Auto-generated method stub
		return dao.comViewfollower(email);
	}

	@Override
	public List<FollowVO> comViewfollowerScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.comViewfollowerScroll(map);
	}

	@Override
	public List<FollowVO> comViewfollowing(String email) throws Exception {
		// TODO Auto-generated method stub
		return dao.comViewfollowing(email);
	}

	@Override
	public List<FollowVO> comViewfollowingScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.comViewfollowingScroll(map);
	}

	@Override
	public void newpost(String id) throws Exception {
		// TODO Auto-generated method stub
		 dao.newpost(id);
	}

	@Override
	public void flasenewpost(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		 dao.flasenewpost(map);
	}

	

}
