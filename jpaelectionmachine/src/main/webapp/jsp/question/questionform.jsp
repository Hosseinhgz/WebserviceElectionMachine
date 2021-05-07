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
<p>Here you can add new question:</p>
<form action='../addquestion' method='post'>
	<input type='text' class="questionbox" name='question' placeholder="New question text" value=''>
	<input class="submit-button" type='submit' name='ok' value='OK'>
</form>
<hr>
<hr>
<ol>
<c:forEach var="question" items="${requestScope.questionlist}">
<div class="row">
<div class="question-container col-md-9">
	<li>${question.question} </li>
</div>

<div class="question-button col-md-3">
	<a href='../deletequestion?id=${question.id}'><button class="submit-button">Delete</button></a> 
	<a href='../readtoupdatequestion?id=${question.id}'><button class="submit-button">Update</button></a>
</div>
</div>
</c:forEach>
</ol>
</div>
</body>
</html>