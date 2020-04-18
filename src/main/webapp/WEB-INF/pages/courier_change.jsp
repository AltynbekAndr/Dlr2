<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.model_for_users.Courier" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Редактирование курьера</title>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/add_courier.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="header_menu/courier_header.jsp"/>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
    Courier courier =(Courier)pageContext.getRequest().getAttribute("courier");
%>

<div class="container">
  <div class="row">
    <form action="update_courier" method="post" enctype="multipart/form-data">
      <input name="id_comp" id="id_comp" type="text" style="display: none" value=<%=company2.getId()%> />
      <input name="courier_id"  type="text" style="display: none" value="<%=courier.getId()%>" />
      <input name="courier_photo"  type="text" style="display: none" value="<%=courier.getPhoto()%>" />
      <h6>Имя</h6>
      <input name="name" id="name" type="text" placeholder="Имя" value="<%=courier.getName()%>"/>
      <h6>Фамилия</h6>
      <input name="l_name" id="l_name" type="text" placeholder="Фамилия" value="<%=courier.getL_name()%>" />
      <h6>Адрес</h6>
      <input name="address" id="address" type="text" placeholder="Адрес" value="<%=courier.getAddress()%>" />
      <h6>Логин</h6>
      <input name="login" style="display: none" type="text" placeholder="Логин" value="<%=courier.getLogin()%>" />
      <input name="login" id="login" type="text" disabled placeholder="Логин" value="<%=courier.getLogin()%>" />
      <h6>Телефон</h6>
      <input name="phone" id="phone" type="text" placeholder="Телефон" value="<%=courier.getPhone()%>" />
      <h6>Пароль</h6>
      <input name="password" id="password" type="text" placeholder="Пароль" value="<%=courier.getPassword()%>" />
      <div>
        <h6>Фото</h6>
        <%
          out.print("<img src=photo_courier?imgName="+courier.getPhoto()+"&companyId="+company2.getId()+" />");
        %>
        <input type="file" id="file" name="file" accept="image/*"/>
      </div>
      <input value="Сохранить" class="button alt" type="submit" />
    </form>
  </div>
</div>
</body>
</html>
