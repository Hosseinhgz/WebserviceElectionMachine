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
<h1>Edit Questions</h1>
<div class="form-container3">
<p>Here you can add new question:</p>
<form action='../addquestion' method='post'>
	<input type='text' class="questionbox" name='question' placeholder="New question text" value=''>
	<input class="submit-button" type='submit' name='ok' value='OK'>
</form>
<hr>
<hr>
<ol>
<c:forEach var="question" items="${requestScope.questionlist}">
<div class="row">
<div class="question-container col-md-8">
	<li>${question.question} </li>
</div>

<div class="question-buttons col-md-4">
	<a href='../deletequestion?id=${question.id}'><button class="submit-button-update">Delete</button></a> 
	<a href='../readtoupdatequestion?id=${question.id}'><button class="submit-button-delete">Update</button></a>
</div>
</div>
</c:forEach>
</ol>
</div>
</main>
</body>
</html>