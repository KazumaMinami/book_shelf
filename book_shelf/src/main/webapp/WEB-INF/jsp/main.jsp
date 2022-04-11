<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
pageContext.setAttribute("title", "本棚", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html lang="jp">

<%@ include file="/WEB-INF/jsp/include/head.jsp" %>

<body>
	<div class="container-md">
		<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
		
		<div>${cnt}冊</div>
		
		<div class="table-responsive">
			<table class="table table-borderd table-hover">
				<thead>
					<tr>
						<th>登録日</th>
						<th>タイトル</th>
						<th>著者</th>
						<th>状態</th>
						<th>読み終わった日</th>
						<th></th>
					</tr>
				</thead>
				
				<tbody>
				<c:forEach var="item" items="${items}">
					<tr>
						<fmt:formatDate var="date" value="${item.registrationDate}" pattern="yyyy/MM/dd"/>
						<td><c:out value="${date}"/></td>
						<td>『<c:out value="${item.title}"/>』</td>
						<td><c:out value="${item.author}"/></td>
						
						<!--  <td><c:out value="${item.status}"/></td> -->
						
						<td>
							<c:choose>
								<c:when test="${item.status==0}">読みたい</c:when>
								<c:when test="${item.status==1}">いま読んでいる</c:when>
								<c:when test="${item.status==2}">積読</c:when>
								<c:when test="${item.status==3}">読み終わっている</c:when>
							</c:choose>
						</td>
						
						<fmt:formatDate var="date" value="${item.finishedDate}" pattern="yyyy/MM/dd"/>
						<td><c:out value="${date}"/></td>
						<td>
							<form action="${root_path}/BookDetail" method="get">
								<input type="hidden" name="id" value="<c:out value="${item.id}"/>">
								<input type="submit" value="詳細" class="btn btn-outline-success">
							</form>
						</td>
					</tr>
				</c:forEach>
				</tbody>
						
						
			</table>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/include/js.jsp" %>

</body>
</html>