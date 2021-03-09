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
		<!-- border는 html에서 내용만이 아니라 디자인까지 건드려서
	추천되지 않는다 -->
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
		</tr>
		<tr>
			<td>1</td>
			<td>밥먹자</td>
			<td>둘리</td>
		</tr>
		<tr>
			<td>2</td>
			<td>모?</td>
			<td>마이콜</td>
		</tr>
	</table>
	<!-- /시작하면 절대경로
	    images/ 이렇게 시작하면 상대경로 -->
	<img src='/helloweb/images/pi.jpg' style="width: 300px" />
	<br/><!-- 줄바꿈 -->
	<img src='images/pi.jpg' style="width: 300px" />
	<a href="form.jsp">폼으로가기</a>
	<a href="/helloweb/index.jsp?no=10">메인으로 가기</a>
	<br/>
	<a href="index.jsp">메인으로 가기2</a>
	<p>
		웹 표준은 월드 와이드 웹의 측면을 서술하고 정의하는 공식 표준이나 다른 기술 규격을 가리키는
		일반적인 용어이다. <br/>최근에 이 용어는 웹 사이트를 작성하는 데 중요도가 높아지고 있으며 웹 디자인,
		개발과 관계가 있다.
		
	</p>
	<!--unordered 리스트 -->
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