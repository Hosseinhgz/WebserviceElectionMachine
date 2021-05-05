<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../CSS/style.css" rel="stylesheet">

<title>Questionnaire</title>
</head>
<body>
<h1>Questionnaire</h1>
<div class="form-container">

<form action="../addquestion" method='post'>
	<ol>
	<c:forEach var="question" items="${requestScope.questionlist }">
		<li>${question.question} 
		   <div class="radio-buttons">
               <input type="radio" Class="recommend-radio" name="${requestScope.question.id}" value="1">
               <label id="definitely">Definitely Agree</label><br>
               <input type="radio" Class="recommend-radio" name="${requestScope.question.id}" value="2">
               <label id="maybe">Agree</label><br>
               <input type="radio" Class="recommend-radio" name="${requestScope.question.id}" value="3">
               <label id="not-sure">Middle opinion</label><br>
               <input type="radio" Class="recommend-radio" name="${requestScope.question.id}" value="4">
               <label id="not-sure">Disagree</label><br>
               <input type="radio" Class="recommend-radio" name="${requestScope.question.id}" value="5">
               <label id="not-sure">Completely disagree</label><br>
           </div>
           <p></p>
           <hr>
	</c:forEach>
	</ol>
     <div class="buttons">
        <button id="reset" class="button"  name="action">Reset</button>
     	<button id="finish" class="button" type="submit"  name="action">Finish</button>
     </div>	
</form>
</div>
</body>
</html>