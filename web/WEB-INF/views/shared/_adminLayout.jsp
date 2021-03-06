<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cooperate.app.business.user.login.LoginDto" %>
<%@ page import="cooperate.infrastructure.constant.SessionConstants" %>
<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% LoginDto login = (LoginDto) request.getSession().getAttribute(SessionConstants.User);
%>
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
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu-content">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" style="padding:10px 15px;" href="/"><spring:message code="label.siteName"/></a>
                </div>
                <%
                    if (login != null) { %>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="true"><%= login.Fullname %><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <%
                                if (login.MemberId > 0) { %>
                            <li><a href="/profile">Profil</a></li>
                            <%}%>
                            <li><a href="/logout">Çıkış</a></li>
                        </ul>
                    </li>

                </ul>
                <%}%>
                <div class="collapse navbar-collapse" id="menu-content">
                </div>
            </div>
        </nav>

    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="well col-md-12 col-xs-12">


                <div class="col-lg-12 hidden-sm hidden-xs no-margin no-padding">
                    <h3>
                        <sitemesh:write property='title' default="Website"/>
                    </h3>


                </div>
                <div class="col-lg-12 hidden-lg hidden-md no-margin no-padding">
                    <h3>
                        <sitemesh:write property='title' default="Website"/>
                    </h3>

                </div>
                <div class="clearfix"></div>
                <hr>
                <sitemesh:write property="page.page_header"/>
                <div id="page-content">
                    <sitemesh:write property='body'/>
                </div>
            </div>
        </div>
        <div>
            Copyright<span>&copy;</span> <spring:message
                code="label.siteName"/> <%= Calendar.getInstance().get(Calendar.YEAR)%>
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
            $("#menu-content").html(html);
        })
    });
</script>
<sitemesh:write property="page.local_script"/>
</body>
</html>