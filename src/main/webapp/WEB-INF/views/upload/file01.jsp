<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!--  태그립 선언  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>단순 파일 업로드 예제</h2>
	<!-- get 요청 성공시 보여줄 화면 -->
	<form action="upload01.do" method="post" enctype="multipart/form-data">
		file : <input type="file" name="file01"><br>      <!-- 파일 생성 html -->
		title : <input type="text" name="title"><br> 	  <!-- 제목 생성 html -->
		content :<textarea name="content"></textarea><br> <!-- 내용 생성 html -->
		<button>저장</button>
	</form>
	
	
	
	<h2> file 화면</h2>
<form>
 			file : <input type="file" name="file02">
 			title : <input type="text" neam="title02">
 			content : <textarea name="content"></textarea>
</form>
</body>
</html>