<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/checkout.css">
    <title>Profile</title>
</head>
<body>
<div class="header">
    <div class="container box-flex">
        <a class="logo" href="product">Буковка</a>
    </div>
</div>
<div class="container flex-1">
    <div class="shipping">
        <h2>Регистрация</h2>
        <h3>Создать нового пользователя</h3>
        <form id="registration" action="profile" method="post">
            <input type="text" class="placeholder" name="login" placeholder="Login*">
            <input type="password" name="password" class="placeholder" placeholder="Password*">
            <input type="hidden" name="type" value="registration">
            <button type="submit" class="box1btn">Зарегистрироваться</button>
        </form>
    </div>
    <div class="shipping">
        <h2>Уже зарегистрированы?</h2>
        <h3>Войдите</h3>
        <form id="login" action="profile" method="post">
            <input type="text" class="placeholder" name="login" placeholder="Login*">
            <input type="password" name="password" class="placeholder" placeholder="Password*">
            <input type="hidden" name="type" value="logIn">
            <button type="submit" class="box1btn">Войти</button>
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