<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.product.create"/></title>
<body>
<content tag="page_header">
    <div class="row">
        <div class="col-xs-12 margin-bottom-10">
            <div class="btn-group pull-right" role="group">
                <a class="btn btn-primary" href="/admin/products"><spring:message code="title.product.index"/></a>

            </div>
        </div>
    </div>
</content>
<div class="row">
    <form:form modelAttribute="product" action="/admin/products/createProduct" method="post" class="form-horizontal">
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="name">
                <spring:message code="label.productName"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="name"/>
                <form:errors cssClass="text-danger" path="name"/>
            </div>
        </div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="producerId">
                <spring:message code="label.producer"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:select cssClass="form-control" path="producerId" items="${producers}" itemLabel="text"
                             itemValue="value"/>
                <form:errors cssClass="text-danger" path="producerId"/>

            </div>
        </div>

        <div class="clearfix"></div>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="productCategoryId">
                <spring:message code="label.productCategory"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:select cssClass="form-control" path="productCategoryId" items="${productCategories}"
                             itemLabel="text" itemValue="value"/>
                <form:errors cssClass="text-danger" path="productCategoryId"/>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="saleTypeId">
                <spring:message code="label.saleType"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:select cssClass="form-control" path="saleTypeId" items="${saleTypes}" itemLabel="text"
                             itemValue="value"/>
                <form:errors cssClass="text-danger" path="saleTypeId"/>
            </div>
        </div>
        <div class="clearfix"></div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="packagingId">
                <spring:message code="label.packaging"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:select cssClass="form-control" path="packagingId" items="${packagings}" itemLabel="text"
                             itemValue="value"/>
                <form:errors cssClass="text-danger" path="packagingId"/>
            </div>
        </div>


        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="unitId">
                <spring:message code="label.unit"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:select cssClass="form-control" path="unitId" items="${units}" itemLabel="text" itemValue="value"/>
                <form:errors cssClass="text-danger" path="unitId"/>
            </div>
        </div>
        <div class="clearfix"></div>

        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="unitPrice">
                <spring:message code="label.unitPrice"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="unitPrice" type="number" step="0.01"/>
                <form:errors cssClass="text-danger" path="unitPrice"></form:errors>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="tags">
                <spring:message code="label.tags"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="tags"/>
                <form:errors cssClass="text-danger" path="tags"></form:errors>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="form-group col-md-6 col-xs-12 ">
            <div class="col-md-offset-4 col-xs-12 ">
                <div class="checkbox">
                    <label>
                        <form:checkbox path="prePayed"/> <spring:message code="label.isPrePayed"/>
                    </label>
                </div>
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
        <div class="form-group col-md-6 col-xs-12">
            <label class="col-md-4 col-xs-12 control-label" for="stockCount">
                <spring:message code="label.stockCount"/>
            </label>
            <div class="col-md-8 col-xs-12">
                <form:input cssClass="form-control" path="stockCount" type="number"/>
                <form:errors cssClass="text-danger" path="stockCount"></form:errors>
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



