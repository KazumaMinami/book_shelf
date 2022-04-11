<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
pageContext.setAttribute("title", "本の追加", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html lang="jp">

<%@ include file="/WEB-INF/jsp/include/head.jsp" %>

<body>
	<div class="container-md">
		<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
		<h1>${title}</h1>
		
		<div class="row">
			<div class="col-md">
				<form action="${root_path}/BookRegister" method="post">
					<input type="hidden" name="token" value="${token}">
					
					<div class="form-group">
						<label for="registrationDate">登録日</label>
						<input type="date" name="registrationDate" id="registrationDate"
							class="form-control<c:if test="${error.registrationDate!=null}"> is-invalid</c:if>"
							value="<c:out value="${book.registrationDate}"/>">
						<span class="text-danger">${error.registrationDate}</span>
					</div>
					
					<div class="form-group">
						<label for="bookTitle">タイトル</label>
						<input type="text" name="bookTitle" id="bookTitle"
							class="form-control<c:if test="${error.title}"> is-invalid</c:if>"
							value="<c:out value="${book.title}"/>">
						<span class="text-danger">${error.title}</span>
					</div>
					
					<div class="form-group">
						<label for="author">著者</label>
						<input type="text" name="author" id="author"
							class="form-control<c:if test="${error.author}"> is-invalid</c:if>"
							value="<c:out value="${book.author}"/>">
						<span class="text-danger">${error.author}</span>
					</div>
					
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status" id="status0"
							value="0" checked>
						<label for="status0" class="form-check-label">読みたい</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status" id="status1"
							value="1">
						<label for="status1" class="form-check-label">いま読んでいる</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status" id="status2"
							value="2">
						<label for="status2" class="form-check-label">積読</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status" id="status3"
							value="3">
						<label for="status3" class="form-check-label">読み終わった</label>
					</div>
					
					<div class="my-3">
						<label for="thoughts" class="form-label">感想</label>
						<textarea class="form-control" name="thoughts" id="thoughts" rows="3"></textarea>
					</div>
					
					<button type="submit" class="btn btn-outline-success">追加</button>
				
				</form>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/include/js.jsp" %>

</body>
</html>