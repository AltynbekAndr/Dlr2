<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.model_for_users.Groups" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавленные магазины</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/groupsParam.css" />
</head>
<body class="subpage">
<jsp:include page="header_menu/add_shops_header.jsp"/>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
    Groups groups = (Groups)pageContext.getRequest().getAttribute("groups");

%>
<div>
    <div class="mydivcourier1">
        <h6><%=groups.getName()%></h6>
    </div>
</div>

<div class='container'>
    <div class=row>


        <TABLE class="table table-striped">
            <TR>
                <TH>Название</TH>
                <TH>Сумма прибавления</TH>
                <TH>Пн</TH>
                <TH>Вт</TH>
                <TH>Ср</TH>
                <TH>Чт</TH>
                <TH>Пт</TH>
                <TH>Сб</TH>
                <TH>Вс</TH>
                <TH></TH>
                <TH></TH>
                <TH></TH>
                <TH></TH>
            </TR>



        <%
            out.print("<TR>");
            out.print("<form action=update_group method=post >");
            out.print("<TD><input  type=text name=groupName value='"+groups.getName()+ "' /></TD>");
            if(groups.getPriceGroup()!=null){
                out.print("<TD><input type=number placeholder='1 cом'  name=priceGroup value="+groups.getPriceGroup()+"></TD>");
            }else{
                out.print("<TD><input type=number placeholder='1 cом'  name=priceGroup /></TD>");
            }


            if(groups.isPn()){
                out.print("<TD><input class=pn name=pn type=checkbox checked></TD>");
            }else{
                out.print("<TD><input class=pn name=pn type=checkbox></TD>");
            }

            if(groups.isVt()){
                out.print("<TD><input class=vt  name=vt type=checkbox checked></TD>");
            }else{
                out.print("<TD><input class=vt  name=vt type=checkbox></TD>");
            }

            if(groups.isSr()){
                out.print("<TD><input class=sr  name=sr type=checkbox checked></TD>");
            }else{
                out.print("<TD><input class=sr  name=sr type=checkbox></TD>");
            }

            if(groups.isCh()){
                out.print("<TD><input class=ch  name=ch type=checkbox checked></TD>");
            }else{
                out.print("<TD><input class=ch  name=ch type=checkbox></TD>");
            }

            if(groups.isPt()){
                out.print("<TD><input class=pt  name=pt type=checkbox checked></TD>");
            }else{
                out.print("<TD><input class=pt  name=pt type=checkbox></TD>");
            }

            if(groups.isSb()){
                out.print("<TD><input class=sb  name=sb type=checkbox checked></TD>");
            }else{
                out.print("<TD><input class=sb  name=sb type=checkbox></TD>");
            }

            if(groups.isVs()){
                out.print("<TD><input class=vs name=vs type=checkbox checked></TD>");
            }else{
                out.print("<TD><input class=vs  name=vs type=checkbox></TD>");
            }

            out.print("<TD><input type=submit value=Сохранить></TD>");

            out.print("<TD><input style='display:none' type=text name=id value='"+groups.getId()+"' /></TD>");
            out.print("<TD><input style='display:none' type=text name=compId value='"+company2.getId()+"' /></TD>");
            out.print("</form>");
            out.print("</TR>");

        %>

        </TABLE>
    </div>
</div>
<body>
</body>


</body>
</html>
