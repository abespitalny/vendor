<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
	This is the Home page for Customer Representative
	This page contains various buttons to navigate the online auction house
	This page contains customer representative specific accessible buttons
-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		<title>Customer Home</title>
	</head>
	<body>
	
		<h1>Welcome to the Online Auction House!</h1>
		<div class="container">
			<h2>Customer Options:</h2>
			<%
			String email = (String)session.getAttribute("email");
			String role = (String)session.getAttribute("role");
			
			//redirect to appropriate home page if already logged in
			if(email != null) {
				if(role.equals("manager")) {
					response.sendRedirect("managerHome.jsp");
				}
				else if(role.equals("customer_rep")) {
					response.sendRedirect("customerRepresentativeHome.jsp");
				}
			}
			else {
				// redirect to log in if not alreaddy logged in
				response.sendRedirect("index.jsp");
			}
			%>
			
			<div class="row">
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Bid History</h5>
    					<div class="container">
							<form action="getCustomerAuctions" method="GET">
								<input type="submit" value="Bid History" class="btn btn-success"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">History of all auctions participated in</h5>
    					<div class="container">
							<form action="getAuctionHistory">
								<input type="submit" value="All History" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Items sold by Seller</h5>
    					<div class="container">
							<form method="GET" action="getSellers">
								<input type="submit" value="Seller Info" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Items in Auctions</h5>
    					<div class="container">
							<form action="searchItems" method="GET">
								<input type="submit" value="View Auctions" class="btn btn-success"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Best Seller Items</h5>
    					<div class="container">
							<form method="GET" action="getBestsellersForCustomer">
								<input type="submit" value="View Best Sellers" class="btn btn-success"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Personalized Item Suggestion List</h5>
    					<div class="container">
							<form action="personalizedSuggestions">
								<input type="submit" value="View Personalized Suggestions" class="btn btn-success"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				
		</div>
		<div class="container">
			<form action="logout">
				<input type="submit" value="Logout" class="btn btn-danger"/>
			</form>
		</div>
		
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>