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
	This is the Item Details page
	This page displays the data for each item
	The details are fetched using the "items" field from the request object
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Item Details</title>
</head>
<body>

	<h1>The Items are:</h1>
	<div class="container">
	<c:if test="${empty items}">
		<h3> Items not found! </h3> 
	</c:if>
	<c:if test="${not empty items}">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>Item ID</th>
		      <th>Name</th>
		      <th>Description</th>
		      <th>Type</th>
		      <th>Number of Copies</th>
		    </tr>
		  </thead>
		  <tbody>
		     <c:forEach items="${items}" var="cd">
		       <tr>
		         <td>${cd.itemID}</td>
		         <td>${cd.name}</td>		         
		         <td>${cd.description}</td>
		         <td>${cd.type}</td>		         
		         <td>${cd.numInStock}</td>
		         <td></td>
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