

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Новый товар</title>
  </head>
  <body>
    <h1>Добавить товар</h1>
    <p>${info}</p>
    <br>
    <a href="index.jsp">Главная страница</a>
    <form action="createProduct" method="POST">
      Название товара <input type="text" name="name"><br>
      Цена товара <input type="text" name="price"><br>
      Кол-во товара <input type="text" name="quantity"><br>
      <input type="submit" name="submit" value="Добавить">
    </form>
  </body>
</html>
