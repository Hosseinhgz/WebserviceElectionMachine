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
         <a class="navbar-brand" href="../index.jsp">Election Machine</a>
         <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
           <span class="navbar-toggler-icon"></span>
         </button>
         <div class="collapse navbar-collapse" id="navbarSupportedContent">
           <ul class="navbar-nav me-auto mb-2 mb-lg-0">
             <li class="nav-item">
               <a class="nav-link" aria-current="page" href=<% if (session.getAttribute("username")==null){out.println("/jsp/adminlogin.jsp");}else{out.println("/checkuser?logout=yes");} %>>
               <% if (session.getAttribute("username")==null){out.println("Login");}else{out.println("logout");} %></a>             </li>
             <li class="nav-item">
               <a class="nav-link" href="../statistics">Statistics</a>
             </li>
           </ul>
         </div>
       </div>
     </nav>
</header>
<main>
<h1>your Answers</h1>
<div class="form-container3">


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

<div class="table-container">
   <table>				<!-- create an table object -->
     <tr>				<!-- "tr" represents a row -->
       <th> Number </th>	<!-- use "th" to indicate header row -->
       <th> Question </th>
       <th> Answer </th>
     </tr> 
		<c:forEach var="question" items="${requestScope.answerlist}">		
		    <tr>				<!-- once again use tr for another row -->
		      <td>${question.id}</td>	<!-- use "td" henceforth for normal rows -->
		      <td>${question.question}</td>
		      <td>${question.answer}</td>
		    </tr>  
		</c:forEach>  
	</table>
</div>

	<div class="info-img">
		<a href="../calcresults"><button class="button" type="button">Go to Suggestions</button></a>
	</div>
	
</div>
</main>
</body>
</html>