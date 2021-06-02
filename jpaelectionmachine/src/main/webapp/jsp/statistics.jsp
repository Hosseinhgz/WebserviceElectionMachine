<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Question" %>
 <%@ page import="data.Statistic" %>
 
 
 <%@ page import="java.sql.Connection" %>  
 <%@ page import="java.sql.DriverManager" %>   



<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
 <head>
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">   
<link href="../CSS/style.css" rel="stylesheet">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {
        packages: ['corechart']
    });
</script>
<title>Statistics</title>
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
           </ul>
         </div>
       </div>
     </nav>
</header>
<main>
<div class="form-container1">
<h1>Statistics:</h1>

<c:forEach var="statistic" items="${requestScope.statisticlist}">
<div class="chart-container">
<h4>Question ${statistic.question} :</h4>
    <div id="container${statistic.question}" style="width: 680px; height: 300px; margin: 5px auto"></div>
    <script language="JavaScript">
        function drawChart() {
            /* Define the chart to be drawn.*/
            var data = google.visualization.arrayToDataTable([
                ['', 'Percent'],
                ['Completely disagree', ${statistic.numAns1}*100/(${statistic.numAns1}+${statistic.numAns2}+${statistic.numAns3}+${statistic.numAns4}+${statistic.numAns5})],
                ['Disagree', ${statistic.numAns2}*100/(${statistic.numAns1}+${statistic.numAns2}+${statistic.numAns3}+${statistic.numAns4}+${statistic.numAns5})],
                ['Middle opinion', ${statistic.numAns3}*100/(${statistic.numAns1}+${statistic.numAns2}+${statistic.numAns3}+${statistic.numAns4}+${statistic.numAns5})],
                ['Agree', ${statistic.numAns4}*100/(${statistic.numAns1}+${statistic.numAns2}+${statistic.numAns3}+${statistic.numAns4}+${statistic.numAns5})],
                ['Completely Agree', ${statistic.numAns5}*100/(${statistic.numAns1}+${statistic.numAns2}+${statistic.numAns3}+${statistic.numAns4}+${statistic.numAns5})]
            ]);
            var options = {
                title: 'Percent of choose',
                isStacked: true
            };
            /* Instantiate and draw the chart.*/
            var chart = new google.visualization.BarChart(document.getElementById('container${statistic.question}'));
            chart.draw(data, options);
        }
        google.charts.setOnLoadCallback(drawChart);
    </script>
</div>
<hr>
</c:forEach>

	<div class="info-img">
		<a href="../index.jsp"><button class="button" type="button">Go to Main</button></a>
	</div>
</div>

</main>
</body>
</html>