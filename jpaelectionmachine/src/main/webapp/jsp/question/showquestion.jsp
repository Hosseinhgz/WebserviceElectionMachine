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
<h1>Questionnaire</h1>
<div class="form-container">
<h4>Question 2/19</h4>
<hr class="hr">
<hr class="hr">
<p>
<c:out value="${requestScope.questionlist[2]}" />
</p>
<form action="../updateanswer?id=1" method='post'>
<!--  ${question.id} - ${question.question}-->
		<input type="hidden" Class="recommend-radio" name="id" value="1">
	   <div class="radio-buttons">
              <input type="radio" Class="recommend-radio" name="answer" value="1">
              <label id="definitely">Definitely Agree</label><br>
              <input type="radio" Class="recommend-radio" name="answer" value="2">
              <label id="maybe">Agree</label><br>
              <input type="radio" Class="recommend-radio" name="answer" value="3">
              <label id="not-sure">Middle opinion</label><br>
              <input type="radio" Class="recommend-radio" name="answer" value="4">
              <label id="not-sure">Disagree</label><br>
              <input type="radio" Class="recommend-radio" name="answer" value="5">
              <label id="not-sure">Completely disagree</label><br>
          </div>

     <div class="buttons">
        <button id="previous" class="button"  name="action">Previous</button>
		<button id="next" class="button"  name="action">next</button>     
     	<button id="finish" class="button" type="submit"  name='ok' value='OK'>Finish</button>
     </div>	
</form>
</div>
</body>
</html>