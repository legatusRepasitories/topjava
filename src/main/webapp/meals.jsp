<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<c:set var="list" value='${requestScope["mealList"]}'/>

<table style="border: black;">
    <tr style="text-align: center">
        <td>id</td>
        <td>description</td>
        <td>time</td>
        <td>calories</td>
    </tr>


<c:forEach var="m" items="${list}">
    <c:set var="id" value="${m.id}"/>
    <c:set var="description" value="${m.description}"/>
    <c:set var="dateTime" value="${m.dateTime}"/>
    <c:set var="formater" value="${m.getFormatter()}"/>
    <c:set var="calories" value="${m.calories}"/>
    <c:set var="excess" value="${m.excess}"/>


    <c:choose>
        <c:when test="${excess eq true}">
            <tr style="background-color: red">
                <td>${id}</td>
                <td>${description}</td>
                    <td>${dateTime.format(formater)}</td>
                <td>${calories}</td>
                <td style="background-color: white"><a href="meals?action=edit&mealId=<c:out value="${id}"/>">Edit</a></td>
                <td style="background-color: white"><a href="meals?action=delete&userId=<c:out value="${id}"/>">Delete</a></td>
            </tr>
        </c:when>

        <c:otherwise>
            <tr style="background-color: green">
                <td>${id}</td>
                <td>${description}</td>
                <td>${dateTime.format(formater)}</td>
                <td>${calories}</td>
                <td style="background-color: white"><a href="meals?action=edit&mealId=<c:out value="${id}"/>">Edit</a></td>
                <td style="background-color: white"><a href="meals?action=delete&mealId=<c:out value="${id}"/>">Delete</a></td>
            </tr>
        </c:otherwise>
    </c:choose>

</c:forEach>
</table>

<p><a href="meals?action=add">Add User</a></p>

</body>
</html>
