<%--
  Created by IntelliJ IDEA.
  User: Azet
  Date: 2016-09-01
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Confirmation Page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>
<body>
<div class="generic-container">
    <div class="alert alert-success lead">
        ${success}
    </div>

    <span class="well floatRight">
			Go to <a href="<c:url value='/itemslist' />">Items List</a>
		</span>
</div>
</body>
</html>
