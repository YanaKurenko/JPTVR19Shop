

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список продуктов</title>
    </head>
    <body>
        <h1>Список продуктов</h1>
        <ol>
            <c:forEach var="product" items="${listProducts}">
                <li>"${product.name}". ${product.price}. ${product.quantity}</li>
            </c:forEach>
        </ol>
    </body>
</html>
