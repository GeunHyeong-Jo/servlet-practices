<%@page import="com.saltlux.guestbook.dao.GuestbookDao"%>
<%@page import="com.saltlux.guestbook.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8"); // 인코딩 깨짐 방지
	
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String message = request.getParameter("message");
	
	GuestbookVo vo = new GuestbookVo();
	vo.setName(name);
	vo.setPassword(password);
	vo.setContents(message);
	
	new GuestbookDao().insert(vo);
	response.sendRedirect("/guestbook01");
	
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>성공적으로 등록 !!</h1>
</body>
</html>