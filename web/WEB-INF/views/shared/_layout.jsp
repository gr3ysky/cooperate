<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><dec:title default="Web Page"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/sb-admin.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/site.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/toastr.min.css" />"/>
    <dec:head/>
</head>
<body>

<div id="page-wrapper" class="container">
    <div id="menu">

    </div>

    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12 hidden-sm hidden-xs">
                <h1 class="page-header">
                    <dec:title default="Web Page"/>
                    <small>${pageDescription} </small>
                </h1>
                <!-- Sub menu will go here -->

            </div>
            <div class="col-lg-12 hidden-lg hidden-md">
                <h4 class="page-header">
                    <dec:title default="Web Page"/>
                    <small>${pageDescription} </small>
                </h4>
                <!-- Sub menu will go here -->

            </div>
        </div>
        <div id="page-content">
            <dec:body/>
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
<script type="text/javascript">
    $(document).ready(function () {
        KS.Ajax.Get("/partial/menu", null, function (html) {
            $("#menu").html(html);
        })
    });
</script>
<dec:getProperty property="page.local_script"></dec:getProperty>
</body>
</html>