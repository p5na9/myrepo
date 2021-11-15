package com.kh.mvc.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.MvcUtils;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	/**
	 * paging recipe
	 * 1. content section
	 *  - cPage 현재페이지
	 *  - numPerPage 한페이지의 게시물수 10
	 * 	- startNum, endNum
	 * 
	 * 
	 * 2. pagebar section
	 * 	- totalContent 총게시물수
	 *  - totalPage 12
	 *  - pagebarSize 5
	 *  - pageNo 증감변수
	 *  - pageStart | pageEnd
	 *  - url
	 * 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {}
		int start = (cPage - 1) * numPerPage + 1; 
		int end = cPage * numPerPage;
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		
		// 2. 업무로직
		// 2-a. content영역 : 페이징쿼리
		List<Board> list = boardService.selectAllBoard(param);
		System.out.println("list@servlet = " + list);
		
		// 2-b. pagebar영역 : MvcUtils.getPagebar호출
		// totalContent
		// url
		int totalContent = boardService.selectTotalBoardCount();
		String url = request.getRequestURI(); // /mvc/board/boardList
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
		System.out.println("pagebar@servlet = " + pagebar);
		
		
		// 3. view단 처리
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request
			.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp")
			.forward(request, response);
		
		
		
	}

}
