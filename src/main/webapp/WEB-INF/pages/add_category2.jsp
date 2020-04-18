<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление категорий</title>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/add_product2.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="header_menu/products_header.jsp"/>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
    boolean categoryDeleted = (Boolean)pageContext.getRequest().getAttribute("categoryDeleted");
    if(categoryDeleted){
        out.print("<div class=modaldiv><img src=/resources/images/icons_checked.png><p>Категория удалена</p><a href=add_category>Список категорий</a></div>");
    }else{
        out.print("<div class=modaldiv><img src=/resources/images/icons_checked.png><p>Категория сохранена</p><a href=add_category>Список категорий</a></div>");
    }
%>


</body>
</html>
