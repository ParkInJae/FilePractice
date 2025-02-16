<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="upload02.do" , method="post" enctype="multipart/form-data">
		 file01 : <input type="file" name="file01"> <br>
		 file02 : <input type="file" name="file02"> <br>
		 file03 : <input type="file" name="file03"> <br>
		 <button> 업로드 </button>
	 </form>
	<hr>
	<form action="upload03.do", method="post" enctype="multipart/form-data">
	ChoiceMultifiles : <input type="file" name="multiFile" multiple><br>
	
	</form> 

</body>
</html>