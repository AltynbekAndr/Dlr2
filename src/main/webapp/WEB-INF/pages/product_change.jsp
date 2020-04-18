<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.models2.Categories2" %>
<%@ page import="com.springapp.mvc.model_for_users.Products" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Редактирование продукта</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/add_product.css" />
</head>
<body class="subpage">
<jsp:include page="header_menu/products_header.jsp"/>


<%
    ServletRequest ctx =  pageContext.getRequest();
    Company2 company2 =(Company2)session.getAttribute("company");
    Categories2[] categories2s =(Categories2[]) session.getAttribute("categories");

    Products products =(Products)ctx.getAttribute("resProducts");



    out.print( "<div class=maincontainer>\n" +
    "    <div class=main_category_container>\n" +
    "        <form action=update_product method=post enctype=multipart/form-data>\n" +
    "            <label for=main_category0>Главная категория</label>\n");
    out.print(
            "<select id=selectCategory  name=product_category><option>-------</option><option id=createCategory>+ Создать категорию</option>");
    for (int i=0;i<categories2s.length;i++){
        out.print("<option>"+categories2s[i].getCategory()+"</option>");
    }
    out.print(
            "</select><br>");
    out.print("<span>Название</span>");
    out.print("<input type=text name=product_name placeholder=Навзвание value="+products.getName()+" /><br>");
    out.print("<span>Цена</span>");
    out.print("<input type=text name=product_price placeholder=Цена value="+products.getPrice()+" /><br>");
    out.print("<span>Рекомендуемая цена</span>");
    out.print("<input type=text name=product_price_recommendation placeholder='Рекомендуемая цена' value="+products.getRec_price()+" /><br>");
    out.print("<span>Габариты(вес,литр)</span>");
    out.print("<input type=text name=product_weight placeholder=Габариты(вес,литр) value="+products.getWeight()+" /><br>");
    out.print("<span>Описание</span>");
    out.print("<textarea rows=10 cols=45 maxlength=255 name=product_description class=textarea >"+products.getDescription()+"</textarea><br>");
    out.print("<input name=product_id type=text style='display:none' value="+products.getId()+" /><br>");
    out.print("<input name=id_comp type=text style='display:none' value="+company2.getId()+" /><br>");
    out.print("<input name=product_photo type=text style='display:none' value="+products.getPhoto()+" /><br>");


    out.print("<div class=contToCheckboxes>");
        if(products.isReturned()){
            out.print("<input type=checkbox name=product_isReturned checked="+products.isReturned()+" /><p>Возврат</p>");
        }else{
            out.print("<input type=checkbox name=product_isReturned /><p>Возврат</p>");
        }
        if(products.isConsignation()){
            out.print("<input type=checkbox name=product_isDebt checked="+products.isConsignation()+" /><p>Дается в долг</p><br>");
        }else{
            out.print("<input type=checkbox name=product_isDebt /><p>Дается в долг</p><br>");
        }

    out.print("</div>");





    out.print("<div class=field>\n" +
            "        <label for=file>Изменить фото продукта</label>\n" +
            "<img src=photo_products?imgName="+products.getPhoto()+"&companyId="+company2.getId()+"  /><br>"+
            "          <input type=file id=file name=file accept=image/* />\n" +
            "      </div>");
    out.print("");
    out.print(  " <input type=submit id=submit value=Сохранить />\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</div>");
%>
</body>
</html>
