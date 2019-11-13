<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.gmail.silverleaf.annn.Client"%>
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
            <td><a href="/add_order">Add new order</a></td>
        </tr>
    </table>
    <form name="report" action="/add_client" method="post">
        <fieldset>
            <legend>Insert new client</legend>
            <table>
                <thead>
                <th class="outer"></th>
                <th></th>
                </thead>
                <tbody>
                <tr>
                    <td><label class="personal" for="login">Login</label></td>
                    <td><input type="text" name="login" id="login" value="testClient"></td>
                </tr>
                <tr>
                    <td><label class="personal" for="phone">Phone</label></td>
                    <td><input type="text" name="phone" id="phone" value="2128506"></td>
                </tr>
                </tbody>
            </table>
        </fieldset>
        <br><input type="submit" class="btn" value="Send">
    </form>
    <h3>Clients</h3>
    <table class="inner">
        <thead>
            <th class="inner">Login</th>
            <th class="inner">Phone</th>
        </thead>
        <tbody>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td class="bottom"><c:out value="${client.getLogin()}" /></td>
                    <td class="bottom"><c:out value="${client.getPhone()}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
