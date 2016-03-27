<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title><spring:message code="title.user.index"/></title>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        Filtreler
    </div>
    <div class="panel-body">
        <div class="row">
            <form id="frmSearchUsers">

                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label">Ad Soyad</label></div>
                    <div class="col-md-8"><input type="text" name="fullname" class="form-control"/></div>
                </div>
                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label">Aktif Mi</label></div>
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
                    <btn class="btn btn-primary btn-sm" onclick="KS.Grid.Search('#test-grid table');"><i
                            class="glyphicon glyphicon-search"></i> &nbsp; <spring:message code="label.search"/>
                    </btn>
                    <btn class="btn btn-danger btn-sm" onclick="$('#frmSearchUsers')[0].reset();"><i
                            class="glyphicon glyphicon-search"></i> &nbsp; <spring:message code="label.reset"/>
                    </btn>
                </div>

            </div>
        </div>
    </div>
</div>

<div id="test-grid">
    <table style="width:100%" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message
                    code="label.operation"/></th>
            <th><spring:message
                    code="label.fullName"/></th>
            <th class="hidden-xs"><spring:message
                    code="label.email"/></th>
            <th class="hidden-xs"><spring:message
                    code="label.role"/></th>
            <th><spring:message
                    code="label.isActive"/></th>
        </tr>
        </thead>
    </table>
</div>
</body>

<content tag="local_script">
    <script type="text/javascript" src="<c:url value="/static/js/view/user.js" />"></script>
    <script>
        $(function () {

            KS.Grid.Render("#test-grid table", "/su/users/list", {
                columns: [
                    {data: "fullName"},
                    {data: "email"},
                    {data: "roleName"},
                    {data: "isActive", type: "bool"}
                ],
                filterForm: "frmSearchUsers",
                customButtons: [
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Create, null, "/su/users/create", null, "<spring:message code="label.new"/>", "User.openCreate(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Enable, null, "/su/users/enable", ["userId", "isActive"], "<spring:message code="label.setEnabled"/>", "User.openEnable(this);")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Update, null, "/su/users/update", ["userId"], "<spring:message code="label.update"/>", "User.openUpdate(this);")},
                ]
            });

        });
    </script>
</content>


