<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Super User</title>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        Filtreler
    </div>
    <div class="panel-body">
        <form id="frmSearchUsers">
            <div class="row">
                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label">Ad Soyad</label></div>
                    <div class="col-md-8"><input type="text" name="fullname" class="form-control"/></div>
                </div>
                <div class="form-group col-md-6 col-xs-12 no-padding">
                    <div class="col-md-4"><label class="control-label">Aktif Mi</label></div>
                    <div class="col-md-8">
                        <select name="isActive" id="IsActive">
                            <option value="-1">Tümü</option>
                            <option value="1">Evet</option>
                            <option value="0">Hayır</option>
                        </select>
                    </div>
                </div>
                <div class="col-xs-12">
                    <div class="col-md-4">
                        <btn class="btn btn-success" onclick="KS.Grid.Search('#test-grid table');"><i
                                class="glyphicon glyphicon-search"></i> &nbsp; Ara
                        </btn>
                    </div>

                </div>
            </div>

        </form>
    </div>
</div>

<div id="test-grid">
    <table style="width:100%" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th>İşlem</th>
            <th>Ad Soyad</th>
            <th class="hidden-xs">Email</th>
            <th class="hidden-xs">Rol</th>
            <th>Aktif mi?</th>
        </tr>
        </thead>
    </table>
</div>
</body>

<content tag="local_script">
    <script>
        $(function () {
            KS.Grid.Render("#test-grid table", "/su/users/test", {
                columns: [
                    {data: "fullName"},
                    {data: "email"},
                    {data: "roleName"},
                    {data: "isActive",type:"bool"}
                ],
                filterForm:"frmSearchUsers",
                customButtons: [
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Create, {"UserId": 1}, "su/user/create", null, "Yeni")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Enable, {"UserId": 1}, "su/user/enable", ["Fullname"], "Aktifleştir")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Update, {"UserId": 1}, "su/user/update", ["Fullname"], "Güncelle")},
                ]
            });

        });
    </script>
</content>


