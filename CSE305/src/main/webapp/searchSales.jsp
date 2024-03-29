<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>

<!--
	This is the Search Sales page
	Sales can be searched by item name or customer name
	This page redirects to the Show Sales Listing page
-->


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	</head>
	<body>
	<h2>Search Sales</h2>
		<div class="container">
			<h2>Search Item Name or Customer Name:</h2>
			<form action="getSales">
                                <select name="searchBy">
                                    <option value="item">Item Name</option>
                                    <option value="buyer">Buyer Name</option>
                                    <option value="seller">Seller Name</option>
                                </select>
				<input type="text" name="searchKeyword" placeholder="Item Name or Customer Name" class="form-control" />
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
