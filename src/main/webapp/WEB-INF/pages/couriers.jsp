<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.springapp.mvc.models2.Courier2" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Курьеры</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/courier.css" />

</head>
<body class="subpage">
<jsp:include page="header_menu/courier_header.jsp"/>
<div>
    <div class="mydivcourier1">
        <p><a href="add_courier"><span class="icon fa-plus-circle"> Добавить курьер</span></a></p>
    </div>
</div>
<%
    String success = "";
    ServletRequest ctx =  pageContext.getRequest();
    Company2 company2 =(Company2)session.getAttribute("company");
    success = String.valueOf(ctx.getAttribute("success"));

   if((success!=null)&&(!success.equals(""))&&(success.length()!=0)&&(!success.equals("null"))){
       out.print("<script type=text/javascript>alert('Курьер сохранен')</script>");
   }
    Gson gson = new Gson();
    String str = String.valueOf(ctx.getAttribute("message"));
    Courier2 [] courier2 = gson.fromJson(str,Courier2[].class);
%>
<div class=container>
    <div class=row>


        <TABLE class="table table-striped">
            <TR>
                <TH>Ф.И.О</TH>
                <TH>Адрес</TH>
                <TH>Телефон</TH>
                <TH></TH>
            </TR>
<%
    for(int i =0;i<courier2.length;i++){
        Courier2 cor = courier2[i];
        out.print("<TR>");
        out.print("<TD>"+cor.getName()+" "+cor.getL_name()+"</TD>");
        out.print("<TD>"+cor.getAddress()+"</TD>");
        out.print("<TD>"+cor.getPhone()+"</TD>");
        out.print("<TD>" +
                    "<div class=crlink>" +
                        "<a href=courier_change?id_courier="+cor.getId()+" class=link1>Редактировать</a>" +
                        "<a href=del_courier?id_courier="+cor.getId()+" class=link2>Удалить</a>" +
                    "</div>" +
                  "</TD>");
        out.print("</TR>");
    }
%>
        </TABLE>
    </div>
</div>

</body>
</html>
