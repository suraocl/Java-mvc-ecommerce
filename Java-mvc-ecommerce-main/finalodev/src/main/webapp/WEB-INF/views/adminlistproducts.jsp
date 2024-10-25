
<%@page import="com.onur.finalodev.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="adminnavbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	font-family: Arial, sans-serif !important;
	background-color: #f0f0f0 !important;
}

.card-img-top {
	height: 200px;
	object-fit: contain;
	object-position: center;
	padding: 10px;
	border-radius: 20px;
}

.card:hover {
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.4) !important;
	cursor: pointer;
}

.bthnnn {
	border: none;
	border-radius: 10px;
	/* Butonu başlangıçta gizleyin */
}
</style>
<body>

		<div >
		
				<form  action="/finalodev/admin/ara" method="post" class="search-box ml-5 mt-3  ms-3">

					<label  style="background-color: white"> 
					
						<input  style="background-color: white" type="text" placeholder="Admin Ürün Ara" name="name" class="search-input" />
						<i class="fas fa-search search-icon"></i>

					</label>
				</form>
		</div>
	<div class="d-flex justify-content-around flex-wrap a">
		<%
		List<Product> products = (List<Product>) request.getAttribute("products");
		if (products != null) {
			for (Product item : products) {
				String categoryName = "";
				int categoryIdd =0;
				for (Category category : categories) {
					if (category.getId() == (item.getCategoryId())) {
						categoryName = category.getName();
						categoryIdd = category.getId();
						break;
					}
				}
		%>
		<form action="/finalodev/admin/editProduct/<%=item.getId()%>" method="post"
			class="card m-4"
			style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); width: 16rem; border: none; border-radius: 20px">
			<img src="<%=item.getImageUrl()%>" class="card-img-top" alt="...">
			<input name="imageUrl" value="<%=item.getImageUrl()%>"
					class="mx-2 text-center"
					style="border: none; witdh: 10rem">
			<div class="card-body d-flex-column">

				<input name="name" value="<%=item.getName()%>"
					class="card-title text-center"
					style="border: none; font-weight: bold; witdh: 10rem"> <input
					name="description" value="<%=item.getDescription()%>"
					style="border: none"> <input name="price"
					value="<%=item.getPrice()%> TL" style="border: none;" class="mb-2">
				<select class="form-select mb-2" id="inputGroupSelect02"
					name="categoryId">
					<option value="<%=categoryIdd %>" selected hidden><%=categoryName%></option>
					<%
					if (categories != null) {
						for (Category category : categories) {
					%>
					<option value="<%=category.getId()%>"><%=category.getName()%></option>
					<%
					}
					}
					%>
				</select>
				<div class="d-flex justify-content-between align-items-center">
					<div>
						<button type="submit" class="btn btn-primary bthnnn py-1">Düzenle</button>
						<a href="/finalodev/admin/deleteProduct/<%=item.getId()%>" class="btn btn-primary bthnnn py-1">Sil</a>
					</div>
				</div>

			</div>
		</form>
		<%
		}
		} else {
		%>
		<p>Mevcut ürün bulunmamaktadır.a</p>
		<%
		}
		%>
	</div>
</body>
</html>