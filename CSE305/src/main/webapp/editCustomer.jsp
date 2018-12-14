<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>

<!--
	This is the Edit Customer page
	This page displays fields to edit a Customer 
	The details are sent to the UpdateCustomerController class in resources package
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit Customer</title>
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />	
	<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	
	<h1>Edit Customer:</h1>
	<c:if test="${empty editCustomer}">
		<h3> Customer details not found! </h3> 
	</c:if>
	<c:if test="${not empty editCustomer}"> 	
	<form action="updateCustomer" method="POST">
	  <div class="form-group">
	    <label for="username">Username</label>
	    <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" value="${editCustomer.username}" required>
	  </div>
          <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" value="${editCustomer.password}" required>
	  </div>
  	  <div class="form-group">
	    <label for="firstName">First Name</label>
	    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" value="${editCustomer.firstName}" required>
	  </div>
  	  <div class="form-group">
	    <label for="lastName">Last Name</label>
	    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" value="${editCustomer.lastName}" required>
	  </div>
   	  <div class="form-group">
	    <label for="address">Address</label>
	    <input type="text" class="form-control" id="address" name="address" placeholder="Address" value="${editCustomer.address}">
	  </div>
   	  <div class="form-group">
	    <label for="city">City</label>
	    <input type="text" class="form-control" id="city" name="city" placeholder="City" value="${editCustomer.city}">
	  </div>
   	  <div class="form-group">
	    <label for="state">State</label>
	    <input type="text" class="form-control" id="state" name="state" placeholder="State" value="${editCustomer.state}">
	  </div>
   	  <div class="form-group">
	    <label for="zipCode">Zip Code</label>
	    <input type="text" class="form-control" id="zipCode" name="zipCode" placeholder="Zip Code" value="${editCustomer.zipCode}">
	  </div>
   	  <div class="form-group">
	    <label for="telephone">Telephone</label>
	    <input type="text" class="form-control" id="telephone" name="telephone" placeholder="Telephone number" value="${editCustomer.telephone}">
	  </div>
          <div class="form-group">
	    <label for="email">Email</label>
	    <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${editCustomer.email}" required>
	  </div>
   	  <div class="form-group">
	    <label for="creditCardNum">Credit Card Number</label>
	    <input type="text" class="form-control" id="creditCardNum" name="creditCardNum" placeholder="XXXX-XXXX-XXXX-XXXX" value="${editCustomer.creditCardNum}">
	  </div>
          <div class="form-group">
	    <label for="rating">Rating</label>
	    <input type="text" class="form-control" id="rating" name="rating" placeholder="Rating" value="${editCustomer.rating}">
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