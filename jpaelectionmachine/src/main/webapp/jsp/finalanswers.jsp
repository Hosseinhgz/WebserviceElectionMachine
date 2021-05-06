<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../CSS/style.css" rel="stylesheet">

<title>Final Answers</title>
</head>
<body>
<h1>Your final answers</h1>
<div class="form-container">

	<ol>
	<c:forEach var="question" items="${requestScope.answerlist }">
		<li>${answer} 
	</c:forEach>
	</ol>

</div>
</body>
</html>