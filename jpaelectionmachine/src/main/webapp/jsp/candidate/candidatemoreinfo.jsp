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
<main>
<h1> ${candidate.firstname} ${candidate.surname}</h1>
<div class="form-container">
	<div class="candidate-info">
	<div class="info-img">
		<img  src="./images/Candidate${candidate.id}.jpg" style="width:400px;height:260px;border-radius:30px;">
	</div>
		<p class="info-text"><b>Candidate ID: </b>${candidate.id}</p>
		<p class="info-text"><b>First Name: </b>${candidate.firstname}</p>
		<p class="info-text"><b>Last Name: </b>${candidate.surname}</p>
		<p class="info-text"><b>IKA: </b>${candidate.ika}</p>
		<p class="info-text"><b>Location: </b>${candidate.location}</p>
		<p class="info-text"><b>Party: </b>${candidate.party}</p>
		<p class="info-text"><b>Profession: </b>${candidate.professional}</p>
		<p class="info-text"><b>WHAT ATHES WANT EDES: </b>${candidate.whatAthesWantEdes}</p>
		<p class="info-text"><b>WHY COMMISSION: </b>${candidate.whyCommission}</p>
	</div>
	<div class="info-img">
		<a href="../showcandidate"><button class="button" type="button">Back to Candidates</button></a>
	</div>
</div>
</main>
</body>
</html>