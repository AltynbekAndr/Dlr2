<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавлнение районов</title>
  <link rel="stylesheet"  href="resources/css/mycss/add_product2.css" />
  <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="header_menu/add_shops_header.jsp"/>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
    boolean groupDeleted = (Boolean) pageContext.getRequest().getAttribute("groupDeleted");
    if(groupDeleted){
        out.print("<div class=modaldiv><img src=/resources/images/icons_checked.png><p>Район удален</p><a href=add_groups>Список районов</a></div>");
    }else{
        out.print("<div class=modaldiv><img src=/resources/images/icons_checked.png><p>Район сохранен</p><a href=add_groups>Список районов</a></div>");
    }
%>


</body>
</html>
