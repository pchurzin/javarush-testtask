<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список книг</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>
<body class="container-fluid">
<form action="/books/">
    <div class="form-group row">
        <div class="col-8">
            <input name="search" class="form-control" placeholder="поиск по названию"/>
        </div>
        <div class="col-2">
            <input type="submit" class="btn btn-success form-control" value="Найти" />
        </div>
        <div class="col-2">
            <a href="/books/add" class="btn btn-secondary form-control">Добавить книгу</a>
        </div>
    </div>
</form>
<div class="row">
<div class="col-12">
  <c:if test="${empty books}">
  Список книг пуст
  </c:if>
  <c:if test="${not empty books}">
  <table class="table table-bordered table-hover">
    <tr>
      <th>Заголовок</th><th>Автор</th><th>Описание</th><th>ISBN</th><th>Год издания</th><th>Прочитано</th><th>Изменить</th><th>Удалить</th>
    </tr>
      <c:forEach items="${books}" var="book">
        <tr>
          <td><c:out value="${book.title}" /></td>
          <td><c:out value="${book.author}" /></td>
          <td><c:out value="${book.description}" /></td>
          <td><c:out value="${book.isbn}" /></td>
          <td><c:out value="${book.year}" /></td>
          <td>
            <c:if test="${book.read}">
              <input type="button" class="btn btn-success" value="Прочитано" disabled/>
            </c:if>
            <c:if test="${not book.read}">
              <form:form method="put" action="/books/${book.id}/read">
                <input type="submit" class="btn btn-success" value="Я прочитал(-а)" />
              </form:form>
            </c:if>
          </td>
          <td>
            <a href="/books/${book.id}" class="btn btn-info">Изменить</a>
          </td>
          <td>
              <form:form method="delete" action="/books/${book.id}" modelAttribute="book">
                  <input type="submit" value="Удалить" class="btn btn-danger"/>
              </form:form>
          </td>
        </tr>
      </c:forEach>
      </table>
  </c:if>
</div><!--end col -->
</div>
<div class="row">
<div class="col-12">
<nav>
  <ul class="pagination">
    <c:if test="${page > 1}" >
      <li class="page-item"><a class="page-link" href="/books/?page=${page - 1}&search=${search.title}">Предыдущая</a></li>
      <li class="page-item"><a class="page-link" href="/books/?page=1&search=${search.title}">1</a></li>
    </c:if>
    <c:if test="${page - 1 > 1}">
      <li class="page-item"><a class="page-link" href="/books/?page=${page - 1}&search=${search.title}"><c:out value="${page - 1}" /></a></li>
    </c:if>
    <li class="page-item active"><a class="page-link" href="/books/?page=${page}&search=${search.title}"><c:out value="${page}" /></a></li>
    <c:if test="${page + 1 < totalPages}">
          <li class="page-item"><a class="page-link" href="/books/?page=${page + 1}&search=${search.title}"><c:out value="${page + 1}" /></a></li>
        </c:if>
    <c:if test="${page < totalPages}">
      <li class="page-item"><a class="page-link" href="/books/?page=${totalPages}&search=${search.title}"><c:out value="${totalPages}" /></a></li>
      <li class="page-item"><a class="page-link" href="/books/?page=${page + 1}&search=${search.title}">Следующая</a></li>
    </c:if>
  </ul>
</nav>
</div>
</div>
</body>
</html>