<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.user.create"/></title>
<body>
<content tag="page_header">
    <div class="row">
        <div class="col-xs-12 margin-bottom-10">
            <div class="btn-group pull-right" role="group">
                <a class="btn btn-primary" href="/admin/users"><spring:message code="title.user.index"/></a>

            </div>
        </div>
    </div>
</content>
<div class="row">
    <form:form modelAttribute="user" action="/admin/users/creteUser" method="post" class="form-horizontal">
        <form:hidden path="roleId" value="3"/>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="fullName">
                <spring:message code="label.fullName"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="fullName"/>
                <form:errors cssClass="text-danger" path="fullName"/>
            </div>
        </div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="email">
                <spring:message code="label.email"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" type="email" path="email"/>
                <label class="label label-info">@boun.edu.tr</label>
                <form:errors cssClass="text-danger" path="email"/>

            </div>
        </div>

        <div class="clearfix"></div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="password">
                <spring:message code="label.password"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="password" type="password"/>
                <form:errors cssClass="text-danger" path="password"></form:errors>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="roleId">
                <spring:message code="label.role"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <spring:message code="label.member"/>
            </div>
        </div>

        <div class="clearfix"></div>

        <div class="form-group col-md-6 col-xs-12 ">
            <div class="col-md-offset-4 col-xs-12 ">
                <div class="checkbox">
                    <label>
                        <form:checkbox path="isActive"/> <spring:message code="label.isActive"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-groupcol-md-6 col-xs-12">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default"><spring:message code="label.save"/></button>
            </div>
        </div>

        <div class="clearfix"></div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>


</div>
</body>

<content tag="local_script">
    <script>
        $(function () {
            if ("${message}" != "") {
                if ("${status}" == "success")
                    KS.Message.Success("${message}");
                else
                    KS.Message.Error("${message}");
            }

        });
    </script>
</content>



