package com.test.domain;

public class SearchCriteria extends Criteria {
	private String searchType;
	private String keyword;
	private String keyword1;
	private String keyword2;

	//Getter
	public String getSearchType() {
		return searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public String getKeyword1() {
		return keyword1;
	}
	
	public String getKeyword2() {
		return keyword2;
	}
	//Setter
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	@Override
	public String toString() {
		return super.toString() +" SearchCriteria [searchType="+ searchType 
				+", keyword="+ keyword +", keyword1="+ keyword1 +"]"+", keyword2="+ keyword2 +"]";
	}
}
