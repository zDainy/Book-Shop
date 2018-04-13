<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="common.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/product.css">
    <link rel="stylesheet" href="js/main.js">
    <title>Книги</title>
</head>
<body>
<div class="header">
    <div class="container box-flex">
        <a class="logo" href="product">Буковка</a>
        <% if (request.getSession(false).getAttribute("login") != null) {
        request.setAttribute("isAuthorized", 1);
        } %>
    </div>
</div>
<div class="container flexand">
    <div class="one">
        <div class="spoiler">
            <input type="checkbox" id="spoilerid_1">
            <label for="spoilerid_1">Профиль</label>
            <div class="spoiler_body">
                <ul>
                    <c:if test="${isAuthorized != 1}">
                    <li><a href="profile">Вход в систему</a></li>
                    </c:if>
                    <li><a href="cart">Корзина</a></li>
                </ul>
            </div>
        </div>
        <div class="spoiler">
            <input type="checkbox" id="spoilerid_2">
            <label for="spoilerid_2">Издательство</label>
            <div class="spoiler_body">
                <ul>
                    <li><a href="product?publish=1">Асгард</a></li>
                    <li><a href="product?publish=2">Эксмо</a></li>
                    <li><a href="product?publish=3">БиблиоГид</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="two">
        <div class="box-flex sort">
            <form method="post" action="product" style="margin-left: 70px;">
                <div class="prices" style="float: left;">
                    <h3>Год издания</h3>
                    <div class="price">
                        <input type="text" pattern="[0-9]*" name="minYear" class="placeholder" placeholder="От 1970" style="width: 280px;"><br>
                        <input type="text" pattern="[0-9]*" name="maxYear" class="placeholder" placeholder="До 2018" style="width: 280px;">
                    </div>
                </div>
                <div class="prices" style="float: left; margin-left: 70px;">
                    <h3>Цена</h3>
                    <div class="price">
                        <input type="text" pattern="[0-9]*" name="minPrice" class="placeholder" placeholder="От 0 руб." style="width: 280px;"><br>
                        <input type="text" pattern="[0-9]*" name="maxPrice" class="placeholder" placeholder="До 10000 руб." style="width: 280px;">
                    </div>
                </div>
                <div class="search" style="float: left; margin-top: 45px; margin-left: 70px;">
                    <button type="submit" class="box4btn">Поиск</button>
                </div>
            </form>
        </div>
        <div id="left">
            <ul>
                <c:forEach var="book" items="${books}">
                    <li>
                        <div class="img"><a href="#"><img alt="" src="${book.imgSrc}"></a></div>
                        <a class="title" href="#">
                            <c:out value="${book.name}"/>
                        </a>
                        <div class="price">
                            <p><c:out value="${book.price}"/>.00 р.</p>
                            <c:if test="${isAuthorized == 1}">
                                <form action="product" method="post" class="prodForm">
                                    <input type="hidden" name="productId" value="${book.id}">
                                    <input type="hidden" name="type" value="add">
                                    <button type="submit" class="box2btn">Добавить</button>
                                </form>
                            </c:if>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<div id="footer">
    <div id="copyrite">
        <div class="container ">
        </div>
    </div>
</div>
</body>
</html>