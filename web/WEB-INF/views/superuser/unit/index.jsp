<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.unit.index"/></title>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        <spring:message code="label.filters"/>
    </div>
    <div class="panel-body">
        <div class="row">
            <form id="frmSearchUnit">

                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label"><spring:message
                            code="label.unit"/></label></div>
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
        </div>
        <div class="col-xs-12">
            <div class="col-md-4">
                <btn class="btn btn-primary btn-sm" onclick="KS.Grid.Search('#unit-grid table');"><i
                        class="glyphicon glyphicon-search"></i> &nbsp; <spring:message code="label.search"/>
                </btn>
                <button class="btn btn-danger btn-sm" onclick="$('#frmSearchUnit')[0].reset();"><i
                        class="glyphicon glyphicon-remove-circle"></i> &nbsp; <spring:message code="label.reset"/>
                </button>
            </div>

        </div>


    </div>
</div>

<div id="unit-grid">
    <table style="width:100%" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message
                    code="label.operation"/></th>
            <th><spring:message
                    code="label.unit"/></th>
            <th class="hidden-xs"><spring:message
                    code="label.resourceKey"/></th>
            <th><spring:message
                    code="label.isActive"/></th>
        </tr>
        </thead>
    </table>
</div>
</body>

<content tag="local_script">
    <script type="text/javascript" src="<c:url value="/static/js/view/unit.js" />"></script>
    <script>
        $(function () {

            KS.Grid.Render("#unit-grid table", "/su/unit/list", {
                columns: [
                    {data: "name"},
                    {data: "resourceKey"},
                    {data: "isActive", type: "bool"}
                ],
                filterForm: "frmSearchUnit",
                customButtons: [
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Create, null, "/su/unit/create", null, "<spring:message code="label.new"/>", "Unit.openCreate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Enable, null, "/su/unit/enable", ["unitId", "isActive"], "<spring:message code="label.setEnabled"/>", "Unit.openEnable(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Update, null, "/su/unit/update", ["unitId"], "<spring:message code="label.update"/>", "Unit.openUpdate(this);")},
                ]
            });

        });
    </script>
</content>


