<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    pageContext.setAttribute("title", "引用文一覧", PageContext.PAGE_SCOPE);
    %>
    
<!DOCTYPE html>
<html lang="jp">

<%@ include file="/WEB-INF/jsp/include/head.jsp" %>

<body>
	<div class="container-md">
	<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
	<h1>${title}</h1>
	
		<div class="table-responsive">
			<table class="table table-borderd table-hover">
				<tbody>
				<c:forEach var="item" items="${list}">
					<tr>
						<td>“<c:out value="${item.quote}"/>”</td>
						<td>著者：<c:out value="${item.bookModel.getAuthor()}"/></td>
					</tr>
					<tr>
						<td>出典：『<c:out value="${item.bookModel.getTitle()}"/>』</td>
						<td>
							<form action="${root_path}/QuoteUpdate" method="get">
								<input type="hidden" name="id" value="<c:out value="${item.id}"/>">
								<input type="hidden" name="bookId" value="${item.bookModel.getId()}">
					
								<input type="submit" value="修正" class="btn btn-outline-success">
							</form>
						</td>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>