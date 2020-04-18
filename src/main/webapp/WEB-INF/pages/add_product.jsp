<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.models2.Categories2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить продукт</title>
  <link rel="stylesheet"  href="resources/css/mycss/add_product.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
</head>
<body>
<jsp:include page="header_menu/products_header.jsp"/>
<%
    Company2 company2 =(Company2)session.getAttribute("company");
%>
<%

  ServletRequest ctx =  pageContext.getRequest();
  Categories2[] categories2s =(Categories2[]) ctx.getAttribute("categories");
   out.print( "<div class=maincontainer>\n" +
    "    <div class=main_category_container>\n" +
    "        <form action=save_product method=post enctype=multipart/form-data><br>" +
    "            <label for=main_category0>Главная категория</label><br>");
    out.print(
            "<select id=selectCategory  name=product_category><option>-------</option><option id=createCategory>+ Создать категорию</option>");
    for (int i=0;i<categories2s.length;i++){
        out.print("<option>"+categories2s[i].getCategory()+"</option>");
    }
    out.print(
            "</select><br>");
    out.print("<span>Название</span>");
    out.print("<input type=text value="+company2.getId()+" name=id_comp style='display:none' /><br>");
    out.print("<input type=text name=product_name placeholder=Название /><br>");

    out.print("<span>Цена</span><br>");
    out.print("<input type=number name=product_price placeholder=Цена /><br>");
    out.print("<span>Рекомендуемая цена</span><br>");
    out.print("<input type=number name=product_price_recommendation placeholder='Рекомендуемая цена'/><br>");

    out.print("<span>Габариты(вес,литр)</span><br>");
    out.print("<input type=text name=product_weight placeholder=Габариты(вес,литр) /><br>");
    out.print("<span>Описание</span>");
    out.print("<textarea rows=10 cols=45 maxlength=255 name=product_description class=textarea >Короткое описание</textarea><br>");

    out.print("<div class=contToCheckboxes>");
        out.print("<input type=checkbox name=product_isReturned /><p>Возврат</p>");
        out.print("<input type=checkbox name=product_isDebt /><p>Дается в долг</p><br>");
    out.print("</div>");

    out.print("<div class=field>\n" +
            "        <label for=file>Выбрать фото продукта</label><br>" +
            "          <input type=file id=file name=file accept=image/* /><br>" +
            "      </div>");

    out.print(  " <input type=submit id=submit value=Сохранить /><br>" +
                "        </form><br>" +
                "    </div>" +
                "</div>");

%>
<script type="application/javascript">
    $('#selectCategory').change(function(){
        var item = $(this).val();
        if(item === "+ Создать категорию"){
            window.location.href = "add_category";
        }
    })
</script>
</body>
</html>
