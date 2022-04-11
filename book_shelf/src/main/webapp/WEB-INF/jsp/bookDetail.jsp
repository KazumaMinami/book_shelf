<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="logic.BookLogic, model.BookModel, model.UserModel" %>

<%
pageContext.setAttribute("title", "本の詳細", PageContext.PAGE_SCOPE);
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
				
					<div class="form-group row">
						<label for="registrationDate" class="col-sm-2 col-form-label">登録日</label>
						<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext" id="registrationDate"
							value="<c:out value="${book.registrationDate}"/>">
						</div>
					</div>
					
					<div class="form-group row">
						<label for="title" class="col-sm-2 col-form-label">タイトル</label>
						<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext" id="title"
							value="<c:out value="${book.title}"/>">
						</div>
					</div>
					
					<div class="form-group row">
						<label for="author" class="col-sm-2 col-form-label">著者</label>
						<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext" id="author"
							value="<c:out value="${book.author}"/>">
						</div>
					</div>
					
					<div class="form-group row">
						<label for="status" class="col-sm-2 col-form-label">状態</label>
						<div class="col-sm-10">
						<input type="text" readonly class="form-control-plaintext" id="status"
							<c:choose>
								<c:when test="${book.status == 0}">value="読みたい"</c:when>
								<c:when test="${book.status == 1}">value="いま読んでいる"</c:when>
								<c:when test="${book.status == 2}">value="積読"</c:when>
								<c:when test="${book.status == 3}">value="読み終わった"</c:when>
							</c:choose>
						>
						</div>
					</div>
					
					<c:if test="${book.status == 3}">
						<div class="form-group row">
							<label for="finishedDate" class="col-sm-2 col-form-label"></label>
							<div class="col-sm-10">
							<input type="date" readonly class="form-control-plaintext" id="finishedDate"
								value="<c:out value="${book.finishedDate}"/>">
							</div>
						</div>
					</c:if>
					
					<div class="form-group row">
						<label for="thoughts" class="col-sm-2 col-control-label">感想</label>
						<div class="col-sm-10">
							<label for="thoughts" class="form-label">感想</label>
							<textarea readonly class="form-control" name="thoughts" id="thoughts" rows="3"
								placeholder="${book.thoughts}"></textarea>
						</div>
					</div>
				
				<form action="${root_path}/BookUpdate" method="get">
					<input type="hidden" name="id" value="<c:out value="${book.id}"/>">
					<div class="text-right">
						<button type="submit" class="btn btn-outline-success">修正</button>
					</div>
				</form>
				
				<h3>引用</h3>
				<form action="${root_path}/QuoteRegister" method="get">
					<input type="hidden" name="bookId" value="<c:out value="${book.id}"/>">
					<input type="hidden" name="title" value="<c:out value="${book.title}"/>">
					<input type="hidden" name="author" value="<c:out value="${book.author}"/>">
					<div class="text-right mb-3">
						<button type="submit" class="btn btn-outline-success">追加</button>
					</div>
				</form>
				
				
				<div class="table-responsible">
					<table class="table table-borderd table-hover">
						<thead>
							<tr>
								<th>引用文</th>
								<th>pp</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${items}">
							<tr>
								<td>“<c:out value="${item.quote}"/>”</td>
								<td><c:out value="${item.page}"/></td>
								<td>
									<form action="${root_path}/QuoteUpdate" method="get">
										<input type="hidden" name="id" value="<c:out value="${item.id}"/>">
										<input type="hidden" name="bookId" value="<c:out value="${book.id}"/>">
										
										<input type="submit" value="修正" class="btn btn-outline-success">
									</form>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				
			</div>
		</div> 
	</div>
	
	<%@ include file="/WEB-INF/jsp/include/js.jsp" %>


</body>
</html>