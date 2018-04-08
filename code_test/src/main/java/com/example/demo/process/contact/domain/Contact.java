package com.example.demo.process.contact.domain;

/**
 * 주소록 도메인
 * 
 * @author baejaeeon
 *
 */
public class Contact {

	private Integer contact_id;				// 주소록 ID
	private String name; 					// 이름
	private String phone_number;			// 휴대전화번호
	private String email;					// 이메일
	private String address;					// 주소
	private String registered_datetime;		// 등록일시
	private String last_updated_datetime; 	// 변경일시
	
	public Integer getContact_id() {
		return contact_id;
	}
	public void setContact_id(Integer contact_id) {
		this.contact_id = contact_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegistered_datetime() {
		return registered_datetime;
	}
	public void setRegistered_datetime(String registered_datetime) {
		this.registered_datetime = registered_datetime;
	}
	public String getLast_updated_datetime() {
		return last_updated_datetime;
	}
	public void setLast_updated_datetime(String last_updated_datetime) {
		this.last_updated_datetime = last_updated_datetime;
	}
	
}