package cn.appsys.tools;

import java.util.List;

/**
 * 分页工具类
 * @author 
 *
 */
public class PageSupport<T> {
	//当前页码-来自于用户输入
	private int currentPageNo = 1;
	
	//总数量（表）
	private int totalCount = 0;
	
	//页面容量
	private int pageSize = 0;
	
	//总页数-totalCount/pageSize（+1）
	private int totalPageCount = 1;

	private List<T> list;
	
	
	
	public PageSupport(int currentPageNo, int totalCount, int pageSize,
			List<T> list) {
		super();
		this.currentPageNo = currentPageNo;
		totalPageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.list = list;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}