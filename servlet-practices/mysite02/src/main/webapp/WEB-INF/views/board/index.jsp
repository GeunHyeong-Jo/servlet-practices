<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<form action="${pageContext.request.contextPath}/board?a=delete"
					method="post">
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>&nbsp;</th>
						</tr>
						<c:set var="count" value="${fn:length(list) }" />
						<c:forEach items='${list }' var='board' varStatus='status'>
							<tr>
								<td>${count-status.index }</td>
								<td style="text-align: left"><c:choose>
										<c:when test="${board.depth > 0 }">
											<img
												style="text-align:left; padding-left:${(board.depth)*20}px"
												src="${pageContext.request.contextPath}/assets/images/reply.png" />
										</c:when>
									</c:choose> <a class="title"
									href="${pageContext.request.contextPath }/board?a=view&no=${board.no }"
									onclick="makeHistory(this)">${board.title }</a></td>
								<td>${board.author }</td>
								<td>${board.views }</td>
								<td>${board.reg_date }</td>
								<!--  여기에 Vo에서 세션에 저장된 no로 비교해야한다 같으면 삭제버튼 활성화 and 가능하면 POST method로 보내야한다 -->
								<c:if test="${authUser.no eq board.user_no}">
									<td><a
										href="${pageContext.request.contextPath}/board?a=delete&no=${board.no }"
										class="del" type="submit">삭제</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</form>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
					<c:if test="${pagerVo.nowPage > pagerVo.cntPage/2 + 1 }">
							<li><a href="${pageContext.request.contextPath }/board">◀</a></li>
					</c:if>
					<c:forEach begin="${pagerVo.firstPageInCurrentPageGroup }" end="${pagerVo.lastPageInCurrentPageGroup }" var="i" step="1">
						<c:choose>
							<c:when test="${i == pagerVo.nowPage }">
								<li class="selected"><a id="link" href="${pageContext.request.contextPath }/board?p=${i }">${i }</a></li>	
							</c:when>
							<c:otherwise>
								<li><a id="link" href="${pageContext.request.contextPath }/board?p=${i }">${i }</a></li>	
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pagerVo.lastPage > 5 && pagerVo.nowPage != pagerVo.lastPage }">
						<li><a href="${pageContext.request.contextPath }/board?p=${pagerVo.lastPage}">▶</a></li>
					</c:if>
						<!--  <li><a href="">◀</a></li>
						<li><a href="/mysite02/board?p=1">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>-->
					</ul>
				</div>
				<!-- pager 추가   선택된 부분에 "selected" 걸어주고, 존재하는 페이지에만 링크를 걸어준다-->


				<div class="bottom">
					<c:if test="${ !empty authUser }">
						<a href="${pageContext.request.contextPath}/board?a=writeform"
							id="new-book">글쓰기</a>
						<!-- <a href="${pageContext.request.contextPath}/board?a=modify" id="new-book">글수정</a> 글은 해당 글에 들어가서 수정해야함 -->
					</c:if>
				</div>
			</div>



	<!-- JavaScript 과제 추가부분 -->

			<a id="hahaha"></a><br />

			<script>
				var output = localStorage.getItem("array");
				var arr = JSON.parse(output);//  출력용 변환
				if (arr == null) {
					arr = [""];
				}
				console.log(arr);
				if (typeof (Storage) !== "undefined") {
					document.getElementById("hahaha").innerHTML = "리스트 : "+localStorage
							.getItem("array");
				}

				function makeHistory(t) {
					alert(t.href + "눌림");
					arr.push(t.href);
					if (arr.length > 4) {
						arr.shift();
					}
					localStorage.setItem("array", JSON.stringify(arr)); // 저장용 변환
				}
			</script>
			<c:import url="/WEB-INF/views/includes/history.jsp" />
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
