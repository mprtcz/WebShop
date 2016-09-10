<%--
  Created by IntelliJ IDEA.
  User: Azet
  Date: 2016-08-29
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Items List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
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
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Items </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td>${item.itemName}</td>
                    <td>${item.price}</td>
                    <td>${item.stock}</td>
                    <td>${item.description}</td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/item/${item.id}/edit' />" class="btn btn-success custom-width">Edit</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/item/${item.id}/delete' />" class="btn btn-danger custom-width">Delete</a></td>
                    </sec:authorize>
                    <td><a href="<c:url value='/item/${item.id}' />" class="btn btn-success custom-width">View</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h5><p align="center">Logged as: <c:out value="${loggedinuser}"/></p></h5>
        <a href="<c:url value="/" />">Back to the main page</a>
        <sec:authorize access="hasRole('ADMIN')">
            <td><a href="<c:url value='/item/add' />" class="btn btn-success">Add another item</a></td>
        </sec:authorize>
    </div>
</body>
</html>