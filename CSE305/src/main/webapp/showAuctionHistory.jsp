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
	This is the Show Auction History page
	This page displays the history of a particular auction in terms of bidding history
	The details are fetched using the "bids" field from the request object
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Auction History</title>
</head>
<body>

	<h1>The Auction History is:</h1>
	<div class="container">
	<c:if test="${empty bids}">
		<h3> Auction History not found! </h3> 
	</c:if>
	<c:if test="${not empty bids}">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>Customer ID</th>
		      <th>Auction ID</th>
		      <th>Bid Price</th>
		      <th>Bid Time</th>
		    </tr>
		  </thead>
  		  <tbody>
		     <c:forEach items="${bids}" var="cd">
		       <tr>
		         <td>${cd.customerID}</td>
		         <td>${cd.auctionID}</td>		         
		         <td>${cd.bidPrice}</td>
		         <td>${cd.bidTime}</td>		         
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