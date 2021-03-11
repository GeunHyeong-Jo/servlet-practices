<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="navigation">
			<ul>
				<li><a href="<%=request.getContextPath() %>">조근형</a></li>
				<li><a href="<%=request.getContextPath() %>/guestbook">방명록</a></li>
				<li><a href="<%=request.getContextPath() %>/board">게시판</a></li>
			</ul>
		</div>
</body>
</html>