<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<div class="jumbotron">
    <div class="container text-center">
        <h1>Epic Shop</h1>
        <p>Buy items and feel glorious</p>
    </div>
</div>

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
                    <li><a href="/login"><span class="glyphicon glyphicon-user"></span> Guest</a></li>
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

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[0].getId()}/">
                        <c:out value="${itemslist[0].getItemName()}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[0].getId()}/image"/>"
                                             class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[0].getDescription()}"/></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[1].getId()}/">
                        <c:out value="${itemslist[1].getItemName()}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[1].getId()}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[1].getDescription()}"/></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[2].getId()}/">
                        <c:out value="${itemslist[2].getItemName()}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[2].getId()}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[2].getDescription()}"/></div>
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
                    <a href="/item/${itemslist[3].getId()}/">
                        <c:out value="${itemslist[3].getItemName()}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[3].getId()}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[3].getDescription()}"/></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[4].getId()}/">
                        <c:out value="${itemslist[4].getItemName()}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[4].getId()}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[4].getDescription()}"/></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a href="/item/${itemslist[5].getId()}/">
                        <c:out value="${itemslist[5].getItemName()}"/>
                    </a>
                </div>
                <div class="panel-body">
                    <img src="<c:url value="/item/${itemslist[5].getId()}/image"/>"
                         class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="panel-footer"><c:out value="${itemslist[5].getDescription()}"/></div>
            </div>
        </div>
    </div>
</div>
<br><br>

<footer class="container-fluid text-center">
    <p>Search for an item all by yourself</p>
    <form class="form-inline">Just type the name:
        <input type="text" class="form-control" size="50" placeholder="Search...">
        <button type="button" class="btn btn-danger">Search</button>
    </form>
</footer>

</body>
</html>

