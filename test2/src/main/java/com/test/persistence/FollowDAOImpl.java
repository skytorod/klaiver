package com.test.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.FollowVO;

@Repository
public class FollowDAOImpl implements FollowDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.test.mapper.FollowMapper";

	@Override
	public void insertfollow(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace +".insertfollow",map);
	}
	
	@Override
	public List<FollowVO> listSearchCriteriaCompany(Map<String, String> map) throws Exception {
		
		return sqlSession.selectList(namespace+".listSearchCriteriaCompany",map);
	}

	@Override
	public List<FollowVO> listSearchsearch(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".listSearchsearch",id);
	}

	@Override
	public List<FollowVO>  getid(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getid",id);
	}

	@Override
	public void delete(Map<String, String> map1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(map1);
		sqlSession.delete(namespace+".delete",map1);
	}

	@Override
	public int followercount(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".followercount",id);
	}

	@Override
	public int followingcount(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".followingcount",id);
	}
	@Override
	public int followerComcount(String code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".followerComcount",code);
	}

	@Override
	public int followingComcount(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".followingComcount",id);
	}

	@Override
	public List<FollowVO> getComid(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getComid",map);
	}

	@Override
	public void insertComfollow(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace +".insertComfollow",map);
	}
	@Override
	public void deleteCom(Map<String, String> map1) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".deleteCom",map1);
	}

	@Override
	public List<FollowVO> soloViewfollower(String email) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".soloViewfollower",email);
	}

	@Override
	public List<FollowVO> soloViewfollowerScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".soloViewfollowerScroll",map);
	}

	@Override
	public List<FollowVO> soloViewfollowing(String email) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".soloViewfollowing",email);
	}

	@Override
	public List<FollowVO> soloViewfollowingScroll(Map<String, String> map) throws Exception {
		return sqlSession.selectList(namespace+".soloViewfollowingScroll",map);
	}

	@Override
	public List<FollowVO> myCompanyfolloer(String code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".myCompanyfolloer",code);
	}

	@Override
	public List<FollowVO> soloComfollowerScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".soloComfollowerScroll",map);
	}

	@Override
	public List<FollowVO> myCompanyfolloing(String email) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".myCompanyfolloing",email);
	}

	@Override
	public List<FollowVO> soloComfollowingScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".soloComfollowingScroll",map);
	}

	@Override
	public List<FollowVO> comViewfollower(String email) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".comViewfollower",email);
	}

	@Override
	public List<FollowVO> comViewfollowerScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".comViewfollowerScroll",map);
	}

	@Override
	public List<FollowVO> comViewfollowing(String email) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".comViewfollowing",email);
	}

	@Override
	public List<FollowVO> comViewfollowingScroll(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".comViewfollowingScroll",map);
	}

	@Override
	public void newpost(String id) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".newpost",id);
	}

	@Override
	public void flasenewpost(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".flasenewpost",map);
	}

}
