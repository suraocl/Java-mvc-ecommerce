
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<style>
body {
	font-family: Arial, sans-serif !important;
	background-color: #f0f0f0 !important;
	height: 100vh
}

.container5 {
	background-color: white;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	display: flex;
	justify-content: center;
	padding-top: 10px;
	padding-bottom: 15px;
}

.formm {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-around;
}

.login {
	display: flex;
	justify-content: center;
	gap: 50px;
	border-bottom: 1px solid gray;
	margin-bottom: 20px;
}

.login a {
	text-decoration: none;
	color: black;
	font-size: 16px;
	font-weight: 550;
	padding: 10px 20px;
}

.login a:hover {
	color: #ff1b6b;
}

.h44 {
	color: #ff1b6b;
	font-size: 30px;
	font-weight: bold;
}

input {
	background-color: #e5edf2;
	border: none;
	padding: 10px;
	margin-bottom: 15px;
	border-radius: 5px;
	outline: none;
}

input::placeholder {
	color: #999;
	font-weight: 600;
}

.button1 {
	background-color: #ff1b6b;
	color: white;
	border: none;
	border-radius: 5px;
	width: 150px;
	height: 40px;
	padding: 10px 20px;
	font-size: 18px;
	cursor: pointer;
}

.inpdiv {
	display: flex;
	flex-direction: column;
	width: 100%;
}
</style>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div
		class="h-75 d-flex flex-column justify-content-center align-items-center">
		<p class="h44">MEGASTORE</p>
		<div class="container5 p-5">
			<form class="formm" action="register" method="post">
				<div class="login">
					<a href="login">Giriş Yap</a> <a href="register">Üye Ol</a>
				</div>
				<div class="inpdiv">

					<input type="text" id="name" name="name" placeholder="İsim"
						required /> <br /> <input type="email" id="email" name="email"
						placeholder="E-Posta Adresi" required /><br /> <input
						type="password" id="password" name="password" placeholder="Şifre"
						required /><br />
				</div>
				<button class="button1" type="submit">Kayıt Ol</button>
			</form>
		</div>

	</div>
	<%
	String message = (String) request.getAttribute("message"); 
	if (message != null) { 
	%>
	<span><%=message%></span>
	<%
	}
	%>
</body>
</html>
