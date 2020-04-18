<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/add_product2.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="header_menu/courier_header.jsp"/>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
    String login = (String)pageContext.getRequest().getAttribute("login");
    String success = (String)pageContext.getRequest().getAttribute("success");
    if(!login.equals("Такой логин существует выберите другую!")){
        out.print("<div class=modaldiv><img src=/resources/images/icons_checked.png><p>Курьер сохранен</p><a href=couriers>Список курьеров</a></div>");
    }else{
        out.print("<div class=modaldiv><img src=/resources/images/icons_unchecked.png><p>"+login+"</p><a href=add_courier >Назад</a></div>");
    }
%>


<script type="application/javascript">
</script>
</body>
</html>
