<%@ page import="com.springapp.mvc.model_for_users.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/admin.css" />
</head>
<body class="subpage">


<jsp:include page="header_menu/admin_header_add_company.jsp"/>
<%
    ServletRequest ctx =  pageContext.getRequest();
    Company company = (Company) ctx.getAttribute("company");
%>
<div class=container>
    <div class=row>

        <form action="update_company" method="post" enctype="multipart/form-data">
            <h6>Название компании</h6>
            <input type="text" value="<%=company.getId()%>" name="id" style="display: none">
            <input type="text" value="<%=company.getPhoto()%>" name="imgName" style="display: none">
            <input type="text" value="<%=company.getName()%>" name="name">
            <h6>Логин</h6>
            <input type="text" style="display: none" value="<%=company.getLogin()%>" name="login">
            <input type="text" disabled value="<%=company.getLogin()%>" name="login">
            <h6>Пароль</h6>
            <input type="text" value="<%=company.getPassword()%>" name="password">
            <h6>Адрес</h6>
            <input type="text" value="<%=company.getAddress()%>" name="address">
            <h6>Телефон</h6>
            <input type="text" value="<%=company.getPhone()%>" name="phone">
            <div>
                <h6>Фото компании</h6>
                <img src="photo_company?companyId=<%=company.getId()%>&imgName=<%=company.getPhoto()%>">
                <input type="file" id="file" name="file" accept="image/*" /><br>
            </div>
            <input type="submit" value="сохранить">
        </form>
    </div>
</div>
<script type="application/javascript" src="/resources/js/bootstrap.min.js"></script>

</body>
</html>
