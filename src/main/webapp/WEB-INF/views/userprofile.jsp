<%--
  Created by IntelliJ IDEA.
  User: Azet
  Date: 2016-09-02
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<div class="conatiner">

    <div class="panel-heading">User Profile</div>

    <div class="panel-body">

        <dl class="dl-horizontal">
            <dt>First Name:</dt>
            <dd>${user.firstName}</dd>
            <dt>Last Name:</dt>
            <dd>${user.lastName}</dd>
            <dt>Email:</dt>
            <dd>${user.email}.</dd>
            <dt>SSO ID:</dt>
            <dd>${user.ssoId}</dd>
            <c:if test="${user.ssoId.equals(userProfileCurrent)}">
                <dt>Balance:</dt>
                <dd>${user.balance}</dd>
            </c:if>
        </dl>

        <div class="btn-toolbar text-center" role="toolbar">
        <sec:authorize access="hasRole('ADMIN')">
            <a href="<c:url value='/user/delete/${user.ssoId}' />" class="btn btn-danger">Delete</a></td>
        </div>
        </sec:authorize>
        <c:if test="${currentUserName.equals(user.ssoId)}">
            <a href="<c:url value='/user/${user.ssoId}/edit' />" class="btn btn-danger">Edit</a></td>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${user.boughtItemsList}" var="item">
                    <tr>
                        <td>${item.itemName}</td>
                        <td>${item.price}</td>
                        <td><a href="<c:url value='/item/${item.id}' />" class="btn btn-success custom-width">View</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <a href="<c:url value="/" />">Back to the main page</a>
    </div>
</div>
</body>
</html>
