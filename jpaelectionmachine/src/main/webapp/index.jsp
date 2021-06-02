<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">   
<link href="../CSS/style.css" rel="stylesheet">
<title>Election Machine</title>
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
           </ul>
         </div>
       </div>
     </nav>
</header>
	<main>
	  <div class="container">
	  	<img src="../images/elections-icon.png" alt="election icon" style="width:100px;height:100px;">
	  
	    <h1>Welcome to Election machine</h1>
	    <p>Path to Choose wise</p>
	    
		<a href="../showcandidate"><button class="button" type="button">Candidates</button></a>
		<a href='../readonequestion?id=1&question=It+is+too+easy+to+live+on+social+security+in+Finland&answer=0'><button class="button" type="button">Questionnaire</button></a>
	
	 </div>
</main>
</body>
</html>