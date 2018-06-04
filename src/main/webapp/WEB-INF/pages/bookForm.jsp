<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Book</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>
<body class="container-fluid">
  <c:if test="${book.id==0}">
    <form:form method="post" action="/books/" modelAttribute="book">
    <div class="form-group row">
        <label for="title" class="col-2 col-form-label">Название</label>
        <form:input path="title" class="form-control col-10" placeholder="Название книги"/>
    </div>
    <div class="form-group row">
        <label for="author" class="col-2 col-form-label">Автор</label>
        <form:input path="author" class="form-control col-10" placeholder="Автор" />
    </div>
    <div class="form-group row">
        <label for="description" class="col-2 col-form-label">Описание</label>
        <form:textarea path="description" class="form-control col-10" placeholder="Описание"/>
    </div>
    <div class="form-group row">
        <label for="isbn" class="col-2 col-form-label">ISBN</label>
        <form:input path="isbn" class="form-control col-10" placeholder="ISBN"/>
    </div>
    <div class="form-group row">
        <label for="year" class="col-2 col-form-label">Год</label>
        <form:input path="year" class="form-control col-10" placeholder="Год" />
    </div>
    <div class="form-group row">
        <input type="submit" value="Сохранить" class="btn btn-success form-control col-2"/>
    </div>
    </form:form>
  </c:if>
  <c:if test="${book.id!=0}">
    <form:form method="put" action="/books/${book.id}" modelAttribute="book">
            <form:hidden path="author" value="${book.author}" />
        <div class="form-group row">
            <label for="title" class="col-2 col-form-label">Название</label>
            <form:input path="title" class="form-control col-10" placeholder="Название книги"/>
        </div>
        <div class="form-group row">
            <label for="description" class="col-2 col-form-label">Описание</label>
            <form:textarea path="description" class="form-control col-10" placeholder="Описание"/>
        </div>
        <div class="form-group row">
            <label for="isbn" class="col-2 col-form-label">ISBN</label>
            <form:input path="isbn" class="form-control col-10" placeholder="ISBN"/>
        </div>
        <div class="form-group row">
            <label for="year" class="col-2 col-form-label">Год</label>
            <form:input path="year" class="form-control col-10" placeholder="Год" />
        </div>
        <div class="form-group row">
            <input type="submit" value="Сохранить" class="btn btn-success form-control col-2"/>
        </div>
    </form:form>
  </c:if>
</body>