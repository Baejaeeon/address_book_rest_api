package com.example.demo.process.contact.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.process.contact.domain.Contact;
import com.example.demo.process.contact.service.ContactService;
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
	
	Logger logger = LoggerFactory.getLogger(ContactController.class); 
	
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
	@RequestMapping(value = "/createContact", method = RequestMethod.POST)
	public ResponseEntity<Contact> createContact(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Contact contact) throws Exception {
		
		//String name = ServletRequestUtils.getStringParameter(request, "name");
		//String phone_number = ServletRequestUtils.getStringParameter(request, "phone_number", "");
		//String email = ServletRequestUtils.getStringParameter(request, "email", "");
		//String address = ServletRequestUtils.getStringParameter(request, "address", "");
		
		//System.out.println(name + ", " + phone_number + "," + email + ", " + address);
		
		//Contact contact = new Contact();
		//contact.setName(name);
		//contact.setPhone_number(phone_number);
		//contact.setEmail(email);
		//contact.setAddress(address);
		
		logger.info("ContactController - createContact - contact: {}", contact.getName() + ", "
				+ contact.getPhone_number() + ", " + contact.getEmail() + ", " + contact.getAddress());
		
		// 주소록 등록
		contactService.createContact(contact);
		
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}
	
	/*
	 * 주소록 목록
	 */
	@RequestMapping(value = "/contactList", method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> contactList(HttpServletRequest request, HttpServletResponse response, Contact contact)
			throws Exception {

		// 등록된 주소록을 가져온다.
		List<Contact> contactList = contactService.getContactList(contact);

		//Gson gson = new Gson();
		//return gson.toJson(contactList); // Json 형태로 리턴
		
		return new ResponseEntity<List<Contact>>(contactList, HttpStatus.OK);
	}
	
	/*
	 * 주소록 수정
	 */
	@RequestMapping(value = "/modifyContact", method = RequestMethod.PUT)
	public ResponseEntity<Contact> modifyContact(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Contact contact) throws Exception {
		
		//int contact_id = ServletRequestUtils.getIntParameter(request, "contact_id");
		//String name = ServletRequestUtils.getStringParameter(request, "name");
		//String phone_number = ServletRequestUtils.getStringParameter(request, "phone_number", "");
		//String email = ServletRequestUtils.getStringParameter(request, "email", "");
		//String address = ServletRequestUtils.getStringParameter(request, "address", "");
		
		//Contact contact = new Contact();
		//contact.setContact_id(contact_id);
		//contact.setName(name);
		//contact.setPhone_number(phone_number);
		//contact.setEmail(email);
		//contact.setAddress(address);
		
		// 주소록 수정
		contactService.modifyContact(contact);
		
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}
	
	/*
	 * 주소록 삭제
	 */
	@RequestMapping(value = "/removeContact", method = RequestMethod.DELETE)
	public ResponseEntity<Contact> removeContact(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Contact contact) throws Exception {
		
		//int contact_id = ServletRequestUtils.getIntParameter(request, "contact_id");
		
		//Contact contact = new Contact();
		//contact.setContact_id(contact_id);
		
		// 주소록 삭제
		contactService.removeContact(contact);
		
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}
	
	/*
	 * 주소록 상세 (식별자로 데이터를 가져온다.)
	 */
	@RequestMapping(value = "/contactDetail", method = RequestMethod.GET)
	public ResponseEntity<Contact> contactDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contact_id") int contact_id) throws Exception {
		
		//int contact_id = ServletRequestUtils.getIntParameter(request, "contact_id");
		
		Contact contact = new Contact();
		contact.setContact_id(contact_id);
		
		// 주소록 상세
		Contact contactInfo = contactService.contactDetail(contact);
		
		//Gson gson = new Gson();
		//return gson.toJson(contactInfo);
		return new ResponseEntity<Contact>(contactInfo, HttpStatus.OK);
	}
}
