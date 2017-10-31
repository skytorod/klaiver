package com.test.domain;

public class Criteria {
	//맴버변수
	private int page;
	private int perPageNum;

	//생성자
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	//Getter
	public int getPage() {
		return page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}

	//Setter
	public void setPage(int page) {
		this.page = page;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}

		this.perPageNum = perPageNum;
	}

	//Method for Mybatis SQL Mapper
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page="+ page +", perPageNum="+ perPageNum +"]";
	}
}
