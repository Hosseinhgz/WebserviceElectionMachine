<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<%@page import="java.text.DecimalFormat" %>
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
<h1>Results with all Candidates</h1>
<div class="form-container3">

<a href='../threesuggestions'><button class="button" type="button">Best candidates</button></a>

<c:forEach var="result" items="${requestScope.resultlist}">

<div class="row">
<div class="question-container2">
	<p >Candidate ID: ${result.candidateid} </p>
    <div class="card_photo">
        <div class="demobox" id="demobox1">
            <img src="./images/Candidate${result.candidateid}.jpg" alt="Candidate - tribute page" style="width:200px;height:130px;">
        </div>
    </div>
    <p><b>Similarity percent: <fmt:formatNumber  value = ' ${result.result}' type = "number" maxFractionDigits="2" /> %</b></p>
	<p><a href='../readtomorecandidate?id=${result.candidateid}' class="btn btn-primary">More Information</a></p>
</div>
</div>
</c:forEach>

</div>
</main>
</body>
</html>