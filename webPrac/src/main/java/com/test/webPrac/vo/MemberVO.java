package com.test.webPrac.vo;

public class MemberVO {

	private int accnt_id;
	private String nickname;
	private String name;
	private String user_type;
	private String phone;
	private String id;
	private String s_passwd;
	private String last_loginDATE;
	private String salt;
	private char is_mobile;
	private String ip;
	private String browser;
	private String os;

	
	public boolean getIs_mobile() {
        // true : 1 / false : 0
        return is_mobile == '1' ? true : false;
	}
	public void setIs_mobile(Boolean is_mobile) {
        // true : 1 / false : 0
        if(is_mobile){ this.is_mobile = '1'; } 
        else { this.is_mobile = '0'; }
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public int getAccnt_id() {
		return accnt_id;
	}
	public void setAccnt_id(int accnt_id) {
		this.accnt_id = accnt_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getS_passwd() {
		return s_passwd;
	}
	public void setS_passwd(String s_passwd) {
		this.s_passwd = s_passwd;
	}
	public String getLast_loginDATE() {
		return last_loginDATE;
	}
	public void setLast_loginDATE(String last_loginDATE) {
		this.last_loginDATE = last_loginDATE;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}