package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.NotesVO;
import com.test.persistence.NotesDAO;

@Service
public class NotesServiceImpl implements NotesService{

	@Inject 
	private NotesDAO dao;
	
	@Override
	public void sendnote(NotesVO nvo) throws Exception {
		String[] recvs = nvo.getRecv_id().split(",");
		for (String recv : recvs) {
			nvo.setRecv_id(recv);
			dao.sendnote(nvo);
		}
		
	}

	@Override
	public List<NotesVO> senderlist(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.senderlist(id);
	}
	@Override
	public void remove(Integer integer) throws Exception {
		dao.remove(integer);		
	}

	@Override
	public void updatenotes(int notes) throws Exception {
		// TODO Auto-generated method stub
		dao.update(notes);
	}

	@Override
	public NotesVO sebdercount(String count) throws Exception{		
		return dao.sebdercount(count);
	}

	@Override
	public void recymove(Integer integer) throws Exception {
		dao.recymoves(integer);
		
	}

	@Override
	public List<NotesVO> recyclelist(String id) throws Exception {
		return dao.recyclelist(id);
	}

	@Override
	public List<NotesVO> storagelist(String id)throws Exception {
		return dao.storagelist(id);
	}

	@Override
	public void storagemove(Integer integer)throws Exception {
		dao.storagemoves(integer);
		
	}

}
