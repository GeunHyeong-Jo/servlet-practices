package com.saltlux.mysite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saltlux.mysite.dao.GuestbookDao;
import com.saltlux.mysite.vo.GuestbookVo;
import com.saltlux.web.mvc.WebUtil;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("a");
		if ("deleteform".equals(action)) {
			WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);
		} else if ("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(content);

			new GuestbookDao().insert(vo);
			WebUtil.redirect(request.getContextPath() +"/guestbook", request, response);
		} else if("delete".equals(action)){
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(Long.parseLong(no));
			vo.setPassword(password);
			new GuestbookDao().delete(vo);

			WebUtil.redirect(request.getContextPath() +"/guestbook", request, response);
		}else {
			//TODO 여기에 findall 넣어서 넘겨줘야함
			List<GuestbookVo> list= new ArrayList<>();
			
			
			list = (new GuestbookDao().findAll());
			request.setAttribute("list", list);
			
			WebUtil.forward("/WEB-INF/views/guestbook/list.jsp", request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
