<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.productFeature.index"/></title>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        <spring:message code="label.filters"/>
    </div>
    <div class="panel-body">
        <div class="row">
            <form id="frmSearchProductFeature">

                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label"><spring:message
                            code="label.productFeature"/></label></div>
                    <div class="col-md-8"><input type="text" name="name" class="form-control"/></div>
                </div>
                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label"><spring:message
                            code="label.title"/></label></div>
                    <div class="col-md-8"><input type="text" name="title" class="form-control"/></div>
                </div>
                <div class="clearfix"></div>
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
        </div>
        <div class="col-xs-12">
            <div class="col-md-4">
                <btn class="btn btn-primary btn-sm" onclick="KS.Grid.Search('#productFeature-grid table');"><i
                        class="glyphicon glyphicon-search"></i> &nbsp; <spring:message code="label.search"/>
                </btn>
                <button class="btn btn-danger btn-sm" onclick="$('#frmSearchProductFeature')[0].reset();"><i
                        class="glyphicon glyphicon-remove-circle"></i> &nbsp; <spring:message code="label.reset"/>
                </button>
            </div>

        </div>


    </div>
</div>

<div id="productFeature-grid">
    <table style="width:100%" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message
                    code="label.operation"/></th>
            <th><spring:message
                    code="label.productFeature"/></th>
            <th class="hidden-xs"><spring:message
                    code="label.nameResourceKey"/></th>
            <th><spring:message
                    code="label.title"/></th>
            <th><spring:message
                    code="label.titleResourceKey"/></th>
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
    <script type="text/javascript" src="<c:url value="/static/js/view/productFeature.js" />"></script>
    <script>
        $(function () {

            KS.Grid.Render("#productFeature-grid table", "/su/product-feature/list", {
                columns: [
                    {data: "name"},
                    {data: "nameResourceKey"},
                    {data: "title"},
                    {data: "titleResourceKey"},
                    {data: "isActive", type: "bool"}
                ],
                filterForm: "frmSearchProductFeature",
                customButtons: [
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Create, null, "/su/product-feature/create", null, "<spring:message code="label.new"/>", "ProductFeature.openCreate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Enable, null, "/su/product-feature/enable", ["productFeatureId", "isActive"], "<spring:message code="label.setEnabled"/>", "ProductFeature.openEnable(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Update, null, "/su/product-feature/update", ["productFeatureId"], "<spring:message code="label.update"/>", "ProductFeature.openUpdate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Custom, null, "", ["imageUrl"], "<spring:message code="label.imagePreview"/>", "ProductFeature.openPreview(this,&quot;<spring:message code="message.noImagePreview"/>&quot;);", "picture", "primary", true, false)},
                ]
            });

        });
    </script>
</content>


