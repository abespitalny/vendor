<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>

<!--
	This is the Search Customer page
	This page contains a text field where search parameter (name, address, city, state, etc) can be entered to search for Customers in the database
	This page redirects to the Customer Listing page
-->


<html>
	<head>
                <title>Search Customer</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	</head>
	<body>
		<div class="container">
			<h2>Search Customer</h2>
			<form action="getCustomers">
				<input type="text" name="searchKeyword" class="form-control" />
				<input type="submit" value="Search" class="btn btn-success"/>
			</form>
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
