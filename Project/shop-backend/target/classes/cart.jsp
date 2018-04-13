<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="common.Book"%>
<%@page import="dao.BookDao"%>
<%@page import="common.Publishing"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/shoppingcard.css">
    <title>Корзина</title>
</head>
<body>
<div class="header">
    <div class="container box-flex">
        <a class="logo" href="product">Буковка</a>
    </div>
</div>
<div class="container box-flex">
    <h2>Детали продукта</h2>
    <div class="zag">
        <h2>Цена за шт.</h2>
        <h2>Год</h2>
        <h2>В наличии</h2>
        <h2>Издательство</h2>
        <h2>Действие</h2>
    </div>
</div>
<c:forEach var="book" items="${books}">
<div class="container zag">
    <div class="strip zag">
        <img class="product" src="${book.imgSrc}" alt="">
        <div class="info">
            <h2>
                <c:out value="${book.name}"/>
            </h2>
            <h3><span>Издательство:</span></h3><h3 id="izdat"><c:out value="${book.publishName}"/></h3>
            <h3><span>Год:</span></h3><h3 id="year"><c:out value="${book.year}"/></h3>
        </div>
    </div>
    <div class="strip box-flex wid-1 ">
        <div class="zag">
            <h3 id="price"><c:out value="${book.price}"/> р.</h3>
            <h3 id="year2"><c:out value="${book.year}"/></h3>
            <h3 id="shipping">в наличии</h3>
            <h3 id="publishing"><c:out value="${book.publishName}"/></h3>
            <a id="no" href="cart?action=remove&id=${book.id}">X</a>
        </div>
    </div>
</div>
</c:forEach>
<div class="container">
    <div class="buttons flex-1">
        <form method="post" action="cart?action=clear" >
            <button type="submit" class="box1btn">Очистить</button>
        </form>
        <form method="post" action="cart?action=continue">
            <button type="submit" class="box1btn">Продолжить</button>
        </form>
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