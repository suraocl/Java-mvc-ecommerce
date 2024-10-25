<%@page import="com.onur.finalodev.model.PaymentMethod"%>
<%@page import="java.util.Map"%>
<%@page import="com.onur.finalodev.model.OrderProduct"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.onur.finalodev.model.Order"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.onur.finalodev.model.OrderProductListing"%>
<%@include file="adminnavbar.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />

<style type="text/css">
body {
	font-family: Arial, sans-serif !important;
	background-color: #f0f0f0 !important;
}
.imagecontainer {
    flex: 0 0 auto;
}

.d-flex.flex-column {
    flex: 1;
    margin-left: 1rem;
}

.product-name,
.product-description,
.product-price,
.product-quantity {
 margin-bottom: 0.5rem; 
}

.product-price {
    color: #FF1B6B;
}
</style>
</head>
<body>

		<div >
		
				<form  action="/finalodev/admin/ara/siparis" method="post" class="search-box ml-5 mt-3  ms-3">

					<label  style="background-color: white"> 
					
						<input  style="background-color: white" type="text" placeholder="Admin Sipariş Ara (Email ile)" name="email" class="search-input" />
						<i class="fas fa-search search-icon"></i>

					</label>
				</form>
		</div>
	<div class="container mt-4">
		<div class="row">
		<%
		Map<Order, Object[]> orders = (Map<Order, Object[]>) request.getAttribute("orders");

		if (orders != null) {
			int count = 0;
			for (Map.Entry<Order, Object[]> entry : orders.entrySet()) {
				Order order = entry.getKey();
				Object[] orderDetails = entry.getValue();
				User orderUser = (User) orderDetails[1];
				List<OrderProductListing> orderProductListings = (List<OrderProductListing>) orderDetails[0];
				PaymentMethod paymentMethod = (PaymentMethod) orderDetails[2];
				
				if (count % 2 == 0) {
					%><div class="w-100 d-none d-md-block"></div><%
				}
		%>
		
		<div class="col-md-6 ms-5 mb-4 d-flex flex-column p-4" 
		style="background-color: white; border-radius: 15px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); width:50%; height:30rem; overflow:auto">
			<div class="d-flex justify-content-between w-100">
				<div class="d-flex flex-column">
					<span><span class="fw-semibold fw-4	">İsim Soyisim: </span> <%= orderUser.getName() %></span>
					<span><span class="fw-semibold fw-4	">Email: </span><%= orderUser.getEmail() %></span>
					<span><span class="fw-semibold fw-4	">Adres: </span><%= order.getAddress() %></span>
					<span><span class="fw-semibold fw-4	">Ödeme Yöntemi: </span><%= paymentMethod.getName() %></span>
					<span><span class="fw-semibold fw-4	">Sipariş Zamanı: </span><%= order.getCreatedAt().toString() %></span>
				</div>
				<div class="d-flex flex-column ">
					<span class="fw-bold fs-5 ms-3 mb-1">Sipariş No: #<%= order.getId() %></span>
					<span class=" fs-5 ms-3 mb-1"><span class="fw-semibold fw-4	">Toplam: </span><%= String.format("%.2f", order.getTotalPrice()) %> TL</span>

					<%
					if(order.getStatus().equals("ONAYLANDI")){
					%>
					<span class="fw-bold fs-6 ms-3 mb-1"  style="color:green">SİPARİŞ ONAYLANDI</span>
					<%
					} else if(order.getStatus().equals("REDDEDILDI")){
					%>
					<span class="fw-bold fs-6 ms-3 mb-1" style="color:red">SİPARİŞ REDDEDİLDİ</span>
					<%
					}
					%>
					<div <%= !order.getStatus().equals("BEKLIYOR") ? "class='d-none'" : "" %>>
						<a href="/finalodev/admin/approveOrder/<%= order.getId() %>" type="button" class="btn btn-success px-4 py-1" style="border-radius:20px">Onayla</a>
						<a href="/finalodev/admin/denyOrder/<%= order.getId() %>" type="button" class="btn btn-danger px-4 py-1" style="border-radius:20px">Reddet</a>
					</div>
				</div>
				
			</div>
				<hr />
				
				<%
				for(OrderProductListing orderProductListing: orderProductListings){
					%>
				<div class="d-flex justify-content-between mb-3">
    <div class="imagecontainer">
        <img src="<%= orderProductListing.getProduct().getImageUrl() %>" style="width:7rem; height:7rem; border-radius:5px">
    </div>
    <div class="d-flex flex-column ">
        <span class="product-name"><%= orderProductListing.getProduct().getName() %></span>
        <span class="product-description"><%= orderProductListing.getProduct().getDescription() %></span>
        <span class="product-price" style="color:#FF1B6B"><%= orderProductListing.getProduct().getPrice() %> x <%= orderProductListing.getQuantity() %> = <%= orderProductListing.getProduct().getPrice() *  orderProductListing.getQuantity() %> TL</span>
        <span class="product-quantity"><span class="fw-semibold fw-4">Adet: </span><%= orderProductListing.getQuantity() %></span>
    </div>
    <hr />
</div>

					<%
				}
			%>
				<hr>
		</div>
	
		<%
			count++;
			}
		}
		%>
		</div>
	</div>
</body>
</html>
