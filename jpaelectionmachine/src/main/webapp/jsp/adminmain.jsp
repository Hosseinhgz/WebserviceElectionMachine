<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
  <head>
  	<script src="jquery.js"></script> 
 
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">   
    <link href="../CSS/style.css" rel="stylesheet">
    
    <title>Hello App Engine</title>
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
	  <div class="container">
	    <h1>Admin page</h1>
	   	<img src="../images/admin-icon.png" alt="admin icon" style="width:100px;height:100px;"><br>    
		<a href="../readcandidate"><button class="button" type="button">Edit Candidates</button></a>
		<a href="../readquestion"><button class="button" type="button">Edit Questions</button></a>

	 </div>
</main>
</body>

</html>