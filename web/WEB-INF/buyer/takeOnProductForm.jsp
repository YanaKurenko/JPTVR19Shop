

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Продажа товара</title>
    
    </head>
    <body>
        <h1>Продать товар</h1>
        <p>${info}</p>
        <form action="takeOnProduct" method="POST">
            Список продуктов:<br>
            <select name="productId">
                <option value="">Выберите товар</option>
                <c:forEach var="product" items="${listProducts}">
                    <option value="${product.id}">"${product.name}". ${product.price}. ${product.quantity}</option>
                </c:forEach>
            </select>
            Список покупателей:<br>
            <select name="buyerId">
                <option value="">Выберите покупателя</option>
                <c:forEach var="buyer" items="${listBuyers}">
                    <option value="${buyer.id}">"${buyer.firstname}". ${buyer.lastname}. ${buyer.purse} </option>
                </c:forEach>
            </select>
            <br>
            Количество:
            <input type="text" name="count">
            <br>
            <input type="submit" value="Продать товар">
        </form>
    </body>
</html>
