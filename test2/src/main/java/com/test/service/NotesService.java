package com.test.service;

import java.util.List;

import com.test.domain.NotesVO;

public interface NotesService {
	 public void sendnote(NotesVO nvo)throws Exception;

	public List<NotesVO> senderlist(String id) throws Exception;

	public  void remove(Integer integer)throws Exception;

	public void updatenotes(int notes)throws Exception;
	
	public NotesVO sebdercount(String count)throws Exception;

	public void recymove(Integer integer)throws Exception;

	public List<NotesVO> recyclelist(String id) throws Exception;

	public List<NotesVO> storagelist(String id) throws Exception;

	public void storagemove(Integer integer) throws Exception;
	
	


}
