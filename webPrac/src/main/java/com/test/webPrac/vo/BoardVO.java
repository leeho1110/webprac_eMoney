package com.test.webPrac.vo;

public class BoardVO {
	
	private int post_num;
	private String title;
	private String content;
	private int writer;
	private String writer_name;
	private String time;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWriter_name() {
		return writer_name;
	}
	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}
	
	@Override
	public String toString() {
		return "BoardVO [post_num=" + post_num + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", writer_name=" + writer_name + ", time=" + time + "]";
	}
	
	
}
