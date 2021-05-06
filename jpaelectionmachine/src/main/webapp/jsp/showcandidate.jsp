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
<h1>Candidates List</h1>
<div class="form-container">

<ol>
<c:forEach var="candidate" items="${requestScope.candidatelist}">
	<div class="card" style="width: 18rem;">
	  <img src="./images/gwen-weustink-I3C1sSXj1i8-unsplash.jpg" class="card-img-top" alt="...">
	  <div class="card-body">
	    <h5 class="card-title">${requestScope.candidate.firstname} - ${requestScope.candidate.surname}</h5>
	   	<p class="card-text">Candidate Party: ${requestScope.candidate.party}</p>	    
	    <p class="card-text">Candidate Party: ${requestScope.candidate.email}</p>    
	    <a href="#" class="btn btn-primary">More details</a>
	  </div>
	</div>

</c:forEach>
</ol>

</div>
</body>
</html>