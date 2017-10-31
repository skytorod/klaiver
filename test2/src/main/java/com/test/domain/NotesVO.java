package com.test.domain;

public class NotesVO {
	private int notes_id; // 식별번호로 중복되지 않는 유일한 값 (비교 값)
	private String recv_id; // 받는이 아이디
	private String sent_id; // 보낸이 아이디
	private String title; // 쪽지 제목
	private String note; // 쪽지 내용
	private String date_sent; //보낸 날짜
	private String date_read; //받는이가 읽은 날짜 -> 체크후 y 이면
	private String recv_read; //받는이가 읽었는지의 여부 -> 여기에 체크
	private String recv_del; //받는이가 받은 쪽지함에서 쪽지를 삭제하였는지의 여부
	private String sent_del; //보낸이가 보낸 쪽지함에서 쪽지를 삭제하였는지의 여부
	private String recycle_yn; // 휴지통으로 이동 여부
	private String storage_yn; // 보관함으로 이동 여부
	private String count;
	

	public String getStorage_yn() {
		return storage_yn;
	}
	public void setStorage_yn(String storage_yn) {
		this.storage_yn = storage_yn;
	}
	
	public String getRecycle_yn() {
		return recycle_yn;
	}
	public void setRecycle_yn(String recycle_yn) {
		this.recycle_yn = recycle_yn;
	}	
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	public int getNotes_id() {
		return notes_id;
	}
	public void setNotes_id(int notes_id) {
		this.notes_id = notes_id;
	}
	
	public String getRecv_id() {
		return recv_id;
	}
	public void setRecv_id(String recv_id) {
		this.recv_id = recv_id;
	}
	
	public String getSent_id() {
	    return sent_id;
	}
	public void setSent_id(String sent_id) {
		this.sent_id = sent_id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getDate_sent() {
		return date_sent;
	}
	public void setDate_sent(String date_sent) {
		this.date_sent = date_sent;
	}
	
	public String getDate_read() {
		return date_read;
	}
	public void setDate_read(String date_read) {
		this.date_read = date_read;
	}
	
	public String getRecv_read() {
		return recv_read;
	}
	public void setRecv_read(String recv_read) {
		this.recv_read = recv_read;
	}
	
	public String getRecv_del() {
		return recv_del;
	}
	public void setRecv_del(String recv_del) {
		this.recv_del = recv_del;
	}
	
	public String getSent_del() {
		return sent_del;
	}
	public void setSent_del(String sent_del) {
		this.sent_del = sent_del;
	}
}
