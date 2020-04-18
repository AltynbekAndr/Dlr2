<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.model_for_users.DealerAdmin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet"  href="resources/css/bootstrap.min.css" />
  <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-main">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">
        <img src="resources/images/running.png" height="36" alt="">
      </a>
      <a class="navbar-brand" href="/sign_in_admin">
        <%
          DealerAdmin dealerAdmin =(DealerAdmin)session.getAttribute("admin");
          out.print(dealerAdmin.getName().toUpperCase());
        %>
      </a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-main">
      <ul class="nav navbar-nav">
        <li><a href="main_admin">Компании</a></li>
        <li class="active"><a href="admin_add_company">Добавить компанию</a></li>
        <li><a href="admin_shops">Магазины</a></li>
      </ul>
    </div>
  </div>
</nav>

</body>
</html>
