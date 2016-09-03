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
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>
<body>
<div class="panel panel-danger">

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
        </dl>

        <div class="btn-toolbar text-center" role="toolbar">
        <sec:authorize access="hasRole('ADMIN')">
            <a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger">Delete</a></td>
        </div>
        </sec:authorize>
        <a href="<c:url value="/" />">Back to the main page</a>
    </div>
</div>
</body>
</html>
