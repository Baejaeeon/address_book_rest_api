# 1. 랜덤 넘버 소켓 통신 테스트

* com.example.demo.process.randomnumber 패키지
  * 스케줄링 작업
  * 한 프로그램에서 createRandomNumber 실행 및 sendToData 실행
  * 다른 프로그램에서 createDateFromMaster 실행 후, sendToData와 createDateFromMaster 소켓 통신

----> 소켓 통신은 java_program repository를 확인하시면 됩니다.


# 2. 주소록 테스트

* com.example.demo.process.contact 패키지
  * postman 툴을 사용해서 테스트
  * 등록 : 서버 구동 후, /contact/createContact post방식으로 파라미터 입력 후 실행
  * 수정 : 수정할	ID를 파라미터로	입력 후,	/contact/modifyContact 실행
  * 삭제 : 삭제할	ID를 파라미터로	입력 후,	/contact/removeContact 실행
  * 목록 : /contact/contactList 실행
  * 상세 : 검색할	ID를 파라미터로	입력 후, /contact/contactDetail 실행

* contact DB ERD
  ![contact_erd](./image/contact_erd.png)
