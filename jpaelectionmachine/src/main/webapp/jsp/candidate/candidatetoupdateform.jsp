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
<div><h3>Candidate ${requestScope.candidate.id}</h3>
	<input type='hidden' id="idbox" class="candidatebox" name='id' value='${requestScope.candidate.id }'><br>
	<label><b>First name:</b></label>		
	<input type='text'  class="candidatebox" name='firstname' value='${requestScope.candidate.firstname }'><br>
	<label><b>Last name:</b></label>		
	<input type='text' class="candidatebox" name='surname' value='${requestScope.candidate.surname }'><br>	
	<label><b>IKA: </b></label>		
	<input type='text' class="candidatebox" name='ika' value='${requestScope.candidate.ika }'><br>	
	<label><b>Location: </b></label>		
	<input type='text' class="candidatebox" name='location' value='${requestScope.candidate.location }'><br>	
	<label><b>Party: </b></label>		
	<input type='text' class="candidatebox" name='party' placeholder="" value='${requestScope.candidate.party }'><br>	
	<label><b>Why Commission: </b></label>		
	<input type='text' class="candidatebox" name='whyCommission' placeholder="" value='${requestScope.candidate.whyCommission }'><br>
	<label><b>What ATHES Want EDES: </b></label>		
	<input type='text' class="candidatebox" name='whatAthesWantEdes' value='${requestScope.candidate.whatAthesWantEdes }'><br>	
	<label><b>Profession: </b></label>		
	<input type='text' class="candidatebox" name='professional' value='${requestScope.candidate.professional }'><br>
	<input class="submit-button" type='submit' name='ok' value='OK'>
</div>
</form>
</div>
</body>
</html>