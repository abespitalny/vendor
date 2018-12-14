<%@page import="org.apache.taglibs.standard.tag.el.core.ForEachTag"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!--
	This is the Open Auction Details page
	This page displays the data for each Auction which is currently open
	The details are fetched using the "auctions" field from the request object
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Open Auction Details</title>
</head>
<body>

	<h1>The Open Auctions are:</h1>
	<div class="container">
	<c:if test="${empty auctions}">
		<h3> Auction Data not found! </h3> 
	</c:if>
	<c:if test="${not empty auctions}">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>Auction ID</th>
		      <th>Item ID</th>
		      <th>Copies</th>
		      <th>Winning Bidder</th>
		      <th>Bid Time</th>
		      <th>Bid Price</th>
		      <th>Sold Price</th>
                      <th>End Date</th>
                      <th></th>
		    </tr>
		  </thead>
		  <tbody>
		     <c:forEach items="${auctions}" var="cd" varStatus="loop">
		       <tr>
		         <td>${cd.auctionID}</td>
		         <td>${cd.itemID}</td>		         
		         <td>${cd.numCopies}</td>
		         <td>${bids[loop.index].customerID}</td>		         
		         <td>${bids[loop.index].bidTime}</td>
		         <td>${bids[loop.index].bidPrice}</td>
		         <td>${cd.currentHighestBidPrice}</td>
                         <td>${cd.endDate}</td>
		         <td>
		         	<form method="POST" action="recordSale">
						<div class="form-group">
			            	<input type="hidden" class="form-control" name="auctionID" value="${cd.auctionID}">
			        	</div>
						<input type="submit" value="Record the Sale" class="btn btn-success"/>
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