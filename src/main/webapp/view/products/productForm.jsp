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
<div class="form">
    <form action="${pageContext.request.contextPath}/products" accept-charset="utf-8" method="post"></br>
        <div class="title">Create product</div>
        <div class="subtitle">Let's create the product!</div>
        <input type="hidden" name="productId" value=0 />
        <div class="input-container ic2">
            <input required id="productName" class="input" type="text" name="productName" placeholder=" "/>
            <div class="cut-short"></div>
            <label for="productName" class="placeholder">Product name</label>
        </div>
        <div class="input-container ic2">
            <input required id="productPrice" class="input" type="number" step="0.01" name="productPrice" placeholder=" "/>
            <div class="cut-short"></div>
            <label for="productPrice" class="placeholder">Product Price</label>
        </div>
        <div class="input-container ic2">
            <input required id="manufacturerName" class="input" type="text" name="manufacturerName" placeholder=" "/>
            <div class="cut-short"></div>
            <label for="manufacturerName" class="placeholder">Manufacturer's name</label>
        </div>
        <button type="submit" class="submit">SUBMIT</button>
    </form>
</div>
</body>
</html>