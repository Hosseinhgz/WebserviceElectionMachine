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

<div class="form-container2">
<div class="add-candidate">
<hr>
<h3>Create new Candidates</h3>
<hr>

<form action='../addcandidate' method='POST' class="candidate_form">
	<label><b>Candidate ID</b></label>
	<input type='text' id="idbox" class="candidatebox" name='id' value=''><br>
	<label><b>First name:</b></label>		
	<input type='text'  class="candidatebox" name='firstname' value=''><br>
	<label><b>Last name:</b></label>		
	<input type='text' class="candidatebox" name='surname' value=''><br>	
	<label><b>IKA: </b></label>		
	<input type='text' class="candidatebox" name='ika' value=''><br>	
	<label><b>Location: </b></label>		
	<input type='text' class="candidatebox" name='location' value=''><br>	
	<label><b>Party: </b></label>		
	<input type='text' class="candidatebox" name='party' placeholder="" value=''><br>	
	<label><b>Why Commission: </b></label>		
	<input type='text' class="candidatebox" name='whyCommission' placeholder="" value=''><br>
	<label><b>What ATHES Want EDES: </b></label>		
	<input type='text' class="candidatebox" name='whatAthesWantEdes' value=''><br>	
	<label><b>Profession: </b></label>		
	<input type='text' class="candidatebox" name='professional' value=''><br>
	<input class="submit-button" type='submit' name='ok' value='OK'>
</form>
</div>

<div class="row">
<c:forEach var="candidate" items="${requestScope.candidatelist}">
<div class="col-sm-4">
    <div id="project1" class="project_cards">
       <div class="card_photo">
           <div class="demobox" id="demobox1">
               <a href="https://codepen.io/hosseinhgz/pen/qBaeoOa"><img src="./images/Candidate${candidate.id}.jpg" alt="Candidate - tribute page" style="width:400px;height:260px;"></a>
           </div>
       </div>
       <div class="card-header">
           <h4>${candidate.firstname} - ${candidate.surname}</h4>
       </div>
       <div class="card-describe">
       	<p class="card-text"><b>Candidate id:</b> ${candidate.id}</p>	    
	   	<p class="card-text"><b>Candidate Party:</b> ${candidate.party}</p>	    
	    <p class="card-text"><b>Candidate Location:</b> ${candidate.location}</p>    
	    <p class="card-text"><b>Candidate profession:</b> ${candidate.professional}</p>
	    <a href='../readtomorecandidate?id=${candidate.id}' class="btn btn-primary">More Information</a>
	    <a href='../deletecandidate?id=${candidate.id}'><button class="btn btn-primary">Delete</button></a> 
		<a href='../readtoupdatecandidate?id=${candidate.id}'><button class="btn btn-primary">Update</button></a>
       </div>
   </div>
</div>	

</c:forEach>
</div>
</div>
</body>
</html>