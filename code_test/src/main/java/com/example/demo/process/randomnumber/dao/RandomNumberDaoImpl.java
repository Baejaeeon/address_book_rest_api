package com.example.demo.process.randomnumber.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.process.randomnumber.domain.RandomNumber;

@Repository
public class RandomNumberDaoImpl implements RandomNumberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertRandomNumber(RandomNumber randomNumber) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("randomNumber.insertRandomNumber", randomNumber);
	}
	
	@Override
	public List<RandomNumber> selectRandomNumberList(RandomNumber randomNumber) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("randomNumber.selectRandomNumberList", randomNumber);
	}
	
	@Override
	public int selectRandomNumberCount() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("randomNumber.selectRandomNumberCount");
	}
	
	@Override
	public int selectMaxRandomNumber() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("randomNumber.selectMaxRandomNumber");
	}
	
	@Override
	public void insertRandomNumberToBackup(RandomNumber ran) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("randomNumber.insertRandomNumberToBackup", ran);
	}
}
