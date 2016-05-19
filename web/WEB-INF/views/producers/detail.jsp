<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>${dto.name}</title>
</head>
<body>
<div class="jumbotron">
    ${dto.description}
</div>

<c:if test="${not empty images}">
    <% int i = -1;%>
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="height: 400px;">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <c:forEach var="item" items="${images}">
                <li data-target="#carousel-example-generic" data-slide-to="<%=++i%>"></li>
            </c:forEach>
        </ol>

        <div class="carousel-inner" role="listbox">
            <c:forEach var="item" items="${images}">
                <div class="item">
                    <img src="${item.imageUrl}" alt="${item.altText}" style="max-height: 300px">
                    <div class="carousel-caption">
                            ${item.altText}
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Önceki</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Sonraki</span>
        </a>
    </div>
</c:if>
<div class="clearfix" style="height: 30px;">&nbsp;</div>


<c:if test="${not empty dto.latitude and not empty dto.longitude }">
    <div class="panel panel-info">
        <div class="panel-heading">
            <i class="glyphicon glyphicon-map-marker"></i>
        </div>
        <div class="panel-body" style="height: 100%">
            <div id="map" style="height: 100%; width: 100%">

            </div>
        </div>
    </div>

</c:if>


</body>

<content tag="local_script">
    <script>
        $(function () {
            $(".item").eq(0).addClass("active");
        });
    </script>

    <c:if test="${not empty dto.latitude and not empty dto.longitude }">
        <script>
            var map;
            function initMap() {
                var myLatLng = {lat: ${dto.latitude}, lng: ${dto.longitude}};

                map = new google.maps.Map(document.getElementById('map'), {
                    center: myLatLng,
                    zoom: 8
                });
                var marker = new google.maps.Marker({
                    position: myLatLng,
                    map: map,
                    title: '${dto.name}'
                });
            }
        </script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB_Rq851tJVtSfG6d814pgktMJ2jh0bWL4&callback=initMap"
                async defer></script>
    </c:if>

</content>

