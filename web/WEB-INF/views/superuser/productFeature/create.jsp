<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.productFeature.create"/></title>
<body>
<content tag="page_header">
    <div class="row">
        <div class="col-xs-12 margin-bottom-10">
            <div class="btn-group pull-right" role="group">
                <a class="btn btn-primary" href="/su/product-feature"> <spring:message
                        code="title.productFeature.index"/></a>

            </div>
        </div>
    </div>
</content>
<div class="row">
    <form:form modelAttribute="productFeature" action="/su/product-feature/create" method="post"
               class="form-horizontal" enctype="multipart/form-data">
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="name">
                <spring:message code="label.productFeature"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="name"/>
                <form:errors cssClass="text-danger" path="name"></form:errors>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="nameResourceKey">
                <spring:message code="label.nameResourceKey"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="nameResourceKey"/>
                <form:errors cssClass="text-danger" path="nameResourceKey"></form:errors>
            </div>
        </div>

        <div class="clearfix"></div>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="title">
                <spring:message code="label.title"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="title"/>
                <form:errors cssClass="text-danger" path="title"></form:errors>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="titleResourceKey">
                <spring:message code="label.titleResourceKey"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="titleResourceKey"/>
                <form:errors cssClass="text-danger" path="titleResourceKey"></form:errors>
            </div>
        </div>

        <div class="clearfix"></div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="image">
                <spring:message code="label.image"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <input type="file" name="image"/>
                <form:errors cssClass="text-danger" path="image"></form:errors>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-12 ">
            <div class="col-md-offset-4 col-xs-12 ">
                <div class="checkbox">
                    <label>
                        <form:checkbox path="isActive"/> <spring:message code="label.isActive"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
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




