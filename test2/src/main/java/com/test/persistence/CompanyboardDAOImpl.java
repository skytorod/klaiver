package com.test.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.ComReplyVO;
import com.test.domain.CompanyBoardVO;

@Repository
public class CompanyboardDAOImpl implements CompanyboardDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.test.mapper.CompanyboardMapper";

	@Override
	public void insert(CompanyBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace +".insert", vo);
	}

	@Override
	public List<CompanyBoardVO> listSearchCriteria(String code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".listSearchCriteria", code);
	}


	@Override
	public String searchmanager(String code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".searchmanager", code);
	}

	@Override
	public List<CompanyBoardVO> scrolllistSearchCriteria(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".scrolllistSearchCriteria", map);
	}

	@Override
	public void comreplyinsert(ComReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.selectList(namespace +".comreplyinsert", vo);
	}

	@Override
	public List<ComReplyVO> comreplylist(int cbid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".comreplylist", cbid);
	}

	@Override
	public List<ComReplyVO> comscrollreplylist(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".comscrollreplylist", map);
	}

	@Override
	public List<CompanyBoardVO> selectupdateBoard(int cbid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".selectupdateBoard", cbid);
	}

	@Override
	public void newsUpdatee(CompanyBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace +".newsUpdatee", vo);
	}

	@Override
	public void newsremove(int cbid) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace +".newsremove", cbid);
	}

	@Override
	public String searchimage(int cbid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace +".searchimage", cbid);
	}

	@Override
	public List<ComReplyVO> searchreplyid(int cbid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".searchreplyid", cbid);
	}

	@Override
	public void maindelAfterRepRemove(int cbid) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace +".maindelAfterRepRemove", cbid);
	}

	@Override
	public List<ComReplyVO> getnewsRepsUpdate(int crid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".getnewsRepsUpdate", crid);
	}

	@Override
	public void newsRepUpdate(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace +".newsRepUpdate", map);
	}

	@Override
	public void replyremove(int crid) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace +".replyremove", crid);
	}

	

}
