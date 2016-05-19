<%@ page import="cooperate.app.business.user.login.LoginDto" %>
<%@ page import="cooperate.infrastructure.constant.SessionConstants" %>
<%@ page import="java.util.Calendar" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% LoginDto login = (LoginDto) request.getSession().getAttribute(SessionConstants.User);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><sitemesh:write property='title'/></title>
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
                    <a class="navbar-brand" style="padding:10px 15px;" href="/"><spring:message
                            code="label.siteName"/></a>
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
                <%} else {%>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="/login">Giriş Yap</a>
                    </li>
                </ul>

                <%}%>
                <div class="collapse navbar-collapse" id="menu-content">
                </div>

            </div>
        </nav>

    <div class="container">
        <!-- Page Heading -->

        <%
            if (login != null && login.MemberId == 0) { %>
        <div class='alert alert-warning'>
            Lütfen profilinizi oluşturmak için <a href='/profile/create'>tıklayın.</a>
        </div>
        <%}%>


        <div class="row">
            <div class="col-lg-12 hidden-sm hidden-xs">
                <h1 class="page-header">
                    <sitemesh:write property='title' default="Website"/>
                    <small>${pageDescription} </small>
                </h1>

                <!-- Sub menu will go here -->

            </div>
            <div class="col-lg-12 hidden-lg hidden-md">
                <h4 class="page-header">
                    <sitemesh:write property='title' default="Website"/>

                </h4>


                <!-- Sub menu will go here -->

            </div>
        </div>
        <div id="page-content">
            <sitemesh:write property='body'/>
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
<script type="text/javascript" src="<c:url value="/static/js/common/ks.grid.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/ks.confirm.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/ks.form.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/common/ks.modal.js" />"></script>
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