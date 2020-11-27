<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Restaurant Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: lightgreen">
			<div >
			
				<a href="<%=request.getContextPath()%>" class="navbar-brand">
					<img src="images/name-logo.png" alt="R & J Burgers" width="200px"> </a>
			</div>

			<ul class="navbar-nav" >
				<%-- <li><a style="color: black; font-weight: bold;" href="<%=request.getContextPath()%>/users?action=list"
					class="nav-link">Users</a></li>&nbsp;&nbsp;&nbsp; --%>
					
				<li><a style="color: black; font-weight: bold;" href="<%=request.getContextPath()%>/customers?action=list"
					class="nav-link">Customers</a></li>&nbsp;&nbsp;&nbsp;
				<li><a style="color: black; font-weight: bold;" href="<%=request.getContextPath()%>/employees?action=list"
					class="nav-link">Employees</a></li>&nbsp;&nbsp;&nbsp;
				<li><a style="color: black; font-weight: bold;" href="<%=request.getContextPath()%>/menus?action=list"
					class="nav-link">Menu</a></li>&nbsp;&nbsp;&nbsp;
				<li><a style="color: black; font-weight: bold;" href="<%=request.getContextPath()%>/deliveryAreas?action=list"
					class="nav-link">DeliveryArea</a></li>&nbsp;&nbsp;&nbsp;
				<li><a style="color: black; font-weight: bold;" href="<%=request.getContextPath()%>/deliveryPersons?action=list"
					class="nav-link">DeliveryPerson</a></li>&nbsp;&nbsp;&nbsp;
				<li><a style="color: black; font-weight: bold;" href="<%=request.getContextPath()%>/payments?action=list"
					class="nav-link">Payment</a></li>&nbsp;&nbsp;&nbsp;
				<li><a style="color: black; font-weight: bold;" href="<%=request.getContextPath()%>/orders?action=list"
					class="nav-link">Order</a></li>&nbsp;&nbsp;&nbsp;
			</ul>
		</nav>
	</header>
	<br>
</body>
</html>
