<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
	String no = request.getParameter("no");
	int number= -1;
	if(no != null && no.matches("\\d*")){
		number= Integer.parseInt(no);
		// 자바코드를 여기에 넣는다
		//<%= 는 변수를 나타낼때 사용
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<h1>Hello World02</h1>
	<a href="/helloweb/tag.jsp" target='_blank'>태그 연습하기</a>
	<!-- target='_blank' 새로운 탭열기 -->
	<h2>넘어온 값은...</h2>
	
	<p>
		<%=number+10 %>
	</p>
</body>
</html>