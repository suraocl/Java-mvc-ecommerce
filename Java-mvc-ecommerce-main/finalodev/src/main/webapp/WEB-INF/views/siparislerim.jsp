<%@page import="com.onur.finalodev.model.OrderProductListing"%>
<%@page import="com.onur.finalodev.model.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.onur.finalodev.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="navbar.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Home</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<style>
body{

	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}
.card-img-top {
	height: 200px;
	object-fit: cover;
	object-position: center;
}

.bthnnn {
	background-color: #ff1b6b !important;
}
</style>
<body>
	<h2>Product List</h2>
	<div class="d-flex justify-content-around flex-wrap">
		<%
		List<OrderProductListing> orders = (List<OrderProductListing>) request.getAttribute("orderProductListings");
		if (orders != null) {
			for (OrderProductListing item : orders) {
		%>
		<div><%= item.getProduct().getName() %></div>
		<div><%= item.getOrder().getAddress() %></div>
		<%
			}
		} else {
		%>
			<p>No products available.</p>
		<%
		}
		%>
	</div>
</body>
</html>
