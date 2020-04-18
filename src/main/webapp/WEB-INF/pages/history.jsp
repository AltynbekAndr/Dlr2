<%@ page import="com.springapp.mvc.model_for_users.MyOrder" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="resources/css/mycss/history.css" />

</head>
<body class="subpage">
<jsp:include page="header_menu/history_header.jsp"/>
            <%
                ServletRequest ctx =  pageContext.getRequest();
                boolean pagesEnd  =(Boolean) ctx.getAttribute("pagesEnd");
                int sizeToPagination  =(Integer) ctx.getAttribute("sizeToPagination");
                int activePageNumber  =(Integer) ctx.getAttribute("activePageNumber");
            %>
<div class="container">
    <div class="row rowfordate">
        <form action="/pdf" method="get">
            <label class="titlePDF">Распечатать в PDF</label>
            <label name="startDate">Начало</label>
            <input type="date" name="fDate" id="startDate">
            <label name="startDate">Конец</label>
            <input type="date" name="sDate" id="endDate">
            <input type="submit" value="показать">
        </form>
    </div>
        <div class=row>


            <TABLE class="table table-striped">
                <TR>
                    <TH>Сумма</TH> <TH>Дата</TH><TH>Статус</TH><TH></TH>
                </TR>
                <%
                    List<MyOrder> myOrders  =(List<MyOrder>) ctx.getAttribute("myOrders");
                    for(int i =0;i<myOrders.size();i++){
                        out.print("<TR>");
                        out.print("<TD id='sumId"+i+"'>"
                                +myOrders.get(i).getSum()+
                                "</TD><TD id='dateId"+i+"'>"
                                +myOrders.get(i).getDate()+
                                "</TD><TD class="+myOrders.get(i).getStatus()+">"
                                +myOrders.get(i).getStatus()+
                                "</TD><TD><a href=detailsOrderHistory?order="
                                +myOrders.get(i).getId()+
                                ">просмотр</a></TD>");
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
                                        out.print("<a class=page-link href=history_p?page="+k+" tabindex=-1>"+k+"</a>");
                                        out.print("</li>");
                                    }else{
                                        out.print("<li class='age-item' \\>");
                                        out.print("<a class=page-link href=history_p?page="+k+" tabindex=-1>"+k+"</a>");
                                        out.print("</li>");
                                    }
                                }
                            }else{
                                sizeToPagination = sizeToPagination+1;
                                for(int k=1;k<sizeToPagination;k++){
                                    if(activePageNumber==k){
                                        out.print("<li class='age-item active' \\>");
                                        out.print("<a class=page-link href=history_p?page="+k+" tabindex=-1>"+k+"</a>");
                                        out.print("</li>");
                                    }else{
                                        out.print("<li class='age-item' \\>");
                                        out.print("<a class=page-link href=history_p?page="+k+" tabindex=-1>"+k+"</a>");
                                        out.print("</li>");
                                    }
                                }
                            }

                            if(activePageNumber<sizeToPagination){
                                int nextpage = activePageNumber+1;
                                out.print("<li class='page-item' \\>");
                                out.print("<a class=page-link href=history_p?page="+nextpage+" tabindex=-1>След.</a>");
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
    $('#getpage').click(function(){
        var v = $("#pageNum").val();
            if(v!=null&& v.length!=0){
                window.location.href = "new_orders_p?page="+v;
            }
        });
</script>
</body>
</html>
