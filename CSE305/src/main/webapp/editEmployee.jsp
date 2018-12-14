<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>

<!--
	This is the Edit Employee page
	This page displays fields to edit an Employee 
	The details are sent to the UpdateEmployeeController class in resources package
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit Employee</title>
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />	
	<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	
	<h1>Edit Employee:</h1>
	<c:if test="${empty editEmployee}">
		<h3> Employee details not found! </h3> 
	</c:if>
	<c:if test="${not empty editEmployee}"> 	
	<form action="updateEmployee" method="POST">
          <div class="form-group">
	    <label for="username">Username</label>
	    <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" value="${editEmployee.username}" required>
	  </div>
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" value="${editEmployee.password}" required>
	  </div>
  	  <div class="form-group">
	    <label for="firstName">First Name</label>
	    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" value="${editEmployee.firstName}" required>
	  </div>
  	  <div class="form-group">
	    <label for="lastName">Last Name</label>
	    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" value="${editEmployee.lastName}" required>
	  </div>
   	  <div class="form-group">
	    <label for="address">Address</label>
	    <input type="text" class="form-control" id="address" name="address" placeholder="Address" value="${editEmployee.address}">
	  </div>
   	  <div class="form-group">
	    <label for="city">City</label>
	    <input type="text" class="form-control" id="city" name="city" placeholder="City" value="${editEmployee.city}">
	  </div>
   	  <div class="form-group">
	    <label for="state">State</label>
	    <input type="text" class="form-control" id="state" name="state" placeholder="state" value="${editEmployee.state}">
	  </div>
   	  <div class="form-group">
	    <label for="zipCode">Zip Code</label>
	    <input type="text" class="form-control" id="zipCode" name="zipCode" placeholder="Zip Code" value="${editEmployee.zipCode}">
	  </div>
   	  <div class="form-group">
	    <label for="telephone">Telephone</label>
	    <input type="text" class="form-control" id="telephone" name="telephone" placeholder="Telephone number" value="${editEmployee.telephone}">
	  </div>
          <div class="form-group">
	    <label for="email">Email</label>
	    <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${editEmployee.email}" required>
	  </div>
   	  <div class="form-group">
	    <label for="ssn">SSN</label>
	    <input type="text" class="form-control" id="ssn" name="ssn" placeholder="XXX-XX-XXXX" value="${editEmployee.ssn}" required>
	  </div>
   	  <div class="form-group">
	    <label for="hourlyRate">Hourly Rate</label>
	    <input type="text" class="form-control" id="hourlyRate" name="hourlyRate" placeholder="Hourly Rate" value="${editEmployee.hourlyRate}" required>
	  </div>
	  
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
	</c:if>
	</div>
	<div class="container pt-1">
		<form action="home.jsp">
			<input type="submit" value="Home" class="btn btn-success"/>
		</form>
	</div>


</body>
</html>