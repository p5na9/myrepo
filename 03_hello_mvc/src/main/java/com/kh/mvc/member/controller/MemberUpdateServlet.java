package com.kh.mvc.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.common.MvcUtils;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 회원정보 수정쿼리
	 *  
	 * update member
	 * set password = ?, member_name = ?, gender = ?, birthday = ?, email = ?, phone = ?, address = ?, hobby = ? 
	 * where member_id = ?
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 1.인코딩처리
		request.setCharacterEncoding("utf-8");
		
		// 2.사용자입력값 처리 사용자입력값 -> Member VO객체 생성
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String _birthday = request.getParameter("birthday"); 
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String[] _hobby = request.getParameterValues("hobby");
		
		Date birthday = null;
		if(!"".equals(_birthday))
			birthday = Date.valueOf(_birthday);
		
		String hobby = null;
		if(_hobby != null) 
			hobby = String.join(",", _hobby);
		
		Member member = 
				new Member(memberId, null, memberName, null, gender, birthday, email, phone, address, hobby, null);
		System.out.println("member@servlet = " + member);
		
		// 3.업무로직 요청 : 서비스객체의 updateMember호출 & Member객체 전달
		int result = memberService.updateMember(member);
		String msg = (result > 0) ? "회원정보 수정 성공!" : "회원정보 수정 실패!";
		if(result > 0) {
			Member updateMember = memberService.selectOneMember(memberId);
			session.setAttribute("loginMember", updateMember); // 세션 loginMember객체도 변경
		}
		
		// 4.리다이렉트처리 및 사용자메세지 준비
		
		session.setAttribute("msg", msg);
		
		String location = request.getContextPath() + "/member/memberView";
		response.sendRedirect(location);
		
	
	}

}
