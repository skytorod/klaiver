package com.test.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.IndividualVO;
import com.test.domain.Re_ReplyVO;
import com.test.domain.ReplyVO;
import com.test.domain.SearchCriteria;

@Repository
public class IndividualDAOImpl implements IndividualDAO{

	@Inject
	SqlSession sqlsession;
	
	private static final String namespace ="com.klaiverjoin.mappers.IndividualMapper";
	
	private static final String namespace_rep ="com.klaiverjoin.mappers.ReplyMapper";

	@Override
	public List<IndividualVO> Individual(String id) throws Exception {
		String email = id;
		return sqlsession.selectList(namespace+".soloView", email);
		
	}
/*
	@Override
	public void introduceinsert(IndividualVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		sqlsession.update(namespace+".introduceinsert", vo);
	}

	@Override
	public void introduceupdate(IndividualVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlsession.update(namespace+".introduceupdate", vo);
	}

	@Override
	public List<IndividualVO> followIndividual(String email) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".soloView", email);
	}

	@Override
	public List<IndividualVO> listSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".listSearch",cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".listSearchCount",cri);
	}

	@Override
	public List<IndividualVO> getsessionimg(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".getsessionimg",id);
	}

	@Override
	public void replyinsert(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlsession.insert(namespace_rep+".replyinsert",vo);
	}

	@Override
	public List<ReplyVO> replylist(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace_rep+".replylist",sbid);
	}

	@Override
	public List<ReplyVO> zroreplylist(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace_rep+".replylist",sbid);
	}

	@Override
	public void re_replyinsert(Re_ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlsession.insert(namespace_rep+".re_replyinsert",vo);
	}

	@Override
	public List<Re_ReplyVO> re_replylist(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace_rep+".re_replylist",sbid);
	}

	@Override
	public List<Re_ReplyVO> zrore_replylist(int sbid) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace_rep+".re_replylist",sbid);
	}

	@Override
	public List<Re_ReplyVO> re_Downreplylist(int re_boardid) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace_rep+".re_Downreplylist",re_boardid);
	}

	@Override
	public void delimg(int three) throws Exception {
		// TODO Auto-generated method stub
		sqlsession.update(namespace+".delimg",three);
	}

	@Override
	public List<IndividualVO> listsolo(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".listsolo",cri);
	}

	@Override
	public void updatepost(String id) throws Exception {
		// TODO Auto-generated method stub
		sqlsession.update(namespace+".updatepost",id);
	}*/
}
