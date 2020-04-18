<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить фирму</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/admin.css" />
</head>
<body class="subpage">


<jsp:include page="header_menu/admin_header_add_company.jsp"/>
<%
    ServletRequest ctx =  pageContext.getRequest();
%>
<div class=container>
    <div class=row>

        <form action="save_company" method="post" enctype="multipart/form-data">
            <h6>Название компании</h6>
            <input type="text" placeholder="name" name="name">
            <h6>Логин</h6>
            <input type="text" placeholder="login" name="login">
            <h6>Пароль</h6>
            <input type="text" placeholder="password" name="password">
            <h6>Адрес</h6>
            <input type="text" placeholder="address" name="address">
            <h6>Телефон</h6>
            <input type="text" placeholder="phone" name="phone">
            <div>
                <h6>Фото компании</h6>
                <input type="file" id="file" name="file" accept="image/*" /><br>
            </div>
            <input type="submit" value="сохранить">
        </form>
    </div>
</div>
<script type="application/javascript" src="/resources/js/bootstrap.min.js"></script>

</body>
</html>
