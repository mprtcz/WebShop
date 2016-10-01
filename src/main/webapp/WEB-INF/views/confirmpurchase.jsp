<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Azet
  Date: 2016-09-08
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Confirm Purchase</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>



<body>
<%@include file="navBar.jsp" %>

<div class="container-fluid text-center">
    <div class="col-sm-2 sidenav">
    </div>
    <div class="col-sm-8 text-center">
        <h1>Confirm Purchase</h1>
        <p>
        <div class="row">
            <div class="col-sm-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <a href="/item/${item.id}/">
                            <c:out value="${item.itemName}"/>
                        </a>
                    </div>
                    <div class="panel-body">
                        <img src="<c:url value="/item/${item.id}/image"/>"
                             class="img-responsive" style="width:100%" alt="Image">
                    </div>
                    <div class="panel-footer">Price1: <c:out value="${item.price}"/> of <c:out value="${item.stock}"/></div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="alert alert-info">
                <strong>User Account balance:</strong> <c:out value="${user.balance}"/>
            </div>
            <form:form method="POST" modelAttribute="purchase" class="form-horizontal">
                <form:input type="hidden" path="userName" value="${user.ssoId}"/>
                <form:input type="hidden" path="itemId" value="${item.id}"/>
                <label for="quantity">Select Quantity</label>
                <form:input type="number" path="quantity" id="quantity" class="form-control input-sm"/>
                <input type="submit" value="Add to Cart">
            </form:form>
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
