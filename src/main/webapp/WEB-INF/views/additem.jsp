<%--
  Created by IntelliJ IDEA.
  User: Azet
  Date: 2016-09-01
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>

<body>
<div class="generic-container">
    <div class="well lead">Sell Item</div>
    <form:form method="POST" modelAttribute="item" class="form-horizontal">
        <form:input type="hidden" path="id" id="id" />


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
                    <form:input type="number" path="price" id="price" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="price" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="stock">Amount</label>
                <div class="col-md-7">
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="number" path="stock" id="stock" class="form-control input-sm" disabled="true"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="number" path="stock" id="stock" class="form-control input-sm" />
                            <div class="has-error">
                                <form:errors path="stock" class="help-inline"/>
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
                    <form:input type="text" path="description" id="description" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="description" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/itemslist' />">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Add Item" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/itemslist' />">Cancel</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    <h5><p align="center">Logged as: <c:out value="${loggedinuser}"/></p></h5>
</div>
</body>
</html>