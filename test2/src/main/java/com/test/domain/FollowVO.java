package com.test.domain;

public class FollowVO {
	private int fid;
	private String myid;
	private String followid;
	private String email;
	private String username;
	private String profimg;
	private boolean flag;
	private boolean new_post;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfimg() {
		return profimg;
	}
	public void setProfimg(String profimg) {
		this.profimg = profimg;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isNew_post() {
		return new_post;
	}
	public void setNew_post(boolean new_post) {
		this.new_post = new_post;
	}
	
	
}
