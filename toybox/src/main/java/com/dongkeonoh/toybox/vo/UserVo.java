package com.dongkeonoh.toybox.vo;

public class UserVo {

	private String usr_id;		
	private String usr_password;
	private String usr_name;
	private String usr_image;	
	private String usr_sms;		
	private String usr_kakao;	
	private String usr_active;
	private int cnt;
	
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_password() {
		return usr_password;
	}
	public void setUsr_password(String usr_password) {
		this.usr_password = usr_password;
	}
	public String getUsr_name() {
		return usr_name;
	}
	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}
	public String getUsr_image() {
		return usr_image;
	}
	public void setUsr_image(String usr_image) {
		this.usr_image = usr_image;
	}
	public String getUsr_sms() {
		return usr_sms;
	}
	public void setUsr_sms(String usr_sms) {
		this.usr_sms = usr_sms;
	}
	public String getUsr_kakao() {
		return usr_kakao;
	}
	public void setUsr_kakao(String usr_kakao) {
		this.usr_kakao = usr_kakao;
	}
	public String getUsr_active() {
		return usr_active;
	}
	public void setUsr_active(String usr_active) {
		this.usr_active = usr_active;
	}	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}	
}
