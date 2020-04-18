<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.model_for_users.Shops" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Заказы на карте</title>
  <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/shop_info.css" />
</head>
<body>
<jsp:include page="header_menu/add_shops_header.jsp"/>

<div class="mydivcourier">
  <div class="mydivcourier1">
    <p><span class="icon fa-check-circle">Информация о магазине</span></p>
  </div>
</div>
<div class="container">
  <div class="row">

    <%
      ServletRequest ctx =  pageContext.getRequest();
      Company2 company2 = (Company2) session.getAttribute("company");
      Shops shop = (Shops) ctx.getAttribute("shop");


    %>
    <TABLE class="table table-striped">
      <TR>
        <TH><%=shop.getName()%></TH>
      </TR>
      <%
        out.print("<TR>");
        out.print("<TD><img src=photo_shops?imgName="+shop.getPhoto()+" /></TD>");
        out.print("</TR>");
        out.print("<TR>");
        out.print("<TD class=latlng>"+shop.getLatitude()+"  -   "+shop.getLongitude()+"</TD>");
        out.print("</TR>");
        out.print("<TR>");
        out.print("<TD class=phone>"+shop.getPhone()+"</TD>");
        out.print("</TR>");
        out.print("<TR>");
        out.print("<TD style='display:none'>"+shop.getGroupsus().getId()+"</TD>");
        out.print("<TD class=raion>"+shop.getGroupsus().getName()+"</TD>");
        out.print("</TR>");
      %>

    </TABLE>
  </div>
</div>

</body>
</html>
