package com.example.demo.process.randomnumber.dao;

import java.util.List;

import com.example.demo.process.randomnumber.domain.RandomNumber;

public interface RandomNumberDao {

	void insertRandomNumber(RandomNumber randomNumber) throws Exception;

	List<RandomNumber> selectRandomNumberList(RandomNumber randomNumber) throws Exception;

	int selectRandomNumberCount() throws Exception;

	int selectMaxRandomNumber() throws Exception;

	void insertRandomNumberToBackup(RandomNumber ran) throws Exception;

}
