<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
pageContext.setAttribute("title", "詳細の修正", PageContext.PAGE_SCOPE);
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
				<form action="${root_path}/BookUpdate" method="post">
					<input type="hidden" name="id" value="<c:out value="${book.id}"/>">
					<input type="hidden" name="finishedDate" value="<c:out value="${book.finishedDate}"/>">
					
					<div class="form-group">
						<label for="registrationDate">登録日</label>
						<input type="date" name="registrationDate"  id="registrationDate"
							class="form-control<c:if test="${error.registrationDate != null}"> is-invalid</c:if>"
							value="<c:out value="${book.registrationDate}"/>">
						<span class="text-danger">${error.registrationDate}</span>
					</div>
					
					<div class="form-group">
						<label for="bookTitle">タイトル</label>
						<input type="text" name="bookTitle" id="bookTitle"
							class="form-control<c:if test="${error.title != null}"> is-invalid</c:if>"
							value="<c:out value="${book.title}"/>">
						<span class="text-danger">${error.title}</span>
					</div>
					
					<div class="form-group">
						<label for="author">著者</label>
						<input type="text" name="author" id="author"
							class="form-control<c:if test="${error.author != null}"> is-invalid</c:if>"
							value="<c:out value="${book.author}"/>">
						<span class="text-danger">${error.author}</span>
					</div>
					
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status" id="status0"
							value="0" <c:if test="${book.status==0}"> checked</c:if>>
						<label for="status0" class="form-check-label">読みたい</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status" id="status1"
							value="1" <c:if test="${book.status==1}"> checked</c:if>>
						<label for="status1" class="form-check-label">いま読んでいる</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status" id="status2"
							value="2" <c:if test="${book.status==2}"> checked</c:if>>
						<label for="status2" class="form-check-label">積読</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status" id="status3"
							value="3" <c:if test="${book.status==3}"> checked</c:if>>
						<label for="status3" class="form-check-label">読み終わった</label>
					</div>
					
					<div class="my-3">
						<label for="thoughts" class="form-label">感想</label>
						<textarea class="form-control" name="thoughts" id="thoughts" rows="3"><c:out value="${book.thoughts}"/></textarea>
					</div>
					
					<div class="form-check">
						<input type="checkbox" name="isDeleted" id="isDeleted" value="1"
							class="form-check-input">
						<label for="isDeleted" class="form-check-label">削除する</label>
					</div>
					
					<div class="text-right">
						<button type="submit" class="btn btn-outline-success">更新</button>
					</div>
					
				</form>
				
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/include/js.jsp" %>

</body>
</html>