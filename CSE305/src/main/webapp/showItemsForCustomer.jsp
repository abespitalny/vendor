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
	This is the Customer-Item Details page
	This page displays the data for items searched using Customer Name
	The details are fetched using the "items" field from the request object
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Customer-Item Details</title>
</head>
<body>

	<h1>The Customer-wise Item Details are:</h1>
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
		      <th>Type</th>
		      <th>Minimum Bid Price</th>
                      <th>Highest Bid Price</th>
                      <th>Copies</th>
		      <th>Open Date</th>
		      <th>End Date</th>
		      <th></th>
		    </tr>
		  </thead>
		  <tbody>
		     <c:forEach items="${items}" varStatus="loop">
		       <tr>
		         <td>${items[loop.index].itemID}</td>
		         <td>${items[loop.index].name}</td>		         
		         <td>${items[loop.index].type}</td>
		         <td>${auctions[loop.index].minBidPrice}</td>
		         <td><c:out value="${empty auctions[loop.index].currentHighestBidPrice ? '-' : auctions[loop.index].currentHighestBidPrice}" /></td>
                         <td>${auctions[loop.index].numCopies}</td>
                         <td>${auctions[loop.index].openDate}</td>
                         <td>${auctions[loop.index].endDate}</td>
		         <td>
    	         	<form method="GET" action="bidInAuction">
						<div class="form-group">
			            	<input type="hidden" class="form-control" name="auctionID" value="${auctions[loop.index].auctionID}">
			        	</div>
						<input type="submit" value="Bid" class="btn btn-success"/>
					</form>		         
		         </td>
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