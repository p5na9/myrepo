package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.사용자 입력값 처리
			String memberId = request.getParameter("memberId");
			
			// 2.서비스로직호출
			int result = memberService.deleteMember(memberId);
			
			// 3.응답처리
			// 성공적으로 탈퇴했다면, 탈퇴후 session의 loginMember객체도 무효화(로그아웃)해야 한다.
			HttpSession session = request.getSession();
			if(result > 0) {
				
				session.setAttribute("msg", "성공적으로 회원정보를 삭제했습니다.");
				session.removeAttribute("loginMember");
	
				// saveId cookie 제거
				Cookie c = new Cookie("saveId",memberId);
				c.setPath(request.getContextPath());
				c.setMaxAge(0);			//쿠키의 유효기간 0=> 즉시삭제
				response.addCookie(c);	
			}
			else {
				session.setAttribute("msg", "회원정보삭제에 실패했습니다.");
			}
			response.sendRedirect(request.getContextPath() + "/");
		
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


}
