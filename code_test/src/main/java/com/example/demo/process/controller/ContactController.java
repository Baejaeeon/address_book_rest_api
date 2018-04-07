package com.example.demo.process.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.process.domain.Contact;
import com.example.demo.process.service.ContactService;
import com.google.gson.Gson;


/**
 * 주소록 Controller
 * 
 * @author baejaeeon
 *
 */
@RestController
@RequestMapping(value = "/contact")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	/*
	 * 테스트
	 */
	@RequestMapping("/test")
	public String hello() {
		return "Hello, Spring Boot~!";
	}

	/*
	 * 주소록 등록
	 */
	@RequestMapping(value = "/createContact")
	public String createContact(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = ServletRequestUtils.getStringParameter(request, "name");
		String phone_number = ServletRequestUtils.getStringParameter(request, "phone_number", "");
		String email = ServletRequestUtils.getStringParameter(request, "email", "");
		String address = ServletRequestUtils.getStringParameter(request, "address", "");
		
		Contact contact = new Contact();
		contact.setName(name);
		contact.setPhone_number(phone_number);
		contact.setEmail(email);
		contact.setAddress(address);
		
		// 주소록 등록
		contactService.createContact(contact);
		
		return "success";
	}
	
	/*
	 * 주소록 목록
	 */
	@RequestMapping(value = "/contactList")
	public String contactList(HttpServletRequest request, HttpServletResponse response, Contact contact)
			throws Exception {

		// 등록된 주소록을 가져온다.
		List<Contact> contactList = contactService.getContactList(contact);

		Gson gson = new Gson();
		return gson.toJson(contactList); // Json 형태로 리턴
	}
	
	/*
	 * 주소록 수정
	 */
	@RequestMapping(value = "/modifyContact")
	public String modifyContact(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int contact_id = ServletRequestUtils.getIntParameter(request, "contact_id");
		String name = ServletRequestUtils.getStringParameter(request, "name");
		String phone_number = ServletRequestUtils.getStringParameter(request, "phone_number", "");
		String email = ServletRequestUtils.getStringParameter(request, "email", "");
		String address = ServletRequestUtils.getStringParameter(request, "address", "");
		
		Contact contact = new Contact();
		contact.setContact_id(contact_id);
		contact.setName(name);
		contact.setPhone_number(phone_number);
		contact.setEmail(email);
		contact.setAddress(address);
		
		// 주소록 수정
		contactService.modifyContact(contact);
		
		return "success";
	}
	
	/*
	 * 주소록 삭제
	 */
	@RequestMapping(value = "/removeContact")
	public String removeContact(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int contact_id = ServletRequestUtils.getIntParameter(request, "contact_id");
		
		Contact contact = new Contact();
		contact.setContact_id(contact_id);
		
		// 주소록 삭제
		contactService.removeContact(contact);
		
		return "success";
	}
	
	/*
	 * 주소록 상세 (식별자로 데이터를 가져온다.)
	 */
	@RequestMapping(value = "/contactDetail")
	public String contactDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int contact_id = ServletRequestUtils.getIntParameter(request, "contact_id");
		
		Contact contact = new Contact();
		contact.setContact_id(contact_id);
		
		// 주소록 상세
		Contact contactInfo = contactService.contactDetail(contact);
		
		Gson gson = new Gson();
		return gson.toJson(contactInfo);
	}
}
