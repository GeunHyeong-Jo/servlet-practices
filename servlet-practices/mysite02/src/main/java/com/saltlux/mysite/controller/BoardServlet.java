package com.saltlux.mysite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saltlux.mysite.dao.BoardDao;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.UserVo;
import com.saltlux.web.mvc.WebUtil;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("a");
		
		if("write".equals(action)) {
			
			WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
		} else if("modify".equals(action)) {
			
			WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
			
		} else if("delete".equals(action)) {
			//글을 올린 본인이 로그인하여 삭제버튼을 눌렀을 때
			String boardNo=(String)request.getAttribute("boardNo");
			System.out.println("눌려진 게시글 번호"+boardNo);
			
			
		} else if("view".equals(action)) {
			
			
			WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
		}else {
			// 현재 세션이 있으면 user의 no를 넘겨받아서 본인일 경우 삭제버튼이 활성화되는 기능을 구현하기 위해서 필요
			HttpSession session = request.getSession();

			List<BoardVo> list = new ArrayList<>();
			
			list = new BoardDao().findAll();
			request.setAttribute("list", list);
			//board의 정보를 모두 넘겨주게 된다
			
			if (session == null) {
				WebUtil.forward("/WEB-INF/views/board/index.jsp", request, response);
				return;
			}
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if (authUser == null) {
				WebUtil.forward("/WEB-INF/views/board/index.jsp", request, response);
				return;
			}

			Long no = authUser.getNo();
			request.setAttribute("my_user_no", no); //로그인이 되어 세션이 존재하면 로그인된 회원의 no값을 넘겨준다
			
			WebUtil.forward("/WEB-INF/views/board/index.jsp", request, response);
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
