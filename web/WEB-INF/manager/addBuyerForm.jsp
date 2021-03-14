

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Новый покупатель</title>
  </head>
  <body>
    <h1>Добавить покупателя</h1>
    <p>${info}</p>
    <br>
    <a href="index.jsp">Главная страница</a>
    <form action="createBuyer" method="POST">
      Имя покупателя <input type="text" name="firstname"><br>
      Фамилия покупателя <input type="text" name="lastname"><br>
      Сумма покупателя <input type="text" name="purse"><br>
      <input type="submit" name="submit" value="Добавить">
    </form>
  </body>
</html>
