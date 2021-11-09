package com.kh.mvc.member.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.common.MvcUtils;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 * 
 * servlet이 mvc pattern에서 controller역할이다.
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.인코딩처리
		request.setCharacterEncoding("utf-8");
		
		// 2.사용자입력값처리 
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String saveId = request.getParameter("saveId");
		System.out.println("memberId = " + memberId + ", password = " + password + ", saveId = " + saveId);
		
		// 3.업무로직 : memberId로 회원조회 & 로그인성공여부 검사
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member@MemberLoginServlet.doPost = " + member);
		
		// Session
//		HttpSession session = request.getSession();
		HttpSession session = request.getSession(true); 
		// create:boolean - session객체가 존재하면 해당객체를 리턴, 존재하지 않으면 새로 만들어서 리턴
		System.out.println(session.getId());
		
		// timeout설정 - web.xml설정보다 우선순위가 높다.
		session.setMaxInactiveInterval(10 * 60); // 초단위
		
		// 세션생성시각
		System.out.println(new Date(session.getCreationTime())); 
		
		// 로그인 성공여부
		if(member != null && password.equals(member.getPassword())) {
			
			// 로그인객체를 session 저장
			session.setAttribute("loginMember", member);

			// 로그인 성공
			session.setAttribute("msg", "로그인 성공!");
			
			
			// 아이디저장 체크박스 처리
			// 브라우져 호환성을 고려해 도메인당 쿠키개수 50개, 하나의 value값은 4kb를 넘지 않도록 한다.
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath());
				
			if(saveId != null) {
				cookie.setMaxAge(7 * 24 * 60 * 60); // 7일
				// persistent(영속)쿠키 : 초단위로 시간을 입력
				// session 쿠키 : setMaxAge설정 안한 경우				
			}
			else {
				cookie.setMaxAge(0); // 즉시 삭제
			}
			response.addCookie(cookie);
			
			
			
		}
		else {
			// 로그인 실패
			session.setAttribute("msg", "로그인 실패!");
			
		}
		
		
		// 4.응답처리 (jsp위임 | redirect)
		// redirect 요청주소를 변경, 새로고침을 통한 오류를 방지
		// location은 브라우져가 새로 요청할 주소
		String location = request.getContextPath() + "/";
		response.sendRedirect(location);
		
		
	}

}
