<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Welcome</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<center>

    <div class="container-fluid">
        <h3>Welcome to this page. What is your quest?</h3>

        <div class="btn-group-vertical">
            <c:if test="${isanonymus}">
                <a href="/login" type="submit" class="btn btn-primary" value="Log in">Log in</a></p>
                <a href="/register" type="submit" class="btn btn-primary" value="Register">Register</a></p>
            </c:if>
            <a href="/items" type="submit" class="btn btn-primary" value="Items List">Go to the items list</a></p>
            <sec:authorize access="hasRole('ADMIN') or hasRole('CUSTOMER')">
                <a href="/users" type="submit" class="btn btn-primary" value="Users List">Go to the list of
                    users</a></p>
            </sec:authorize>
        </div>
    </div>
</center>
</body>
</html>


