package com.test.domain;

public class ScrollBoardVO {
	private String first;
	private String last;
	private String company;
	private String profimg;
	private int sbid;
	private String userid;
	private String content;
	private String image;
	private String regdate;
	private String readid;
	private String warea;
	private String bnew_post;
	private int fid;
	private String myid;
	private String followid;
	
	private String fakeimg;
	
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getSbid() {
		return sbid;
	}
	public void setSbid(int sbid) {
		this.sbid = sbid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getMyid() {
		return myid;
	}
	public void setMyid(String myid) {
		this.myid = myid;
	}
	public String getFollowid() {
		return followid;
	}
	public void setFollowid(String followid) {
		this.followid = followid;
	}
	@Override
	public String toString() {
		return "ScrollBoardVO [followid="+ followid +"]";
	}
	public String getReadid() {
		return readid;
	}
	public void setReadid(String readid) {
		this.readid = readid;
	}
	public String getWarea() {
		return warea;
	}
	public void setWarea(String warea) {
		this.warea = warea;
	}
	public String getFakeimg() {
		return fakeimg;
	}
	public void setFakeimg(String fakeimg) {
		this.fakeimg = fakeimg;
	}
	public String getProfimg() {
		return profimg;
	}
	public void setProfimg(String profimg) {
		this.profimg = profimg;
	}
	public String getBnew_post() {
		return bnew_post;
	}
	public void setBnew_post(String bnew_post) {
		this.bnew_post = bnew_post;
	}
	
}
