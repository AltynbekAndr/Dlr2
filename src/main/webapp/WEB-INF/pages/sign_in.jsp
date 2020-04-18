<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet"  href="resources/css/mycss/sign_in.css" />
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
</head>
<body>
    <%
        ServletRequest ctx =  pageContext.getRequest();
        String str = String.valueOf(ctx.getAttribute("error"));
    %>
    <%
      if (str!=null&&str.length()!=0&&str!=""&&str!="null"){
        out.print("<script type=text/javascript>alert('"+str+"')</script>");
      }else{

      }
    %>

<div class="container_one">
    <div class="container_two">
        <p>Вход <span class="icon fa-sign-in"></span></p>
        <form method="post" action="main">
            <input type="text" name="login" placeholder="login" maxlength="20" ><br>
            <input type="password" name="password" placeholder="password" maxlength="20" ><br>
            <input type="submit" value="Войти" >
        </form>
    </div>
</div>


<script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>

</body>
</html>