<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    pageContext.setAttribute("title", "引用文の修正", PageContext.PAGE_SCOPE);
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
					
					<form action="${root_path}/QuoteUpdate" method="post">
						<input type="hidden" name="token" value="${token}">
						<input type="hidden" name="quoteId" value="${quoteId}">
						<input type="hidden" name="bookId" value="${bookId}">
					<!--  
						<div class="form-group">
							<label for="title">タイトル</label>
							<input type="text" name="title" id="title" 
								class="form-control<c:if test="${error.title != null}"> is-invalid</c:if>"
								value="<c:out value="${model.bookModel.getTitle()}"/>">
							<span class="text-danger">${error.title}</span>
						</div>
						
						<div class="form-group">
							<label for="author">著者</label>
							<input type="text" name="author" id="author"
								class="form-control<c:if test="${error.author != null}"> is-invalid</c:if>"
								value="<c:out value="${model.bookModel.getAuthor()}"/>">
							<span class="text-danger">${error.author}</span>
						</div> 
						-->
						<div class="form-group">
							<label for="quote">引用</label>
							<textarea name="quote" id="quote" rows=3
								class="form-control<c:if test="${error.quote != null}"> is-invalid</c:if>"
								placeholder="引用">${model.quote}</textarea>
							<span class="text-danger">${error.quote}</span>
						</div>
						
						<div class="form-group">
							<label for="page">ページ番号</label>
							<input type="text" name="page" id="page" 
								class="form-control<c:if test="${error.pp != null}"> is-invalid</c:if>"
								value="<c:out value="${model.page}"/>">
							<span class="text-danger">${error.pp}</span>
						</div>
						
						<div class="form-check">
							<input type="checkbox" name="isDeleted" id="isDeleted" value="1"
								class="form-check-input">
							<label for="isDeleted" class="form-check-label">削除する</label>
						</div>
						
						
						<button type="submit" class="btn btn-outline-success">修正</button>
					</form>
				
				</div>
			</div>
	</div>
		
		<%@ include file="/WEB-INF/jsp/include/js.jsp" %>

</body>
</html>