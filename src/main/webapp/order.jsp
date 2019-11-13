<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.gmail.silverleaf.annn.Order, com.gmail.silverleaf.annn.Client, com.gmail.silverleaf.annn.Good"%>
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
        <td><a href="/add_good">Add new good</a></td>
        <td><a href="/add_client">Add new client</a></td>
    </tr>
</table>
<form name="report" action="/add_order" method="post">
    <fieldset>
        <legend>Insert new order</legend>
        <table>
            <thead>
            <th class="outer"></th>
            <th></th>
            </thead>
            <tbody>
            <tr>
                <td><label class="personal" for="client">Client</label></td>
                <td>
                    <select name="client" id="client">
                        <c:forEach items="${clients}" var="client_item">
                            <option value="<c:out value="${client_item.getId()}"/>"><c:out value="${client_item.getLogin()}"/></option>
                        </c:forEach>
                    </select>
            </tr>
            <tr>
                <td><label class="personal" for="good">Good</label></td>
                <td>
                    <select multiple size="5" name="good" id="good">
                        <c:forEach items="${goods}" var="good">
                            <option value="<c:out value="${good.getId()}"/>"><c:out value="${good.getTitle()}"/></option>
                        </c:forEach>
                    </select>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <br><input type="submit" class="btn" value="Send">
</form>
</body>
</html>