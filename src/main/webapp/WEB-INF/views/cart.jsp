<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Azet
  Date: 2016-09-10
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Add Item(s) to Cart</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


<body>
<%@include file="navBar.jsp" %>

<c:set value="danger" var="trClass"/>
<c:if test="${accountBalance > itemsValue}" var="affordable">
    <c:set value="info" var="trClass"/>
</c:if>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
        </div>
        <div class="col-sm-8 text-left">
            <h1>User's cart</h1>
            <p>
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading"><span class="lead">
                        Total items' value: ${itemsValue},
                        Current account balance: ${accountBalance}
                    </span></div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cartItems}" var="entry">
                            <tr class="${trClass}">
                                <td>${entry.key.itemName}</td>
                                <td>${entry.key.price}</td>
                                <td>${entry.value}</td>
                                <td><a href="/removefromcart/${item.id}" class="btn btn-danger" role="button">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
            <c:if test="${!affordable}">
                <p class="bg-danger">Warning! Not enough money for all items!</p>
            </c:if>
            <p class="text-center">
                <a href="/buyall" class="btn btn-info" role="button">Purchase all</a> or <a
                    href="<c:url value="/items" />">Buy More</a>
            </p>
            <h5>
                <a href="<c:url value="/" />">Back to the main page</a>
            </h5>
        </div>
    </div>
    <div class="col-sm-2 sidenav">
    </div>
</div>

<footer class="container-fluid text-center">
    <p>Kopyrajt 2080 ©</p>
</footer>

</body>
</html>
