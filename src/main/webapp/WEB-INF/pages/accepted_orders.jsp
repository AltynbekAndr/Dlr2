<%@ page import="com.springapp.mvc.model_for_users.Courier" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.model_for_users.MyOrder" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список заказов</title>
    <%--<meta name="viewport" content="width=650px, initial-scale=1">--%>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet" href="resources/assets/css/menu_style.css"/>
    <link rel="stylesheet"  href="resources/css/mycss/new_orders.css" />
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
            var options = document.getElementById("selectCourier").options;
            for(var i =0;i<options.length;i++){
                if(i==0){
                    continue;
                }
                $(".selectCourier").append("<option class="+$(options[i]).attr('class')+" >"+options[i].value+"</option>");
            }
        });
    </script>
</head>
<body class="subpage">


<jsp:include page="header_menu/new_orders.jsp"/>
<div class="mydivcourier">
    <div class="mydivcourier1">
        <%

            ServletRequest ctx =  pageContext.getRequest();
            Company2  company2 =(Company2) session.getAttribute("company");
            List<Courier> couriers  =(List<Courier>) ctx.getAttribute("couriers");

            boolean pagesEnd  =(Boolean) ctx.getAttribute("pagesEnd");
            int sizeToPagination  =(Integer) ctx.getAttribute("sizeToPagination");
            int activePageNumber  =(Integer) ctx.getAttribute("activePageNumber");




            out.print("<p id=companyId style='display:none'>"+company2.getId()+"</p>");
            out.print(
                    "<select id=selectCourier  name=product_category style='display:none'><option>Назначить курьер</option>");
            for (int i=0;i<couriers.size();i++){
                out.print("<option class=crId"+couriers.get(i).getId()+" >"+couriers.get(i).getName()+"</option>");
            }
            out.print(
                    "</select>");
        %>

    </div>
</div>
<div id='cssmenu'>
    <ul>
        <h1 class=title_category style='color:white'>Категории</h1>
        <li><a href='orders_in_map'><span class='icon fa-arrow-circle-right'> </span><span>На карте</span></a></li>
        <li><a href='new_orders'><span class='icon fa-arrow-circle-right'> </span><span>Новые</span></a></li>
        <li><a href='accepted_orders'><span class='icon fa-arrow-circle-right'> </span><span>Назначенные</span></a></li>
        <li><a href='complete_orders'><span class='icon fa-arrow-circle-right'> </span><span>Выполненные</span></a></li>
    </ul>
</div>
<div class="container">
<div class=row>
    <TABLE class="table table-striped">
        <TR>
            <TH></TH><TH>Общ. сумма заказа(сом)</TH><TH>Дата заказа</TH><TH>Назначить курьер</TH><TH>Статус</TH><TH></TH>
        </TR>
<%
    List<MyOrder> myOrders  =(List<MyOrder>) ctx.getAttribute("myOrders");
     for(int i =0;i<myOrders.size();i++){
            out.print("<TR>");
            out.print("<TD><INPUT style='display:none' TYPE=checkbox id='check"+i+"'></TD><TD style='display:none' id='orderId"+i+"'>"
                    +myOrders.get(i).getId()+
                    "</TD><TD id='sumId"+i+"'>"
                    +myOrders.get(i).getSum()+
                    "</TD><TD id='dateId"+i+"'>"
                    +myOrders.get(i).getDate()+
                    "</TD><TD><select class='selectCourier form-control'  name=product_category id='selectId"+i+"'><option>Сменить курьер</option></select></TD><TD class="+myOrders.get(i).getStatus()+">"
                    +myOrders.get(i).getStatus()+
                    "</TD><TD><a href=detailsAcceptedOrder?order="
                    +myOrders.get(i).getId()+
                    ">просмотр</a></TD><TD style='display:none' id=shopId"+i+">"
                    +myOrders.get(i).getShops().getId()+"</TD>");
            out.print("</TR>");
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
                                out.print("<a class=page-link href=accepted_orders_p?page="+k+" tabindex=-1>"+k+"</a>");
                                out.print("</li>");
                            }else{
                                out.print("<li class='age-item' \\>");
                                out.print("<a class=page-link href=accepted_orders_p?page="+k+" tabindex=-1>"+k+"</a>");
                                out.print("</li>");
                            }
                        }
                    }else{
                        sizeToPagination = sizeToPagination+1;
                        for(int k=1;k<sizeToPagination;k++){
                            if(activePageNumber==k){
                                out.print("<li class='age-item active' \\>");
                                out.print("<a class=page-link href=accepted_orders_p?page="+k+" tabindex=-1>"+k+"</a>");
                                out.print("</li>");
                            }else{
                                out.print("<li class='age-item' \\>");
                                out.print("<a class=page-link href=accepted_orders_p?page="+k+" tabindex=-1>"+k+"</a>");
                                out.print("</li>");
                            }
                        }
                    }

                    if(activePageNumber<sizeToPagination){
                        int nextpage = activePageNumber+1;
                        out.print("<li class='page-item' \\>");
                        out.print("<a class=page-link href=accepted_orders_p?page="+nextpage+" tabindex=-1>След.</a>");
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
<script type="application/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="application/javascript">
    $('.selectCourier').change(function(){
        var item = $(this).val();
        var itemClass = $(this).find('option:selected').attr('class');
        if(item === "Назначить курьер"){
            return;
        }else{
            let id = this.closest('select').id;
            number =id.substring(8);

            var orderId = $('#orderId'+number).text();
            var sumId = $('#sumId'+number).text();
            var dateId = $('#dateId'+number).text();
            var shopId = $('#shopId'+number).text();
            var companyId = $('#companyId').text();

            var courierId = itemClass.substring(4);

            var data = JSON.stringify({
                courierId: courierId[0],id: orderId, sum: sumId, date: dateId,companyId: companyId,shopsId:shopId});
            $.ajax({
                type: "POST",
                url: "/update_myorder",
                data:data,
                contentType:"application/json",
                dataType:"json",
                success: function(data) {
                    if(data!=null){
                        if(data.result == 200){
                            alert("Назначено");
                        }
                    }}})
        }

    });
    $('#getpage').click(function(){
        var v = $("#pageNum").val();
        if(v!=null&& v.length!=0){
            window.location.href = "new_orders_p?page="+v;
        }

    });
</script>
</body>
</html>
