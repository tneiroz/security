<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="container">
   <h2>로그아웃</h2>
   <form action="${pageContext.request.contextPath}/customLogout" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      <button class="btn btn-primary">로그아웃</button>
   </form>
</div>
<%@ include file="../layout/footer.jsp" %>