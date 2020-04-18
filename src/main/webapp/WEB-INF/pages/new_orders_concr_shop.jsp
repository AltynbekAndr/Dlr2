<%@ page import="com.springapp.mvc.model_for_users.Courier" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.model_for_users.MyOrder" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.model_for_users.Shops" %>
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
                $(".selectCourier").append("<option>"+options[i].value+"</option>");
            }
        });
    </script>
</head>
<body class="subpage">


<jsp:include page="header_menu/new_orders.jsp"/>
<div class="mydivcourier">
    <%
        ServletRequest ctx =  pageContext.getRequest();
        String shopid = (String)ctx.getAttribute("shopid");
    %>
    <div class="mydivcourier1">
       <p>Все заказы магазина  <a href='shop_info?shopId=<%=shopid%>'><span class='icon fa-check-circle'>Просмотр магазина</span></a></p>
            <%

                Company2  company2 =(Company2) session.getAttribute("company");
                List<Courier> couriers  =(List<Courier>) ctx.getAttribute("couriers");
                out.print("<p id=companyId style='display:none'>"+company2.getId()+"</p>");
                out.print(
                        "<select id=selectCourier  name=product_category style='display:none'><option>Назначить курьер</option>");
                for (int i=0;i<couriers.size();i++){
                    out.print("<option>"+couriers.get(i).getName()+" ("+couriers.get(i).getId()+")</option>");
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
    List<MyOrder> myOrders  =(List<MyOrder>) ctx.getAttribute("orders");
     for(int i =0;i<myOrders.size();i++){
         String status = myOrders.get(i).getStatus();
         String url="";
         if(status.equals("new")){
             url = "detailsOrder";
         }else if(status.equals("accepted")){
             url = "detailsAcceptedOrder";
         }else if(status.equals("completed")){
             url = "detailsCompletedOrder";
         }
            out.print("<TR>");
            out.print("<TD><INPUT style='display:none' TYPE=checkbox id='check"+i+"'></TD><TD style='display:none' id='orderId"+i+"'>"
                    +myOrders.get(i).getId()+
                    "</TD><TD id='sumId"+i+"'>"
                    +myOrders.get(i).getSum()+
                    "</TD><TD id='dateId"+i+"'>"
                    +myOrders.get(i).getDate()+
                    "</TD><TD><select class=selectCourier  name=product_category id='selectId"+i+"'><option>Назначить курьер</option></select></TD><TD class="+myOrders.get(i).getStatus()+">"
                    +myOrders.get(i).getStatus()+
                    "</TD><TD><a id='linkId"+i+"' href="+url+"?order="
                    +myOrders.get(i).getId()+
                    ">просмотр</a></TD><TD style='display:none' id=shopId"+i+">"
                    +myOrders.get(i).getShops().getId()+
                    "</TD>");
            out.print("</TR>");
    }
%>

    </TABLE>
</div>
</div>
<script type="application/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="application/javascript">
    $('.selectCourier').change(function(){
        var item = $(this).val();
        if(item === "Назначить курьер"){
            return;
        }else{
            let id = this.closest('select').id;
            number =id.substring(8);

            var orderId = $('#orderId'+number).text();
            var sumId = $('#sumId'+number).text();
            var dateId = $('#dateId'+number).text();
            var shopId = $('#shopId'+number).text();
            var hr = $('#linkId'+number).attr("href");
            var newHref = "detailsAcceptedOrder?order="+hr.substring(19);
            $('#linkId'+number).attr("href",newHref);
            var companyId = $('#companyId').text();

            var courierId = item.split("(");
            courierId = courierId[1].split(")");
            courierId = courierId[0].split(",");

            var data = JSON.stringify({
                courierId: courierId[0],id: orderId, sum: sumId, date: dateId,companyId: companyId,shopId:shopId,shopsId:shopId
            });
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
</script>
</body>
</html>
