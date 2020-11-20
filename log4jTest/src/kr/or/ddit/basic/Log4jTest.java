package kr.or.ddit.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {
	// Logger클래스의 인스턴스 생성하기
	static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		// log 기록 출력하기
		
		// 방법1
		logger.trace("이 메시지는 Trace레벨의 메시지 입니다.");
		logger.debug("이 메시지는 Debug레벨의 메시지 입니다.");
		logger.info("이 메시지는 Infomation레벨의 메시지 입니다.");
		logger.warn("이 메시지는 Warning레벨의 메시지 입니다.");
		logger.error("이 메시지는 Error레벨의 메시지 입니다.");
		logger.fatal("이 메시지는 Fatal레벨의 메시지 입니다.");

		
		// 방법2
//		logger.log(Level.FATAL, "이 메시지는 Level.FATAL레벨의 메시지 입니다.");
//		logger.log(Level.ERROR, "이 메시지는 Level.ERROR레벨의 메시지 입니다.");
//		logger.log(Level.WARN, "이 메시지는 Level.WARN레벨의 메시지 입니다.");
//		logger.log(Level.INFO, "이 메시지는 Level.INFO레벨의 메시지 입니다.");
//		logger.log(Level.DEBUG, "이 메시지는 Level.DEBUG레벨의 메시지 입니다.");
//		logger.log(Level.TRACE, "이 메시지는 Level.TRACE레벨의 메시지 입니다.");
	}

}
