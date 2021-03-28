package com.saltlux.mysite.vo;

public class BoardVo { 
	private Long no;
	private String title;
	private String author;
	private Long views;
	private String reg_date;
	private String content;
	private Long g_no;
	private Long o_no;
	private Long depth;
	private Long user_no;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getG_no() {
		return g_no;
	}
	public void setG_no(Long g_no) {
		this.g_no = g_no;
	}
	public Long getO_no() {
		return o_no;
	}
	public void setO_no(Long o_no) {
		this.o_no = o_no;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", author=" + author + ", views=" + views + ", reg_date="
				+ reg_date + ", content=" + content + ", g_no=" + g_no + ", o_no=" + o_no + ", depth=" + depth
				+ ", user_no=" + user_no + "]";
	}
}
