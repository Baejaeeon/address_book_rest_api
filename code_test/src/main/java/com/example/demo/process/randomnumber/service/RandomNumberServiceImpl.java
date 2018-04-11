package com.example.demo.process.randomnumber.service;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.process.randomnumber.dao.RandomNumberDao;
import com.example.demo.process.randomnumber.domain.RandomNumber;

@Component
public class RandomNumberServiceImpl implements RandomNumberService {

	@Autowired
	private RandomNumberDao randomNumberDao;
	
	private static Logger logger = Logger.getLogger(RandomNumberServiceImpl.class);
	
	/*
	 * 0.1초 주기로 랜덤 번호 생성 후 DB에 저장
	 */
	@Override
	//@Scheduled(fixedDelayString = "100")
	public void createRandomNumber() throws Exception {
		// 랜덤 정수 가져온다.
		Random random = new Random();
		
		// 현재 시간을 구한다.
		LocalDateTime localDate = LocalDateTime.now();
		Timestamp time = Timestamp.valueOf(localDate);
		
		RandomNumber randomNumber = new RandomNumber();
		randomNumber.setRandom_number(random.nextInt());
		randomNumber.setRegistered_datetime(time);
		
		logger.info("실행 :: 정수 >>> " + randomNumber.getRandom_number() + " 시간 >>> " + randomNumber.getRegistered_datetime());
		
		randomNumberDao.insertRandomNumber(randomNumber);
	}
	
	/*
	 * 1초 주기로 master DB에 있는 데이터를 전송 
	 */
	@Override
	//@Scheduled(fixedDelayString = "1000")
	public void sendToData() throws Exception {
		System.out.println("진입");
		
		// 자동 close
		try(ServerSocket server = new ServerSocket()) {
			// 서버 초기화
			InetSocketAddress isa = new InetSocketAddress(9999);
			server.bind(isa);
			
			logger.info("Server Initialize Complete");

			// Listen 대기
			Socket client = server.accept();
			client.setSoTimeout(2000); // 타임 아웃 설정
			logger.info("Connection");
			
			// send, receiver 스트림 받아오기
			// 자동 close
			try(ObjectOutputStream sender = new ObjectOutputStream(client.getOutputStream());
					ObjectInputStream receiver = new ObjectInputStream(client.getInputStream());) {
				
				// 클라이언트로 부터 ID 값 받아오기
				int random_number_id = ((RandomNumber) receiver.readObject()).getRandom_number(); // 수신
				
				RandomNumber randomNumber = new RandomNumber();
				randomNumber.setRandom_number_id(random_number_id);
				
				// 넘겨 받은 random_number_id를 통해서 master에 있는 데이터를 가져온다.
				List<RandomNumber> randomNumberList = randomNumberDao.selectRandomNumberList(randomNumber);
				
				logger.info("데이터 가져옴...");
				
				// 객체 전송시 직렬화 해서 전송
				RandomNumber temp = new RandomNumber();
				temp.setRandom_number_list(randomNumberList);
				
				sender.writeObject(temp); // 전송
				
				sender.flush();
			}
					
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	//@Scheduled(fixedDelayString = "1000")
	public void createDateFromMaster() throws Exception {
		// TODO Auto-generated method stub
		
		try (Socket client = new Socket()) {
			// 클라이언트 초기화
			InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 9999);
			// 접속
			client.connect(isa);
			logger.info("Client Connection...");
			
			// send, receiver 스트림 받아오기
			try(ObjectOutputStream sender = new ObjectOutputStream(client.getOutputStream());
					ObjectInputStream receiver = new ObjectInputStream(client.getInputStream());) {
				// 서버로 데이터 전송
				int randomNumberCount = randomNumberDao.selectRandomNumberCount();
				int random_number_id = 0;
				
				if(randomNumberCount > 0) { // 데이터가 있으면
					random_number_id = randomNumberDao.selectMaxRandomNumber();
				}
				
				RandomNumber temp = new RandomNumber();
				temp.setRandom_number(random_number_id);
				
				sender.writeObject(temp); // 전송
				sender.flush();
				
				List<RandomNumber> randomNumberList = ((RandomNumber) receiver.readObject()).getRandom_number_list(); // 수신
				
				for(RandomNumber ran : randomNumberList) {
					randomNumberDao.insertRandomNumberToBackup(ran);
				}
				
			}
		}
	}
	
}
