<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">   
<link href="../CSS/style.css" rel="stylesheet">

<title>Insert title here</title>
</head>
<body>
<header>
  <nav class="navbar navbar-expand-lg navbar-dark bg-light navbar-fixed-top">
      <div class="container-fluid">
        <a class="navbar-brand" href="../index.html">Election Machine</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href='/checkuser?logout=yes'>Log out</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#project-title">Statistics</a>
            </li>
          </ul>
        </div>
      </div>
   </nav>
</header>
<main>
<h1>Edit Candidates</h1>


<div class="form-container4">
<hr>
<h3>Create new Candidates</h3>
<hr>

<form action='../addcandidate' method='POST' >
	<label><b>Candidate ID </b></label>
	<input type='text' id="idbox" class="candidatebox" name='id' value=''><br>
	<label><b>First name: </b></label>		
	<input type='text'  class="candidatebox1" name='firstname' value=''><br>
	<label><b>Last name: </b></label>		
	<input type='text' class="candidatebox1" name='surname' value=''><br>	
	<b>IKA:</b><input type='text' class="candidatebox" name='ika' value=''><br>	
	<label><b>Location: </b></label>		
	<input type='text' class="candidatebox1" name='location' value=''><br>	
	<label><b>Party: </b></label>		
	<input type='text' class="candidatebox1" name='party' placeholder="" value=''><br>	
	<label><b>Why Commission: </b></label>		
	<input type='text' class="candidatebox2" name='whyCommission' placeholder="" value=''><br>
	<label><b>What ATHES Want EDES: </b></label>		
	<input type='text' class="candidatebox2" name='whatAthesWantEdes' value=''><br>	
	<label><b>Profession: </b></label>		
	<input type='text' class="candidatebox2" name='professional' value=''><br>
	<input class="submit-button" type='submit' name='ok' value='OK'>
</form>
</div>
<div class="form-container2">
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
</main>
</body>
</html>