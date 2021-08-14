<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Application</title>
    <style>
        <%@include file="/view/css/style.css" %>
    </style>
</head>
<body>
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
    <c:import url="/view/navibar.jsp"/>
</security:authorize>
</body>

<div class="error">
    <h1>Opps...</h1>
    <table width="100%" border="1">
        <tr valign="top">
            <td width="40%"><b>Error:</b></td>
            <td>${pageContext.exception}</td>
        </tr>

        <tr valign="top">
            <td><b>URI:</b></td>
            <td>${pageContext.errorData.requestURI}</td>
        </tr>

        <tr valign="top">
            <td><b>Status code:</b></td>
            <td>${pageContext.errorData.statusCode}</td>
        </tr>

        <tr valign="top">
            <td><b>Stack trace:</b></td>
            <td>
                <c:forEach var="trace"
                           items="${pageContext.exception.stackTrace}">
                    <p>${trace}</p>
                </c:forEach>
            </td>
        </tr>
    </table>
</div>

<%--<div class="error">
    <pre>
    <p>
        <a>Something went wrong</a>
    <c:if test="${message!=null}">
        <a>Cause: '${message}'</a>
        &lt;%&ndash;<a><% exception.printStackTrace(response.getWriter()); %></a>&ndash;%&gt;
    </c:if>
    </p>
    </pre>
</div>--%>
</html>