<%@ page import="com.springapp.mvc.model_for_users.Products" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.model_for_users.Categories" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Продукты</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/goods.css" />
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
<jsp:include page="header_menu/products_header.jsp"/>
<%
    String products_cat = (String)pageContext.getRequest().getAttribute("products_cat");
    String textColor = "white";
    if(products_cat.equals("В категории нет продуктов")){
        textColor = "red";
    }
%>
<div class="mydivcourier">
    <div class="mydivcourier1">
        <p><a href="add_category"><span class="icon fa-plus-circle"> Добавить категории</span></a></p>
        <p><a href="add_product"><span class="icon fa-plus-circle"> Добавить продукт</span></a></p>
        <p><span class="icon fa fa-bars" style="color:<%=textColor%>"> <%=products_cat%></span></p>
    </div>
</div>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
    List<Products> productses = (List)pageContext.getRequest().getAttribute("products");
    List<Categories> categorieses = (List)pageContext.getRequest().getAttribute("categorieses");
    out.print("<div id=cssmenu>" +
              "<ul>");
    out.print("<h1 class=title_category style='color:white'>Категории</h1>");
    out.print("<li class=main_li><a href=products><span class='icon fa-arrow-circle-right'> </span><span>Все</span></a></li>");
    for(int i =0;i<categorieses.size();i++){
        out.print("<li class=main_li><a href=get_products_by_categoryId?category_id="+categorieses.get(i).getId()+"><span class='icon fa-arrow-circle-right'> </span><span>"+categorieses.get(i).getCategory()+" </span></a></li>");
    }

    out.print("</ul>" +
            "</div>");
%>
<div class=container>
    <div class=row>


        <TABLE class="table table-striped">
            <TR>
                <TH>Название</TH>
                <TH>Цена(сом)</TH>
                <TH>Объем(вес/литр)</TH>
                <TH>Рек.цена</TH>
                <TH></TH>
            </TR>
                <%
                    for(int i =0;i<productses.size();i++){
                        Products products = productses.get(i);
                        out.print("<TR>");
                        out.print("<TD>"+products.getName()+"</TD>");
                        out.print("<TD>"+products.getPrice()+"</TD>");
                        out.print("<TD>"+products.getWeight()+"</TD>");
                        out.print("<TD>"+products.getRec_price()+"</TD>");
                        out.print("<TD>" +
                                    "<div class=crlink>" +
                                        "<a href=product_change?id_product="+products.getId()+" class=link1>Редактировать</a>" +
                                        "<a href=del_product?id_product="+products.getId()+" id=prd class=link2>Удалить</a>" +
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
