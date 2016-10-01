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

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


<body>
<%@include file="navBar.jsp" %>

<div class="container-fluid text-center">
        <!-- Default panel contents -->
    <c:choose>
        <c:when test="${expression!=null}">
            <div class="panel-heading"><span class="lead">Search results for ${expression}</span></div>
        </c:when>
        <c:otherwise>
            <div class="panel-heading"><span class="lead">List of Items </span></div>
        </c:otherwise>
    </c:choose>
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