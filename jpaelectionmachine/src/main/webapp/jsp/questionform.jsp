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
<h1>Edit Questions</h1>
<form action='../addquestion' method='post'>
<input type='text' name='question' value=''>
<input class="submit-button" type='submit' name='ok' value='OK'>
</form>
<ol>
<c:forEach var="question" items="${requestScope.questionlist }">
	<li>${question} <button class="submit-button"><a href='../deletequestion?id=${question.id}'>Delete</a></button> <button class="submit-button">
	<a href='../readtoupdatequestion?id=${question.id}'>Update</a></button>
</c:forEach>
</ol>
</div>
</body>
</html>