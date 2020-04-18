<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить курьер</title>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/add_courier.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="header_menu/courier_header.jsp"/>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
%>
<div class="container">
    <div class="row">
        <form action="save_courier" method="post" enctype="multipart/form-data">
            <input name="id_comp" id="id_comp" type="text" style="display: none" value=<%=company2.getId()%> />
            <h6>Имя</h6>
            <input name="name" id="name" type="text" placeholder="Имя" />
            <h6>Фамилия</h6>
            <input name="l_name" id="l_name" type="text" placeholder="Фамилия" />
            <h6>Адрес</h6>
            <input name="address" id="address" type="text" placeholder="Адрес" />
            <h6>Логин</h6>
            <input name="login" id="login" type="text" placeholder="Логин" />
            <h6>Телефон</h6>
            <input name="phone" id="phone" type="text" placeholder="Телефон" />
            <h6>Пароль</h6>
            <input name="password" id="password" type="text" placeholder="Пароль" />
            <div>
                <h6>Фото</h6>
                <input type="file" id="file" name="file" accept="image/*"/>
            </div>
            <input value="Сохранить" class="button alt" type="submit" />
        </form>
    </div>
</div>

</body>
</html>
