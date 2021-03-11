<%@page import="com.saltlux.mysite.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@page import="com.saltlux.mysite.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
GuestbookDao dao = new GuestbookDao();
List<GuestbookVo> list = dao.findAll();
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath()%>/guestbook?a=add"
					method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
							<table>
								<%
									int i=list.size()+1;
									for (GuestbookVo vo : list) {
									i--;
								%>
								<tr>
									<td><%=i%></td>
									<td><%=vo.getName()%></td>
									<td><%=vo.getReg_date()%></td>
									<td><a href="<%=request.getContextPath()%>/guestbook?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4><%=vo.getContents().replace("\r\n", "<br>")%></td>
								</tr>
								<%
									}
								%>
							</table>
							<br>
					</li>
				</ul>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>