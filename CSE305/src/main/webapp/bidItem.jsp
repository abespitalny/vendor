<%@page import="model.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<!--
	This is the Bid Item page
	This page contains a text field where user can enter the bid and click on update
	This page also refreshes the value of highest bid periodically using AJAX query
	This page redirects to the Home page after clicking on Home button
-->

<html>	

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />

		<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<h2>Bid Item</h2>
                        <c:if test="${empty item}">
                            <h3> Bid item not found! </h3> 
                        </c:if>
                        <c:if test="${not empty item}">
                            <div class="jumbotron">
                              <h1 class="display-4">Item ${item.itemID}</h1>
                              <p class="lead">${item.name}</p>
                              <p class="lead">${item.type}</p>
                              <hr class="my-4">
                              <p>${item.description}</p>
                              <div class="lead">
                                  <strong>Highest Bid: </strong><span id="highestBid"></span>
                                  <br />
                                  <strong>Current winner: </strong><span id="currentWinner"></span>
                                  <br />
                                  <button id="refreshBid">Refresh Bid</button>
                              </div>
                            </div>
                        </c:if>
		</div>
		
		<div class="container">
			<form action="home.jsp">
				<input type="submit" value="Home" class="btn btn-success"/>
			</form>
		</div>
		
		
                <script>
                    $(document).ready(function() {
                        $.get("getBid?auctionID=" + ${auctionID}, function(responseText) {                    
                            var data = responseText.split(",");
                            $("#highestBid").text(data[0]);
                            $("#currentWinner").text(data[1]);                      
                        });
                    });
                    
                    $("#refreshBid").on("click", function() {
                        $.get("getBid?auctionID=" + ${auctionID}, function(responseText) {                    
                            var data = responseText.split(",");
                            $("#highestBid").text(data[0]);
                            $("#currentWinner").text(data[1]);                      
                        });
                    });
		</script>
	</body>
</html>
