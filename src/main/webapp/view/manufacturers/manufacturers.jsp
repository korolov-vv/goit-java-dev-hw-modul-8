<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Application</title>
    <style>
        <%@include file="/view/css/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp" />

<table class="table table-hover" >
    <thead>
    <tr>
        <th scope="col">Name</th>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <th scope="col" colspan="2"></th>
        </security:authorize>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="manufacturer" items="${manufacturers}">
        <tr>
            <td align="center"><b>${manufacturer.manufacturerName}</b></td>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <td align="center">
                    <a href="/manufacturers/form/update?manufacturerName=${manufacturer.manufacturerName}">
                        <button class="btn btn-outline-info my-2 my-sm-0">Update</button>
                    </a>
                </td>
                <td align="center">
                    <a href="/manufacturers/delete?manufacturerName=${manufacturer.manufacturerName}">
                        <button class="btn btn-outline-danger my-2 my-sm-0">Delete</button>
                    </a>
                </td>
            </security:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>