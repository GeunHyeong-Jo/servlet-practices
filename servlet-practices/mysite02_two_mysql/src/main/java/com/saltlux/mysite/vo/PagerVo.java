package com.saltlux.mysite.vo;

public class PagerVo {
	private int nowPage;
	private int total;
	private int lastPage;
	private int firstPageInCurrentPageGroup;
	private int lastPageInCurrentPageGroup;

	private int cntPerPage = 10;
	private int cntPage = 5;

	public PagerVo(int total, int cntPage, int nowPage ) {
		this.total = total;
		this.cntPage = cntPage;
		this.nowPage = nowPage;
		
		calcLastPage(total, cntPerPage);
		
		if(nowPage <= cntPage/2) {
			this.firstPageInCurrentPageGroup = 1;
			this.lastPageInCurrentPageGroup =  cntPage;
			if(lastPage < this.lastPageInCurrentPageGroup) {
				this.lastPageInCurrentPageGroup = lastPage;
			}
		} else {
			this.firstPageInCurrentPageGroup = nowPage - (cntPage-1)/2;
			this.lastPageInCurrentPageGroup =  nowPage + (cntPage-1)/2;
			if(this.lastPageInCurrentPageGroup >= lastPage) {
				this.lastPageInCurrentPageGroup =  lastPage;
			}
		}
	}
	public void calcLastPage(int total, int cntPerPage) {
		setLastPage((int) Math.ceil((double) total / (double) cntPerPage));
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCntPage() {
		return cntPage;
	}

	public void setCntPage(int cntPage) {
		this.cntPage = cntPage;
	}

	public int getFirstPageInCurrentPageGroup() {
		return firstPageInCurrentPageGroup;
	}

	public void setFirstPageInCurrentPageGroup(int firstPageInCurrentPageGroup) {
		this.firstPageInCurrentPageGroup = firstPageInCurrentPageGroup;
	}

	public int getLastPageInCurrentPageGroup() {
		return lastPageInCurrentPageGroup;
	}

	public void setLastPageInCurrentPageGroup(int lastPageInCurrentPageGroup) {
		this.lastPageInCurrentPageGroup = lastPageInCurrentPageGroup;
	}

	@Override
	public String toString() {
		return "PagerVo [nowPage=" + nowPage + ", total=" + total + ", lastPage=" + lastPage
				+ ", firstPageInCurrentPageGroup=" + firstPageInCurrentPageGroup + ", lastPageInCurrentPageGroup="
				+ lastPageInCurrentPageGroup + ", cntPerPage=" + cntPerPage + ", cntPage=" + cntPage + "]";
	}

}