<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.onur.finalodev.model.Product"%>
<%@include file="navbar.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Detail</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
}
.card {
    width: 50%;
    margin: auto;
    margin-top: 50px;
    padding: 20px;
    border-radius: 20px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}
.card-img-top {
    height: 300px;
    object-fit: contain;
    object-position: center;
}
.bthnnn {
	background-color: #ff1b6b !important;
	border: none;
	border-radius: 10px;
	color:white;
}
</style>
</head>
<body>
    <%
    Product product = (Product) request.getAttribute("product");
    if (product != null) {
    %>
    <div class="card">
        <img src="<%= product.getImageUrl() %>" class="card-img-top" alt="<%= product.getName() %>">
        <div class="card-body text-center">
            <h5 class="card-title fw-semibold"><%= product.getName() %></h5>
            <p class="card-text"><%= product.getDescription() %></p>
            <p class="card-text"><strong>Fiyat: </strong><%= product.getPrice() %> TL</p>
            <form action="/finalodev/addToCart/<%= product.getId() %>" method="post">
                <button type="submit" class="bthnnn py-1 px-4">Sepete Ekle</button>
            </form>
        </div>
    </div>
    <%
    } else {
    %>
        <p>Ürün bilgisi bulunamadı.</p>
    <%
    }
    %>
</body>
</html>
