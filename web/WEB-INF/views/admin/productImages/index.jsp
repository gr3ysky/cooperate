<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.productimages.index"/></title>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        Filtreler
    </div>
    <div class="panel-body">
        <div class="row">
            <form id="frmSearchProductImages">
                <input type="hidden" name="productId" id="productId" value="${productId}"/>
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
                    <btn class="btn btn-primary btn-sm" onclick="KS.Grid.Search('#productImages-grid table');"><i
                            class="glyphicon glyphicon-search"></i> &nbsp; <spring:message code="label.search"/>
                    </btn>
                    <btn class="btn btn-danger btn-sm" onclick="$('#frmSearchProductImages')[0].reset();"><i
                            class="glyphicon glyphicon-remove-circle"></i> &nbsp; <spring:message code="label.reset"/>
                    </btn>
                </div>

            </div>
        </div>
    </div>
</div>

<div id="productImages-grid">
    <table style="width:100%" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message
                    code="label.operation"/></th>
            <th><spring:message
                    code="label.producerName"/></th>
            <th><spring:message
                    code="label.altText"/></th>
            <th><spring:message
                    code="label.orderNo"/></th>
            <th><spring:message
                    code="label.isActive"/></th>
        </tr>
        </thead>
    </table>
</div>

<div id="imagePreviewModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><spring:message code="label.imagePreview"/></h4>
            </div>
            <div class="modal-body">
                <p><spring:message code="message.noImagePreview"/></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="label.close"/></button>
            </div>
        </div>

    </div>
</div>
</body>

<content tag="local_script">
    <script type="text/javascript" src="<c:url value="/static/js/view/productImage.js" />"></script>
    <script>
        $(function () {

            KS.Grid.Render("#productImages-grid table", "/admin/product-images/list", {
                columns: [
                    {data: "productName"},
                    {data: "altText"},
                    {data: "orderNo"},
                    {data: "isActive", type: "bool"}
                ],
                filterForm: "frmSearchProductImages",
                customButtons: [
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Create, null, "/admin/product-images/create", null, "<spring:message code="label.new"/>", "ProductImage.openCreate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Enable, null, "/admin/product-images/enable", ["productImageId", "isActive"], "<spring:message code="label.setEnabled"/>", "ProductImage.openEnable(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Update, null, "/admin/product-images/update", ["productImageId", "productId"], "<spring:message code="label.update"/>", "ProductImage.openUpdate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Custom, null, "", ["imageUrl"], "<spring:message code="label.imagePreview"/>", "ProductImage.openPreview(this,&quot;<spring:message code="message.noImagePreview"/>&quot;);", "picture", "primary", true, false)},

                ]
            });

        });
    </script>
</content>


