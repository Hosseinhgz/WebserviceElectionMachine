<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../CSS/style.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">   


<title>Insert title here</title>
</head>
<body>
<h1>Edit Candidates</h1>

<div class="form-container">
<h3>Create new Candidates</h3>

<form action='../addcandidate' method='post'>
	<label>Candidate ID</label>
	<input type='text' class="questionbox" name='id' placeholder="id" value=''><br>
	<label>Candidate Last name:</label>	
	<input type='text' class="questionbox" name='surname' placeholder="Last name" value=''><br>
	<label>Candidate first name:</label>		
	<input type='text' class="questionbox" name='firstname' placeholder="First name" value=''><br>
	
	<input class="submit-button" type='submit' name='ok' value='OK'>
	<hr>
</form>
<ol>
<c:forEach var="candidate" items="${requestScope.candidatelist}">
	<li>${candidate.firstname} 
	<!--  <a href='../deletecandidate?id=${candidate.id}'><button class="submit-button">Delete</button></a> 
	<a href='../readtoupdatecandidate?id=${candidate.id}'><button class="submit-button">Update</button></a>-->
</c:forEach>
</ol>
</div>
</body>
</html>