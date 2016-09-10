<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Epic Shop</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
                <li><a href="/items">Products</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
        </div>
        <div class="col-sm-8 text-left">
            <h1>User's cart</h1>
            <p>
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading"><span class="lead">List of Items </span></div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cartItems}" var="item">
                            <tr>
                                <td>${item.itemName}</td>
                                <td>${item.price}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>Total items' value:</td>
                            <td><c:out value="${itemsValue}"/></td>
                        </tr>
                        </tbody>
                    </table>
            <c:if test="${accountBalance < itemsValue}"><p>Warning! Not enough money for all items!</p></c:if>
            <p>Current account balance: ${accountBalance}</p>
            <h5>
                <p align="center">Logged as: <c:out value="${loggedinuser}"/></p>
                <a href="<c:url value="/" />">Back to the main page</a>
            </h5>
        </div>
    </div>
    <div class="col-sm-2 sidenav">
    </div>
</div>

<footer class="container-fluid text-center">
    <p>Footer Text</p>
</footer>

</body>
</html>
