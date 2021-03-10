<%@page import="com.saltlux.guestbook.dao.GuestbookDao"%>
<%@page import="com.saltlux.guestbook.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String no= request.getParameter("no");
	String password= request.getParameter("password");
	
	GuestbookVo vo = new GuestbookVo();
	vo.setNo(Long.parseLong(no));
	vo.setPassword(password);
	
	new GuestbookDao().delete(vo);
	response.sendRedirect("/guestbook01");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a>성공적으로 삭제!!</a>
</body>
</html>