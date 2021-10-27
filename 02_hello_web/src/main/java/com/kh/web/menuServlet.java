package com.kh.web;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class menuServlet
 */
@WebServlet("/menu.do")
public class menuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		0. 인코딩처리
		request.setCharacterEncoding("UTF-8");
		
//		1. 요청처리: 사용지입력값 -> 자바변수

		String mainMenu = request.getParameter("mainMenu");
		String sideMenu = request.getParameter("sideMenu");
		String drinkMenu = request.getParameter("drinkMenu");
		
		System.out.println("mainMenu = " + mainMenu);
		System.out.println("sideMenu = " + sideMenu);
		System.out.println("drinkMenu = " + drinkMenu);
		
		int totalPrice = 0;
		
		switch(mainMenu) {
		case "한우버거":
			totalPrice += 5000;
			break;
		
		}
//		3. 응답처리 : jsp 위임 (RequestDispatcher객체의 forward)
		request.setAttribute("totalPrice", totalPrice);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/menuEnd.jsp");
		requestDispatcher.forward(request, response);
		
	}
	

}
