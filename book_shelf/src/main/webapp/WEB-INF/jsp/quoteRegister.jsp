<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    pageContext.setAttribute("title", "引用新規追加", PageContext.PAGE_SCOPE);
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
				
				<form action="${root_path}/QuoteRegister" method="post">
					<input type="hidden" name="token" value="${token}">
					<input type="hidden" name="bookId" value="${bookId}">
					
					<div class="form-group row">
						<label for="title" class="col-sm-2 col-form-label">タイトル</label>
						<div class="col-sm-10">
						<input type="text" readonly name="title" id="title" 
							class="form-control-plaintext"
							value="<c:out value="${quotes.title}"/>">
						</div>
						<span class="text-danger">${error.title}</span>
					</div>
					
					<div class="form-group row">
						<label for="author" class="col-sm-2 col-form-label">著者</label>
						<div class="col-sm-10">
						<input type="text" readonly name="author" id="author"
							class="form-control-plaintext"
							value="<c:out value="${quotes.author}"/>">
						</div>
						<span class="text-danger">${error.author}</span>
					</div>
					
					<div class="form-group">
						<label for="quote">引用</label>
						<textarea name="quote" id="quote" rows=3
							class="form-control<c:if test="${error.quote != null}"> is-invalid</c:if>"
							placeholder="引用">${quotes.quote}</textarea>
						<span class="text-danger">${error.quote}</span>
					</div>
					
					<div class="form-group">
						<label for="page">ページ番号</label>
						<input type="text" name="page" id="page" 
							class="form-control<c:if test="${error.pp != null}"> is-invalid</c:if>"
							value="<c:out value="${quotes.page}"/>">
						<span class="text-danger">${error.pp}</span>
					</div>
					
					
					
					<button type="submit" class="btn btn-outline-success">追加</button>
				</form>
			
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/include/js.jsp" %>

</body>
</html>