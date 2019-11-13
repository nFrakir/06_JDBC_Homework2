<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.gmail.silverleaf.annn.Order"%>
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
            <td><a href="/add_client">Add new client</a></td>
            <td><a href="/add_good">Add new good</a></td>
            <td><a href="/add_order">Add new order</a></td>
        </tr>
    </table>
    <h3>All orders</h3>
    <table>
        <thead>
            <th class="outer"></th>
            <th class="outer"></th>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>Order id=<c:out value="${order.getId()}" /></td>
                <td>from: <c:out value="${order.getDateString()}" /></td>
            </tr>
            <tr>
                <td>Client name: <c:out value="${order.getClient().getLogin()}" />;</td>
                <td>phone: <c:out value="${order.getClient().getPhone()}" /></td>
            </tr>
            <tr>
                <td></td>
                <td>Total: <b><c:out value="${order.getTotal()}" /></b></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <table class="inner">
                        <thead>
                            <th class="inner">Title</th>
                            <th class="inner">Price</th>
                        </thead>
                        <tbody>
                        <c:forEach items="${order.getGoodList()}" var="good">
                            <tr>
                                <td><c:out value="${good.getTitle()}" /></td>
                                <td><c:out value="${good.getPrice()}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr class="bottom"><td colspan="2"></td></tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
