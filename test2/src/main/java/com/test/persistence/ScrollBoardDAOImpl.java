package com.test.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.ReplyVO;
import com.test.domain.ScrollBoardVO;
import com.test.domain.SearchCriteria;

@Repository
public class ScrollBoardDAOImpl implements ScrollBoardDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.test.mapper.ScrollboardMapper";
	
	private static final String namespace_rep ="com.klaiverjoin.mappers.ReplyMapper";
	@Override
	public void insert(ScrollBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".insertboard",vo);
	}

	@Override
	public List<ScrollBoardVO> listSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".listSearch",cri);
	}

	@Override
	public List<ScrollBoardVO> infiniteScrillDown(Map<String, String> map) throws Exception {
		// TODO Auto-generated method 
		return sqlSession.selectList(namespace+".infiniteScrillDown",map);
	}

	@Override
	public List<ScrollBoardVO> categoryget(ScrollBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getid",vo);
	}

	@Override
	public List<ScrollBoardVO> zerolistSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("온다"+cri.getKeyword());
		return sqlSession.selectList(namespace+".zerolistSearch",cri);
	}

	@Override
	public List<ScrollBoardVO> zeroinfiniteScrillDown(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".zeroinfiniteScrillDown",map);
	}

	@Override
	public List<ScrollBoardVO> sololistSearchCriteria(String email) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".sololistSearchCriteria",email);
	}

	@Override
	public List<ScrollBoardVO> soloinfiniteScrillDown(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".soloinfiniteScrillDown",map);
	}

	@Override
	public List<ScrollBoardVO> serssionidName(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".serssionidName",id);
	}

	@Override
	public List<ScrollBoardVO> updateBoard(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".updateBoard",sbid);
	}

	@Override
	public void individualBupdatePOST(ScrollBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".individualBupdatePOST",vo);
	}

	@Override
	public void remove(int sbid) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".remove",sbid);
	}

	@Override
	public List<ReplyVO> getReplyupdate(int rid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace_rep+".getReplyupdate",rid);
	}

	@Override
	public void updaterep(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace_rep+".updaterep",map);
	}

	@Override
	public void replyremove(int rid) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace_rep+".replyremove",rid);
	}

	@Override
	public List<ReplyVO> searchsbid(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".searchsbid",map);
	}

	@Override
	public List<ScrollBoardVO> updatenewpost(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".updatenewpost",id);
	}

}
