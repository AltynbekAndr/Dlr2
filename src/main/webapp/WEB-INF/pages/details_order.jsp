<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.model_for_users.MyOrder" %>
<%@ page import="com.springapp.mvc.model_for_users.Products" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.model_for_users.Courier" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Просмотр заказа</title>
  <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/details_order.css" />
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
<jsp:include page="header_menu/new_orders.jsp"/>
<div class="mydivcourier">
    <div class="mydivcourier1">
        <%
            ServletRequest ctx =  pageContext.getRequest();
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
<%
    Company2 company2 =(Company2)session.getAttribute("company");
    List<Products> productsList =  (List<Products>)ctx.getAttribute("products");
    MyOrder myOrder =  (MyOrder)ctx.getAttribute("myOrder");
    out.print("<script type='application/javascript' >$('.mydivcourier1').append('Заказ №"+myOrder.getId()+"') </script>");
%>
<div class=container>
    <div class =row>


    <TABLE class="table table-striped">
        <TR>
            <TH>Название</TH> <TH class="price">Цена(сом)</TH><TH>Объем</TH><TH>Кол/ство</TH><TH>Возврат</TH><TH>В долг</TH>
        </TR>
<%



    for(int i =0;i<productsList.size();i++){
        Products products = productsList.get(i);
        String nameImg = "";
        if(products.isConsignation()){
            nameImg = "check";
        }else{
            nameImg = "uncheck";
        }
        out.print("<TR>");
        out.print("<TD id='productId"+i+
                "' style='display:none'>"
                +products.getId()+
                "</TD><TD id='sumId"+i+"'>"
                +products.getName()+
                "</TD><TD class='price' id='dateId"+i+"'>"
                +products.getPrice()+
                "</TD><TD id='dateId"+i+"'>"
                +products.getWeight()+
                "</TD><TD id='dateId"+i+"'>"
                +products.getAmount()+
                "</TD><TD id='dateId"+i+"'>"
                +products.getReturnAmount()+
                "</TD><TD><img src=/resources/images/"+nameImg+".png></TD>");
        out.print("</TR>");

    }
%>

    </TABLE>
        <%
            out.print("<div class=shop_div>");
            out.print("<div>");
                    out.print("<p>Магазин(заказчик)</p>");
                    try{
                        out.print("<img src=photo_shops?imgName="+myOrder.getShops().getPhoto()+"&companyId="+company2.getId()+"  ><br>");
                        out.print("<span>Магазин:"+myOrder.getShops().getName() +"</span><br>");
                        out.print("<span>Адрес:"+myOrder.getShops().getAddress()+"</span><br>");
                        out.print("<span>Телефон:"+myOrder.getShops().getPhone()+"</span><br>");
                    }catch (NullPointerException e){
                        out.print("<img src='/resources/images/icon.png'><br>");
                        out.print("<span>Магазин:_________</span><br>");
                        out.print("<span>Адрес:_________</span><br>");
                        out.print("<span>Телефон:_________</span><br>");
                    }
            out.print("</div>");
            out.print("</div>");

            out.print("<div class=courier_div>");
            out.print("<div>");
                out.print("<p>Курьер</p>");
                try{
                    Courier courier = myOrder.getCourier();

                    out.print("<img src=photo_courier?imgName="+courier.getPhoto()+"&companyId="+company2.getId()+"  ><br>");
                    out.print("<span>Имя:"+courier.getName() +"</span><br>");
                    out.print("<span>Фамилия:"+courier.getL_name()+"</span><br>");
                    out.print("<span>Адрес:"+courier.getAddress()+"</span><br>");
                    out.print("<span>Телефон:"+courier.getPhone()+"</span><br>");
                }catch (NullPointerException e){
                    out.print("<img src='/resources/images/icon.png'><br>");
                    out.print("<span>Имя:_________</span><br>");
                    out.print("<span>Фамилия:_________</span><br>");
                    out.print("<span>Адрес:_________</span><br>");
                    out.print("<span>Телефон:_________</span><br>");
                }
            out.print("</div>");
            out.print("</div>");
        %>
</div>
</div>

</body>
</html>
