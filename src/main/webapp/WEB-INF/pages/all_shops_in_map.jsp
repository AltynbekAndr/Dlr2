<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Магазины на карте</title>
    <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/png"/>
    <script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet"  href="resources/css/mycss/shopsInMap.css" />
    <script src="https://maps.api.2gis.ru/2.0/loader.js?pkg=full"></script>

</head>
<body class="subpage">
<jsp:include page="header_menu/add_shops_header.jsp"/>
<div>
    <%--<jsp:include page="left_menu.jsp"/>--%>
    <div class="mydivcourier1">
        <p><a href="add_courier"><span class="icon fa-plus-circle"> Добавить магазин</span></a></p>
    </div>
</div>
<body>
.map {
width: 100%;
height: 400px;
}
<div id="map" style="width:100%; height:100%"></div>
</body>
<script type="text/javascript">
    var map;

    DG.then(function () {
        map = DG.map('map', {
            center: [42.853255000000004, 74.49951166666666],
            zoom: 13
        });
    });
</script>
<script type="text/javascript">
    var map;

    DG.then(function () {
        map = DG.map('map', {
            center: [42.853255000000004, 74.49951166666666],
            zoom: 13
        });

        DG.marker([42.853255000000004, 74.49951166666666]).addTo(map);
    });
</script>
<script type="text/javascript">
    var map;

    DG.then(function () {
        map = DG.map('map', {
            center: [42.853255000000004, 74.49951166666666],
            zoom: 13
        });

        DG.marker([42.853255000000004, 74.49951166666666]).addTo(map).bindPopup('Вы кликнули по мне!');
    });
</script>
</body>
</html>

