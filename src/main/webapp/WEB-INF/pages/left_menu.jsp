<%@ page import="org.hibernate.Session" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div id='cssmenu'>
  <ul>
    <li><a href='#'><span>Мучное</span></a></li>
    <li class='active has-sub'><a href='#'><span>Шоколад</span></a>
      <ul>
        <li class='has-sub'><a href='#'><span>Сливочный</span></a>
          <ul>
            <li><a href='#'><span>Sub Product 0</span></a></li>
            <li><a href='#'><span>Sub Product 1</span></a></li>
            <li class='last'><a href='#'><span>Sub Product 2</span></a></li>
          </ul>
        </li>
        <li class='has-sub'><a href='#'><span>Молочный</span></a>
          <ul>
            <li><a href='#'><span>Sub Product 0</span></a></li>
            <li><a href='#'><span>Sub Product 1</span></a></li>
            <li><a href='#'><span>Sub Product 2</span></a></li>
            <li><a href='#'><span>Sub Product 3</span></a></li>
            <li class='last'><a href='#'><span>Sub Product 4</span></a></li>
          </ul>
        </li>
      </ul>
    </li>
    <li><a href='#'><span>Напиток</span></a></li>
    <li class='last'><a href='#'><span>Мясное</span></a></li>
  </ul>
</div>

<link rel="stylesheet" href="resources/assets/css/menu_style.css" />
</body>
</html>
