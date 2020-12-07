package com.test.webPrac.vo;

public class LoginVO {
	
	private String id;
	private String pw;
	private int accnt_id;
	private char is_mobile;
	private String ip;
	private String browser;
	private String os;
	
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
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public boolean getIs_mobile() {
		// true : 1 / false : 0
		return is_mobile == '1' ? true : false;
	}
	public char setIs_mobile(boolean is_mobile) {
		// true : 1 / false : 0
		if(is_mobile){ return '1'; } 
		else { return '0'; }
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
