<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%
	pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>${board.title}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${board.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(board.content,newline,"<br>") }
							</div>
						</td>
					</tr>
				</table>
			
				<div class="bottom">
					
					<a href="${pageContext.request.contextPath }/board">글목록</a>
					<c:if test="${authUser.no eq board.user_no }">
						<a href="${pageContext.request.contextPath }/board?a=modifyform&no=${board.no}">글수정</a>
					</c:if>
					<c:if test="${!empty authUser }">
						<a href="${pageContext.request.contextPath }/board?a=reply&boardNo=${board.no}">답글</a><!-- 자신의 글이 아닐경우 글수정 없애고 답글 활성화 -->
					</c:if>
				</div>
			</div>
			<c:import url="/WEB-INF/views/includes/history.jsp" />
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>