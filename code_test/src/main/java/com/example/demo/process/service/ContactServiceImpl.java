package com.example.demo.process.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.process.dao.ContactDao;
import com.example.demo.process.domain.Contact;

/**
 * 주소록 Service 클래스
 * 
 * @author baejaeeon
 *
 */
@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;
	
	@Override
	public void createContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		contactDao.insertContact(contact);
	}
	
	@Override
	public List<Contact> getContactList(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		return contactDao.selectContactList(contact);
	}
	
	@Override
	public void modifyContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		contactDao.updateContact(contact);
	}
	
	@Override
	public void removeContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		contactDao.deleteContact(contact);
	}
	
	@Override
	public Contact contactDetail(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		return contactDao.contactDetail(contact);
	}
}
