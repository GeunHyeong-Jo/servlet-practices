package com.saltlux.helloweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Joinservlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// POST 방식으로 넘어오는 데이터의 엔코딩
		request.setCharacterEncoding("utf-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String birthYear = request.getParameter("birthYear");
		String gender = request.getParameter("gender");
		// 여러개의 값이 전달되는 체크박스의 경우에는 배열로 받는다.
		String[] hobbies = request.getParameterValues("hobbies");
		String desc = request.getParameter("desc");

		System.out.println("eamil : " + email + " password: " + password);
		System.out.println("생년: " + birthYear);
		System.out.println("성별: " + gender);
		System.out.print("취미: ");
		for (String hobby : hobbies) {
			System.out.print(hobby + " ");
		}
		System.out.println();
		System.out.println("자기소개: " + desc);

		response.getWriter().print("OK");
		response.setContentType("text/html; charset=utf-8");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}