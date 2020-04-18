<%@ page import="com.springapp.mvc.models2.Company2" %>
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
      <a class="navbar-brand" href="/">
        <%
          Company2 company2 =(Company2)session.getAttribute("company");
          out.print(company2.getName().toUpperCase());
        %>
      </a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-main">
      <ul class="nav navbar-nav">
        <li><a href="new_orders">Новые заказы</a></li>
        <li class="active"><a href="all_shops">Магазины</a></li>
        <li><a href="products">Товары</a></li>
        <li><a href="couriers">Курьеры</a></li>
        <li><a href="history">История и статистика заказов</a></li>
        <li><a href="debt_shops">Долги</a></li>
      </ul>
    </div>
  </div>
  <div class="col-sm-3 col-md-3 pull-left">
    <form class="navbar-form" role="search" method="get" action="get_shops_by_name">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="поиск" name="shop_name" id="srch-term">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
        </div>
      </div>
    </form>
  </div>
</nav>

</body>
</html>
