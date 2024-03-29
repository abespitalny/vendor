<%@page import="org.apache.taglibs.standard.tag.el.core.ForEachTag"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Bid"%>
<%@page import="model.Auction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>


<!--
	This is the Sales Listing page
	This page displays the data for sales based on search criteria (item name or customer name)
	The details are fetched using the "bids" field from the request object
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Sales Listing</title>
</head>
<body>

	<h1>The Sales Listing is:</h1>
	<h4>(Based on the search criteria)</h4>
	<div class="container">
	<c:if test="${empty bids}">
		<h3> Bids not found! </h3> 
	</c:if>
	<c:if test="${not empty bids}">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>Buyer</th>
		      <th>Auction ID</th>
                      <th>Seller</th>
		      <th>Bid Price</th>
                      <th>Sold Price</th>
		      <th>Bid Time</th>
		    </tr>
		  </thead>
		  <tbody>
		     <c:forEach items="${bids}" var="cd" varStatus="loop">
		       <tr>
		         <td>${cd.customerID}</td>
		         <td>${cd.auctionID}</td>
                         <td>${auctions[loop.index].seller}</td>
		         <td>${cd.bidPrice}</td>
                         <td>${auctions[loop.index].currentHighestBidPrice}</td>
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