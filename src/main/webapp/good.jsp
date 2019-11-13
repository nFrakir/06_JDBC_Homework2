<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.gmail.silverleaf.annn.Good"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Very simple e-shop</title>
    <meta charset="utf-8">
    <link rel='stylesheet' href='style.css' type='text/css' media='all' />
</head>
<body>
<h3>Actions:</h3>
<table>
    <tr>
        <td><a href="/">Return to main page</a></td>
        <td><a href="/add_client">Add new client</a></td>
        <td><a href="/add_order">Add new order</a></td>
    </tr>
</table>
<form name="report" action="/add_good" method="post">
    <fieldset>
        <legend>Insert new good</legend>
        <table>
            <thead>
            <th class="outer"></th>
            <th></th>
            </thead>
            <tbody>
            <tr>
                <td><label class="personal" for="title">Title</label></td>
                <td><input type="text" name="title" id="title" value="testGood"></td>
            </tr>
            <tr>
                <td><label class="personal" for="price">Price</label></td>
                <td><input type="text" name="price" id="price" value="150.0"></td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <br><input type="submit" class="btn" value="Send">
</form>
<h3>Goods</h3>
<table class="inner">
    <thead>
    <th class="inner">Title</th>
    <th class="inner">Price</th>
    </thead>
    <tbody>
    <c:forEach items="${goods}" var="good">
        <tr>
            <td class="bottom"><c:out value="${good.getTitle()}" /></td>
            <td class="bottom"><c:out value="${good.getPrice()}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>