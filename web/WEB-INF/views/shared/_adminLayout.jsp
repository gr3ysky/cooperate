<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><sitemesh:write property='title'/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/datatables.bootstrap.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/site.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/toastr.min.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/skin.css" />"/>
    <sitemesh:write property='head'/>
</head>
<body class="layout">

<div id="page-wrapper">
    <div id="menu">

    </div>

    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="well col-md-12 col-xs-12">


                <div class="col-lg-12 hidden-sm hidden-xs no-margin no-padding">
                    <h1>
                        <sitemesh:write property='title' default="Website"/>
                    </h1>
                    <h5 class="text-muted">${pageDescription} </h5>

                </div>
                <div class="col-lg-12 hidden-lg hidden-md no-margin no-padding">
                    <h3>
                        <sitemesh:write property='title' default="Website"/>
                    </h3>
                    <h5>${pageDescription} </h5>
                </div>
                <div class="clearfix"></div>
                <hr>
                <div id="page-content">
                    <sitemesh:write property='body'/>
                </div>
            </div>
        </div>

    </div>

</div>

<div id="loaded-content" style="display:none"></div>
<div id="page-message-wrap"></div>
<script type="text/javascript" src="<c:url value="/static/js/jquery-2.2.0.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/toastr.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/ks.base.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/ks.common.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/ks.confirm.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/ks.form.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/ks.modal.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/datatables.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/datatables.bootstrap.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/ks.grid.js" />"></script>
<script type="text/javascript">
    $(document).ready(function () {
        KS.Ajax.Get("/partial/menu", null, function (html) {
            $("#menu").html(html);
        })
    });
</script>
<sitemesh:write property="page.local_script"/>
</body>
</html>