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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<sec:authentication property="principal" var="userProfileCurrent"/>
<c:set value="anonymousUser" var="anonymousUser"/>
<c:set value="Guest" var="guest"/>
<c:choose>
    <c:when test="${!userProfileCurrent.equals(anonymousUser)}">
        <sec:authentication property="principal.username" var="userProfileCurrent"/>
    </c:when>
    <c:otherwise>
        <c:set var="userProfileCurrent" value="Guest"/>
    </c:otherwise>
</c:choose>

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
                <c:choose>
                    <c:when test="${userProfileCurrent.equals(guest)}">
                        <li><a href="/login"><span class="glyphicon glyphicon-question-sign"></span> Guest</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/user"><span class="glyphicon glyphicon-user"></span>
                            <c:out value=" ${userProfileCurrent} "/> </a></li>
                        <li><a href="/user/cart"><span class="glyphicon glyphicon-shopping-cart"></span>
                            Cart </a></li>
                        <li><a href="/logout"><span class="glyphicon glyphicon-off"></span>
                            Logout </a></li>
                    </c:otherwise>
                </c:choose>
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
        <a href="<c:url value="/" />">Back to the main page</a>
        <sec:authorize access="hasRole('ADMIN')">
            <td><a href="<c:url value='/item/add' />" class="btn btn-success">Add another item</a></td>
        </sec:authorize>
    </div>
</body>
</html>