<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>


<!--
	This is the Highest Revenue Employee Details page
	This page displays the data for Employee (Customer Representative) who generated the highest revenue
	The details are fetched using the "employee" field from the request object
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Highest Revenue Employee Details</title>
</head>
<body>

	
	<div class="container">
	<h3>The Customer Representative that generated the highest revenue is:</h3>
	<c:if test="${empty employee}">
		<h3> Customer Representative details not found! </h3> 
	</c:if>
	<c:if test="${not empty employee}">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>Employee ID</th>
		      <th>First Name</th>
		      <th>Last Name</th>
                      <th>Email</th>
                      <th>Revenue</th>
		    </tr>
		  </thead>
		  <tbody>
		       <tr>
		         <td>${employee.username}</td>
		         <td>${employee.firstName}</td>
		         <td>${employee.lastName}</td>
		         <td>${employee.email}</td>
                         <td>${employee.revenue}</td>
		       </tr>
		  </tbody>
		</table>
	</c:if>
	</div>
	<div class="container pt-1">
		<form action="home.jsp">
			<input type="submit" value="Home" class="btn btn-success"/>
		</form>
	</div>

	
	<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
</body>
</html>