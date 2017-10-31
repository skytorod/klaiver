package com.test.persistence;

import java.util.List;

import com.test.domain.NotesVO;

public interface NotesDAO {
 public void sendnote(NotesVO nvo)throws Exception;

public List<NotesVO> senderlist(String id) throws Exception;

public void remove(Integer integer) throws Exception;

public void update(int notes) throws Exception;

public NotesVO sebdercount(String count)throws Exception;

public void recymoves(Integer integer) throws Exception;

public List<NotesVO> recyclelist(String id) throws Exception;

public List<NotesVO> storagelist(String id)throws Exception;

public void storagemoves(Integer integer)throws Exception;
}
