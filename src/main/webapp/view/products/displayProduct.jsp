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
<c:import url="/view/navibar.jsp"/>

<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Product name</th>
        <th scope="col">Price</th>
        <th scope="col">Manufacturer</th>
            <th scope="col" colspan="2"></th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td align="center"><b>${product.productName}</b></td>
            <td align="center"><b>${product.productPrice}</b></td>
            <td align="center"><b>${product.manufacturer.manufacturerName}</b></td>
                <td align="center">
                    <a href="/products/form/update?name=${product.productName}">
                        <button class="btn btn-outline-info my-2 my-sm-0">Update</button>
                    </a>
                </td>
                <td align="center">
                    <a href="/products/delete?name=${product.productName}">
                        <button class="btn btn-outline-danger my-2 my-sm-0">Delete</button>
                    </a>
                </td>
        </tr>
    </tbody>
</table>

</body>
</html>