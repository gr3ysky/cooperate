<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.producer.index"/></title>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        Filtreler
    </div>
    <div class="panel-body">
        <div class="row">
            <form id="frmSearchProducers">

                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label"><spring:message
                            code="label.producerName"/></label></div>
                    <div class="col-md-8"><input type="text" name="name" class="form-control"/></div>
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
                    <btn class="btn btn-primary btn-sm" onclick="KS.Grid.Search('#producer-grid table');"><i
                            class="glyphicon glyphicon-search"></i> &nbsp; <spring:message code="label.search"/>
                    </btn>
                    <btn class="btn btn-danger btn-sm" onclick="$('#frmSearchProducers')[0].reset();"><i
                            class="glyphicon glyphicon-remove-circle"></i> &nbsp; <spring:message code="label.reset"/>
                    </btn>
                </div>

            </div>
        </div>
    </div>
</div>

<div id="producer-grid">
    <table style="width:100%" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message
                    code="label.operation"/></th>
            <th><spring:message
                    code="label.producerName"/></th>
            <th><spring:message
                    code="label.memberSince"/></th>
            <th><spring:message
                    code="label.isActive"/></th>
        </tr>
        </thead>
    </table>
</div>
</body>

<content tag="local_script">
    <script type="text/javascript" src="<c:url value="/static/js/view/producer.js" />"></script>
    <script>
        $(function () {

            KS.Grid.Render("#producer-grid table", "/admin/producers/list", {
                columns: [
                    {data: "name"},
                    {data: "memberSince"},
                    {data: "isActive", type: "bool"}
                ],
                filterForm: "frmSearchProducers",
                customButtons: [
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Create, null, "/admin/producers/create", null, "<spring:message code="label.new"/>", "Producer.openCreate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Enable, null, "/admin/producers/enable", ["producerId", "isActive"], "<spring:message code="label.setEnabled"/>", "Producer.openEnable(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Update, null, "/admin/producers/update", ["producerId"], "<spring:message code="label.update"/>", "Producer.openUpdate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Custom, null, "/admin/producer-images", ["producerId"], "<spring:message code="label.images"/>", "Producer.openImages(this);", "picture", "warning", true, false)}
                ]
            });

        });
    </script>
</content>


