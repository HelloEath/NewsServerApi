package com.glut.news.vo;

import java.util.List;

public class JsonModel {
	private int status;//	1
	private int	totalCount;//	322
	private int	ftotalCount;//	198
	private int	pageSize	;//20
	private int pageNumber;//	2
	private int returnNum	;//20
	private List<DataList> data	;//[…]
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getFtotalCount() {
		return ftotalCount;
	}
	public void setFtotalCount(int ftotalCount) {
		this.ftotalCount = ftotalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(int returnNum) {
		this.returnNum = returnNum;
	}
	public List<DataList> getData() {
		return data;
	}
	public void setData(List<DataList> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "JsonModel [status=" + status + ", totalCount=" + totalCount + ", ftotalCount=" + ftotalCount
				+ ", pageSize=" + pageSize + ", pageNumber=" + pageNumber + ", returnNum=" + returnNum + ", data="
				+ data + "]";
	}
	
	

}
