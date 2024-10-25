<%@include file="adminnavbar.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>
<style>
    body {
        font-family: Arial, sans-serif !important;
        background-color: #f0f0f0 !important;
    }

    .boxadmin {
        height: 250px;
        width: 250px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.4);
        border-radius: 20px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        text-decoration: none;
        color: black;
        overflow: hidden;
        background-color: white;
        position: relative;
        margin-top: 25px;
    }

    .boxadmin img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        opacity: 0.40;
        transition: opacity 0.3s ease;
    }

    .boxadmin:hover img {
        opacity: 1;
    }

    .boxadmin span {
        position: absolute;
        font-weight: bold;
        
        font-size: 1.2rem;
        text-align: center;
        z-index: 1;
        color: #000;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-4 d-flex justify-content-center">
                <a class="boxadmin" href="/finalodev/admin/users">
                    <img src="https://i0.wp.com/networknuts.net/wp-content/uploads/2019/11/zahir-accounting-software-have-more-than-60.000-users.png?resize=600%2C400&ssl=1" alt="">
                    <span>Kayıtlı kullanıcılar</span>
                </a>
            </div>
            <div class="col-md-4 d-flex justify-content-center">
                <a class="boxadmin" href="/finalodev/admin/orders">
                    <img src="https://www.mimsangrup.com.tr/images/haberler/teslimat_76a73.jpg" alt="">
                    <span>Kayıtlı Siparişler</span>
                </a>
            </div>
            <div class="col-md-4 d-flex justify-content-center">
                <a class="boxadmin" href="/finalodev/admin/products">
                    <img src="https://miro.medium.com/v2/resize:fit:1100/format:webp/1*7sZoXca4YQ-iS7gKPmqetg.png" alt="">
                    <span>Kayıtlı Ürünler</span>
                </a>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-4 d-flex justify-content-center">
                <a class="boxadmin"  href="/finalodev/admin/createproduct">
                    <img src="https://www.jetstok.com/assets/images/uploadeditor/456df0e4-d6f3-4d30-94e7-c3c0f20589f6.png" alt="">
                    <span>Ürün Oluştur</span>
                </a>
            </div>
            <div class="col-md-4 d-flex justify-content-center">
                <a class="boxadmin" href="/finalodev/admin/createCategory">
                    <img src="https://paym.es/blog/wp-content/uploads/2017/05/ecommerce-products.jpg" alt="">
                    <span>Kategori Oluştur</span>
                </a>
            </div>
            <div class="col-md-4 d-flex justify-content-center">
                <a class="boxadmin" href="/finalodev/admin/odemeYontemleri">
                    <img src="https://weepay.co/blog/wp-content/uploads/2020/07/guvenli-odeme.jpg" alt="">
                    <span>Ödeme Yöntemleri</span>
                </a>
            </div>
        </div>
    </div>
</body>
</html>