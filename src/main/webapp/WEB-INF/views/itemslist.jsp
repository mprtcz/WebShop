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
    <title>Users List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>

<body>
<div class="generic-container">
    <div class="panel panel-default">
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
                        <td><a href="<c:url value='/item/${item.id}/edit' />" class="btn btn-success custom-width">edit</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/item/${item.id}/delete' />" class="btn btn-danger custom-width">delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h5><p align="center">Logged as: <c:out value="${loggedinuser}"/></p></h5>
        <a href="<c:url value="/" />">Back to the main page</a></span>
        <sec:authorize access="hasRole('ADMIN')">
            <td><a href="<c:url value='/item/add' />" class="btn btn-success custom-width">Add another item</a></td>
        </sec:authorize>
    </div>
</div>
</body>
</html>