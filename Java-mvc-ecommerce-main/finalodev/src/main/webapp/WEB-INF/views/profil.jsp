<%@page import="com.onur.finalodev.model.PaymentMethod"%>
<%@page import="com.onur.finalodev.model.Order"%>
<%@page import="java.util.Map"%>
<%@page import="com.onur.finalodev.model.OrderProductListing"%>
<%@page import="com.onur.finalodev.model.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.onur.finalodev.model.User"%>
<%@include file="navbar.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<title>Insert title here</title>
</head>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

.profile-container, .order-history {
	background: #FFFFFF;
	border-radius: 15px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	padding: 30px;
	margin-top: 7 0px;
	height: 400px;
}

.profile-container {
	background: #FFFFFF;
	border-radius: 15px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	padding: 30px;
	margin-top: 50px;
}

.profile-container {
	position: relative; /* Relative position for the container */
}

.profile-header {
	display: flex;
	align-items: center;
	justify-content: center; /* Yatayda ortalama */
	height: 100%;
	text-align: left;
	margin-bottom: 30px;
}

.profile-icon {
	font-size: 100px;
	color: #FF1B6B;
	margin-right: 80px;
	margin-top: 50px;
}

.profile-info {
	font-size: 1.2rem;
	color: #000000;
}

.profile-info span {
	display: block;
	margin-bottom: 10px;
}

.order-history {
	background: #FFFFFF;
	border-radius: 15px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	padding: 30px;
	margin-top: 50px;
	height: 400px;
	overflow-y: auto; 
}

.order-history-header {
	font-size: 24px;
	font-weight: bold;
	color: #FF1B6B;
	margin-bottom: 20px;
}

.order-list {
	list-style: none;
	padding: 0;
}

.order-item {
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f9f9f9;
	border-radius: 10px;
}

.logout-link {
	position: absolute; 
	bottom: 10px; 
	right: 10px; 
	color: #FF1B6B;
	text-decoration: none;
	display: flex;
	align-items: center;
}

.logout-link i {
	margin-right: 5px; 
}

.header {
	background-color: #FFFFFF;
	color: #FF1B6B;
	padding: 10px;
	border-radius: 10px;
	margin-bottom: 20px;
	margin-top: 30px;
	font-weight: bold;
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header">KULLANICI BİLGİLERİM</div>
		<div class="row justify-content-between">
			<div class="col-md-6 order-md-2">
				<div class="order-history">
					<div class="order-history-header">Siparişlerim</div>
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
						%>
						<%
						}
						%>

						<div class=" mb-4 d-flex flex-column p-4"
							style="background-color: white; border-radius: 15px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); width: 100%;  overflow: auto">
							<div class="d-flex justify-content-between w-100">
								<div class="d-flex flex-column">
									<span><span class="fw-semibold fw-4	">İsim
											Soyisim: </span> <%=orderUser.getName()%></span> <span><span
										class="fw-semibold fw-4	">Email: </span><%=orderUser.getEmail()%></span>
									<span><span class="fw-semibold fw-4	">Adres: </span><%=order.getAddress()%></span>
									<span><span class="fw-semibold fw-4	">Ödeme
											Yöntemi: </span><%=paymentMethod.getName()%></span>
								</div>
								<div class="d-flex flex-column ">
									<span class="fw-bold fs-5 ms-3 mb-1">Sipariş No: #<%=order.getId()%></span>
									<span class=" fs-5 ms-3 mb-1"><span
										class="fw-semibold fw-4	">Toplam: </span><%=String.format("%.2f", order.getTotalPrice())%>
										TL</span>

									<%
									if (order.getStatus().equals("ONAYLANDI")) {
									%>
									<span class="fw-bold fs-6 ms-3 mb-1" style="color: green">SİPARİŞ
										ONAYLANDI</span>
									<%
									} else if (order.getStatus().equals("REDDEDILDI")) {
									%>
									<span class="fw-bold fs-6 ms-3 mb-1" style="color: red">SİPARİŞ
										REDDEDİLDİ</span>
									<%
									}
								 else if (order.getStatus().equals("BEKLIYOR")) {
									%>
									<span class="fw-bold fs-6 ms-3 mb-1" style="color: orange">ONAY
										BEKLENIYOR</span>
									<%
									}
									%>
								</div>

							</div>
							<hr />

							<%
							for (OrderProductListing orderProductListing : orderProductListings) {
							%>
							<div class="d-flex justify-content-between mb-3">
								<div class="imagecontainer">
									<img src="<%=orderProductListing.getProduct().getImageUrl()%>"
										style="width: 7rem; height: 7rem; border-radius: 5px">
								</div>
								<div class="d-flex flex-column ">
									<span class="product-name"><%=orderProductListing.getProduct().getName()%></span>
									<span class="product-description"><%=orderProductListing.getProduct().getDescription()%></span>
									<span class="product-price" style="color: #FF1B6B"><%=orderProductListing.getProduct().getPrice()%>
										x <%=orderProductListing.getQuantity()%> = <%=orderProductListing.getProduct().getPrice() * orderProductListing.getQuantity()%>
										TL</span> <span class="product-quantity"><span
										class="fw-semibold fw-4">Adet: </span><%=orderProductListing.getQuantity()%></span>
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
			</div>
			<div class="col-md-6 order-md-1" style="height: 400px">
				<div class="profile-container">
					<div class="profile-header d-flex flex-column">
						<i class="fas fa-user profile-icon"></i>
						<!-- Kişi ikonu -->
						<div class="profile-info mt-5">
							<span><strong>İsim:</strong> <%=user.getName()%></span> <span><strong>E-mail:</strong>
								<%=user.getEmail()%></span>
						</div>
					</div>
					<div class="logout-link">
						<i class="fas fa-sign-out-alt"></i> <a href="/finalodev/logout">Çıkış
							Yap</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>