<%@include file="adminnavbar.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
      body {
        font-family: Arial, sans-serif !important;
        background-color: #f0f0f0 !important;
        height: 100vh;
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
      .button1 {
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
      input {
        background-color: #e5edf2;
        border: none;
        padding: 10px;
        margin-bottom: 15px;
        border-radius: 5px;
        outline: none;
      }

      .formm {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-around;
      }
    </style>
     <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <div
      class="h-75 d-flex flex-column justify-content-center align-items-center"
    >
      <div class="container5 p-5">
        <form class="formm" action="/finalodev/admin/products" method="post">
          <div class="inpdiv">
            <input
              type="text"
              id="name"
              name="name"
              required
              placeholder="Ürün Adı"
            /><br />
            <input
              type="text"
              id="imageUrl"
              name="imageUrl"
              required
              placeholder="Fotoğraf Linki"
            /><br />
            <div class="input-group mb-3">
              <select class="form-select" id="inputGroupSelect02" name="categoryId">
                <option selected hidden>Kategori Seçiniz</option>
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
            </div>
            <input
              type="text"
              id="description"
              name="description"
              required
              placeholder="Ürün Açıklaması"
            /><br />
            <div class="input-group mb-3">
              <input type="text" name="price" class="form-control" placeholder="Fiyat" />
              <span class="input-group-text">TL</span>
            </div>
          </div>
          <button class="button1 btn btn-primary" type="submit">Oluştur</button>
        </form>
      </div>
    </div>
  </body>
</html>