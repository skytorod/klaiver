package com.test.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.NotesVO;

@Repository
public class NotesDAOImpl implements NotesDAO{

	@Inject
	private SqlSession session;
	
	private static final String namespace = "com.test.mappers.NotesMapper";

	
	@Override
	public void sendnote(NotesVO nvo) throws Exception {
		session.insert(namespace+".insertnotes", nvo);
	}


	@Override
	public List<NotesVO> senderlist(String id) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".sendernotes", id);
	}


	@Override
	public void remove(Integer integer) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace+".deletenotes", integer);
	}


	@Override
	public void update(int notes) throws Exception {
		// TODO Auto-generated method stub
		 session.update(namespace+".updatenotes",notes);
	}


	@Override
	   public NotesVO sebdercount(String count) throws Exception {
	      // TODO Auto-generated method stub
	      return session.selectOne(namespace+".sebdercount", count);
	   }

	// 휴지통 에서 이동
	@Override
	public void recymoves(Integer integer) throws Exception {
		session.update(namespace+".recycleupdate",integer);
		
	}

	@Override
	public List<NotesVO> recyclelist(String id) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".recylenotes", id);
	}


	@Override
	public List<NotesVO> storagelist(String id) throws Exception{
		return session.selectList(namespace+".storagenotes", id);
	}

// 보관함으로 이동
	@Override
	public void storagemoves(Integer integer)throws Exception {
		session.update(namespace+".storageupdate",integer);
		
	}

}
