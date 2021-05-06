<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../CSS/style.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">   

<title>Candidates List</title>
</head>
<body>
<div class="form-container">
<h1>Update the  candidates ${requestScope.candidate.id}</h1>
<form action='../updatecandidate?id=${candidate.id}' method='post'>
<div>${requestScope.candidate.id }.
<input type='text' name='candidate' class="questionbox" value='${requestScope.candidate.question }'>
<input class="submit-button" type='submit' name='ok' value='OK'></div>
</form>
</div>
</body>
</html>