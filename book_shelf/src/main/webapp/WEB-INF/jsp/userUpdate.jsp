<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    pageContext.setAttribute("title", "会員情報更新", PageContext.PAGE_SCOPE);
    %>
    
<!DOCTYPE html>
<html lang="jp">

<%@ include file="/WEB-INF/jsp/include/head.jsp" %>

<body>
	<div class="container-md">
		<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="card my-3">
					<div class="card-header">${title}</div>
					<div class="card-body">
						<c:if test="${db_error != null}">
							<div class="alert alert-danger" role="alert">${db_error }</div>
						</c:if>
						
						<form action="${root_path}/UserUpdate" method="post">
							<div class="form-group">
								<label for="email">E-mailアドレス</label>
								<input type="text" name="email" id="email" 
									class="form-control<c:if test="${error.email != null}"> is-invalid</c:if>"
									value="<c:out value="${user.email }"/>">
								<c:if test="${error.email != null }">
									<span class="text-danger">${error.email}</span>
								</c:if>
							</div>
							
							<div class="form-group">
								<label for="password">パスワード</label>
								<input type="password" name="password" id="password"
									class="form-control<c:if test="${error.password!=null}"> is-invalid</c:if>"
									value="<c:out value="${user.password}"/>">
								<c:if test="${error.password!=null}">
									<span class="text-danger">${error.password}</span>
								</c:if>
							</div>
							
							<div class="form-group">
								<label for="name">ニックネーム</label>
								<input type="text" name="name" id="name"
									class="form-control<c:if test="${error.name!=null}"> is-invalid</c:if>"
									value="<c:out value="${user.name}"/>">
								<c:if test="${error.name!=nul}">
									<span class="text-danger">${error.name}</span>
								</c:if>
							</div>
							<button type="submit" class="btn btn-outline-success">更新</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/include/js.jsp" %>

</body>
</html>