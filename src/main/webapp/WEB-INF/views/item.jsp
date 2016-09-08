<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Azet
  Date: 2016-09-06
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <!-- Theme Made By www.w3schools.com - No Copyright -->
    <title>${item.itemName}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            font: 20px Montserrat, sans-serif;
            line-height: 1.8;
            color: #f5f6f7;
        }
        p {font-size: 16px;}
        .margin {margin-bottom: 45px;}
        .bg-1 {
            background-color: #1abc9c; /* Green */
            color: #ffffff;
        }
        .bg-2 {
            background-color: #474e5d; /* Dark Blue */
            color: #ffffff;
        }
        .bg-3 {
            background-color: #ffffff; /* White */
            color: #555555;
        }
        .bg-4 {
            background-color: #2f2f2f; /* Black Gray */
            color: #fff;
        }
        .container-fluid {
            padding-top: 70px;
            padding-bottom: 70px;
        }
        .product .img-responsive {
            margin: 0 auto;
        }
        .navbar {
            padding-top: 15px;
            padding-bottom: 15px;
            border: 0;
            border-radius: 0;
            margin-bottom: 0;
            font-size: 12px;
            letter-spacing: 5px;
        }
        .navbar-nav  li a:hover {
            color: #1abc9c !important;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Home</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
                <li><a href="/items"><span class="glyphicon glyphicon-th-list"></span>Items List</a></li>
                <li><a href="/login?logout"><span class="glyphicon glyphicon-user"></span>Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- First Container -->
<div class="container-fluid bg-1 text-center">
    <h3 class="margin"><c:out value="${item.itemName}"/></h3>
    <img src="<c:url value="/item/${item.id}/image"/>" class="img-responsive center-block" style="width:50%" alt="Image">
</div>

<!-- Second Container -->
<div class="container-fluid bg-2 text-center">
    <h3 class="margin">Description</h3>
    <p><c:out value="${item.description}"/> </p>
</div>

<!-- Third Container (Grid) -->
<div class="container-fluid bg-3 text-center">
    <h3 class="margin">Buy this item:</h3><br>
    <a href="<c:url value="/item/${item.id}/purchase"/>" class="btn btn-default btn-lg">
        <span class="glyphicon glyphicon-shopping-cart"></span> ${item.price}
    </a>
</div>

<!-- Footer -->
<footer class="container-fluid bg-4 text-center">
    <p>Back to top</p>
</footer>
<a class="back-to-top glyphicon glyphicon-arrow-up" href="/item/${item.id}" title="Top"></a>
</body>
</html>
