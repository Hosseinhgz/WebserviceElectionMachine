<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../CSS/style.css" rel="stylesheet">

<title>Insert title here</title>
</head>
<body>
<div class="form-container">
<h1>Update the  question ${requestScope.question.id }</h1>
<form action='../updatequestion' method='post'>
<input type='text' name='id' value='${requestScope.question.id }'>
<input type='text' name='question' value='${requestScope.question.question }'>
<input class="submit-button" type='submit' name='ok' value='OK'>
</form>
</div>
</body>
</html>