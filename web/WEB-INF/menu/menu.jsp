<%-- 
    Document   : menu
    Created on : 15.02.2021, 15:37:46
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">Магазин</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-link <c:if test='${activeAddProduct}'>active</c:if>" aria-current="page" href="addProduct">Добавить товар</a>
        <a class="nav-link <c:if test='${activeListProduct}'>active</c:if>" href="listProducts">Список товаров</a>
        <a class="nav-link <c:if test='${activeListBuyers}'>active</c:if>" href="listBuyers">Список покупателей</a>
        <a class="nav-link" href="takeOnProductForm">Продать товар</a>
        <a class="nav-link" href="adminForm">Панель администратора</a>
        <a class="nav-link" href="loginForm">Войти</a>
        <a class="nav-link" href="logout">Выйти</a>
        <a class="nav-link" href="registrationForm">Регистрация</a>
      </div>
    </div>
  </div>
</nav>
