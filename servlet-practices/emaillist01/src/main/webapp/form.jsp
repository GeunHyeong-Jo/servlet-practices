<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>���� ����Ʈ ����</h1>
	<p>
		���� ����Ʈ�� �����Ϸ���,<br>
		�Ʒ� �׸��� �����ϰ� submit ��ư�� Ŭ���ϼ���.
	</p>
	<form action="/emaillist01/add.jsp" method="post">
	    First name: <input type="text" name="firstName" value="" ><br>
	    Last name: <input type="text" name="lastName" value=""><br>
	    Email address: <input type="text" name="email" value=""><br>
	    <input type="submit" value="���">
	</form>
	<br>
	<p>
	<a href="/emaillist01">����Ʈ �ٷΰ���</a>
	</p>
</body>
</html>