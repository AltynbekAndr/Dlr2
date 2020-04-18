<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.model_for_users.Shops" %>
<%@ page import="com.springapp.mvc.model_for_users.GroupsUs" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Магазины</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/admin.css" />
    <link rel="stylesheet" href="resources/assets/css/menu_style.css" />
    <style type="text/css">
        .title_category {
            color: #000000;
            text-align: center;
            padding-top: 10px;
            margin:0 auto;
            background: #4285F4;
            padding-bottom: 15px;
            font-size: 20px;
        }
    </style>
</head>
<body class="subpage">


<jsp:include page="header_menu/admin_header_shops.jsp"/>
<%
    ServletRequest ctx =  pageContext.getRequest();
    List<Shops> shops  =(List<Shops>) ctx.getAttribute("shops");
    List<GroupsUs> groups  =(List<GroupsUs>) ctx.getAttribute("groups");
    String group_us = (String) pageContext.getRequest().getAttribute("group_us");
%>
<div id='cssmenu'>
    <ul>
        <h1 class=title_category style='color:white'>Категории</h1>
        <%
        for(int i =0;i<groups.size();i++){
            GroupsUs groupsUs = groups.get(i);
            out.print("<li><a href=#?groupsUsId="+groupsUs.getId()+">"+groupsUs.getName()+"</a></li>");
        }
        %>
    </ul>
</div>
<div class=container>
    <div class=row>
        <TABLE class="table table-striped">
            <TR>
                <TH>Название</TH>
                <TH>Адрес</TH>
                <TH>Телефон</TH>
                <TH>Логин</TH>
                <TH></TH>
            </TR>
            <%
                for(int i =0;i<shops.size();i++){
                    Shops shops1 = shops.get(i);
                    out.print("<TR>");
                    out.print("<TD>"+shops1.getName()+"</TD>");
                    out.print("<TD>"+shops1.getAddress()+"</TD>");
                    out.print("<TD>"+shops1.getPhone()+"</TD>");
                    out.print("<TD>"+shops1.getLogin()+"</TD>");
                    out.print("<TD>" +
                            "<div class=crlink>" +
                            "<a href=admin_shop_info?shopId="+shops1.getId()+" class=link2>Просмотр</a>" +
                            "</div>" +
                            "</TD>");
                    out.print("</TR>");
                }
            %>
        </TABLE>
    </div>
</div>
<script type="application/javascript" src="/resources/js/bootstrap.min.js"></script>

</body>
</html>
