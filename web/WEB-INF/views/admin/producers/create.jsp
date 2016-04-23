<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.producer.create"/></title>
<body>
<content tag="page_header">
    <div class="row">
        <div class="col-xs-12 margin-bottom-10">
            <div class="btn-group pull-right" role="group">
                <a class="btn btn-primary" href="/admin/producers"><spring:message code="title.producer.index"/></a>

            </div>
        </div>
    </div>
</content>
<div class="row">
    <form:form modelAttribute="producer" action="/admin/producers/createProducer" method="post" class="form-horizontal">
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="name">
                <spring:message code="label.producerName"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="name"/>
                <form:errors cssClass="text-danger" path="name"/>
            </div>
        </div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="description">
                <spring:message code="label.description"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:textarea cssClass="form-control" path="description"/>
                <form:errors cssClass="text-danger" path="description"/>

            </div>
        </div>

        <div class="clearfix"></div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="latitude">
                <spring:message code="label.latitude"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="latitude" type="number" step="0.01"/>
                <form:errors cssClass="text-danger" path="latitude"></form:errors>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="longitude">
                <spring:message code="label.longitude"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="longitude" type="number" step="0.01"/>
                <form:errors cssClass="text-danger" path="longitude"></form:errors>
            </div>
        </div>

        <div class="clearfix"></div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="memberSince">
                <spring:message code="label.memberSince"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="memberSince" type="date"/>
                <form:errors cssClass="text-danger" path="memberSince"></form:errors>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-12 ">
            <div class="col-md-offset-4 col-xs-12 ">
                <div class="checkbox">
                    <label>
                        <form:checkbox path="active"/> <spring:message code="label.isActive"/>
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



