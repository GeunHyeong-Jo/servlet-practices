package com.saltlux.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saltlux.mysite.dao.UserDao;
import com.saltlux.mysite.vo.UserVo;
import com.saltlux.web.mvc.WebUtil;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("a");

		if ("joinform".equals(action)) {
			WebUtil.forward("/WEB-INF/views/user/joinform.jsp", request, response);
		} else if ("joinsuccess".equals(action)) {
			WebUtil.forward("/WEB-INF/views/user/joinsuccess.jsp", request, response);
		} else if ("loginform".equals(action)) {
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
		} else if ("logout".equals(action)) {
			HttpSession session = request.getSession();

			// 로그아웃 처리
			if (session != null && session.getAttribute("authUser") != null) {
				session.removeAttribute("authUser");
				session.invalidate();
			}

			WebUtil.redirect(request.getContextPath(), request, response);

		} else if ("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserVo vo = new UserVo();
			vo.setEmail(email);
			vo.setPassword(password);

			UserVo authUser = new UserDao().findByEmailAndPassword(vo);
			if (authUser == null) {
				request.setAttribute("authResult", "fail");
				WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
				return; // 여기서 끝내야 한다 아님 else문을 비워서 만들어도 괜찮은데 권장되지 않는다.
			}

			// 인증 처리

			HttpSession session = request.getSession(true);// session manager에게 있으면 있는거 없으면 생성 요청
			session.setAttribute("authUser", authUser);

			// 응답

			WebUtil.redirect(request.getContextPath(), request, response);

		} else if ("join".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);

			new UserDao().insert(userVo);

			WebUtil.redirect(request.getContextPath() + "/user?a=joinsuccess", request, response);
		} else {
			WebUtil.redirect(request.getContextPath(), request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}