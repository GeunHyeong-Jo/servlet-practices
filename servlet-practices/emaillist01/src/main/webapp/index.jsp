<%@page import="com.saltlux.emaillist.vo.EmaillistVo"%>
<%@page import="java.util.List"%>
<%@page import="com.saltlux.emaillist.dao.EmaillistDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
EmaillistDao dao = new EmaillistDao();
List<EmaillistVo> list = dao.findAll();


%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>���� ����Ʈ</h1>
	<p>�Է��� ���� �����Դϴ�.</p>
	<!-- �������� ����Ʈ -->

	<%
		for(EmaillistVo vo : list){
	%>
	<table border="1" cellpadding="5" cellspacing="2">
		<tr>
			<td align=right>First name:</td>
			<td>
				<%= vo.getFirstName() %>
			</td>
		</tr>
		<tr>
			<td align=right width="110">Last name:</td>
			<td width="110">
				<%=vo.getLastName() %>
			</td>
		</tr>
		<tr>
			<td align=right>Email address:</td>
			<td>
				<%= vo.getEmail() %>
			</td>
		</tr>
	</table>
	<br>

	<%
		}
	%>



	<p>
		<a href="/emaillist01/form.jsp">�߰����� ���</a>
	</p>
	<br>
</body>
</html>