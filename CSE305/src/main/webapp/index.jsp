<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--
	This is the login page
	This page contains a text field for username and another for password
	This page redirects to the Home page
-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		<title>Login | Online Auction House</title>
	</head>
	<body>
	
		<h1>Welcome to the Online Auction House</h1>
		<div class="container">
			<h2>Login</h2>
			<%
				String email = (String)session.getAttribute("email");
				String role = (String)session.getAttribute("role");
				
				//redirect to home page if already logged in
				if(email != null) {
					if(role.equals("manager")) {
						response.sendRedirect("managerHome.jsp");
					}
					else if(role.equals("customer_rep")) {
						response.sendRedirect("customerRepresentativeHome.jsp");
					}
					else {
						response.sendRedirect("home.jsp");	
					}
				}
				
				String status = request.getParameter("status");
				if(status != null) {
					if(status.equals("false")) {
						out.print("Incorrect Login credentials!");
					}
					else {
						out.print("Some error occurred! Please try again.");
					}
				}
			%>
			<form action="login" method="POST">
				<div class="form-group">
					<input class="form-control" name="username" placeholder="Username" required>
				</div>
				<div class="form-group">
	            	<input type="password" class="form-control" name="password" placeholder="Password" required>
	        	</div>
				<input type="submit" value="Login" class="btn btn-success"/>
			</form>
		</div>
		
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>