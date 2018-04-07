package com.example.demo.process.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.process.domain.Contact;

/**
 * 주소록 Dao 클래스
 * 
 * @author baejaeeon
 *
 */
@Repository
public class ContactDaoImpl implements ContactDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertContact(Contact contact) {
		// TODO Auto-generated method stub
		sqlSession.insert("contact.insertContact", contact);
	}
	
	@Override
	public List<Contact> selectContactList(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("contact.selectContactList", contact);
	}
	
	@Override
	public void updateContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("contact.updateContact", contact);
	}
	
	@Override
	public void deleteContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("contact.deleteContact", contact);
	}
	
	@Override
	public Contact contactDetail(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contact.contactDetail", contact);
	}
	
}
