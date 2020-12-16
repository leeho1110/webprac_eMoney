package com.test.webPrac.vo;

public class LoginVO {
	
	private String id;
	private String pw;
	private int accnt_id;
	private String loginApi;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getAccnt_id() {
		return accnt_id;
	}
	public void setAccnt_id(int accnt_id) {
		this.accnt_id = accnt_id;
	}
	public String getLoginApi() {
		return loginApi;
	}
	public void setLoginApi(String loginApi) {
		this.loginApi = loginApi;
	}
	
	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", pw=" + pw + ", accnt_id=" + accnt_id + ", loginApi=" + loginApi + "]";
	}
}
