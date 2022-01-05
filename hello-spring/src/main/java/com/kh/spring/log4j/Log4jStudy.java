package com.kh.spring.log4j;

import org.apache.log4j.Logger;

/**
 * Logging 
 * 	- 개발 & 운영 전반에 걸쳐 필요한 정보를 수집/기록(console, file)
 * 	- level에 따른 로그메소드제공. 선별적으로 기록이 가능
 * 	- System.out.println 출력은 성능저하를 야기함.
 *
 * PSA인 Slf4j의존을 통해서 실제 구현체를 제어함.
 * 	- log4j단독사용가능
 *
 */
public class Log4jStudy {

	private static Logger log = Logger.getLogger(Log4jStudy.class);
	
	public static void main(String[] args) {
		log.fatal("fatal"); // 심각한 에러상황
		log.error("error");	// 요청 처리중에 예외/에러 발생
		log.warn("warn");	// 경고. 현재 문제가 되지는 않지만, 향후 에러가능성 있음.
		log.info("info");	// 정보성 메세지.
		
		log.debug("debug");	// 개발시 디버그용
		log.trace("trace");	// 세밀한 디버깅을 위해 구역설정
	}

}
