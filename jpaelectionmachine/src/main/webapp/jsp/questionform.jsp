<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action='../addquestion' method='post'>
<input type='text' name='question' value=''>
<input type='submit' name='ok' value='OK'>
</form>
<ol>
<c:forEach var="question" items="${requestScope.questionlist }">
	<li>${fish} <a href='../deletequestion?id=${fish.id}'>Delete</a> <a href='../readtoupdatefish?id=${question.id}'>Update</a>
</c:forEach>
</ol>
</body>
</html>