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
	This is the Show All Auctions page
	This page displays the data for each Auction in the "auctions" request object
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Auction Details</title>
</head>
<body>

	<h1>The Auctions are:</h1>
	<div class="container">
	<c:if test="${empty auctions}">
		<h3> Auction Data not found! </h3> 
	</c:if>
	<c:if test="${not empty auctions}">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>Auction ID</th>
		      <th>Minimum Bid Price</th>
		      <th>Highest Bid Price</th>
		      <th>Copies</th>
		      <th>Seller</th>
		      <th>Item ID</th>
		      <th>Open Date</th>
		      <th>End Date</th>
                      <th></th>
		    </tr>
		  </thead>
		  <tbody>
		     <c:forEach items="${auctions}" var="cd">
		       <tr>
		         <td>${cd.auctionID}</td>
		         <td>${cd.minBidPrice}</td>
                         <td><c:out value="${empty cd.currentHighestBidPrice ? '-' : cd.currentHighestBidPrice}" /></td>
		         <td>${cd.numCopies}</td>
		         <td>${cd.seller}</td>		         
		         <td>${cd.itemID}</td>
		         <td>${cd.openDate}</td>
                         <td>${cd.endDate}</td>
		         <td>
		         	<form action="bidHistory" method="GET">
						<div class="form-group">
			            	<input type="hidden" class="form-control" name="auctionID" value="${cd.auctionID}" />
			        	</div>
						<input type="submit" value="Bid History" class="btn btn-success"/>
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