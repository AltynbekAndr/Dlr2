<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.model_for_users.Groups" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавлнение районов</title>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet"  href="resources/css/mycss/add_category.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body class="subpage">
<jsp:include page="header_menu/add_shops_header.jsp"/>

<div class="container">
    <div class="row">
        <div>
            <h4>Список районов</h4>
            <TABLE class="table table-striped">
                <TH>Название</TH>
                <TH>*</TH>
                <TH>*</TH>
                <%
                    ServletRequest ctx =  pageContext.getRequest();
                    List<Groups> groups = (List<Groups>) ctx.getAttribute("groups");
                    if(groups.size()!=0) {
                        for(int i =0;i<groups.size();i++){
                            out.print("<TR class=myTr>");
                            out.print("<TD><a class=linkName href=#>"+groups.get(i).getName()+"</a></TD>");
                            out.print("<TD><a class=change href=setGroup?group_id="+groups.get(i).getId()+" >редактировать</a></TD>");
                            out.print("<TD><a class=remove href=del_group?id_group="+groups.get(i).getId()+" >удалить</a></TD>");
                            out.print("</TR>");
                        }
                    }
                %>
            </TABLE>
        </div>
        <form action="save_groups" method="post">
            <input type="text" name="groups" placeholder="Название"/>
            <input type="submit" value="Сохранить">
        </form>
    </div>
</div>



</body>

</html>
