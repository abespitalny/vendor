<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
	This is the Home page for Manager
	This page contains various buttons to navigate the online auction house
	This page contains manager specific accessible buttons
-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		<title>Manager Home</title>
	</head>
	<body>
	
		<h1>Welcome to the Online Auction House!</h1>
		<div class="container">
			<h2>Manager Options:</h2>
			<%
				String email = (String)session.getAttribute("email");
				String role = (String)session.getAttribute("role");
				
				//redirect to appropriate home page if already logged in
				if(email != null) {
					if(role.equals("customer_rep")) {
						response.sendRedirect("customerRepresentativeHome.jsp");
					}
					else if(!role.equals("manager")){
						response.sendRedirect("home.jsp");	
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
					    <h5 class="card-title">Manage Employee</h5>
    					<div class="container">
							<form method="GET" action="addEmployee.jsp">
								<input type="submit" value="Add Employee" class="btn btn-primary"/>
							</form>
							<form method="GET" action="getEmployees" class="pt-1">
								<input type="submit" value="View / Edit / Delete Employee" class="btn btn-primary"/>
							</form>
							
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Sales Report</h5>
    					<div class="container">
							<form action="searchSalesReport.jsp">
								<input type="submit" value="Sales Report" class="btn btn-success"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Items</h5>
    					<div class="container">
							<form action="getItems" method="GET">
								<input type="submit" value="View Items" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Sales</h5>
    					<div class="container">
							<form method="GET" action="searchSales.jsp">
								<input type="submit" value="View Sales" class="btn btn-success"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Revenue Summary</h5>
    					<div class="container">
							<form action="searchSummaryListing.jsp">
								<input type="submit" value="View Revenue Summary" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Highest Revenue Generators</h5>
    					<div class="container">
							<form method="GET" action="getHighestRevenue">
								<input type="submit" value="Customer Representative" class="btn btn-success"/>
							</form>
							<form method="GET" action="getHighestRevenueCustomer" class="pt-1">
								<input type="submit" value="Customer" class="btn btn-success"/>
							</form>
							
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Bestseller Items</h5>
    					<div class="container">
							<form action="getBestsellers">
								<input type="submit" value="View Bestsellers" class="btn btn-primary"/>
							</form>
						</div>
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