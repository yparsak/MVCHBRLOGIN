<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>  
    <title>Title</title>
    <meta name="description" content="#">
    <meta name="keywords" content="#">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/custom.css">
    <script src="js/jquery-1.12.4.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/custom.js"></script>  
  </head>
  <body>
  <div class="header">
    <div class="container">
      <div class="logo"></div>
      <div class="loginbar">
      
      <c:if test="${empty id}">
        <input id="loginbtn" class="bluebtn" name="#" type="submit" value="Login">
      </c:if>
      <c:if test="${! empty id}">
        <a href="logout">Logout</a>
      </c:if>
        
      </div>
    </div>
  </div>
  <div class="main">
	  <c:if test="${!empty id}">
	  	${id} - ${name} ${lastname} ${email}
	  </c:if>
	  <c:if test="${empty id}">Please login or register</c:if>
  </div>
  <div class="footer">
    <div class="container">
      <div class="colL">&copy; 2017</div>
      <div class="colR">1.1</div>
    </div>
  </div>
  <div id="dialog" class="dialog" title="Login">
    <div id="login_form">
		<form method="POST" action="login">
      		<div class="row"><div class="colL">Login Name</div><div class="colR"><input id="Lname" name="name" type="text"></div></div>
      		<div class="row"><div class="colL">Password</div><div class="colR"><input id="Lpassword" name="password" type="password"></div></div>
      		<div class="row"><input id="loginsubmit" class="bluebtn" name="#" type="submit" value="Login"></div>
    	</form>
    		<div class="row"><a href="#" id="registerbtn">Register</a></div>
    </div>
    <div id="register_form">
	    <form method="POST" action="register">
	      <div class="row"><div class="colL">Name</div><div class="colR"><input id="Rname" name="name" type="text"></div></div>
	      <div class="row"><div class="colL">Last Name</div><div class="colR"><input id="Rlastname" name="lastname" type="text"></div></div>
	      <div class="row"><div class="colL">Email</div><div class="colR"><input id="Remail" name="email" type="text"></div></div>            
	      <div class="row"><div class="colL">Password</div><div class="colR"><input id="Rpassword" name="password" type="password"></div></div>
	      <div class="row"><input id="registersubmit" class="bluebtn" name="#" type="submit" value="Register"></div>
	    </form>
    </div>
  </div>
  <div id="coverup" class="coverup"></div>
  </body>
</html>