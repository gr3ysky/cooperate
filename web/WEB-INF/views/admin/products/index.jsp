<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.product.index"/></title>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        Filtreler
    </div>
    <div class="panel-body">
        <div class="row">
            <form id="frmSearchProducts">

                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label"><spring:message
                            code="label.productName"/></label></div>
                    <div class="col-md-8"><input type="text" name="name" id="name" class="form-control"/></div>
                </div>
                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label"><spring:message
                            code="label.producerName"/></label></div>
                    <div class="col-md-8"><input type="text" name="producerName" id="producerName"
                                                 class="form-control"/></div>
                </div>
                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label"><spring:message
                            code="label.tags"/></label></div>
                    <div class="col-md-8"><input type="text" name="tag" id="tag" class="form-control"/></div>
                </div>
                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label"><spring:message code="label.isActive"/></label>
                    </div>
                    <div class="col-md-8">
                        <select name="isActive" id="IsActive">
                            <option value="-1"><spring:message code="label.all"/></option>
                            <option value="1"><spring:message code="label.yes"/></option>
                            <option value="0"><spring:message code="label.no"/></option>
                        </select>
                    </div>
                </div>
            </form>
            <div class="col-xs-12">
                <div class="col-md-4">
                    <btn class="btn btn-primary btn-sm" onclick="KS.Grid.Search('#product-grid table');"><i
                            class="glyphicon glyphicon-search"></i> &nbsp; <spring:message code="label.search"/>
                    </btn>
                    <btn class="btn btn-danger btn-sm" onclick="$('#frmSearchProducts')[0].reset();"><i
                            class="glyphicon glyphicon-remove-circle"></i> &nbsp; <spring:message code="label.reset"/>
                    </btn>
                </div>

            </div>
        </div>
    </div>
</div>

<div id="product-grid">
    <table style="width:100%" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message
                    code="label.operation"/></th>
            <th><spring:message
                    code="label.product"/></th>
            <th><spring:message
                    code="label.producer"/></th>
            <th><spring:message
                    code="label.stockCount"/></th>
            <th><spring:message
                    code="label.tags"/></th>
            <th><spring:message
                    code="label.isActive"/></th>
        </tr>
        </thead>
    </table>
</div>
</body>

<content tag="local_script">
    <script type="text/javascript" src="<c:url value="/static/js/view/product.js" />"></script>
    <script>
        $(function () {

            KS.Grid.Render("#product-grid table", "/admin/products/list", {
                columns: [
                    {data: "name"},
                    {data: "producer"},
                    {data: "stockCount"},
                    {data: "tags"},
                    {data: "isActive", type: "bool"}
                ],
                filterForm: "frmSearchProducts",
                customButtons: [
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Create, null, "/admin/products/create", null, "<spring:message code="label.new"/>", "Product.openCreate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Enable, null, "/admin/products/enable", ["productId", "isActive"], "<spring:message code="label.setEnabled"/>", "Product.openEnable(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Update, null, "/admin/products/update", ["productId"], "<spring:message code="label.update"/>", "Product.openUpdate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Custom, null, "/admin/product-images", ["productId"], "<spring:message code="label.images"/>", "Product.openImages(this);", "picture", "warning", true, false)}
                ]
            });

        });
    </script>
</content>


