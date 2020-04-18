<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.models2.Categories2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавление категорий</title>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/add_category.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="header_menu/products_header.jsp"/>
<%
    Company2 company2 =(Company2)session.getAttribute("company");

%>

<div class="container">
    <div class="row">
        <div>
        <h4>Список категорий</h4>
        <TABLE class="table table-striped">
            <TH>Название</TH>
            <TH>*</TH>
            <TH>*</TH>

        <%
            ServletRequest ctx =  pageContext.getRequest();
            Categories2 [] categories2s =(Categories2[]) ctx.getAttribute("categories");
            if(categories2s.length!=0) {
                for(int i =0;i<categories2s.length;i++){
                    out.print("<TR class=myTr>");
                    out.print("<TD><a class=linkName href=#>"+categories2s[i].getCategory()+"</a></TD>");
                    out.print("<TD><a class=change href=change_category?id_category="+categories2s[i].getId()+">редактировать</a></TD>");
                    out.print("<TD><a class=remove href=del_category?id_category="+categories2s[i].getId()+" >удалить</a></TD>");
                    out.print("</TR>");
                }
            }
        %>
        </TABLE>
        </div>
        <form action="save_category" method="post">
            <input type="text" name="mainCategory" placeholder="Название"/>
            <input type="submit" value="Сохранить">
        </form>
    </div>
</div>


</body>

</html>
