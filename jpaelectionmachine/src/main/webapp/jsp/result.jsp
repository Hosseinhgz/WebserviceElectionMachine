<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">   
<link href="../CSS/style.css" rel="stylesheet">
<title>result page</title>
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
                  <a class="nav-link" aria-current="page" href="../jsp/adminlogin.jsp">Login</a>
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
<h1>your result</h1>
<div class="form-container3">

<ul>
<c:forEach var="result" items="${requestScope.resultlist}">
<div class="row">
<div class="question-container1">
	<li>${result.id} ** ${result.result} </li>
</div>
</div>
</c:forEach>
</ul>


<ul>
<c:forEach var="candidateanswer" items="${requestScope.candidateanswerlist}">
<div class="row">
<div class="question-container1">
	<li>${candidateanswer.id} ** ${candidateanswer.candidateid} ** ${candidateanswer.candidateid} ** ${candidateanswer.question} - ${candidateanswer.candidateans} </li>
	<p>${candidateanswer.comment}</p>
</div>
</div>
</c:forEach>
</ul>

<ul>
<c:forEach var="question" items="${requestScope.answerlist}">
<div class="row">
<div class="question-container">
	<li>${question.id} - ${question.question} - ${question.answer} </li>
</div>
</div>
</c:forEach>
</ul>
</div>
</main>
</body>
</html>