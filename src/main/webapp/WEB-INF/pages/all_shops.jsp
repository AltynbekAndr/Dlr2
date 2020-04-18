<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.model_for_users.Groups" %>
<%@ page import="com.springapp.mvc.model_for_users.UndGroups" %>
<%@ page import="com.springapp.mvc.model_for_users.Shops" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавленные магазины</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/shops.css" />
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
<jsp:include page="header_menu/all_shops_header.jsp"/>
<%
    ServletRequest ctx =  pageContext.getRequest();
    Company2 company2 =(Company2)session.getAttribute("company");
    List<Groups> groups = (List<Groups>)ctx.getAttribute("groups");
    List<UndGroups> undGroups = (List<UndGroups>)ctx.getAttribute("undGroupses");
    String groupName = (String)ctx.getAttribute("groupName");
    String group_id = (String)ctx.getAttribute("group_id");
    boolean statusAddingLink = (Boolean)ctx.getAttribute("statusAddingLink");
    boolean pagesEnd = false;
    int sizeToPagination = 0;
    int activePageNumber  = 0;
    try {
     pagesEnd  =(Boolean) ctx.getAttribute("pagesEnd");
    }catch (NullPointerException e){}
    try {
     sizeToPagination  =(Integer) ctx.getAttribute("sizeToPagination");
    }catch (NullPointerException e){}
    try {
     activePageNumber  =(Integer) ctx.getAttribute("activePageNumber");
    }catch (NullPointerException e){}
    boolean group = false;
    try {
        group  =(Boolean) ctx.getAttribute("group");
    }catch (NullPointerException e){}
%>
<p style="display: none" id="companyId"><%=company2.getId()%></p>
<div>
    <div class="mydivcourier1">
        <p><a href=add_groups> <span class='icon fa-plus-circle'> Новая группа</span></a></p>
        <p><a href="add_shops"><span class="icon fa-plus-circle"> Добавить магазин</span></a></p>
        <%
        if(groupName.equals("Нет магазинов!")){
            out.print("<p style='color:yellow'><span class='icon fa-bars'> " +groupName+"</span></p>");
        }else{
            out.print("<p style='color:#6CC091'><span class='icon fa-bars'> " +groupName.toUpperCase()+"</span></p>");
        }
        if(statusAddingLink){
            out.print("<p><a href=setGroup?group_id="+group_id+"><span class='icon fa-plus-circle'> Настроить район</span></a></p>");
        }
        %>
    </div>
</div>
<%

    out.print("<div id=cssmenu>" +
            "<ul>");
    out.print("<h1 class=title_category style='color:white'>Категории</h1>");
    if(groups!=null&&groups.size()!=0){
        for(int i =0;i<groups.size();i++){
            out.print("<li class=main_li><a href=shops_from_undgroup?group_id="+groups.get(i).getId()+"&groupName="+groups.get(i).getName()+"><span class='icon fa-arrow-circle-right'> </span><span>"+groups.get(i).getName()+" </span></a></li>");
        }
    }


    out.print("</ul>" +
            "</div>");

%>
<div class=container>
    <div class=row>


        <TABLE class="table table-striped">
            <TR>
               <TH>Название</TH> <TH>Адрес</TH><TH>Группа</TH><TH>Давать в долг</TH><TH>Действие</TH>
            </TR>
        <%

            if(undGroups!=null&&undGroups.size()!=0){
                for(int i =0;i<undGroups.size();i++){
                    Shops shops = undGroups.get(i).getShops();
                    Groups groups1 = undGroups.get(i).getGroups();
                    out.print("<TR>");
                    out.print("<TD>"+shops.getName()+"</TD>");
                    out.print("<TD>"+shops.getAddress()+"</TD>");
                    out.print("<TD>"+groupName+"</TD>");
                    if(undGroups.get(i).isShopCons()){
                        out.print("<TD><input class='isCons custom-control-input' id=check"+i+"  name=pt type=checkbox checked></TD>");
                    }else{
                        out.print("<TD><input class='isCons custom-control-input' id=check"+i+"  name=pt type=checkbox ></TD>");
                    }
                    out.print("<TD><a href=shop_info?shopId="+shops.getId()+">просмотр</a></TD>");
                    out.print("<TD style='display:none' class=shopId"+i+" >"+shops.getId()+"</TD>");
                    out.print("<TD style='display:none' class=undGrId"+i+" >"+undGroups.get(i).getId()+"</TD>");
                    out.print("<TD style='display:none' class=undGrDebt"+i+" >"+undGroups.get(i).getShopDebt()+"</TD>");
                    out.print("<TD style='display:none' class=grId"+i+" >"+groups1.getId()+"</TD>");
                    out.print("</TR>");
                }
            }

        %>

        </TABLE>
        <nav aria-label="...">
            <ul class="pagination">
                <%
                    if(sizeToPagination!=0){
                        String ulrPage = "all_shops_p";
                        if(group){
                            ulrPage = "all_shops_pt";
                            out.print("<script type='application/javascript'>var globUrl='"+ulrPage+"';var globGrId='"+group_id+"'</script>");
                        }
                        if(pagesEnd){
                            sizeToPagination = sizeToPagination+1;
                            for(int k=1;k<=sizeToPagination;k++){
                                if(activePageNumber==k){
                                    out.print("<li class='age-item active' \\>");
                                    out.print("<a class=page-link href="+ulrPage+"?page="+k+"&group_id="+group_id+" tabindex=-1>"+k+"</a>");
                                    out.print("</li>");
                                }else{
                                    out.print("<li class='age-item' \\>");
                                    out.print("<a class=page-link href="+ulrPage+"?page="+k+"&group_id="+group_id+" tabindex=-1>"+k+"</a>");
                                    out.print("</li>");
                                }
                            }
                        }else{
                            sizeToPagination = sizeToPagination+1;
                            for(int k=1;k<sizeToPagination;k++){
                                if(activePageNumber==k){
                                    out.print("<li class='age-item active' \\>");
                                    out.print("<a class=page-link href="+ulrPage+"?page="+k+"&group_id="+group_id+" tabindex=-1>"+k+"</a>");
                                    out.print("</li>");
                                }else{
                                    out.print("<li class='age-item' \\>");
                                    out.print("<a class=page-link href="+ulrPage+"?page="+k+"&group_id="+group_id+" tabindex=-1>"+k+"</a>");
                                    out.print("</li>");
                                }
                            }
                        }

                        if(activePageNumber<sizeToPagination){
                            int nextpage = activePageNumber+1;
                            out.print("<li class='page-item' \\>");
                            out.print("<a class=page-link href="+ulrPage+"?page="+nextpage+"&group_id="+group_id+" tabindex=-1>След.</a>");
                            out.print("</li>");
                        }else{

                            out.print("<li class='page-item disabled' \\>");
                            out.print("<a class=page-link href=# tabindex=-1>След.</a>");
                            out.print("</li>");
                        }

                        out.print("<div class=col-lg-3>");
                        out.print("<div class=input-group>");
                        out.print("<input type=number class=form-control id=pageNum placeholder=№ />");
                        out.print("<span class=input-group-btn>");
                        out.print("<button class=btn btn-default id=getpage type=button>Вперед</button>");
                        out.print("</span>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                %>
            </ul>
        </nav>
    </div>
</div>
<body>
</body>
<script type="application/javascript">
    $('.isCons').click(function(){
        var bool;
        if(this.checked){
          bool = true;
        }else{
          bool = false;
        }
        let id = this.closest('input').id;
        id = id.substring(5);
        var obj = new Object();
        obj.id = $('.undGrId'+id).text();
        obj.shopDebt = $('.undGrDebt'+id).text();
        obj.shopId = $('.shopId'+id).text();
        obj.grId = $('.grId'+id).text();
        obj.compId =$('#companyId').text();
        obj.shopCons = bool;

        $.ajax({
            type: "POST",
            url: "/update_und_group",
            data:JSON.stringify(obj),
            contentType:"application/json",
            dataType:"json",
            success: function(data) {
                if(data!=null){
                    if(data.result == 200){
                        alert("Сохранено");
                    }
                }}})
    });
    $('#getpage').click(function(){
        var v = $("#pageNum").val();
        if(v!=null&& v.length!=0){
            window.location.href = globUrl+"?page="+v+"&globGrId="+globGrId;
        }

    });
</script>

</body>
</html>
