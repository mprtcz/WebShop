<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {
            height: 450px
        }

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }

            .row.content {
                height: auto;
            }
        }
    </style>
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
            <div class="row">
                <div class="col-sm-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><c:out value="${item.itemName}"/></div>
                        <div class="panel-body"><img src="<c:url value="/item/${item.id}/image"/>"
                                                     class="img-responsive" style="width:20%" alt="Image"></div>
                        <div class="panel-footer">Price: <c:out value="${item.price}"/></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-8 text-left">
            <h1>Confirm Purchase</h1>
            <p>
            <div class="col-xs-2">
                <div class="alert alert-info">
                    <strong>User Account balance:</strong> <c:out value="${user.balance}"/>
                </div>
                <strong>${purchaseResult}</strong>

                <form:form method="POST" modelAttribute="purchase" class="form-horizontal">
                    <form:input type="hidden" path="userName" value="${user.ssoId}"/>
                    <form:input type="hidden" path="itemId" value="${item.id}"/>
                    <label for="quantity">Select Quantity</label>
                    <form:input type="number" path="quantity" id="quantity" class="form-control input-sm"/>
                    <input type="submit" value="Purchase">
                </form:form>
            </div>
            </p>
            <hr>
        </div>
        <div class="col-sm-2 sidenav">
        </div>
    </div>
</div>
</div>
</div>

<footer class="container-fluid text-center">
    <p>Footer Text</p>
</footer>

</body>
</html>
