<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a>이전에접속한경로</a>
	<a id="history1"></a>
	<br/>
	<a>현재유입된경로</a>
	<a id="history2"></a>
	<br/>
	<p id="list"></p>
	<script>
	//1번기능
		if (typeof (Storage) !== "undefined") {
			document.getElementById("history1").innerHTML = localStorage.getItem("url");
			//var nowURL = window.location.protocol + "//" + window.location.host+ "/" + window.location.pathname; // 이건 정확하지 않다
			var nowURL =window.location.href;
			localStorage.setItem("url", nowURL);
			///////////////////////////
			document.getElementById("history2").innerHTML = localStorage.getItem("url");
			
		} else {
			document.getElementById("history1").innerHTML = "Sorry, your browser does not support Web Storage";
		}
	</script>
</body>
</html>