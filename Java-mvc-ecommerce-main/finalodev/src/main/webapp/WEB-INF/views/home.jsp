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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}
.card-img-top {
	height: 200px;
	object-fit: contain;
	object-position: center;
	border-radius:20px;
}

.bthnnn {
	background-color: #ff1b6b !important;
	border: none;
	display: none;
	border-radius: 10px;
	 /* Butonu başlangıçta gizleyin */
}

/* Hover efekti için yeni CSS */
.price-container {
	position: relative;
	cursor: pointer;
}

.card:hover .price-text {
	display: none;
}
.card:hover{
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.4) !important;
  cursor: pointer;
  
  }

.card:hover .bthnnn {
	display: block; 
	color: white;
	background: none;
}
</style>
<body>
    <div class="d-flex justify-content-around flex-wrap">
        <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null) {
            for (Product item : products) {
        %>
        <div class="card m-4 d-flex justify-content-between"
            style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);width: 14rem; border:none; border-radius:20px"
            onclick="location.href='/finalodev/urun/<%= item.getId() %>'">
            <img src="<%= item.getImageUrl() %>" class="card-img-top p-3" alt="...">
            <div class="card-body d-flex flex-column align-items-center text-center">
                <h5 class="card-title fs-3"><%= item.getName() %></h5>
                <div class="d-flex align-items-center price-container">
                    <p class="card-text p-0 m-1 price-text fs-6"><%= item.getPrice() %> TL</p>
                    <form action="/finalodev/addToCart/<%= item.getId() %>" method="post">
                        <button type="submit" class="bthnnn py-1 px-3">Sepete Ekle</button>
                    </form>
                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
            <p>Mevcut ürün bulunmamaktadır.</p>
        <%
        }
        %>
    </div>
</body>
</html>
