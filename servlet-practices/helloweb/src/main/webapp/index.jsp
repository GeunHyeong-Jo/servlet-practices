<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
	String no = request.getParameter("no");
	int number= -1;
	if(no != null && no.matches("\\d*")){
		number= Integer.parseInt(no);
		// �ڹ��ڵ带 ���⿡ �ִ´�
		//<%= �� ������ ��Ÿ���� ���
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
	<a href="/helloweb/tag.jsp" target='_blank'>�±� �����ϱ�</a>
	<!-- target='_blank' ���ο� �ǿ��� -->
	<h2>�Ѿ�� ����...</h2>
	
	<p>
		<%=number+10 %>
	</p>
</body>
</html>