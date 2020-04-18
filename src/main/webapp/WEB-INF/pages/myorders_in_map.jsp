<%@ page import="com.springapp.mvc.model_for_users.Products" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.models2.Company2" %>
<%@ page import="com.springapp.mvc.model_for_users.MyOrder" %>
<%@ page import="com.springapp.mvc.model_for_users.Shops" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Заказы на карте</title>
  <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
  <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
  <link rel="stylesheet" href="resources/assets/css/menu_style.css"/>
  <link rel="stylesheet"  href="resources/css/mycss/myorders_in_map.css" />
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
<body>
<jsp:include page="header_menu/new_orders.jsp"/>
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
  <div class="row">
    <div id="map" style="width:100%; height:80%"></div>
  </div>
</div>
<%
  ServletRequest ctx =  pageContext.getRequest();
  Company2 company2 = (Company2) session.getAttribute("company");
  List<Products> products = (List<Products>) ctx.getAttribute("products");

  HashSet<Shops> shopsSet = new HashSet<Shops>();
  HashSet<MyOrder> myOrdersSet = new HashSet<MyOrder>();
  List<Products> productsList = new ArrayList<Products>();
  List<Shops> shopslist = new ArrayList<Shops>();

  for(int i=0;i<products.size();i++){
    Products product = products.get(i);
    MyOrder order = product.getMyorder();
    Shops shops = order.getShops();
    shopsSet.add(shops);
    myOrdersSet.add(order);
  }

  Iterator iterator = shopsSet.iterator();
  while(iterator.hasNext()){
    shopslist.add((Shops)iterator.next());
  }
  out.print("<script type='text/javascript'>");
    out.print("\nvar shops=new Array();\n"+"var shop;\n");
    for(int i =0;i<shopslist.size();i++){
      out.println("shop=new Object();");
      out.println(
              "shop.id = " + shopslist.get(i).getId() + ";"
                      + "shop.name='" + shopslist.get(i).getName().replaceAll("\\\\t","\t") + "';"
                      + "shop.address='" + shopslist.get(i).getAddress().replaceAll("\\\\n", "\n") + "';"
                      + "shop.photo='" + shopslist.get(i).getPhoto().replaceAll("\\\\n", "\n") + "';"
                      + "shop.phone='" + shopslist.get(i).getPhone().replaceAll("\\\\n", "\n") + "';");
      out.println("shop.lat='" + shopslist.get(i).getLatitude().replaceAll("\\\\n", "\n") + "';" + "shop.lng='" + shopslist.get(i).getLongitude().replaceAll("\\\\n", "\n") + "';\n" + "shops[" + i + "]=shop;");
    }



  out.print("</script>");
%>
<script src="https://maps.api.2gis.ru/2.0/loader.js?pkg=full"></script>
<script type="text/javascript">
  var map;
  DG.then(function () {
    map = DG.map('map', {
      center: [42.853255000000004, 74.49951166666666],
      zoom: 15
    });
   for(var si = 0;si<shops.length;si++){
     var htmlToPopup = "<div class=popup-container><div class=container-img><img src=small_photo_shops?imgName="+shops[si].photo+" /></div><div>"+shops[si].name+"</div><div>"+shops[si].address+"</div><div>"+shops[si].phone+"</div><div><a href=new_orders_concr_shop?shopId="+shops[si].id+">Список заказов</a></div><div>";
     DG.marker([shops[si].lat, shops[si].lng]).addTo(map).bindPopup(htmlToPopup);
   }

  });
</script>
</body>
</html>
