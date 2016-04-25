<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.productImage.create"/></title>
<body>
<content tag="page_header">
    <div class="row">
        <div class="col-xs-12 margin-bottom-10">
            <div class="btn-group pull-right" role="group">
                <a class="btn btn-primary" href="/admin/product-images/${productImage.productId}"><spring:message
                        code="title.productimages.index"/></a>

            </div>
        </div>
    </div>
</content>
<div class="row">
    <form:form modelAttribute="productImage" action="/admin/product-images/createProductImage" method="post"
               class="form-horizontal" enctype="multipart/form-data">
        <form:hidden path="productId"/>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="image">
                <spring:message code="label.image"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <input type="file" name="image" data-val="true" data-val-required="Resim seçilmelidir."/>
                <form:errors cssClass="text-danger" path="image"></form:errors>
            </div>
        </div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="altText">
                <spring:message code="label.altText"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input type="text" cssClass="form-control" path="altText"/>
                <form:errors cssClass="text-danger" path="AltText"/>

            </div>
        </div>

        <div class="clearfix"></div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="orderNo">
                <spring:message code="label.orderNo"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="orderNo" type="number"/>
                <form:errors cssClass="text-danger" path="orderNo"></form:errors>
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



