<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Üreticiler</title>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="header">
            <strong>Üreticiler</strong>
        </div>
    </div>
    <div class="panel-body">
        <table class="table table-responsive table-bordered">
            <thead>
            <tr>
                <th>Üretici Adı</th>
                <th>Katılım Tarihi</th>
                <th>Git</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.memberSince}</td>
                    <td><a class="btn-link" href="/producers/view/${item.producerId}"><i
                            class="glyphicon glyphicon-link"></i></a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

</body>

