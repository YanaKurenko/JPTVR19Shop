

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список покупателей</title>
    </head>
    <body>
        <h1>Список покупателей</h1>
        <ol>
            <c:forEach var="buyer" items="${listBuyers}">
                <li>"${buyer.firstname}". ${buyer.lastname}. ${buyer.purse}</li>
            </c:forEach>
        </ol>
    </body>
</html>
