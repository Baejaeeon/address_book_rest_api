package com.example.demo.process.contact.dao;

import java.util.List;

import com.example.demo.process.contact.domain.Contact;

/**
 * 주소록 Dao 인터페이스
 * 
 * @author baejaeeon
 *
 */
public interface ContactDao {

	void insertContact(Contact contact) throws Exception;

	List<Contact> selectContactList(Contact contact) throws Exception;

	void updateContact(Contact contact) throws Exception;

	void deleteContact(Contact contact) throws Exception;

	Contact contactDetail(Contact contact) throws Exception;
	
}
