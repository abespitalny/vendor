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
	This is the Search Items page
	This page displays fields to search an item by type or by name 
	The item type details are fetched from "items" request attribute
-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	</head>
	<body>
	
		<h1>Search Item:</h1>
		<div class="container">
			<h2>Search Options:</h2>
			<div class="row">
				<c:if test="${empty items}">
				</c:if>
				<c:if test="${not empty items}">						
					<div class="col">
						<div class="card">
						  <div class="card-body">
						    <h5 class="card-title">Search by Item Type</h5>
	    					<div class="container">
								<form method="GET" action="searchItemsByType">
									<label for="itemType">Select Item Type:</label>
				   				    <select class="form-control" name="itemType">
										<c:forEach items="${items}" var="cd">
											<option value="${cd.type}"> <c:out value = "${cd.type}"/></option>
										</c:forEach>
								    </select>
									<input type="submit" value="Search" class="btn btn-primary"/>
								</form>
							</div>
						  </div>
						</div>
					</div>
				</c:if>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Search by Item Name</h5>
    					<div class="container">
							<form method="GET" action="searchItemsByName">
								<label for="itemName">Item Name:</label>
	    						<input type="text" class="form-control" id="itemName" name="itemName" placeholder="Type Item Name or a Part of it">
								<input type="submit" value="Search" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
			</div>				
		</div>
		<div class="container pt-1">
			<form action="home">
				<input type="submit" value="Home" class="btn btn-success"/>
			</form>
		</div>
		
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>