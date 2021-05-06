<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../CSS/style.css" rel="stylesheet">

<title>Edit admin accounts</title>
</head>
<body>
<h1>Edit Admins</h1>
<div class="form-container">
<form action='../addadmin' method='post'>
	<input type='text' class="textfield" name='firstname' placeholder="First Name" required><br>
	<input type='text' class="textfield" name='lastname' placeholder="Family name" required><br>
	<input type='text' class="textfield" name='username' placeholder="Username" required><br>
	<input type='email' class="textfield" name='email' placeholder="example@test.com" required><br>
	<input type='password' class="textfield" name='password' placeholder="Password" required><br>
	
	<input class="submit-button" type='submit' name='ok' value='OK'>
</form>
<ol>
<c:forEach var="admin" items="${requestScope.adminlist}">
	<li>${admin} <a href='../deleteadmin?id=${admin.id}'><button class="submit-button">Delete</button></a> 
	
</c:forEach>
</ol>
</div>
</body>
</html>