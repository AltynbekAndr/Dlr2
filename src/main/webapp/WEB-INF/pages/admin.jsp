<%@ page import="com.springapp.mvc.model_for_users.Company" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Фирмы</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/admin.css" />
</head>
<body class="subpage">


<jsp:include page="header_menu/admin_header.jsp"/>
<%
    ServletRequest ctx =  pageContext.getRequest();
    List<Company> companies  =(List<Company>) ctx.getAttribute("companies");
%>
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
                for(int i =0;i<companies.size();i++){
                    Company company = companies.get(i);
                    out.print("<TR>");
                    out.print("<TD>"+company.getName()+"</TD>");
                    out.print("<TD>"+company.getAddress()+"</TD>");
                    out.print("<TD>"+company.getPhone()+"</TD>");
                    out.print("<TD>"+company.getLogin()+"</TD>");





                    out.print("<TD>" +
                            "<div class=crlink>" +
                            "<a href=admin_change_company?companyId="+company.getId()+" class=link1>Редактировать</a>" +
                            "<a href=admin_del_company?companyId="+company.getId()+" class=link2>Удалить</a>" +
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
