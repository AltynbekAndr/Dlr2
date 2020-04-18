<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.models2.Categories2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Удаление</title>
  <link rel="stylesheet"  href="resources/css/mycss/add_product2.css" />
  <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="header_menu/courier_header.jsp"/>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
    String courierOrProd = (String) pageContext.getRequest().getAttribute("courierOrProd");
    if(courierOrProd.equals("courier")){
        out.print("<div class=modaldiv><img src=/resources/images/icons_checked.png><p>Курьер удален</p><a href=couriers>Список курьеров</a></div>");
    }else if(courierOrProd.equals("courierError")){
        out.print("<div class=modaldiv><img src=/resources/images/icons_unchecked.png><p>Курьер не может быть удален пока у него есть заказы.</p><a href=couriers>Список курьеров</a></div>");
    }else{
        out.print("<div class=modaldiv><img src=/resources/images/icons_checked.png><p>Продукт удален</p><a href=products>Список продуктов</a></div>");
    }
%>


</body>
</html>
