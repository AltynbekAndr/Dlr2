<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить фирму</title>
  <link rel="stylesheet"  href="resources/css/mycss/add_product2.css" />
  <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
</head>
<body class="subpage">
<jsp:include page="header_menu/admin_header_add_company.jsp"/>
<%
  boolean removeBool = (Boolean)pageContext.getRequest().getAttribute("remove");
  String login = (String)pageContext.getRequest().getAttribute("login");
  if(!login.equals("Такой логин существует выберите другую!")){
    if(removeBool){
      out.print("<div class=modaldiv><img src=/resources/images/icons_checked.png><p>Компания успешно удалено</p><a href=/main_admin>Список компаний</a></div>");
    }else{
      out.print("<div class=modaldiv><img src=/resources/images/icons_checked.png><p>Компания сохранена</p><a href=/main_admin>Список компаний</a></div>");
    }
  }else{
    out.print("<div class=modaldiv><img src=/resources/images/icons_unchecked.png><p>"+login+"</p><a href=/admin_add_company >Назад</a></div>");
  }

%>


<script type="application/javascript" src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
