<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.model_for_users.GroupsUs" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.model_for_users.Shops" %>
<%@ page import="com.springapp.mvc.model_for_users.Groups" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление магазинов</title>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/create_groups.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
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
    <script type="application/javascript">
        $(document).ready(function(){
            var options = document.getElementById("selectGroup").options;
            for(var i =0;i<options.length;i++){
                $(".selectGroup").append("<option class="+$(options[i]).attr('class')+">"+options[i].value+"</option>");
            }
        });
    </script>
</head>
<body class="subpage">
<jsp:include page="header_menu/add_shops_header.jsp"/>
<%
    ServletRequest ctx =  pageContext.getRequest();
    String group_id = (String)ctx.getAttribute("group_id");
    boolean pagesEnd  =(Boolean) ctx.getAttribute("pagesEnd");
    int sizeToPagination  =(Integer) ctx.getAttribute("sizeToPagination");
    int activePageNumber  =(Integer) ctx.getAttribute("activePageNumber");
    boolean group = false;
    try {
        group  =(Boolean) ctx.getAttribute("group");
    }catch (NullPointerException e){}
    String group_us = (String) pageContext.getRequest().getAttribute("group_us");
%>
<div>
    <div class="mydivcourier1">
        <%
        if(group_us.equals("Все добавлены")){
           out.print("<p><span class='icon fa fa-warning' style='color:yellow'> "+group_us.toUpperCase()+"</span></p>");
        }else{
            out.print("<p><span class='icon fa fa-bars' style='color:white'> "+group_us.toUpperCase()+"</span></p>");
        }
        %>

    </div>
</div>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
    List<GroupsUs> groupsUs = (List<GroupsUs>) pageContext.getRequest().getAttribute("GroupsUs");
    List<Groups> groups = (List<Groups>) pageContext.getRequest().getAttribute("groups");
    List<Shops> shops = (List<Shops>) pageContext.getRequest().getAttribute("shops");

        if(groups!=null&&groups.size()!=0){
            out.print("<div class=select_container style='display:none'>");
                out.print("<select id=selectGroup>");
                    for(int i =0;i<groups.size();i++){
                        out.print("<option class=grId"+groups.get(i).getId()+">"+groups.get(i).getName()+"</option>");
                    }
                out.print("<select>");
            out.print("</div>");
        }

    out.print("<div id=cssmenu>" +
                        "<ul>");
    out.print("<h1 class=title_category style='color:white'>Категории</h1>");
    for(int i=0;i<groupsUs.size();i++){
        out.print("<li class=main_li><a href=get_shops_by_id?group_id="+groupsUs.get(i).getId()+"><span class='icon fa-arrow-circle-right'> </span><span>"+groupsUs.get(i).getName()+" </span></a></li>");

    }
     out.print("</ul>" +
                    "</div>");
%>
<div class=container>
    <div class=row>


        <TABLE class="table table-striped">
            <TR>
                <TH>#</TH><TH>Название</TH> <TH>Адрес</TH><TH>Добавление</TH><TH></TH>
            </TR>
    <%


        if(shops!=null&&shops.size()!=0){
            for(int i =0;i<shops.size();i++){
                Shops shop = shops.get(i);
                out.print("<TR>");
                out.print("<TD class=id style='display:none'>"+shop.getId()+"</TD>");
                out.print("<TD><INPUT TYPE=checkbox id='check"+i+"'></TD>");
                out.print("<TD>"+shop.getName()+"</TD>");
                out.print("<TD>"+shop.getAddress()+"</TD>");
                out.print("<TD><select class='selectGroup form-control'  name=product_category ><option>В группу</option></select></TD>");
                out.print("<TD><a href=shop_info?shopId="+shop.getId()+">просмотр</a></TD>");
                out.print("</TR>");
            }
        }

    %>
        </TABLE>
        <nav aria-label="...">
            <ul class="pagination">
                <%
                    if(sizeToPagination!=0){
                        String ulrPage = "add_shops_p";
                        if(group){
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

<script type="application/javascript">
    $('.selectGroup').change(function(){
        var item = $(this).val();
        var itemClass = $(this).find('option:selected').attr('class');
        if(item === "В группу"){
            return;
        }else{
            var par = $(this).parent();
            par = $(par).parent();
            number = itemClass.substring(4);
            var data = JSON.stringify({
                grId: number,shopId: $(par).children().html()
            });
            $.ajax({
                type: "POST",
                url: "/save_undGroup",
                data:data,
                contentType:"application/json",
                dataType:"json",
                success: function(data) {
                    if(data!=null){
                        if(data.result == 200){
                            alert("Сохранено");
                        }else{
                            alert("Ошибка!");
                        }
                    }}})
        }

    });
    $('#getpage').click(function(){
        var v = $("#pageNum").val();
        if(v!=null&& v.length!=0){
            window.location.href = globUrl+"?page="+v+"&group_id="+globGrId;
        }

    });
</script>
</body>

</html>
