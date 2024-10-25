<!DOCTYPE html>
<%@page import="com.onur.finalodev.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.onur.finalodev.model.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>home</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
.navbar1 {
	display: flex;
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	flex-direction: column;
	margin-left: 20px;
	margin-right: 20px;
}

.container1 {
	margin-top: 20px; /* Containerlar arasına boşluk ekle */
}

.container1 {
	display: flex;
	/* Container-1 içindeki öğeleri yan yana sıralamak için */
	justify-content: space-between;
	align-items: center; /* Yatayda hizalamak için */
	background-color: white;
}

.container-2 {
	display: flex;
	/* Container-1 içindeki öğeleri yan yana sıralamak için */
	justify-content: space-between;
	align-items: center; /* Yatayda hizalamak için */
	background-color: #ff1b6b;
	height: 40px;
	border-radius: 5px;
	padding-inline: 30px;
}

.container-2 a {
	color: white;
	text-decoration: none;
	font-weight: 700;
	padding: 0 10px; /* Sağ ve sol boşluk */
}

.title {
	color: #ff1b6b;
	font-size: 26px;
	font-weight: 800;
	padding: 0;
	margin: 0;
	text-decoration: none;
	/* Başlık ile diğer öğeler arasına boşluk ekleyelim */
}

.search-box {
	position: relative;
	display: inline-block;
}

.search-input {
	width: 300px;
	height: 30px;
	background-color: #f0f0f0;
	border: none;
	border-radius: 15px;
	outline: none;
	padding: 10px;
}

.search-icon {
	position: absolute;
	right: 5px;
	top: 50%;
	transform: translateY(-50%);
}

.button { /* Butonlar arasına boşluk ekleyelim */
	color: white;
	background-color: #ff1b6b;
	border: none;
	padding: 10px 30px;
	border-radius: 5px;
	width: 150px;
	height: 30px;
	text-decoration: none;
	font-weight: 550;
}

.aaaa:hover {
	background-color: #bf114e;
}

.aaaa:active {
	background-color: #9d1041;
}

.aaaa {
	border-radius: 15px;
}

.navvbarr {
	background-color: #f0f0f0 !important;
	position: sticky;
	z-index: 1000;
	top: 0;
	position: -webkit-sticky; /* Safari */
	position: sticky;
}
</style>
</head>
<body>
	<nav class="navvbarr">
		<div class="d-flex flex-column">
			<div
				class="d-flex justify-content-around my-3 bg-white p-2 align-items-center mx-3">
				<a href="/finalodev/" class="title">MEGASTORE</a>

				<form action="/finalodev/ara" method="post" class="search-box ml-5">

					<label> <input type="text" name="name" class="search-input" />
						<i class="fas fa-search search-icon"></i>

					</label>
				</form>
				<div>
					<%
					User user = (User) session.getAttribute("user");
					if (user != null) {
					%>
					<a href="/finalodev/sepet" class="button"><i class="fas fa-solid fa-shopping-cart"></i> Sepet</a>
					<a href="/finalodev/profil" class="button mx-2"><i class="fas fa-solid fa-user"></i> Hesabım</a>

					<%
					if (user.getRole().equals("ADMIN")) {
					%>

					<a href="/finalodev/admin" class="button mx-2"><i class="fa-solid fa-user-gear"></i> Yönetici</a>
					<%
					}

					} else {
					%>
					<a href="/finalodev/login" class="button mx-2">Giriş Yap/Üye Ol</a>
					<%
					}
					%>
				</div>
			</div>


			<div class="container-2 mx-3">
				<%
				List<Category> categories = (List<Category>) request.getAttribute("categories");
				if (categories != null) {
					for (Category category : categories) {
				%>
				<a class="aaaa w-100 d-flex justify-content-center py-2"
					href="/finalodev/category?name=<%=category.getName()%>"><%=category.getName()%></a>
				<%
				}
				}
				%>
			</div>
		</div>
	</nav>
</body>
</html>