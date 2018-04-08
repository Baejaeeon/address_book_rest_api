package com.example.demo.process.contact.service;

import java.util.List;

import com.example.demo.process.contact.domain.Contact;

/**
 * 주소록 Service 인터페이스
 * 
 * @author baejaeeon
 *
 */
public interface ContactService {

	void createContact(Contact contact) throws Exception;

	List<Contact> getContactList(Contact contact) throws Exception;

	void modifyContact(Contact contact) throws Exception;

	void removeContact(Contact contact) throws Exception;

	Contact contactDetail(Contact contact) throws Exception;

}
