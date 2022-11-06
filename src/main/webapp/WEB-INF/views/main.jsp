<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome BookShop</title>
<link rel ="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css"> 
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
</head>
<body>
<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
			<h1>gnb area</h1>
		</div>
		<div class="top_area">
			<div class="logo_area">
				<h1>logo area</h1>
			</div>
			<div class="search_area">
				<h1>Search area</h1>
			</div>
			<div class="login_area">
				<sec:authorize access="isAnonymous()">
				<div class="login_button"><a href="${pageContext.request.contextPath}/customLogin">로그인</a></div>
				<span><a href="${pageContext.request.contextPath}/member/signUp">회원가입</a></span>
               </sec:authorize>
               <sec:authorize access="hasRole('MEMBER')">
                  <form action="${pageContext.request.contextPath}/member/logout" method="post">
                     <a class="nav-link logout" href="#" >로그아웃</a>
                     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  </form>
               </sec:authorize>
			</div>
			<div class="clearfix"></div>			
		</div>
		<div class="navi_bar_area">
			<h1>navi area</h1>
		</div>
		<div class="content_area">
			<h1>content area</h1>
		</div>
	</div>
</div>
</body>

<script>
$(function(){
	   $('.logout').on('click',function(e){
	      e.preventDefault();
	      $(this).closest('form').submit();
	   })
	})



</script>





</html>




