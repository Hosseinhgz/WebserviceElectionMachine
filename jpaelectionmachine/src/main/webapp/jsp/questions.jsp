<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
  	<script src="jquery.js"></script> 
 
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">   
    <link href="../CSS/style.css" rel="stylesheet">

<title>Questionnaire</title>
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
               <% if (session.getAttribute("username")==null){out.println("Login");}else{out.println("logout");} %></a></li>
             <li class="nav-item">
               <a class="nav-link" href="../calcstatistics">Statistics</a>
             </li>
             <li class="nav-item">
               <a class="nav-link" href=<% if (session.getAttribute("username")==null){out.println("/jsp/adminlogin.jsp");}else{out.println("/jsp/adminmain.jsp");} %>>Admin</a>
             </li>             
           </ul>
         </div>
       </div>
     </nav>
</header>
<main>
<h1>Questionnaire</h1>
<div class="form-container">
<hr class="hr">
<h4>Question ${requestScope.question.id}/19</h4>
<hr class="hr">

<p>
<c:out value="${requestScope.question.id} - ${requestScope.question.question}" />
</p>
<!--  <form action='../readonequestion?id=${question.id+1}' method='post'>-->
<form data-ajax="false" method="post"  id="radioanswer">

		<!--  <script>
		function changeAction(val) {
		document.forms[0].action = val;
		}
		</script>-->
		
		<input type="hidden"  name="id2" value="${requestScope.question.id}">
		<input type="hidden"  name="question" value="${requestScope.question.question}">
		<input type="hidden"  name="" value="${requestScope.question.answer}" >

		
	   <div class="radio-buttons">
	          <input type="radio" Class="recommend-radio" name="answer" value="0" style="visibility: hidden;" checked ><br>
              <input type="radio" Class="recommend-radio" name="answer" value="1" >
              <label id="definitely">Definitely Agree</label><br>
              <input type="radio" Class="recommend-radio" name="answer" value="2" >
              <label id="maybe">Agree</label><br>
              <input type="radio" Class="recommend-radio" name="answer" value="3">
              <label id="not-sure">Middle opinion</label><br>
              <input type="radio" Class="recommend-radio" name="answer" value="4">
              <label id="not-sure">Disagree</label><br>
              <input type="radio" Class="recommend-radio" name="answer" value="5">
              <label id="not-sure">Completely disagree</label><br>
              <hr>
              <p><b>your current saved choice is : ${requestScope.question.answer}</b></p>
              <hr>
          </div>

     <div class="buttons" data-role="fieldcontain">
     	<input formaction='../backonequestion?id=${requestScope.question.id-1}'  id="previous" type="submit" class="question-button" name="action" value="Previous"> 
		<input formaction='../readonequestion?id=${requestScope.question.id+1}' id="next" type="submit" class="question-button"  name="action" value="Next">
		<input formaction='../showresult'  type="submit" class="question-button"  name="ok" value="Finish">
			
     </div>	
</form>
</div>
</main>
</body>
<script type="text/javascript">
	let index =  ${requestScope.question.id} ;
	if (index==1){
		document.getElementById("previous").disabled = true;
	}
	let index2 =  ${requestScope.question.id} ;
	if (index2==19){
		document.getElementById("next").disabled = true;
	}

</script>
</html>