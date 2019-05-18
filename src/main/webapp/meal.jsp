<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <title>meal</title>
</head>
<body>
<c:set var="meal" value='${requestScope["meal"]}'/>
<c:set var="formatter" value="${meal.getFormatter()}"/>

<form method="POST" action='meals' name="frmAddMeal">
    User ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${meal.id}" />"/> <br/>
    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />"/> <br/>
    calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />"/> <br/>
    Date : <input
        type="text" name="dateTime"
        value="${meal.dateTime.format(formatter)}"/> <br/><input
        type="submit" value="Submit" />
</form>
</body>
</html>
