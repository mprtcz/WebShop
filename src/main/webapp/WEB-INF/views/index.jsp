<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Azet
  Date: 2016-09-03
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Epic Shop</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        /* Remove the navbar's default rounded borders and increase the bottom margin */
        .navbar {
            margin-bottom: 50px;
            border-radius: 0;
        }

        /* Remove the jumbotron's default bottom margin */
        .jumbotron {
            margin-bottom: 0;
        }

        /* Add a gray background color and some padding to the footer */
        footer {
            background-color: #f2f2f2;
            padding: 25px;
        }
    </style>
</head>

<div class="jumbotron">
    <div class="container text-center">
        <h1>Epic Shop</h1>
        <p>Buy items and feel glorious</p>
    </div>
</div>

<body>
<%@include file="navBar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[0].id}/">
                        <c:out value="${itemslist[0].itemName}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[0].id}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[0].description}"/></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[1].id}/">
                        <c:out value="${itemslist[1].itemName}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[1].id}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[1].description}"/></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[2].id}/">
                        <c:out value="${itemslist[2].itemName}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[2].id}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[2].description}"/></div>
            </div>
        </div>
    </div>
</div>
<br>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[3].id}/">
                        <c:out value="${itemslist[3].itemName}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[3].id}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[3].description}"/></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[4].id}/">
                        <c:out value="${itemslist[4].itemName}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[4].id}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[4].description}"/></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[5].id}/">
                        <c:out value="${itemslist[5].itemName}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[5].id}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[5].description}"/></div>
            </div>
        </div>
    </div>
</div>
<br><br>

<footer class="container-fluid text-center">
    <p>Search for an item all by yourself</p>
    <form name="search" action="/search" method="get" class="form-inline">Just type here:
        <input type="text" class="form-control" size="50" name="expression" placeholder="Search...">
        <div class="checkbox">
            <label><input type="checkbox" name="searchdescriptions"> Search descriptions</label>
        </div>
        <input type="submit" class="btn btn-danger" value="Search">

</form>
</footer>

</body>
</html>

