<%@page import="com.onur.finalodev.model.Category"%>
<%@page import="com.onur.finalodev.model.PaymentMethod"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="navbar.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Satın Alım</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 0;
}
.container {
    max-width: 600px;
    margin-top: 50px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    padding: 30px;
}
h2 {
    color: #333;
    margin-bottom: 20px;
}
.total-price {
    font-size: 1.5rem;
    color: #ff1b6b;
    margin-bottom: 20px;
}
.form-group {
    margin-bottom: 15px;
}
textarea {
    resize: none;
    height: 100px;
}
.btn-custom {
    background-color: #ff1b6b;
    border: none;
}
.btn-custom:hover {
    background-color: #e01460;
}
.text-center{
        font-size: 40px;
        font-weight: bold;
}
</style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Satın Alım</h2>
        <div class="text-center total-price"><%= request.getAttribute("totalPrice") %> TL</div>
   
        <%
            String successMessage = (String) request.getAttribute("satinAlim");
            if (successMessage != null) {
        %>
        <div class="success-message"><%= successMessage %></div>
        <%
            }
        %>
        
        <form method="post" action="satinalpost">
            <div class="form-group">
                <label for="address">Adres</label>
                <textarea class="form-control" name="address" id="address" required></textarea>
            </div>
            <div class="form-group">
                <label for="paymentMethod">Ödeme Yöntemi</label>
                <select class="form-control" name="paymentMethod" id="paymentMethod" required>
                    <%
                        List<PaymentMethod> paymentMethods = (List<PaymentMethod>) request.getAttribute("paymentMethods");
                        for (PaymentMethod paymentMethod : paymentMethods) {
                    %>
                    <option value="<%= paymentMethod.getId() %>"><%= paymentMethod.getName() %></option>
                    <%
                        }
                    %>
                </select>
            </div>
            <button type="submit" class="btn btn-custom w-100">Satın Al</button>
        </form>
    </div>
</body>
</html>