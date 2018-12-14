<%@page import="org.apache.taglibs.standard.tag.el.core.ForEachTag"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>


<!--
	This is the Customer Mailing List page
	This page displays the data for each Customer object in the ArrayList from the getCustomers method in dao.CustomerDao class
	The details are fetched using the "customers" field from the request object
-->


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Customer Mailing List</title>
</head>
<body>
	<h1>Customer Mailing List:</h1>
	<br/>
	<div class="container">
	<h3>The Customer Details are:</h3>
	<c:if test="${empty customers}">
		<h3> Customer details not found! </h3> 
	</c:if>
	<c:if test="${not empty customers}">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>Customer ID</th>
		      <th>First Name</th>
		      <th>Last Name</th>
		      <th>Address</th>
		      <th>City</th>
		      <th>State</th>
		      <th>Zip Code</th>
                      <th>Email</th>
		    </tr>
		  </thead>
		  <tbody>
		     <c:forEach items="${customers}" var="cd">
		       <tr>
		         <td>${cd.username}</td>
		         <td>${cd.firstName}</td>
		         <td>${cd.lastName}</td>
		         <td>${cd.address}</td>
		         <td>${cd.city}</td>
		         <td>${cd.state}</td>
		         <td>${cd.zipCode}</td>
		         <td>${cd.email}</td>
		       </tr>
		     </c:forEach>
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