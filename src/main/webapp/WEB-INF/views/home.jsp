<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<hr>
	<!--  file 화면 보여주기 get요청-->
	<%-- #{pageContext.request.contextPath } > 절대 경로 설정  --%>
	<a href="${pageContext.request.contextPath}/file/upload01.do" style="color:black"> 단일 파일 업로드 </a><br>
	<!--  file 화면 보여주기 get요청-->
	<a href="${pageContext.request.contextPath}file/upload02.do"> 파일 업로드 02  </a>
	
</body>
</html>
