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
<h1>Edit Questions</h1>
<div class="form-container">

<form action='../addquestion' method='post'>
	<input type='text' id="addquestion" name='question' value=''>
	<input class="submit-button" type='submit' name='ok' value='OK'>
</form>
<ol>
<c:forEach var="question" items="${requestScope.questionlist }">
	<li>${question.question} <a href='../deletequestion?id=${question.id}'><button class="submit-button">Delete</button></a> 
	<a href='../readtoupdatequestion?id=${question.id}'><button class="submit-button">Update</button></a>
</c:forEach>
</ol>
</div>
</body>
</html>