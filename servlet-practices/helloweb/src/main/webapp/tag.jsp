<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World</h1>
	<h2>Hello World</h2>
	<h3>Hello World</h3>
	<h4>Hello World</h4>
	<h5>Hello World</h5>
	<h6>Hello World</h6>

	<table border="1" cellspacing="0" cellpadding="10">
		<!-- border�� html���� ���븸�� �ƴ϶� �����α��� �ǵ����
	��õ���� �ʴ´� -->
		<tr>
			<th>��ȣ</th>
			<th>����</th>
			<th>�۾���</th>
		</tr>
		<tr>
			<td>1</td>
			<td>�����</td>
			<td>�Ѹ�</td>
		</tr>
		<tr>
			<td>2</td>
			<td>��?</td>
			<td>������</td>
		</tr>
	</table>
	<!-- /�����ϸ� ������
	    images/ �̷��� �����ϸ� ����� -->
	<img src='/helloweb/images/pi.jpg' style="width: 300px" />
	<br/><!-- �ٹٲ� -->
	<img src='images/pi.jpg' style="width: 300px" />
	<a href="form.jsp">�����ΰ���</a>
	<a href="/helloweb/index.jsp?no=10">�������� ����</a>
	<br/>
	<a href="index.jsp">�������� ����2</a>
	<p>
		�� ǥ���� ���� ���̵� ���� ������ �����ϰ� �����ϴ� ���� ǥ���̳� �ٸ� ��� �԰��� ����Ű��
		�Ϲ����� ����̴�. <br/>�ֱٿ� �� ���� �� ����Ʈ�� �ۼ��ϴ� �� �߿䵵�� �������� ������ �� ������,
		���߰� ���谡 �ִ�.
		
	</p>
	<!--unordered ����Ʈ -->
	<ul>
		<li>aaaaaa</li>
		<li>bbbbbb</li>
		<li>cccccc</li>
	
	</ul>
	
	<ol>
		<li>aaaaaa</li>
		<li>bbbbbb</li>
		<li>cccccc</li>
	
	</ol>
</body>
</html>