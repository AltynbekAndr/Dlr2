<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.models2.UndGroupsMin" %>
<%@ page import="com.springapp.mvc.models2.Shops2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список долгов</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/shops.css" />
</head>
<body class="subpage">
<jsp:include page="header_menu/debt_shops_header.jsp"/>
<%
    ServletRequest ctx =  pageContext.getRequest();
    Company2 company2 =(Company2)session.getAttribute("company");
    List<UndGroupsMin> undGroups = (List<UndGroupsMin>)ctx.getAttribute("undGroupsMins");

    boolean pagesEnd  =(Boolean) ctx.getAttribute("pagesEnd");
    int sizeToPagination  =(Integer) ctx.getAttribute("sizeToPagination");
    int activePageNumber  =(Integer) ctx.getAttribute("activePageNumber");
%>
<p style="display: none" id="companyId"><%=company2.getId()%></p>

<div class=container>
    <div class=row>


        <TABLE class="table table-striped">
            <TR>
               <TH>Название</TH> <TH>Адрес</TH><TH>Долг</TH><TH>Давать в долг</TH><TH>Действие</TH>
            </TR>
        <%

            if(undGroups!=null&&undGroups.size()!=0){
                for(int i =0;i<undGroups.size();i++){
                    Shops2 shops = undGroups.get(i).getShops2();
                    out.print("<TR>");
                    out.print("<TD>"+shops.getName()+"</TD>");
                    out.print("<TD>"+shops.getAddress()+"</TD>");
                    out.print("<TD>"+undGroups.get(i).getDebt()+"</TD>");
                    if(undGroups.get(i).isShopCons()){
                        out.print("<TD><input class=isCons id=check"+i+"  name=pt type=checkbox checked></TD>");
                    }else{
                        out.print("<TD><input class=isCons id=check"+i+"  name=pt type=checkbox ></TD>");
                    }
                    out.print("<TD><a href=shop_info?shopId="+shops.getId()+">просмотр</a></TD>");
                    out.print("<TD style='display:none' class=shopId"+i+" >"+shops.getId()+"</TD>");
                    out.print("<TD style='display:none' class=undGrId"+i+" >"+undGroups.get(i).getId()+"</TD>");
                    out.print("<TD style='display:none' class=undGrDebt"+i+" >"+undGroups.get(i).getDebt()+"</TD>");
                    out.print("<TD style='display:none' class=grId"+i+" >"+undGroups.get(i).getGroups2().getId()+"</TD>");
                    out.print("</TR>");
                }
            }

        %>

        </TABLE>
        <nav aria-label="...">
            <ul class="pagination">
                <%
                    if(sizeToPagination!=0){
                        if(pagesEnd){
                            sizeToPagination = sizeToPagination+1;
                            for(int k=1;k<=sizeToPagination;k++){
                                if(activePageNumber==k){
                                    out.print("<li class='age-item active' \\>");
                                    out.print("<a class=page-link href=debt_shops_p?page="+k+" tabindex=-1>"+k+"</a>");
                                    out.print("</li>");
                                }else{
                                    out.print("<li class='age-item' \\>");
                                    out.print("<a class=page-link href=debt_shops_p?page="+k+" tabindex=-1>"+k+"</a>");
                                    out.print("</li>");
                                }
                            }
                        }else{
                            sizeToPagination = sizeToPagination+1;
                            for(int k=1;k<sizeToPagination;k++){
                                if(activePageNumber==k){
                                    out.print("<li class='age-item active' \\>");
                                    out.print("<a class=page-link href=debt_shops_p?page="+k+" tabindex=-1>"+k+"</a>");
                                    out.print("</li>");
                                }else{
                                    out.print("<li class='age-item' \\>");
                                    out.print("<a class=page-link href=debt_shops_p?page="+k+" tabindex=-1>"+k+"</a>");
                                    out.print("</li>");
                                }
                            }
                        }

                        if(activePageNumber<sizeToPagination){
                            int nextpage = activePageNumber+1;
                            out.print("<li class='page-item' \\>");
                            out.print("<a class=page-link href=debt_shops_p?page="+nextpage+" tabindex=-1>След.</a>");
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
            window.location.href = "debt_shops_p?page="+v;
        }

    });
</script>

</body>
</html>
