<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">   
<link href="../CSS/style.css" rel="stylesheet">
<title>Candidates List</title>
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
<div class="form-container1">
<h1>Update the  candidates ${requestScope.candidate.id}</h1>
<form action='../updatecandidate?id=${candidate.id}' method='post'>
<div><h3>Candidate ${requestScope.candidate.id}</h3>
	<input type='hidden' id="idbox" class="candidatebox" name='id' value='${requestScope.candidate.id }'><br>
	<label><b>First name:</b></label>		
	<input type='text'  class="candidatebox" name='firstname' value='${requestScope.candidate.firstname }'><br>
	<label><b>Last name:</b></label>		
	<input type='text' class="candidatebox1" name='surname' value='${requestScope.candidate.surname }'><br>	
	<label><b>IKA: </b></label>		
	<input type='text' class="candidatebox1" name='ika' value='${requestScope.candidate.ika }'><br>	
	<label><b>Location: </b></label>		
	<input type='text' class="candidatebox1" name='location' value='${requestScope.candidate.location }'><br>	
	<label><b>Party: </b></label>		
	<input type='text' class="candidatebox1" name='party' placeholder="" value='${requestScope.candidate.party }'><br>	
	<label><b>Why Commission: </b></label>		
	<input type='text' class="candidatebox2" name='whyCommission' placeholder="" value='${requestScope.candidate.whyCommission }'><br>
	<label><b>What ATHES Want EDES: </b></label>		
	<input type='text' class="candidatebox2" name='whatAthesWantEdes' value='${requestScope.candidate.whatAthesWantEdes }'><br>	
	<label><b>Profession: </b></label>		
	<input type='text' class="candidatebox2" name='professional' value='${requestScope.candidate.professional }'><br>
	<input class="submit-button" type='submit' name='ok' value='OK'>
</div>
</form>
</div>
</body>
</html>