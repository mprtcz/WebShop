<%--
  Created by IntelliJ IDEA.
  User: Azet
  Date: 2016-09-01
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        <li><span class="glyphicon glyphicon-user"></span>
                            <c:out value=" ${userProfileCurrent.balance} "/> </a></li>
                        <li><a href="/user/cart"><span class="glyphicon glyphicon-shopping-cart"></span>
                            Cart </a></li>
                        <li><a href="/logout"><span class="glyphicon glyphicon-off"></span>
                            Logout> </a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<div class="well lead">Add Items to sell</div>
<div class="col-sm-2 sidenav">
</div>
<div class="col-sm-8 text-left">
    <form:form method="POST" modelAttribute="item" class="form-horizontal" enctype="multipart/form-data">
        <form:input type="hidden" path="id" id="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="itemName">Item Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="itemName" id="itemName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="itemName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="price">Price</label>
                <div class="col-md-7">
                    <form:input type="number" path="price" id="price" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="price" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="item">Amount</label>
                <div class="col-md-7">
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="number" path="item" id="item" class="form-control input-sm"
                                        disabled="true"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="number" path="item" id="item" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="item" class="help-inline"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="description">Description</label>
                <div class="col-md-7">
                    <form:input type="text" path="description" id="description" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="description" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Upload a picture (Optional)</label>
                <div class="col-md-7">
                    <input type="file" name="file" class="form-control input-sm"/>
                    <c:if test="${isfileerror}">
                        ${errorMsg}
                    </c:if>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Link to a picture (Optional)</label>
                <div class="col-md-7">
                    <input type="text" name="imageAddress" class="form-control input-sm"/>
                    <c:if test="${islinkerror}">
                        ${errorLinkMsg}
                    </c:if>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <p class="text-center">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value='/items' />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Add Item" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value='/items' />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
        </div>
    </form:form>

    <p class="text-center">
        <a href="<c:url value="/" />">Back to the main page</a>
    </p>
</div>
</body>
</html>